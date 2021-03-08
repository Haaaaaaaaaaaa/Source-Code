package cn.edu.ujn.selectsort;

/**
 * @author Sun
 * @description SelectSort 直接选择排序算法实例
 * @date 2021-3-4
 */
public class SelectSort {
    public static void main(String[] args) {
        //创建一个原始数组
        int[] array = {44, 11, 22, 44, 66, 33, 22};
        //创建直接排序类对象
        SelectSort sorter = new SelectSort();
        //调用排序对象的方法
        sorter.sort(array);
        sorter.backwardSort(array);
    }

    /**
     * 直接选择排序，正排序
     *
     * @param array
     */
    public void sort(int[] array) {
        int index;
        for (int i = 1; i < array.length; i++) {
            index = 0;
            for (int j = 1; j <= array.length - i; j++) {
                if (array[j] > array[index]) {
                    index = j;
                }
            }
            //交换在位置array.length-i和index（最大值）上的两个数
            int temp = array[array.length - i];//把死一个元素值保存到临时变量中
            array[array.length - i] = array[index];//把第二个元素值保存到第一个元素单元中
            array[index] = temp;//把临时变量也就是第一个元素值保存在第二个元素中
        }
        showArray(array);//打印排完序的数组
    }

    /**
     * 直接选择排序，倒排序
     *
     * @param array
     */
    public void backwardSort(int[] array) {
        int index;
        for (int i = 1; i < array.length; i++) {
            index = 0;
            for (int j = 1; j <= array.length - i; j++) {
                if (array[j] < array[index]) {
                    index = j;
                }
            }
            int temp = array[array.length - i];
            array[array.length - i] = array[index];
            array[index] = temp;
        }
        showArray(array);
    }

    /**
     * 显示数组中的所有元素
     *
     * @param array 要显示的数组
     */
    public void showArray(int[] array) {
        for (int i : array) {
            System.out.println(i);
        }

    }

}
