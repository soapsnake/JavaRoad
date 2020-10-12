package com.soapsnake.soapbook.model;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * Created on 2020-06-01
 */
public class Member extends Person {
    private Integer memberId;
    private Date dateOfMembership;
    private String name;

    private Profile profile;
    private HashSet<Integer> memberFollows;
    private HashSet<Integer> memberConnections;
    private HashSet<Integer> pageFollows;
    private HashSet<Integer> memberSuggestions;
    private HashSet<ConnectionInvitation> connectionInvitations;
    private HashSet<Integer> groupFollows;

    public Member(Integer memberId) {
        this.memberId = memberId;
    }

    public boolean sendMessage(Message message) {
        return false;
    }

    public boolean createPost(Post post) {
        return false;
    }

    public boolean sendConnectionInvitation(ConnectionInvitation invitation) {
        return false;
    }

    private Map<Integer, Integer> searchMemberSuggestions() {
        Map<Integer, Integer> suggestions = new HashMap<>();
        for(Integer memberId : this.memberConnections) {
            HashSet<Integer> firstLevelConnections = new Member(memberId).getMemberConnections();
            for(Integer firstLevelConnectionId : firstLevelConnections) {
                this.findMemberSuggestion(suggestions, firstLevelConnectionId);
                HashSet<Integer> secondLevelConnections = new Member(firstLevelConnectionId).getMemberConnections();
                for(Integer secondLevelConnectionId : secondLevelConnections) {
                    this.findMemberSuggestion(suggestions, secondLevelConnectionId);
                }
            }
        }

        // sort by value (increasing count), i.e., by highest number of mutual connection count
        Map<Integer, Integer> result = new LinkedHashMap<>();
        suggestions.entrySet().stream()
//                .sorted(reverseOrder(Map.Entry.comparingByValue()))
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

        return result;
    }

    private HashSet<Integer> getMemberConnections() {

        return null;
    }

    private <V> Comparator<? super Entry<Integer, Integer>> reverseOrder(
            Comparator<Entry<Object, V>> comparingByValue) {
        return null;
    }

    private void findMemberSuggestion(Map<Integer, Integer> suggestions, Integer connectionId) {
        // return if the proposed suggestion is already a connection or if there is a
        // pending connection invitation
        if(this.memberConnections.contains(connectionId) ||
                this.connectionInvitations.contains(connectionId)) {
            return;
        }
        int count = suggestions.containsKey(connectionId) ? suggestions.get(connectionId) : 0;
        suggestions.put(connectionId, count + 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
