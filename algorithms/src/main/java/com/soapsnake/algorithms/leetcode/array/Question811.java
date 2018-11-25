package com.soapsnake.algorithms.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author soapsnake
 * @date 2018/10/26
 *
 * Input:
 * ["9001 discuss.leetcode.com"]
 * Output:
 * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
 * Explanation:
 * We only have one website domain: "discuss.leetcode.com".
 * As discussed above, the subdomain "leetcode.com" and "com" will also be visited.
 * So they will all be visited 9001 times.
 */
class Question811 {
    //傻逼办法,单有效
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> domainMap = new HashMap<>();

        for (String domain:cpdomains) {
            String[] strs = domain.split(" ");
            Integer count = Integer.valueOf(strs[0]);
            String[] subs = strs[1].split("\\.");
            StringBuilder sb = new StringBuilder();
            for (int i=subs.length - 1;i>=0;i--) {
                String dest = i == subs.length - 1 ? subs[i] : subs[i] + ".";
                sb.insert(0, dest);
                if (domainMap.containsKey(sb.toString())) {
                    Integer oldvalue = domainMap.get(sb.toString());
                    domainMap.replace(sb.toString(), oldvalue, oldvalue + count);
                }else {
                    domainMap.put(sb.toString(), count);
                }
            }
        }
        List<String> result = new ArrayList<>();
        for (Map.Entry entry : domainMap.entrySet()){
            result.add(entry.getValue() + " " + entry.getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        Question811 question811 = new Question811();
        String[] strings = {"9001 discuss.leetcode.com"};
        List<String> res = question811.subdomainVisits(strings);
        ArrayUtils.printList(res);
    }

}
