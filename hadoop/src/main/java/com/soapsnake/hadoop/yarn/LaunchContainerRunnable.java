package com.soapsnake.hadoop.yarn;

import java.awt.event.ContainerListener;

import org.apache.hadoop.yarn.api.records.Container;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-09-27
 */
public class LaunchContainerRunnable implements Runnable {

    private Container container;

    public LaunchContainerRunnable(Container container) {
        this.container = container;
    }
    @Override
    public void run() {

    }
}
