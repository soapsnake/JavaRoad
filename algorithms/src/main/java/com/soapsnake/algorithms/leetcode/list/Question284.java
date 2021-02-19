package com.soapsnake.algorithms.leetcode.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Question284 {

    public static void main(String[] args) {
//        PeekingIterator peekingIterator = new PeekingIterator(new i);
//
//        System.out.println(peekingIterator.peek());
//
//        System.out.println(peekingIterator.next());
//
//        System.out.println(peekingIterator.hasNext());
//
//
//        List<Integer> list = new ArrayList<>();
//        list.iterator();
    }


    static class PeekingIterator implements Iterator<Integer> {
        private Integer next = null;
        private Iterator<Integer> iter;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            iter = iterator;
            if (iter.hasNext())
                next = iter.next();
        }

        // Returns the next element in the iteration without advancing the iterator.
        //返回迭代中的下一个元素而不推进迭代器
        public Integer peek() {
            return next;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            Integer res = next;
            next = iter.hasNext() ? iter.next() : null;
            return res;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }
    }
}
