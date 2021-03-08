package cn.edu.ujn.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Sun
 * @description FileInputStream
 * @date 2021/3/8
 */

public class FileInput {
    public static void main(String[] args) {
        //创建文件对象
        File file = new File("word.txt");

        try {
            //创建FileInputStream类对象
            FileInputStream in = new FileInputStream(file);
            //创建byte数组
            byte byt[] = new byte[1024];
            int len = in.read(byt);
            //将文件中的信息输出
            System.out.println("文件中的信息是：" + new String(byt, 1, len));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
