package com.soapsnake.hadoop.yarn;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.util.Shell;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.protocolrecords.RegisterApplicationMasterResponse;
import org.apache.hadoop.yarn.api.records.ApplicationAttemptId;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerId;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.FinalApplicationStatus;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.Priority;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.client.api.AMRMClient.ContainerRequest;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync.AbstractCallbackHandler;
import org.apache.hadoop.yarn.client.api.async.NMClientAsync;
import org.apache.hadoop.yarn.client.api.async.impl.NMClientAsyncImpl;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.util.ConverterUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-09-26
 */
@Slf4j
public class YarnApplicationClient {

    public static void main(String[] args) throws IOException, YarnException {
        Configuration conf = new Configuration();

        //yarnClient的主要作用是方便client提交任务给yarn执行,相当于mvc的controller
        org.apache.hadoop.yarn.client.api.YarnClient yarnClient = org.apache.hadoop.yarn.client.api.YarnClient.createYarnClient();
        yarnClient.init(conf);
        yarnClient.start();

        //use yarnclient to create an application
        YarnClientApplication app = yarnClient.createApplication();
        GetNewApplicationResponse appResponse = app.getNewApplicationResponse();

        //设置应用提交上下文
        ApplicationSubmissionContext appContext = app.getApplicationSubmissionContext();
        ApplicationId appId = appContext.getApplicationId();

        boolean keepContainers = false;  //是否保留容器??
        appContext.setKeepContainersAcrossApplicationAttempts(keepContainers);
        String appName = "soapsnake_first_yarn_task";
        appContext.setApplicationName(appName);

        // set local resources for the application master,local files or archives as needed
        Map<String, LocalResource> localResources = new HashMap<>();
        log.info("Copy App Master jar from local filesystem and add to local environment");

        // Copy the application master jar to the filesystem
        // Create a local resource to point to the destination jar path
        FileSystem fs = FileSystem.get(conf);
        String appMasterJar = "";
        String appMasterJarPath = "";
        addToLocalResources(fs, appMasterJar, appMasterJarPath, appId.toString(),
                localResources, null);

        String log4jPropFile = "";
        String log4jPath = "";
        // Set the log4j properties if needed
        if (!log4jPropFile.isEmpty()) {
            addToLocalResources(fs, log4jPropFile, log4jPath, appId.toString(),
                    localResources, null);
        }

        // The shell script has to be made available on the final container(s)
        // where it will be executed.
        // To do this, we need to first copy into the filesystem that is visible
        // to the yarn framework.
        // We do not need to set this as a local resource for the application
        // master as the application master does not need it.
        String hdfsShellScriptLocation = "";
        long hdfsShellScriptLen = 0;
        long hdfsShellScriptTimestamp = 0;
        String shellScriptPath = "";
        String SCRIPT_PATH = "";
        if (!shellScriptPath.isEmpty()) {
            Path shellSrc = new Path(shellScriptPath);
            String shellPathSuffix =
                    appName + "/" + appId.toString() + "/" + SCRIPT_PATH;
            Path shellDst =
                    new Path(fs.getHomeDirectory(), shellPathSuffix);
            fs.copyFromLocalFile(false, true, shellSrc, shellDst);
            hdfsShellScriptLocation = shellDst.toUri().toString();
            FileStatus shellFileStatus = fs.getFileStatus(shellDst);
            hdfsShellScriptLen = shellFileStatus.getLen();
            hdfsShellScriptTimestamp = shellFileStatus.getModificationTime();
        }
        String shellCommand = "";
        String shellCommandPath = "";
        if (!shellCommand.isEmpty()) {
            addToLocalResources(fs, null, shellCommandPath, appId.toString(),
                    localResources, shellCommand);
        }

        String shellArgs = "";
        String shellArgsPath = "";
        if (shellArgs.length() > 0) {
            addToLocalResources(fs, null, shellArgsPath, appId.toString(),
                    localResources, StringUtils.join(shellArgs, " "));
        }

        // Set the env variables to be setup in the env where the application master will be run
        log.info("Set the environment for the application master");
        Map<String, String> env = new HashMap<>();

        // put location of shell script into env
        // using the env info, the application master will create the correct local resource for the
        // eventual containers that will be launched to execute the shell scripts
        env.put(DSConstants.DISTRIBUTEDSHELLSCRIPTLOCATION, hdfsShellScriptLocation);
        env.put(DSConstants.DISTRIBUTEDSHELLSCRIPTTIMESTAMP, Long.toString(hdfsShellScriptTimestamp));
        env.put(DSConstants.DISTRIBUTEDSHELLSCRIPTLEN, Long.toString(hdfsShellScriptLen));

        // Add AppMaster.jar location to classpath
        // At some point we should not be required to add
        // the hadoop specific classpaths to the env.
        // It should be provided out of the box.
        // For now setting all required classpaths including
        // the classpath to "." for the application jar
        StringBuilder classPathEnv = new StringBuilder(Environment.CLASSPATH.$$())
                .append(ApplicationConstants.CLASS_PATH_SEPARATOR).append("./*");
        for (String c : conf.getStrings(
                YarnConfiguration.YARN_APPLICATION_CLASSPATH,
                YarnConfiguration.DEFAULT_YARN_CROSS_PLATFORM_APPLICATION_CLASSPATH)) {
            classPathEnv.append(ApplicationConstants.CLASS_PATH_SEPARATOR);
            classPathEnv.append(c.trim());
        }
        classPathEnv.append(ApplicationConstants.CLASS_PATH_SEPARATOR).append(
                "./log4j.properties");

        // Set the necessary command to execute the application master
        Set<CharSequence> vargs = new HashSet<>(30);

        // Set java executable command
        log.info("Setting up app master command");
        vargs.add(Environment.JAVA_HOME.$$() + "/bin/java");
        // Set Xmx based on am memory size
        int amMemory = 1;
        vargs.add("-Xmx" + amMemory + "m");
        // Set class name
        String appMasterMainClass = "";
        vargs.add(appMasterMainClass);
        // Set params for Application Master
        int containerMemory = 1;
        int containerVirtualCores = 1;
        int numContainers = 1;
        int shellCmdPriority = 1;
        vargs.add("--container_memory " + String.valueOf(containerMemory));
        vargs.add("--container_vcores " + String.valueOf(containerVirtualCores));
        vargs.add("--num_containers " + String.valueOf(numContainers));
        vargs.add("--priority " + String.valueOf(shellCmdPriority));

        Map<String, String> shellEnv = new HashMap<>();
        for (Map.Entry<String, String> entry : shellEnv.entrySet()) {
            vargs.add("--shell_env " + entry.getKey() + "=" + entry.getValue());
        }
        boolean debugFlag = true;
        if (debugFlag) {
            vargs.add("--debug");
        }

        vargs.add("1>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/AppMaster.stdout");
        vargs.add("2>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/AppMaster.stderr");

        // Get final command
        StringBuilder command = new StringBuilder();
        for (CharSequence str : vargs) {
            command.append(str).append(" ");
        }

        log.info("Completed setting up app master command " + command.toString());
        List<String> commands = new ArrayList<String>();
        commands.add(command.toString());

        // Set up the container launch context for the application master
        ContainerLaunchContext amContainer = ContainerLaunchContext.newInstance(
                localResources, env, commands, null, null, null);

        // Set up resource type requirements
        // For now, both memory and vcores are supported, so we set memory and
        // vcores requirements
        int amVCores = 1;
        Resource capability = Resource.newInstance(amMemory, amVCores);
        appContext.setResource(capability);

        // Service data is a binary blob that can be passed to the application
        // Not needed in this scenario
        // amContainer.setServiceData(serviceData);

        // Setup security tokens
        if (UserGroupInformation.isSecurityEnabled()) {
            // Note: Credentials class is marked as LimitedPrivate for HDFS and MapReduce
            Credentials credentials = new Credentials();
            String tokenRenewer = conf.get(YarnConfiguration.RM_PRINCIPAL);
            if (tokenRenewer == null || tokenRenewer.length() == 0) {
                throw new IOException(
                        "Can't get Master Kerberos principal for the RM to use as renewer");
            }

            // For now, only getting tokens for the default file-system.
            final org.apache.hadoop.security.token.Token<?>[] tokens =
                    fs.addDelegationTokens(tokenRenewer, credentials);
            if (tokens != null) {
                for (Token<?> token : tokens) {
                    log.info("Got dt for " + fs.getUri() + "; " + token);
                }
            }
            DataOutputBuffer dob = new DataOutputBuffer();
            credentials.writeTokenStorageToStream(dob);
            ByteBuffer fsTokens = ByteBuffer.wrap(dob.getData(), 0, dob.getLength());
            amContainer.setTokens(fsTokens);
        }
        appContext.setAMContainerSpec(amContainer);


        // Set the priority for the application master
        int amPriority = 1;
        Priority pri = Priority.newInstance(amPriority);
        appContext.setPriority(pri);

        // Set the queue to which this application is to be submitted in the RM
        String amQueue = "default";
        appContext.setQueue(amQueue);

        // Submit the application to the applications manager
        // SubmitApplicationResponse submitResp = applicationsManager.submitApplication(appRequest);
        yarnClient.submitApplication(appContext);


        // Get application report for the appId we are interested in
        ApplicationReport report = yarnClient.getApplicationReport(appId);

        //if one task taking too long,we can manually kill this task by this instruction
        yarnClient.killApplication(appId);


        Map<String, String> envs = System.getenv();
//        String containerIdString = envs.get(ApplicationConstants.AM_CONTAINER_ID_ENV);  //这个报错
        String containerIdString = envs.get(ApplicationConstants.APP_SUBMIT_TIME_ENV);

        if (containerIdString == null) {
            // container id should always be set in the env by the framework
            throw new IllegalArgumentException(
                    "ContainerId not set in the environment");
        }
        ContainerId containerId = ConverterUtils.toContainerId(containerIdString);
        ApplicationAttemptId appAttemptID = containerId.getApplicationAttemptId();

        //amRMClient是client((AM)ApplicationMaster,mr的管理员)用来和ResourceManager进行通信用的
        AMRMClientAsync.AbstractCallbackHandler allocListener = new RMCallbackHandler();
        AMRMClientAsync amRMClient = AMRMClientAsync.createAMRMClientAsync(1000, allocListener);
        amRMClient.init(conf);
        //启动ResourceManager
        amRMClient.start();

        //nmClientAsync是client((AM)ApplicationMaster,mr的管理员)用来和NodeManager进行通信用的
        NMClientAsync.AbstractCallbackHandler containerListener = createNMCallbackHandler();
        NMClientAsync nmClientAsync = new NMClientAsyncImpl(containerListener);
        nmClientAsync.init(conf);
        //启动NodeManager
        nmClientAsync.start();


        // Register ApplicationMaster with ResourceManager
        // This will start heartbeating to the RM
        //MR的ApplicationMaster向yarn的ResourceManager注册自己,这个执行后AM将向RM发送心跳
        String appMasterHostname = NetUtils.getHostname();
        int appMasterRpcPort = 123;
        String appMasterTrackingUrl = "";
        RegisterApplicationMasterResponse response = amRMClient.registerApplicationMaster(appMasterHostname, appMasterRpcPort,
                        appMasterTrackingUrl);

        // Dump out information about cluster capability as seen by the
        // resource manager
        //利用resourceMaster查询集群资源信息
        int maxMem = response.getMaximumResourceCapability().getMemory();
        log.info("Max mem capability of resources in this cluster " + maxMem);
        int maxVCores = response.getMaximumResourceCapability().getVirtualCores();
        log.info("Max vcores capability of resources in this cluster " + maxVCores);

        // A resource ask cannot exceed the max.
        if (containerMemory > maxMem) {
            log.info("Container memory specified above max threshold of cluster."
                    + " Using max value." + ", specified=" + containerMemory + ", max="
                    + maxMem);
            containerMemory = maxMem;
        }

        if (containerVirtualCores > maxVCores) {
            log.info("Container virtual cores specified above max threshold of  cluster."
                    + " Using max value." + ", specified=" + containerVirtualCores + ", max="
                    + maxVCores);
            containerVirtualCores = maxVCores;
        }

        List<Container> previousAMRunningContainers =
                response.getContainersFromPreviousAttempts();
        log.info("Received " + previousAMRunningContainers.size() + " previous AM's running containers on AM registration.");

        int numTotalContainers = 100;
        int numTotalContainersToRequest =
                numTotalContainers - previousAMRunningContainers.size();
        // Setup ask for containers from RM
        // Send request for containers to RM
        // Until we get our fully allocated quota, we keep on polling RM for containers
        // Keep looping until all the containers are launched and shell script
        // executed on them ( regardless of success/failure).
        for (int i = 0; i < numTotalContainersToRequest; ++i) {
            ContainerRequest containerAsk = setupContainerAskForRM(1,2);
            amRMClient.addContainerRequest(containerAsk);
        }

        // Set the necessary command to execute on the allocated container
        // Set executable command
        vargs.add(shellCommand);
        // Set shell script path
        String scriptPath = "hdfs://ffds";
        String ExecBatScripStringtPath = "";
        String ExecShellStringPath = "";
        if (!scriptPath.isEmpty()) {
            vargs.add(Shell.WINDOWS ? ExecBatScripStringtPath
                                    : ExecShellStringPath);
        }

        // Set args for the shell command if any
        vargs.add(shellArgs);
        // Add log redirect params
        vargs.add("1>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stdout");
        vargs.add("2>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/stderr");

        // Get final command
        StringBuilder command1 = new StringBuilder();
        for (CharSequence str : vargs) {
            command1.append(str).append(" ");
        }

        List<String> commandList = new ArrayList<>();
        commandList.add(command1.toString());

        // Set up ContainerLaunchContext, setting local resource, environment,
        // command and token for constructor.

        // Note for tokens: Set up tokens for the container too. Today, for normal
        // shell commands, the container in distribute-shell doesn't need any
        // tokens. We are populating them mainly for NodeManagers to be able to
        // download anyfiles in the distributed file-system. The tokens are
        // otherwise also useful in cases, for e.g., when one is running a
        // "hadoop dfs" command inside the distributed shell.
        ByteBuffer allTokens = ByteBuffer.allocate(1);
        ContainerLaunchContext ctx = ContainerLaunchContext.newInstance(
                localResources, shellEnv, commands, null, allTokens.duplicate(), null);
//        containerListeners.addContainer(container.getId(), container);
        Container container = null;

        //NodeManager异步启动container
        nmClientAsync.startContainerAsync(container, ctx);


        FinalApplicationStatus appStatus = FinalApplicationStatus.SUCCEEDED;
        String appMessage = "";
        try {
            //After the ApplicationMaster determines the work is done, it needs to unregister itself through the
            //AM-RM client, and then stops the client.
            amRMClient.unregisterApplicationMaster(appStatus, appMessage, null);
        } catch (YarnException | IOException ex) {
            log.error("Failed to unregister application", ex);
        }
        //关闭
        amRMClient.stop();

        Collections.singletonMap("pacakge", response);

    }

    //组装设置container的内存和vCores的request
    private static ContainerRequest setupContainerAskForRM(int containerMemory, int containerVirtualCores) {
        int requestPriority = 1;
        // setup requirements for hosts
        // using * as any host will do for the distributed shell app
        // set the priority for the request
        Priority pri = Priority.newInstance(requestPriority);

        // Set up resource type requirements
        // For now, memory and CPU are supported so we set memory and cpu requirements
        Resource capability = Resource.newInstance(containerMemory, containerVirtualCores);

        ContainerRequest request = new ContainerRequest(capability, null, null, pri);
        log.info("Requested container ask: " + request.toString());
        return request;
    }

    private static NMClientAsync.AbstractCallbackHandler createNMCallbackHandler() {

        return null;
    }

    private static void addToLocalResources(FileSystem fs, String appMasterJar, String appMasterJarPath,
            String toString, Map<String, LocalResource> localResources, Object o) {
        //todo
    }
}
