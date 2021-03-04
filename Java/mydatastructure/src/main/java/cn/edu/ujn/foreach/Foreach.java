package cn.edu.ujn.foreach;

public class Foreach {
    public static void main(String[] args) {
        int arr2[][] = {{1, 2, 3}, {1, 2}};
        System.out.println("数组中的元素是：");
        int i = 0;
        for (int x[] : arr2) {
            i++;
            int j = 0;
            for (int e : x) {
                j++;
                if (i == arr2.length && j == x.length)
                    System.out.println(e);
                else
                    System.out.println(e);
            }
        }
    }
}
