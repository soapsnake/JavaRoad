package com.soapsnake.algorithms.leetcode.str;

import java.util.List;
import java.util.Stack;

import com.soapsnake.algorithms.leetcode.list.NestedInteger;


/**
 *
 * Created on 2020-03-05
 */
public class Question385 {

    //leetcode385
    public NestedInteger deserialize(String s) {
        if (s.isEmpty())
            return null;
        if (s.charAt(0) != '[') // ERROR: special case
            return new NestedIntegerImpl(Integer.valueOf(s));

        Stack<NestedIntegerImpl> stack = new Stack<>();
        NestedIntegerImpl curr = null;
        int l = 0; // l shall point to the start of a number substring;
        // r shall point to the end+1 of a number substring
        for (int r = 0; r < s.length(); r++) {
            char ch = s.charAt(r);
            if (ch == '[') {
                if (curr != null) {
                    stack.push(curr);
                }
                curr = new NestedIntegerImpl();
                l = r+1;
            } else if (ch == ']') {
                String num = s.substring(l, r);
                if (!num.isEmpty())
                    curr.add(new NestedIntegerImpl(Integer.valueOf(num)));
                if (!stack.isEmpty()) {
                    NestedIntegerImpl pop = stack.pop();
                    pop.add(curr);
                    curr = pop;
                }
                l = r+1;
            } else if (ch == ',') {
                if (s.charAt(r-1) != ']') {
                    String num = s.substring(l, r);
                    curr.add(new NestedIntegerImpl(Integer.valueOf(num)));
                }
                l = r+1;
            }
        }
        return curr;
    }

    static class NestedIntegerImpl implements NestedInteger {

        private int value;

        public NestedIntegerImpl() {

        }

        public NestedIntegerImpl(Integer valueOf) {
            this.value = valueOf;
        }

        @Override
        public boolean isInteger() {
            return false;
        }

        @Override
        public Integer getInteger() {
            return null;
        }

        @Override
        public void setInteger(int value) {

        }

        @Override
        public void add(NestedInteger ni) {

        }

        @Override
        public List<NestedInteger> getList() {
            return null;
        }
    }

}
