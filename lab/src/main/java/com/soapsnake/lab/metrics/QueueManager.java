package com.soapsnake.lab.metrics;

import java.util.LinkedList;
import java.util.Queue;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

/**
 * Created on 2020-01-10
 */
public class QueueManager {
    private Queue<Integer> queue;

    public QueueManager(MetricRegistry metrics, String name) {
        this.queue = new LinkedList<>();
        metrics.register(MetricRegistry.name(QueueManager.class, name, "size"),

                //gauge能做的就是返回一个变量的瞬时值
                new Gauge<Integer>() {
                    @Override
                    public Integer getValue() {
                        return queue.size();
                    }
                });
    }
}