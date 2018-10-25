package com.vico.license.rpc;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * 对象池创建类
 * Created by liudun on 2017/6/4.
 */
public class ThriftClientPoolFactory extends BasePoolableObjectFactory<TServiceClient> {
    private String serverAddress;

    private TServiceClientFactory<TServiceClient> clientFactory;

    private ThriftServiceClientProxyFactory.PoolOperationCallback callback;

    public ThriftClientPoolFactory(String serverAddress,
                                   TServiceClientFactory<TServiceClient> clientFactory,
                                   ThriftServiceClientProxyFactory.PoolOperationCallback callback) {
        this.serverAddress = serverAddress;
        this.clientFactory = clientFactory;
        this.callback = callback;

    }

    @Override
    public TServiceClient makeObject() throws Exception {
        String[] args = serverAddress.split(":");
        TSocket tSocket = new TSocket(args[0], Integer.parseInt(args[1]));
        TProtocol protocol = new TBinaryProtocol(tSocket);
        TServiceClient client = clientFactory.getClient(protocol);
        tSocket.open();
        if (callback != null) {
            try {
                callback.make(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    public void destoryObject(TServiceClient client) {
        if (callback != null) {
            try {
                callback.destory(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validateObject(TServiceClient client) {
        TTransport pin = client.getInputProtocol().getTransport();
        return pin.isOpen();
    }


}
