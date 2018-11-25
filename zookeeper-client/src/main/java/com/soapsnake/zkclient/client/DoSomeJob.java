//package com.soapsnake.zk.client;
//
//import com.soapsnake.zk.util.ChildrenCache;
//import com.soapsnake.zk.util.TaskObject;
//import jdk.nashorn.internal.ir.BreakableNode;
//import org.apache.zookeeper.AsyncCallback;
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.KeeperException;
//import org.apache.zookeeper.Op;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//import org.apache.zookeeper.ZooDefs;
//import org.apache.zookeeper.ZooKeeper;
//import org.apache.zookeeper.data.Stat;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.Calendar;
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.Executor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * Created by liudun on 2017/5/22.
// */
//public class DoSomeJob {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DoSomeJob.class);
//
//    private ZooKeeper zk;
//    private ChildrenCache workersCache;
//    private Random random;
//    private String serverId;
//    private ExecutorService exe;
//
//    public DoSomeJob(){
//         random = new Random();
//         serverId =  String.valueOf(random.nextLong());
//         exe = Executors.newCachedThreadPool();
//
//    }
//
//    //workers watcher,当/workers及其下面的子节点发生变化时将会触发process函数:触发后会再一次设置监控点
//    Watcher workersChangeWatcher = new Watcher() {
//        public void process(WatchedEvent event) {
//            if (event.getType() == Event.EventType.NodeChildrenChanged){
//                assert "/workers".equals(event.getPath());
//                getWorkers();
//            }
//        }
//    };
//
//    //这里种的监控点是/workers及其下面的所有子节点
//    void getWorkers(){
//        zk.getChildren("/workers",workersChangeWatcher,wokersGetChildrenCallback,null);
//    }
//
//    void getWorkerList(){
//        zk.getChildren("/workers",workersChangeWatcher,wokersGetChildrenCallback,null);
//    }
//
//    //注册worker
//    void register(){
//        zk.create("/workers/worker-" + serverId,new byte[0],
//                ZooDefs.Ids.OPEN_ACL_UNSAFE,
//                CreateMode.EPHEMERAL,
//                createWorkerCallback,
//                null);
//    }
//
//    //异步注册worker回调
//    AsyncCallback.StringCallback createWorkerCallback = new AsyncCallback.StringCallback() {
//        public void processResult(int rc, String path, Object ctx, String name) {
//            switch (KeeperException.Code.get(rc)){
//                case CONNECTIONLOSS:
//                    register();
//                    break;
//                case OK:
//                    LOGGER.info("registered successfully:"+serverId);
//                    break;
//                case NODEEXISTS:
//                    LOGGER.warn("worker already registered:" + serverId);
//                    break;
//                default:
//                    LOGGER.error("create worker went wrong:",KeeperException.create(KeeperException.Code.get(rc),path));
//            }
//        }
//    };
//
//    //异步回调
//    private AsyncCallback.ChildrenCallback wokersGetChildrenCallback = new AsyncCallback.ChildrenCallback() {
//        public void processResult(int rc, String path, Object ctx, List<String> children) {
//                  switch (KeeperException.Code.get(rc)){
//                      case CONNECTIONLOSS:
//                          getWorkerList();
//                          break;
//                      case OK:
//                          LOGGER.info("succesfully got a list of workers:"+children.size()+"workers");
//                          reassignAndSet(children);
//                          break;
//                      default:
//                          LOGGER.error("getchildren failed",KeeperException.create(KeeperException.Code.get(rc),path));
//                  }
//        }
//    };
//
//    void reassignAndSet(List<String> children){
//        List<String> toProcess;
//        if (workersCache == null){
//            workersCache = new ChildrenCache(children);
//            toProcess = null;  //手动置空
//        }else {
//            LOGGER.info("Remove and Setting");
//            toProcess = workersCache.removedAndSet(children);
//        }
//
//        if (toProcess != null){
//            for (String worker : toProcess){
//                getAbsentWorkerTasks(worker);
//            }
//        }
//    }
//
//    Watcher tasksChangeWatcher = new Watcher() {
//        public void process(WatchedEvent event) {
//            if (event.getType() == Event.EventType.NodeChildrenChanged){
//                assert "/tasks".equals(event.getPath());
//                getTasks();
//            }
//        }
//    };
//
//    void getTasks(){
//        zk.getChildren("/tasks",tasksChangeWatcher,tasksGetChildreCallback,null);
//    }
//
//    AsyncCallback.ChildrenCallback tasksGetChildreCallback = new AsyncCallback.ChildrenCallback() {
//        public void processResult(int rc, String path, Object ctx, List<String> children) {
//            switch (KeeperException.Code.get(rc)){
//                case CONNECTIONLOSS:
//                    getTasks();
//                    break;
//                case OK:
//                    if (children != null){
//                        assignTasks(children);
//                    }
//                    break;
//                default:
//                    LOGGER.error("getTasksChildren failed.",KeeperException.create(KeeperException.Code.get(rc),path));
//            }
//        }
//    };
//
//    void assignTasks(List<String> tasks){
//        for (String task : tasks){
//            getTasksData(task);
//        }
//    }
//
//    void getTasksData(String task){
//        zk.getData("/tasks/"+task,false,taskDataCallback,task);
//    }
//
//    AsyncCallback.DataCallback taskDataCallback = new AsyncCallback.DataCallback() {
//        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
//            switch (KeeperException.Code.get(rc)){
//                case CONNECTIONLOSS:
//                    getTasksData((String)ctx);
//                    break;
//                case OK:
//                    int worker = random.nextInt(workerList.size());
//                    String designatedWorker = workerList.get(worker);
//
//                    String assignmentPath = "/assign/" + designatedWorker + "/" + (String)ctx;
//                    createAssignment(assignmentPath,data);
//                    break;
//                default:
//                    LOGGER.error("error when trying to get task data.",KeeperException.create(KeeperException.Code.get(rc),path));
//            }
//        }
//    };
//
//    void createAssignment(String path,byte[] data){
//        zk.create(path,data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,assignTaskCallback,data);
//    }
//
//    AsyncCallback.StringCallback assignTaskCallback = new AsyncCallback.StringCallback() {
//        public void processResult(int rc, String path, Object ctx, String name) {
//            switch (KeeperException.Code.get(rc)){
//                case CONNECTIONLOSS:
//                    createAssignment(path,(byte[])ctx);
//                    break;
//                case OK:
//                    LOGGER.info("tasks assigned correctly:" + name);
//                    deleteTask(name.substring(name.indexOf("/")+1));
//                    break;
//                case NODEEXISTS:
//                    LOGGER.warn("Task already assigned");
//                    break;
//                default:
//                    LOGGER.error("error when trying to assign tasks.",KeeperException.create(KeeperException.Code.get(rc),path));
//            }
//        }
//    };
//
//    void deleteTask(String name){
//        try {
//            zk.delete("name",-1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (KeeperException e) {
//            e.printStackTrace();
//        }
//    }
//
//    Watcher newTaskWatcher = new Watcher() {
//        public void process(WatchedEvent event) {
//            if (event.getType() == Event.EventType.NodeChildrenChanged){
//                assert new String("/assign/worker-"+serverId).equals(event.getPath());
//                getAssign();
//            }
//        }
//    };
//
//    void getAssign(){
//        zk.getChildren("/assign/worker-"+serverId,
//                newTaskWatcher,
//                tasksGetAssignChildreCallback,
//                null);
//    }
//
//    AsyncCallback.ChildrenCallback tasksGetAssignChildreCallback = new AsyncCallback.ChildrenCallback() {
//        public void processResult(int rc, String path, Object ctx, List<String> children) {
//            switch (KeeperException.Code.get(rc)){
//                case CONNECTIONLOSS:
//                    getAssign();
//                    break;
//                case OK:
//                    if (children != null){
//                        exe.execute(new Runnable() {
//                            List<String> children;
//                            DataCallback cb;
//
//                            public Runnable init(List<String> children,DataCallback cb){
//                                this.children = children;
//                                this.cb = cb;
//                                return this;
//                            }
//
//                            public void run() {
//                                LOGGER.info("Lopping into tasks");
//                                synchronized (onGoingTasks){
//                                    for (String task : children){
//                                        if (! onGoingTasks.contais(task)) LOGGER.trace("new task: {}",task);
//                                        zk.getData("/assign/worker-"+serverId+"/"+task,
//                                                false,
//                                                cb,
//                                                task);
//                                        onGoingTasks.add(task);
//                                    }
//                                }
//                            }
//                        }.init(children,taskDataCallback));
//                    }
//                    break;
//                default:
//                    System.out.println("getChildren failed: "+KeeperException.create(KeeperException.Code.get(rc),path));
//            }
//        }
//    };
//
//    void submitTask(String task,TaskObject taskCtx){
//        taskCtx.setTask(task);
//        zk.create("/tasks/task-",
//                task.getBytes(),
//                ZooDefs.Ids.OPEN_ACL_UNSAFE,
//                CreateMode.PERSISTENT_SEQUENTIAL,
//                createTaskCallback,
//                taskCtx);
//    }
//
//    AsyncCallback.StringCallback createTaskCallback = new AsyncCallback.StringCallback() {
//        public void processResult(int rc, String path, Object ctx, String name) {
//            switch (KeeperException.Code.get(rc)){
//                case CONNECTIONLOSS:
////                    submitTask((TaskObject)ctx).getTask(),(TaskObject)ctx);
//                    break;
//                case OK:
//                    LOGGER.info("My created task name:" + name);
////                    ((TaskObject)ctx).setTaskName(name);
//                    watchStatus("status" + name.replace("/tasks/",""),ctx);
//                    break;
//                default:
//                    LOGGER.error("something wrong in create task"+ KeeperException.create(KeeperException.Code.get(rc),path));
//            }
//        }
//    };
//
//    ConcurrentHashMap<String,Object> ctxMap = new ConcurrentHashMap<String, Object>();
//
//    void watchStatus(String path,Object ctx){
//        ctxMap.put(path,ctx);
//        zk.exists(path,
//                statusWatcher,
//                existsCallback,
//                ctx);
//    }
//
//    Watcher statusWatcher = new Watcher() {
//        public void process(WatchedEvent event) {
//            if (event.getType() == Event.EventType.NodeCreated){
//                assert event.getPath().contains("/status/task-");
//                zk.getData(event.getPath(),
//                        false,
//                        getDataCallback,
//                        ctxMap.get(event.getPath()));
//            }
//        }
//    };
//
//    AsyncCallback.StatCallback existsCallback = new AsyncCallback.StatCallback() {
//        public void processResult(int rc, String path, Object ctx, Stat stat) {
//            switch (KeeperException.Code.get(rc)){
//                case CONNECTIONLOSS:
//                    watchStatus(path,ctx);
//                    break;
//                case OK:
//                    if (stat != null){
////                        zk.getData(path,false,getDataCallback,null);
//                    }
//                    break;
//                case NONODE:
//                    break;
//                default:
//                    LOGGER.error("something wrong in "+ KeeperException.create(KeeperException.Code.get(rc),path));
//            }
//        }
//    };
//
//
//
//
//}
