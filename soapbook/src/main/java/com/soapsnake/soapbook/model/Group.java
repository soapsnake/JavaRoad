package com.soapsnake.soapbook.model;

import java.util.List;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-01
 */
public class Group {
    private Integer groupId;
    private String name;
    private String description;
    private int totalMembers;
    private List<Member> members;

    public boolean addMember(Member member) {
        return false;
    }

    public boolean updateDescription(String description) {
        return false;
    }
}
