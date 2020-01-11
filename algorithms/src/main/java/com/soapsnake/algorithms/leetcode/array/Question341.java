package com.soapsnake.algorithms.leetcode.array;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-01-09
 */
public class Question341 {

    public static void main(String[] args) {
        List<NestedInteger> nestedList = null;
        NestedIterator i = new NestedIterator(nestedList);
        while (i.hasNext()) {
            int num = i.next();
            System.out.println(num);
        }
    }

    static class NestedIterator implements Iterator<Integer> {
        Stack<NestedInteger> stack = new Stack<>();
        //最后一个元素最先入栈,这样push的时候,最先出栈的还是排在前面的
        public NestedIterator(List<NestedInteger> nestedList) {
            for(int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger(); //栈顶,问题这里返回的有可能是null
        }

        @Override
        public boolean hasNext() {
            while(!stack.isEmpty()) {
                NestedInteger curr = stack.peek();
                if(curr.isInteger()) {
                    return true;
                }
                stack.pop();  //到这里说明栈顶不是一个数字,而是一个list,这时候需要判断list是否有元素
                for(int i = curr.getList().size() - 1; i >= 0; i--) {
                    stack.push(curr.getList().get(i));  //把list中的元素重新压到栈里面
                }
            }
            return false;
        }
    }


    public interface NestedInteger {
             // @return true if this NestedInteger holds a single integer, rather than a nested list.
             boolean isInteger();

             // @return the single integer that this NestedInteger holds, if it holds a single integer
             // Return null if this NestedInteger holds a nested list
             Integer getInteger();

             // @return the nested list that this NestedInteger holds, if it holds a nested list
            // Return null if this NestedInteger holds a single integer
             List<NestedInteger> getList();
    }
}
