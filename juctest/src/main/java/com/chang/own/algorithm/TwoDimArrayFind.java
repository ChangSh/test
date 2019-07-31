package com.chang.own.algorithm;

/**
 * @author: changsh
 * @date: 2019/2/14 0014
 */

/* 在一个二维数组中（每个一维数组的长度相同），
 每一行都按照从左到右递增的顺序排序，
 每一列都按照从上到下递增的顺序排序。
 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。*/

/* 思路：key比右上角大消除行，比右上角小，消除列*/

public class TwoDimArrayFind {

    public static boolean find(int target, int[][] array) {
        if (array.length == 0) {
            return false;
        }
        int row = array.length;
        int column = array[0].length;

        int i = 0;
        int j = column - 1;
        while (i < row && j > 0) {
            if (target == array[i][j]) {
                return true;
            }
            if (target > array[i][j]) {
                i++;
                continue;
            }
            if (target < array[i][j]) {
                j--;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        boolean result = find(10, new int[][]{{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}});

        System.out.println(result);
    }
}
