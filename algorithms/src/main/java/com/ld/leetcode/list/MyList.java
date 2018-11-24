package com.ld.leetcode.list;

/**
 * 本人自用双向链表
 * @author soapsnake
 * @date 2018/11/24
 * todo 1.目前对链表进行delete操作后node.toString会抛异常
 *      2.链表不允许插入null节点,但是JDK的LinkedList是可以的
 *      3. 线程不安全
 *      4. 还实现不了迭代
 *      5. 没有索引
 *      6. add接口只支持插到链表尾部,还不能插到中间或头部
 */
public class MyList<T> {
    static class Node<T> {
        private Node prev;
        private Node next;
        T val;
        private int index;
        Node(T val, Node prev, Node next){
            this.prev = prev;
            this.next = next;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "prev=" + prev +
                    ", next=" + next +
                    ", val=" + val +
                    '}';
        }
    }

   private Node head;
   private Node tail;
   private int size;

   public MyList() {

   }

    public MyList(Node head) {
        if (head == null || head.val == null) {
            throw new RuntimeException("head node must not be null!");
        }
        this.head = head;
        this.tail = head;
        this.size = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    /**
     * 添加节点
     * @param val
     *
     * 注意了,这里的实现不允许存在null节点,但是JDK的LinkedList是允许null节点的
     */
    public void add(T val) {
        if (val == null) {
            throw new RuntimeException("node must not be null!");
        }

        Node node = new Node(val, null, null);
        if (this.head == null) {
            this.head = node;
            this.tail = node;
            this.size = 1;
            return;
        }
        this.tail.next = node;
        node.prev = this.tail;
        this.tail = node;
        this.size++;
    }

    /**
     * 判断链表中是否存在有该值的节点
     */
    public boolean contains(T val) {
        Node head = this.head;

        while (head != null) {
            if (head.val.equals(val)){
                return true;
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 删除节点
     * @param val
     */
    public void deleteNode(T val) {

        if (!contains(val)) {
            System.out.println("链表中不存在该节点");
            return;
        }

        Node head = this.head;
        while (head != null) {
            if (head.val.equals(val)) {
                Node prev = head.prev;
                Node next = head.next;
                if (prev == null) { //要删的刚好是头结点
                    this.head = next;
                } else {
                    prev.next = next;
                }
                if (next == null) { //刚好是尾节点
                    this.tail = prev;
                }
                this.size--;
                System.out.println();
            }
            head = head.next;
        }
    }

    /**
     * 修改节点的值
     * newVal为该节点新的值
     */
    public void updateNode(Node node, T newVal) {

    }

    /**
     * todo 返回某节点的index
     * @param val
     * @return
     */
    public int nodeIndex(T val) {
        return 0;
    }

    /**
     * 打印链表
     * @param list
     */
    public static void printMyList(MyList list) {
        if (list == null) {
            throw new RuntimeException("list is null!");
        }
        Node head = list.head;
        while (head != null) {
            if (head.next != null) {
                System.out.print(head.val + " -> ");
            } else {
                System.out.print(head.val);
            }
            head = head.next;
        }
        System.out.println(" list.size=" + list.size + " list.head=" + list.getHead().val +
                " list.tail=" + list.getTail().val);
    }

    @Override
    public String toString() {
        return "MyList{" +
                "head=" + head +
                ", tail=" + tail +
                ", size=" + size +
                '}';
    }


    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<>();

        myList.add(1);
        myList.add(3);
        myList.add(2);
        myList.add(4);
        myList.add(5);

        printMyList(myList);
        System.out.println(myList.contains(4));
        System.out.println(myList.contains(6));

        myList.deleteNode(1);
        myList.deleteNode(5);
        printMyList(myList);

        myList.deleteNode(2);
        printMyList(myList);

        myList.deleteNode(1);

        myList.add(7);
        printMyList(myList);
    }
}
