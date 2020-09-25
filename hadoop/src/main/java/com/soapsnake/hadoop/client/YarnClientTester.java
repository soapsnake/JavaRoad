package com.soapsnake.hadoop.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.collections.BeanMap;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.yarn.api.ApplicationConstants;
import org.apache.hadoop.yarn.api.ApplicationConstants.Environment;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationRequest;
import org.apache.hadoop.yarn.api.protocolrecords.GetNewApplicationResponse;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationSubmissionContext;
import org.apache.hadoop.yarn.api.records.ContainerLaunchContext;
import org.apache.hadoop.yarn.api.records.LocalResource;
import org.apache.hadoop.yarn.api.records.Resource;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.client.api.YarnClientApplication;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet.MAP;
import org.apache.htrace.shaded.commons.logging.impl.WeakHashtable;

import sun.rmi.runtime.Log;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-09-23
 */
public class YarnClientTester {


    private static final String SCRIPT_PATH = "";
    private static Object DSConstants;

    public static void main(String[] args) throws IOException, YarnException {
        YarnClient yarnClient = YarnClient.createYarnClient();

        Configuration configuration = new Configuration();
        yarnClient.init(configuration);
        yarnClient.start();

        YarnClientApplication app = yarnClient.createApplication();

        GetNewApplicationResponse response = app.getNewApplicationResponse();

        ApplicationSubmissionContext applicationSubmissionContext = app.getApplicationSubmissionContext();
        ApplicationId appId = applicationSubmissionContext.getApplicationId();
        applicationSubmissionContext.setKeepContainersAcrossApplicationAttempts(true);
        String appName = "firstYarnApp";
        applicationSubmissionContext.setApplicationName(appName);

        Map<String, LocalResource> localResourceMap = new HashMap<>();

        System.out.println("Copy App Master jar from local filesystem and add to local environment");

        FileSystem fs = FileSystem.get(configuration);
//        addToLocalResources(fs, appMasterJar, appMasterJarPath, appId.toString(),
//                localResources, null);

        // Set the log4j properties if needed
//        if (!log4jPropFile.isEmpty()) {
//            addToLocalResources(fs, log4jPropFile, log4jPath, appId.toString(),
//                    localResources, null);
//        }

        // The shell script has to be made available on the final container(s)
        // where it will be executed.
        // To do this, we need to first copy into the filesystem that is visible
        // to the yarn framework.
        // We do not need to set this as a local resource for the application
        // master as the application master does not need it.
        String hdfsShellScriptLocation = "";
        String shellScriptPath = "";
        long hdfsShellScriptLen = 0;
        long hdfsShellScriptTimestamp = 0;
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

        Map<String, String> shellCommand = new HashMap<>();
        if (!shellCommand.isEmpty()) {
//            addToLocalResources(fs, null, shellCommandPath, appId.toString(),
//                    localResources, shellCommand);
        }

        Object[] shellArgs = new Object[10];
        if (shellArgs.length > 0) {
//            addToLocalResources(fs, null, shellArgsPath, appId.toString(),
//                    localResources, StringUtils.join(shellArgs, " "));
        }

        // Set the env variables to be setup in the env where the application master will be run
        System.out.println("Set the environment for the application master");
        Map<String, String> env = new HashMap<String, String>();

        // put location of shell script into env
        // using the env info, the application master will create the correct local resource for the
        // eventual containers that will be launched to execute the shell scripts
//        env.put(DSConstants.DISTRIBUTEDSHELLSCRIPTLOCATION, hdfsShellScriptLocation);
//        env.put(DSConstants.DISTRIBUTEDSHELLSCRIPTTIMESTAMP, Long.toString(hdfsShellScriptTimestamp));
//        env.put(DSConstants.DISTRIBUTEDSHELLSCRIPTLEN, Long.toString(hdfsShellScriptLen));

        // Add AppMaster.jar location to classpath
        // At some point we should not be required to add
        // the hadoop specific classpaths to the env.
        // It should be provided out of the box.
        // For now setting all required classpaths including
        // the classpath to "." for the application jar
        StringBuilder classPathEnv = new StringBuilder(Environment.CLASSPATH.$$())
                .append(ApplicationConstants.CLASS_PATH_SEPARATOR).append("./*");
        for (String c : configuration.getStrings(
                YarnConfiguration.YARN_APPLICATION_CLASSPATH,
                YarnConfiguration.DEFAULT_YARN_CROSS_PLATFORM_APPLICATION_CLASSPATH)) {
            classPathEnv.append(ApplicationConstants.CLASS_PATH_SEPARATOR);
            classPathEnv.append(c.trim());
        }
        classPathEnv.append(ApplicationConstants.CLASS_PATH_SEPARATOR).append(
                "./log4j.properties");

        // Set the necessary command to execute the application master
        Vector<CharSequence> vargs = new Vector<CharSequence>(30);

        // Set java executable command
        System.out.println("Setting up app master command");
        vargs.add(Environment.JAVA_HOME.$$() + "/bin/java");
        // Set Xmx based on am memory size
        int amMemory = 0;
        vargs.add("-Xmx" + amMemory + "m");
        // Set class name
        String appMasterMainClass = "";
        vargs.add(appMasterMainClass);
        // Set params for Application Master
        int containerMemory = 1;
        int containerVirtualCores = 1;
        int numContainers = 1;
        int shellCmdPriority = 1;
        vargs.add("--container_memory " + containerMemory);
        vargs.add("--container_vcores " + containerVirtualCores);
        vargs.add("--num_containers " + numContainers);
        vargs.add("--priority " + shellCmdPriority);

        Map<String, String> shellEnv = new HashMap<>();
        for (Map.Entry<String, String> entry : shellEnv.entrySet()) {
            vargs.add("--shell_env " + entry.getKey() + "=" + entry.getValue());
        }

        vargs.add("1>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/AppMaster.stdout");
        vargs.add("2>" + ApplicationConstants.LOG_DIR_EXPANSION_VAR + "/AppMaster.stderr");

        // Get final command
        StringBuilder command = new StringBuilder();
        for (CharSequence str : vargs) {
            command.append(str).append(" ");
        }

        System.out.println("Completed setting up app master command " + command.toString());
        List<String> commands = new ArrayList<String>();
        commands.add(command.toString());

        // Set up the container launch context for the application master
        Map<String, LocalResource> localResources = new HashMap<>();
        ContainerLaunchContext amContainer = ContainerLaunchContext.newInstance(
                localResources, env, commands, null, null, null);

        // Set up resource type requirements
        // For now, both memory and vcores are supported, so we set memory and
        // vcores requirements
        int amVCores = 0;
        Resource capability = Resource.newInstance(amMemory, amVCores);
        applicationSubmissionContext.setResource(capability);

        // Service data is a binary blob that can be passed to the application
        // Not needed in this scenario
        // amContainer.setServiceData(serviceData);

        // Setup security tokens
        if (UserGroupInformation.isSecurityEnabled()) {
            // Note: Credentials class is marked as LimitedPrivate for HDFS and MapReduce
            Credentials credentials = new Credentials();
            String tokenRenewer = configuration.get(YarnConfiguration.RM_PRINCIPAL);
            if (tokenRenewer == null || tokenRenewer.length() == 0) {
                throw new IOException(
                        "Can't get Master Kerberos principal for the RM to use as renewer");
            }

            // For now, only getting tokens for the default file-system.
            final org.apache.hadoop.security.token.Token<?>[] tokens =
                    fs.addDelegationTokens(tokenRenewer, credentials);
            if (tokens != null) {
                for (Token<?> token : tokens) {
                    System.out.println("Got dt for " + fs.getUri() + "; " + token);
                }
            }
            DataOutputBuffer dob = new DataOutputBuffer();
            credentials.writeTokenStorageToStream(dob);
            ByteBuffer fsTokens = ByteBuffer.wrap(dob.getData(), 0, dob.getLength());
            amContainer.setTokens(fsTokens);
        }

        applicationSubmissionContext.setAMContainerSpec(amContainer);
    }

}
