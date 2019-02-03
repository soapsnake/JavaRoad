package com.soapsnake.algorithms.raft;

import lombok.Data;

/**
 * @Auther soapsnake@gmail.com
 * @Date 2019-02-02 19:24
 */
@Data
public class RaftLog {

    //日志条目任期号
    private int logTerm;

    //日志内容
    private String data;



    //清空自startIndex之后的所有日志条目
    public void clean(int startIndex) {

    }
}
