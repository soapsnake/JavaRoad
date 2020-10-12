package com.soapsnake.mydubbo.server;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.*;

import javax.management.ServiceNotFoundException;

import com.soapsnake.mydubbo.annotate.RpcService;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-09
 */
public class RpcInvocationHandler implements InvocationHandler {

    private Object realImp;

    private static Map<String, Class> serviceMap = new HashMap<>();

    static {
        doScanAsFile(Server.class.getPackage());
        //serviceMap.put("TestService", TestServiceImpl2.class);
    }

    public RpcInvocationHandler(String serviceName) throws ServiceNotFoundException {
        if (!serviceMap.containsKey(serviceName)) {
            throw new ServiceNotFoundException(serviceName + "==>该服务不存在");
        }
        try {
            realImp = serviceMap.get(serviceName).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new ServiceNotFoundException(serviceName + "==>该服务不存在");
        }

    }

    public static void doScanAsFile(Package pakage) {
        String packageName = pakage.getName();
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件
                    scanAndLoadService(packageName, filePath);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scanAndLoadService(String packageName, String filePath) {
        File dir = new File(filePath);
        if (!dir.exists()) {
            System.out.println(filePath + "不存在!");
            return;
        }
        System.out.println("扫描路径:" + filePath);
        if (dir.isDirectory()) {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            File file[] = dir.listFiles(file1 -> (file1.isDirectory())
                    || (file1.getName().endsWith(".class")));

            for (int i = 0; i < file.length; i++) {
                System.out.println(file[i].getPath());
                String preDirName = file[i].getName();
                System.out.println(preDirName);
                scanAndLoadService(packageName + "." + preDirName, file[i].getPath());
            }
        } else if (dir.isFile()) {
            System.out.println("扫描类:" + dir.getPath());
            try {
                //                int start = dir.getName().lastIndexOf("/") + 1;
                //                int end = dir.getName().length();
                //                String className = dir.getName().substring(start,end);
                int end = packageName.lastIndexOf(".");
                packageName = packageName.substring(0, end);
                Class<?> t = Thread.currentThread().getContextClassLoader().loadClass(packageName);
                RpcService rpcServiceAnnotion = t.getAnnotation(RpcService.class);
                if (rpcServiceAnnotion != null) {
                    //加入到serviceMap
                    serviceMap.put(rpcServiceAnnotion.value(), t);
                    System.out.println(rpcServiceAnnotion.value());
                    System.out.println(serviceMap.get(rpcServiceAnnotion.value()));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(realImp,args);
    }
}
