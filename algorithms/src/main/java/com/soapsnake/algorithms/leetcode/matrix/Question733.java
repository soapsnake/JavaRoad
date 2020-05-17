package com.soapsnake.algorithms.leetcode.matrix;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-11
 */
public class Question733 {

	//leetcode733
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		if (image[sr][sc] == newColor) return image;
		fill(image, sr, sc, image[sr][sc], newColor);
		return image;
	}

	private void fill(int[][] image, int sr, int sc, int color, int newColor) {
		if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) return;
		image[sr][sc] = newColor;
		fill(image, sr + 1, sc, color, newColor);
		fill(image, sr - 1, sc, color, newColor);
		fill(image, sr, sc + 1, color, newColor);
		fill(image, sr, sc - 1, color, newColor);
	}
}
