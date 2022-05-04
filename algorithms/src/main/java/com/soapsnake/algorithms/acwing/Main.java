package com.soapsnake.algorithms.acwing;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main{

    public void main(String[] args) throws NumberFormatException, IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        
        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];

        String[] res = br.readLine().split(" ");
        for (int i = 0; i < num; i++ ) {
            arr[i] = Integer.parseInt(res[i]);
        }
        quickSort(arr, 0, num - 1);
        
        for(int i =0 ; i < num; i++) {
            System.out.println(arr[i]+ " ");
        }
        br.close();
    }

    private void quickSort(int[] q, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = q[l + r >> 1];
        int i = l - 1;
        int j = r + 1;

        while(i < j) {
            do {
                i++;
             } while (q[i] < mid);
             
            do { j--;
             } while (q[j] > mid);
            
            if(i < j) {
                int temp = q[i];
                q[i] = q[j];
                q[j] = temp;
            }
        }
        quickSort(q, l, j);
        quickSort(q, j + 1, r);
    }

    public static int gcd(int a, int b) {
        return a == 0 ? b : gcd(a, a % b);
    }

    public int trap(int[] height) {
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int bottomIndex =stack.pop();
                if (stack.isEmpty()) break;
                int leftIndex = stack.peek();
                int width = i - leftIndex - 1;
                int high = Math.min(height[leftIndex], height[i]) - height[bottomIndex];
                ans += width * high;
            }
            stack.push(i);
        }
        return ans;
    }


}
