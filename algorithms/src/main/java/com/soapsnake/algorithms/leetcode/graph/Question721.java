package com.soapsnake.algorithms.leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Question721 {

    //leetcode721
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //规则:两个名字一样,只有邮箱存在相同的才能够进行合并,否则视为不同的用户不允许进行合并
        //如此就不能用map.groupBY了
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : accounts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));  //email -> email (same as key)
                owner.put(a.get(i), a.get(0));  //email -> name
            }
        }
        for (List<String> a : accounts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++)
                parents.put(find(a.get(i), parents), p);
        }
        for(List<String> a : accounts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
            for (int i = 1; i < a.size(); i++)
                unions.get(p).add(a.get(i));
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }
    private String find(String s, Map<String, String> p) {
        return p.get(s) == s ? s : find(p.get(s), p);
    }

    //图的dfs算法
    Map<String, String> emailToName = new HashMap();    //email -> name
    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        Map<String, ArrayList<String>> graph = new HashMap();  //email1 -> [email1, email2, email3]
        //构造一个图,这个构造的过程是整个算法的关键
        this.buildGraph(graph, accounts);

        Set<String> seen = new HashSet();
        List<List<String>> ans = new ArrayList();
        for (String email: graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack();  //email
                stack.push(email);
                List<String> component = new ArrayList();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei: graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                //到此component已经添加完所有与该email相关的email(有关指的是所有这些email属于同一个人)
                component.add(0, emailToName.get(email)); //所有相关email这里取出来的都是同一个人
                ans.add(component);
            }
        }
        return ans;
    }

    private void buildGraph(Map<String, ArrayList<String>> graph, List<List<String>> accounts) {
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x-> new ArrayList<String>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x-> new ArrayList<String>()).add(email);
                emailToName.put(email, name);
                //email1 -> name, email2 -> name, email 3 -> name
            }
        }
    }
}
