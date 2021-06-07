package com.soapsnake.hadoop.mrexample;

import java.io.IOException;
import java.net.URI;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


/**
 * 
 * Created on 2020-09-24
 */
public class WordCount {


    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens()) {
                word.set(itr.nextToken());
                context.write(word, one);   //"hello,world" => hello:1, world:1
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context)
                throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);   //["hello:1", "hello:1", "world:1"] => "hello:2", "world:1"
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");

        //手动指定reduce任务的数量
        job.setNumReduceTasks(10);
        job.setJarByClass(WordCount.class);

        //map类
        job.setMapperClass(TokenizerMapper.class);

        //这个干嘛的?
        job.setCombinerClass(IntSumReducer.class);

        //reduce类
        job.setReducerClass(IntSumReducer.class);

        //输出文件也是k-v格式的,这里分别指定key的类型, value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //执行hadoop任务时hadoop jar  xxx  参数1   参数2,参数1就是输入文件的地址, 参数2就是输出文件的地址,原来是这样指定的....
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //map任务是否能够以推测的方式执行
        job.setMapSpeculativeExecution(true);

        URI uri = URI.create("hdfs://localhost:9000/user/soapsnake/input/yarn-site.xml");

        //把uri指向的文件放入分布式缓存,工作节点可以从该缓存中拿到这个文件
        job.addCacheFile(uri);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
