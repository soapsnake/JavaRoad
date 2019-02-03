package com.soapsnake.algorithms.raft;

import lombok.Data;

/**
 * raft集群节点
 *
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-02 19:20
 */
@Data
public class RaftNode implements Runnable {

    //本台机器编号
    public int id;

    //服务器最后一次知道的leader的任期号(初始化0,持续递增)
    public int  currentTerm;

    //在当前获取选票的候选人的Id
    public int votedFor;

    //日志条目集合
    public RaftLog[] logs;

    //已知的最大的已经被提交的日志条目的索引值
    public int commitIndex;

    //最后被应用到状态机的日志条目索引值(初始化为0, 持续递增)
    public int lastApplied;

    //节点角色: 1: leader 2:follower  3:candidate
    private RaftCast cast;

    enum RaftCast {
        FLEADER(1, "leader"),
        FOLLOWER(2, "follower"),
        CANDIDATE(3, "candidate");

        RaftCast(int code, String desc) {

        }
    }

    /**
     *
     * @param term   领导人的任期号
     * @param leaderId   领导人的Id
     * @param prevLogIndex   行的日志条目紧随之前的索引值
     * @param prevLogTerm   prevLogIndex条目的任期号
     * @param entries   准备存储的日志条目(当表示心跳时为空,一次性发送多个是为了提高效率)
     * @param leaderCommit   领导人已经提交的日志的索引值
     * @return 是否追加成功
     */
    public RaftRpcRes addRaftLogRPC(int term, int leaderId, int prevLogIndex, int prevLogTerm, RaftLog[] entries, int leaderCommit) {
        RaftRpcRes res = new RaftRpcRes();

        //如果 term < currentTerm 就返回 false
        if (term < this.currentTerm) {
            res.setIsSuccess(false);
            return res;
        }

        //如果日志在 prevLogIndex 位置处的日志条目的任期号和 prevLogTerm 不匹配，则返回 false
        if (this.logs[prevLogIndex].getLogTerm() != prevLogTerm) {
            res.setIsSuccess(false);
            return res;
        }

        //如果已经存在的日志条目和新的产生冲突（索引值相同但是任期号不同），删除这一条和之后所有的
        for (int i = 0; i < this.logs.length - 1; i++) {
            if (this.logs[i].getLogTerm() != entries[i].getLogTerm()) {
                this.logs[i].clean(i);
            }
        }

        //附加任何在已有的日志中不存在的条目
        for (int i = this.logs.length - 1; i < entries.length; i++) {
            this.logs[i] = entries[i];
        }

        if (leaderCommit > this.commitIndex) {
            this.commitIndex = Math.min(leaderCommit, this.minOfNewEntry(entries));
        }



        return new RaftRpcRes();
    }

    //新日志条目索引值的最小值
    private int minOfNewEntry(RaftLog[] entries) {
        return 0;
    }


    /**
     * 发起选leader的投票
     * @param term  候选人的任期号
     * @param candidateId  拉选票的人的机器Id
     * @param lastLogIndex  拉选票者的最后日志条目的索引值
     * @param lastLogTerm 拉选票者最后日志条目的任期号
     * @return
     */
    public RaftRpcRes fireVoteRpc(int term, int candidateId, int lastLogIndex, int lastLogTerm) {
        RaftRpcRes res = new RaftRpcRes();

        if (term < this.currentTerm) {
            res.setVoteGranted(false);
            return res;
        }

        //如果 votedFor 为空或者就是 candidateId，并且候选人的日志至少和自己一样新，那么就投票给他
        //自己可以给自己投票
        if (this.id == candidateId && this.logs[logs.length - 1].getLogTerm() == lastLogTerm ) {
            res.setTerm(this.currentTerm);
            res.setVoteGranted(true);
            return res;
        }

        res.setTerm(term);
        res.setVoteGranted(true);
        return res;
    }


    /**
     * 节点角色变更为follwer,由选举产生的leader调用
     * @param T  新leader的任期号
     */
    public void changeCastToFollower(int T, RaftLog[] newLogs) {
        if (this.commitIndex > lastApplied) {
            this.logs = newLogs;   //刷入新的日志
        }
        if (T > this.currentTerm) {
            this.currentTerm = T;
            this.cast = RaftCast.FOLLOWER; //角色切换为跟随者
        }
    }

    /**
     * 节点角色切换为候选人,自己调用
     */
    public void changeCastToCandidate() {

    }

    /**
     * 与leader保持心跳,leader死亡则返回false
     */
    public boolean pingLeader() {
        return false;
    }


    @Override
    public void run() {
        //与leader保持心跳
        while (!pingLeader()) {
            this.currentTerm++;
            this.fireVoteRpc(0,0,0,0);
            this.resetCurrentTerm();
        }
    }

    //重置当前任期号
    private void resetCurrentTerm() {

    }



    //角色变更为leader
    public void changedToLeader() {


    }



    @Data
    static class RaftRpcRes {
        //当前的任期号,用于领导人去更新自己
        private Integer term; //如果是投票

        //跟随者包含了匹配上 prevLogIndex 和 prevLogTerm 的日志时为真
        private Boolean isSuccess;

        //候选人赢得了此张选票时为真
        private Boolean voteGranted;
    }




}
