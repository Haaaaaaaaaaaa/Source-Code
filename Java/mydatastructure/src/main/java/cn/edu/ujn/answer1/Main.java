package cn.edu.ujn.answer1;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Sun
 * @description 整数求平方根
 * @date 2021/3/11
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat formatResult = new DecimalFormat("#.000");
        int num = scanner.nextInt();
        System.out.println(formatResult.format(mySqrt(num)));
    }

    /**
     * 不使用库函数，求整数的平方根
     *
     * @param num
     */
    public static double mySqrt(int num) {
        double temp, result;
        temp = 1;
        result = temp / 2.0 + num / (2 * temp);
        while (Math.abs(result - temp) > 1e-4) {
            temp = result;
            result = temp / 2.0 + num / (2 * temp);
        }
        return result;
    }
}

