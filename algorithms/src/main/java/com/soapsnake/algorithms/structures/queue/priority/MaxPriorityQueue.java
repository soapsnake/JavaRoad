package com.soapsnake.algorithms.structures.queue.priority;

import com.soapsnake.algorithms.utils.NumberUtils;

import java.util.Arrays;
import java.util.Collection;

/**
 * 大顶堆, 大顶堆可以用来解决最小k个数的问题
 * 在运算的过程中,大顶堆的顶部一定是算到目前为止的最大数字,比如我大顶堆大小为10,如果有10000个数字,如果
 * 从头开始运算,如果第一个数字就是这10000个数字里面最大的数字,那么大顶堆的顶部将永远是这个数字
 *
 * @param <K>
 */
public class MaxPriorityQueue<K extends Comparable<K>> extends AbstractPriorityQueue<K> {
    private static MaxPriorityQueue maxPriorityQueue;

    //不允许用无参构造
    private MaxPriorityQueue() {

    }

    /**
     * create a priority queue of initial capacity max
     *
     * @param max
     */
    @SuppressWarnings("unchecked")
    public MaxPriorityQueue(int max) {  //max应该是大顶堆的最大容量
        //学着点泛型数组的创建方式
        //数组元素从1开始,0位留空
        super.pq = (K[]) new Comparable[max + 1];
        super.LEN = max + 1;
        super.tail = 0;
    }

    /**
     * create a priority queue from the keys in a[]
     *
     * @param a
     */
    @SuppressWarnings("unchecked")
    public MaxPriorityQueue(K[] a) {
        this(a.length);
        //数组元素从1开始,0位留空
        System.arraycopy(a, 0, pq, 1, a.length);
        super.tail = pq.length - 1;
        super.LEN = pq.length;
        System.out.println("原始pq = " + Arrays.toString(pq));
        this.sinkTop();
        System.out.println("sink后pq = " + Arrays.toString(pq));
        //todo 这个有问题,sink()一次似乎并不能确定pq一定就是最大堆了
    }

    //单例工厂方法
    public static <K> MaxPriorityQueue buildMaxPq(int size) {
        if (maxPriorityQueue == null) {
            synchronized (MaxPriorityQueue.class) {
                maxPriorityQueue = new MaxPriorityQueue(size);
            }
        }
        return maxPriorityQueue;
    }

    public static void main(String[] args) {
//        MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>(10);
        Collection<Integer> numbers = NumberUtils.batchGenNonDup(10);
        System.out.println("原始数组 = " + numbers);
//        for (Integer i : numbers) {
//            queue.insert(i);
//        }
        MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>(numbers.toArray(new Integer[0]));
        //堆有序而不是pq有序!!!!!!!!!!!!
        System.out.println(Arrays.toString(queue.pq));
//        while (!queue.isEmpty()) {
//            System.out.println(queue.delMax());
//        }

        //排序
        System.out.println(Arrays.toString(queue.sort()));
        /**
         * todo 如何利用maxpq来做大数据的topN问题?(数据量远大于pq的容量)
         * 假设数据量为m,我们的pq的容量是n,有 n <<< m
         * 思路:目前的pq只是实现了delmax,这是因为pq[1]一定是最大值,但是最小值并不容易取到(绝不是pq[n]!!!!)
         * 所以假设我们的pq为了腾容量删数据,那么删掉的有可能就是最终的最大值!!!
         */
    }

    public K max() {
        return super.pq[1];   //堆顶的元素永远最大
    }

    /**
     * return and remove the largest key
     * <p>
     * 这个接口到底干嘛用的: 当insert插入完待排序的元素后,我们的大顶堆就已经是堆有序的了,此时调这个delMax挨个把元素从
     * 大顶堆当中取出来比如放进一个ArrayList,那么这个ArrayList当中的数字就是有序的了
     */
    private K delMax() {
        K max = pq[1];   // 堆顶的元素永远是最大的,这里用max缓存起来,方便后面返回
        super.exchange(1, tail--);  //堆顶元素和堆底元素互换,为后面的操作做准备,注意这里是n--,也就是交换之后n指针才会后移
        pq[tail + 1] = null;   //最后一位置空方便GC,非常nice(此时n+1是指向原来的堆顶的也就是max)
        this.sinkTop();    //现在的堆顶其实是原来的堆底,肯定不是最大数,需要下沉,下沉完了堆顶还是最大的
        return max;
    }

    @Override
    //新插入的元素是插入到数组的最后的,也就是处于树的最低端,如果是比较大的数字可能需要上浮
    public void insert(K k) {
        if (k == null) {
            return;
        }
        if (tail + 1 < LEN) {               //如果pq没满那么直接插入
            super.pq[++tail] = k;
            this.swim(tail);
        } else {                             //如果pq满了,那么新元素需要视情况入队还是舍弃
            if (k.compareTo(pq[1]) >= 0) {

            } else {
                pq[1] = k;
                this.sinkTop();
            }
        }
    }

    //这个数据结构最核心的其实就是这个swim和下面的sink函数
    //新插入的堆底元素上浮
    private void swim(int n) {
        while (n > 0 && super.lessThan(n / 2, n)) { //如果父节点比子节点要小
            super.exchange(n / 2, n); //那么交换父节点和该子节点
            n /= 2;   //n / 2后指向其父节点,这里其实并不关心兄弟节点的值得情况
        }
    }

    //堆中的第i个元素下沉
    private void sinkTop() {
        int i = 1;
        while (2 * i <= tail) {
            int j = 2 * i;    //i元素的左子节点
            if (j < tail && super.lessThan(j, j + 1)) {  //我们的j指针应该指向比较大的那个子节点
                j++;  //j在两个子节点上移动,指向比较大的那个
            }
            if (!super.lessThan(i, j)) { //父节点的值已经比子节点的值大的情况下就没必要继续算了
                break;
            }
            super.exchange(i, j);  //父节点比子节点小那么需要交换
            i = j;
        }
    }

    //这个排序算法,由于要用到del方法,排序结束会清空pq,所以这里用了点技巧把pq缓存了起来,排序结束后再赋值回来
    @Override
    @SuppressWarnings("unchecked")
    public K[] sort() {
        K[] backup = (K[]) new Comparable[super.LEN];
        System.arraycopy(super.pq, 0, backup, 0, super.tail);
        K[] res = (K[]) new Comparable[super.tail];
        for (int i = 0; i < res.length; i++) {
            res[i] = this.delMax();
        }
        super.pq = backup;
        return res;
    }

    @Override
    public boolean isEmpty() {
        //如果指针移动到了第0位,那么表明pq已经空了
        return super.tail == 0;
    }

    @Override
    public int size() {
        return super.tail;
    }
}
