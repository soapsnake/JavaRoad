package com.soapsnake.thrift.license.rpc;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cglib.proxy.Proxy;

/**
 * Created by soapsnake on 2017/6/4.
 */
public class ThriftServiceClientProxyFactory implements FactoryBean, InitializingBean {

    private String service;

    private String serverAddress;

//    private ThriftServerAddressProvider addressProvider;

    private Integer maxActive;

    private Integer idleTime;

    private Object proxyClient;
    private Class objectClass;
    private GenericObjectPool<TServiceClient> pool;
    private PoolOperationCallback callback = new PoolOperationCallback() {
        @Override
        public void destory(TServiceClient client) {
            System.out.println("destory");
        }

        @Override
        public void make(TServiceClient client) {
            System.out.println("create");
        }
    };

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(Integer idleTime) {
        this.idleTime = idleTime;
    }

    public Object getProxyClient() {
        return proxyClient;
    }

    public void setProxyClient(Object proxyClient) {
        this.proxyClient = proxyClient;
    }

    @Override
    public Object getObject() throws Exception {
        return proxyClient;
    }

    @Override
    public Class<?> getObjectType() {
        return objectClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        zookeeper获取服务端地址,暂不开放
//        if (serverAddress != null){
//            addressProvider = new FixedAddressProvider(serverAddress);
//        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        //获取thrift service的class,并且加载这个类,下面的代理类创建过程中会使用这个class
        objectClass = classLoader.loadClass(service + "$Iface");

        //感叹这种加载类的手法
        Class<TServiceClientFactory<TServiceClient>> fi = (Class<TServiceClientFactory<TServiceClient>>) classLoader.loadClass(service + "$Client$Factory");

        //创建TServiceClientFactory对象
        TServiceClientFactory<TServiceClient> clientFactory = fi.newInstance();

        //客户端对象池
        ThriftClientPoolFactory clientPool = new ThriftClientPoolFactory(serverAddress, clientFactory, callback);
        //客户端对象池的属性设置
        GenericObjectPool.Config poolConfig = new GenericObjectPool.Config();
        poolConfig.maxActive = maxActive;
        poolConfig.minIdle = 0;
        poolConfig.minEvictableIdleTimeMillis = idleTime;
        poolConfig.timeBetweenEvictionRunsMillis = idleTime / 2L;
        //池创建:ThriftClientPoolFactory对象,config对象
        pool = new GenericObjectPool<>(clientPool, poolConfig);

        proxyClient = Proxy.newProxyInstance(classLoader, new Class[]{objectClass}, (o, method, objects) -> {
            //从pool中获取client对象
            TServiceClient client = pool.borrowObject();
            try {
                return method.invoke(client, objects);
            } catch (Exception e) {
                throw e;
            } finally {
                pool.returnObject(client);
            }
        });
    }

    static interface PoolOperationCallback {

        void destory(TServiceClient client);

        void make(TServiceClient client);
    }
}
