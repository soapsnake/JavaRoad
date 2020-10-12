package com.soapsnake.algorithms.raft;

/**
 * 领导者
 *
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-02 19:19
 */
public class RaftLeader extends RaftNode {

    //对于每一个服务器,需要发送给他的下一个日志条目的索引值(初始化为领导人最后的索引值加1)
    private int[] nextIndex;  //数组的索引代表不同的服务器,比如nextIndex[0]就代表第0台服务器

    //对于每一个服务器,已经复制给他的日志的最高索引值
    private int[] matchIndex;


    private boolean isLeader; // 是否是真的leader


}
