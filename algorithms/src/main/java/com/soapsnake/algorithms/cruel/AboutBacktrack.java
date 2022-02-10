package com.soapsnake.algorithms.cruel;

/**
 *xxxx
 * Created on 2022-02-08
 */
public class AboutBacktrack {

    //1774
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        result = baseCosts[0];
        for (int base : baseCosts) {
            this.dfs(base, toppingCosts, 0, target);
        }
        return result;
    }
    private int result;

    private void dfs(int base, int[] toppingCosts, int curIndex, int target) {
        if (Math.abs(target - base) < Math.abs(target - result)
                || Math.abs(target - result) == Math.abs(target - base) && base < target) {

            System.out.println("1=" + (Math.abs(target - base) < Math.abs(target - result)) +
                                "2=" + (Math.abs(target - result) == Math.abs(target - base)) +
                                "3=" + (base < target));
            result = base;
        }
        if (curIndex >= toppingCosts.length || base >= target) {
            return;
        }
        dfs(base, toppingCosts, curIndex + 1, target);
        dfs(base + toppingCosts[curIndex], toppingCosts, curIndex + 1, target);
        dfs(base + toppingCosts[curIndex] * 2, toppingCosts, curIndex + 1, target);
    }

    public static void main(String[] args) {
        AboutBacktrack aboutBacktrack = new AboutBacktrack();
        int[] base = {3,10};
        int[] topp = {2,5};
        int tartget = 9;
        System.out.println(aboutBacktrack.closestCost(base, topp, tartget));
    }
}
