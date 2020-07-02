package com.soapsnake.algorithms.leetcode.queue.priority;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;


/**

 * Created on 2020-01-07
 */
public class Question332 {

    /**
     * Example 1:
     * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     *
     *
     * Example 2:
     * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
     * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
     *              But it is larger in lexical order.
     */
    //leetcode332
    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> targets = new HashMap<>(); //小顶堆
        for (List<String> ticket : tickets) {
            targets.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>()).add(ticket.get(1));
        }
        System.out.println(targets);
        List<String> route = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        stack.push("JFK");
        while (!stack.empty()) {
            while (targets.containsKey(stack.peek()) && !targets.get(stack.peek()).isEmpty()) {
                stack.push(targets.get(stack.peek()).poll());
            }
            route.add(0, stack.pop());
        }
        return route;
    }

    public static void main(String[] args) {
        Question332 question332 = new Question332();
        List<String> list = Arrays.asList("JFK","SFO");
        List<String> list1 = Arrays.asList("JFK","ATL");
        List<String> list2 = Arrays.asList("SFO","ATL");
        List<String> list3 = Arrays.asList("ATL","JFK");
        List<String> list4 = Arrays.asList("ATL","SFO");
        List<List<String>> tickets = Arrays.asList(list, list1, list2, list3, list4);
        System.out.println(question332.findItinerary(tickets));
    }
}
