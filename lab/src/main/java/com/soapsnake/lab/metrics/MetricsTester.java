package com.soapsnake.lab.metrics;

import static com.codahale.metrics.MetricRegistry.name;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;


/**
 * Created on 2020-01-10
 */
public class MetricsTester {

    private  static final MetricRegistry metricRegistry = new MetricRegistry();
    private final Meter request = metricRegistry.meter("requests");

    //Histogram度量类型用于测量一个数据流各值的统计分布。其除了能够测量最大值、最小值、平均值外，还可以测量中位数、75、90、95、98、99和99.9%等。
    private final Histogram responseSizes = metricRegistry.histogram(name(RequestHandler.class, "response-sizes"));

    //Timer度量类型包含了Meter和Histogram的统计，即比率和统计信息的综合。
    private final Timer responses = metricRegistry.timer(name(RequestHandler.class, "responses"));

//    public String handleRequest(Request request, Response response) {
//        responseSizes.update(response.getFaultBlockCount());
//        final Timer.Context context = responses.time();
//        try {
//            // etc;
//            return "OK";
//        } finally {
//            context.stop();
//        }
//    }

    public static void startReport() {
        //使用Reporter可以输出测量结果，支持ConsoleReporter, CsvReporter, Slf4Reporter, JmxReporter等等。
        ConsoleReporter rep = ConsoleReporter.forRegistry(metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS).build();
        rep.start(1, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        startReport();

        //Meter用来度量事件并发的数量和速度。
        Meter requests = metricRegistry.meter("requests");
        requests.mark();
        wait5second();
    }

    private static void wait5second() {

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {

        }
    }
}
