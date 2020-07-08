package com.soapsnake.algorithms.structures.unionfind;

/**
 *
 * Created on 2020-07-07
 * 并查集,union-find数据结构,主要用于联通性探测
 */
public class DSU {
    int[] parent;
    int[] rank;

    public DSU(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
        rank = new int[size];
    }

    //返回x元素所在组的根root,为什么是根实在想不明白
    public int find(int x) {
        // 寻找x节点所在组的根节点，根节点具有性质 root = parent[root]
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    /**
     * union的作用是联通x和y,但是如果union返回false,表名x和y本来就是联通的,不需要再操作
     * 如果返回true则表名x和y本来不是联通的,但是现在联通了
     */
    public boolean union(int x, int y) {
        //xr代表x的组, yr代表y的组
        int xr = find(x), yr = find(y);
        if (xr == yr) {
            //如果x和y的root相同,代表x和y是联通的,这里返回了false,表示x和y本来就是联通的,不需要再次联通,
            //因此union操作失败,返回false
            return false;
        } else if (rank[xr] < rank[yr]) {
            //x所在的树比y所在的树小,因此需要把x挂到y所在的树上
            parent[xr] = yr;
        } else if (rank[xr] > rank[yr]) {
            //x所在的树比y所在的树要大,因此把y挂到x所在的树上
            parent[yr] = xr;
        } else {
            //如果x和y树一般大,这种情况强制把y挂到x树上,然后增大x树的size
            parent[yr] = xr;
            rank[xr]++;
        }
        //到这里x所在的树和y所在的树已经合并,x和y本来不连通,现在联通了
        return true;
    }
}
