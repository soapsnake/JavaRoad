package com.soapsnake.algorithms.paypay;

import org.junit.Assert;
import org.junit.Test;


public class ImmutableQueueTest {
    private ImmutableQueue<String> immutableQueue;

    public ImmutableQueueTest() {
        this.immutableQueue = new ImmutableQueue<>();
        immutableQueue.enQueue("Hello");
        immutableQueue.enQueue("world");
        immutableQueue.enQueue("I am");
        immutableQueue.enQueue("xxxx");
    }

    @Test
    public void testEnQueue() throws Exception {
        immutableQueue.enQueue("hi");
        Assert.assertSame("Hello", immutableQueue.head());
    }


    @Test
    public void testDeQueue() throws Exception {
        immutableQueue.deQueue();
        Assert.assertSame("world", immutableQueue.head());
    }

    @Test
    public void testHead() throws Exception {
        Assert.assertSame("Hello", immutableQueue.head());
    }

    @Test
    public void testIsEmpty() throws Exception {
        immutableQueue.deQueue();
        immutableQueue.deQueue();
        immutableQueue.deQueue();
        immutableQueue.deQueue();
        Assert.assertTrue(immutableQueue.isEmpty());
    }
}
