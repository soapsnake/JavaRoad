package com.soapsnake.soapbook.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Created on 2020-06-01
 */
public class SearchIndex implements Search {
    HashMap<String, List<Member>> memberNames;
    HashMap<String, List<Group>> groupNames;
    HashMap<String, List<Page>> pageTitles;
    HashMap<String, List<Post>> posts;

    public boolean addMember(Member member) {
        if (memberNames.containsKey(member.getName())) {
            memberNames.get(member.getName()).add(member);
        } else {
            memberNames.put(member.getName(), Collections.singletonList(member));
        }
        return true;
    }

    public boolean addGroup(Group group) {
        return false;
    }

    public boolean addPage(Page page) {
        return false;
    }

    public boolean addPost(Post post) {
        return false;
    }

    public List<Member> searchMember(String name) {
        return memberNames.get(name);
    }

    public List<Group> searchGroup(String name) {
        return groupNames.get(name);
    }

    public List<Page> searchPage(String name) {
        return pageTitles.get(name);
    }

    public List<Post> searchPost(String word) {
        return posts.get(word);
    }

}
