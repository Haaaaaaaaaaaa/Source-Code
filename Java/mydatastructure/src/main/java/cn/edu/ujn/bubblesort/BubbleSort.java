package cn.edu.ujn.bubblesort;

/**
 * @Description:BubbleSort
 * @Author:caitao
 * @Date:2021-3-4
 */

public class BubbleSort {
    public static void main(String[] args) {
        //创建一个二维数组
        int[] array = {3, 4, 2, 3, 6, 88, 44, 33};
        //创建冒泡排序类的对象
        BubbleSort sorter = new BubbleSort();
        sorter.sort(array);
    }

    /**
     * 冒泡排序
     *
     * @param array
     */
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            //比较相邻的两个元素，较大的往后排
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];//把第一个元素值保存在临时变量中
                    array[j] = array[j + 1];//把第二个元素的值保存在第一个元素单元中
                    array[j + 1] = temp;//把临时变量的值保存在第二个元素单元中
                }
            }
        }
        showArray(array);//输出冒泡排序后的数组
    }

    /**
     * 打印数组
     *
     * @param array
     */
    public void showArray(int[] array) {
        for (int i : array) {//遍历数组
            System.out.println(i);//输出每个元素的值
        }
    }
}
