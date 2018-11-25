package com.soapsnake.hbaseclient.mapreduce;

import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Created by soapsnake on 2017/9/26.
 */
public class ImportFromFile {

    public static final String NAME = "ImportFromFile";

    public enum Counters {LINES}

    static class ImportMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Writable> {

    }
}
