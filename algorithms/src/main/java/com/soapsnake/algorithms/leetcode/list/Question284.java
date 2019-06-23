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

        Iterator<Integer> iterator;  //iterator代理了一个list

        Integer next;

        boolean hasMoreElement;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
            this.advanceIter();  //初始化时候先初始化下next的值
        }

        private void advanceIter() {
            if (!this.iterator.hasNext()) {
                this.hasMoreElement = false;
            } else {
                this.hasMoreElement = true;
                this.next = iterator.next();
            }
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
            if (!hasMoreElement) {
                throw new NoSuchElementException();
            } else {
                Integer res = next;
                this.advanceIter();
                return res;
            }
        }

        @Override
        public boolean hasNext() {
            return this.hasMoreElement;
        }
    }
}
