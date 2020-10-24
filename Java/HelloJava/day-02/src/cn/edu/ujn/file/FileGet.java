package cn.edu.ujn.file;

import java.io.File;
import java.io.IOException;

public class FileGet {
    public static void main(String[] args) throws IOException {
        File f1 = new File("D:\\AppData\\Git\\SourceCode\\Java\\HelloJava\\day-02\\2.txt");
        if(f1.exists()){
            System.out.println("存在");
        }else{
            f1.createNewFile();
        }
        show01();
    }

    private static void show01() {
        System.out.println("hello");
    }
}
