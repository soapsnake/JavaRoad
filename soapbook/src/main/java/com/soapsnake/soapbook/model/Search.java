package com.soapsnake.soapbook.model;

import java.util.List;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-06-01
 */
public interface Search {
    public List<Member> searchMember(String name);

    public List<Group> searchGroup(String name);

    public List<Page> searchPage(String name);

    public List<Post> searchPost(String word);
}
