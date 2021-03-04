package cn.edu.ujn.sparsearray;

/**
 * @Description: SparseArray
 * @Author: caitao
 * @Date: 2020-10-25
 */

public class SparseArray {
    public static void main(String[] args) {
        /**
         * 创建一个原始的数组，大小11*11
         * 0:表示棋盘对应位置没有棋子
         * 1:表示棋盘对应位置为黑棋
         * 2:表示棋盘对应位置为白棋
         */
        int[][] chessArray1 = new int[11][11];
        chessArray1[1][2] = 1;
        chessArray1[2][3] = 2;
        chessArray1[4][5] = 2;

        //打印原始数组
        for (int[] row : chessArray1) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
