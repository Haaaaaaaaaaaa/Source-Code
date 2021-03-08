package cn.edu.ujn.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Sun
 * @desxription FileOutputStream
 * @date 2021/3/8
 */
public class FileOutput {
    public static void main(String[] args) {
        //创建文件对象
        File file = new File("word.txt");
        try {
            //创建FileOutputStream对象
            FileOutputStream out = new FileOutputStream(file);
            //创建byte数组
            byte byt[] = "我有一只小毛驴。".getBytes();
            //将数组中的信息写入文件
            out.write(byt);
            //关闭流
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
