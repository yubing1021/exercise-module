package com.darben.lucene;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-10 12:12
 */
public class TryWithResource {

    public static void main(String[] args) {
        //TryWithResource.test01();
        //TryWithResource.test02();
    }

    public static void test01(){
        FileInputStream inputStream =null;
        try {
            inputStream = new FileInputStream(new File("G:\\MyPrivate\\MyKit\\lucene\\demo\\test.txt"));

            byte[] bytes = new byte[1024];
            StringBuffer content  = new StringBuffer();
            while (inputStream.read(bytes)!=-1){
                content.append(new String(bytes,0, bytes.length));
            }
            System.out.println(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream!=null){
                    inputStream.close();
                    inputStream=null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
    *@Description: try-with-resource 自动关闭外部资源
    *@Param:
    *@return:
    *@date: 2020/4/10
    */
    public static String readFileToString(String filepath){
        StringBuffer content  = new StringBuffer();
        try (FileInputStream inputStream = new FileInputStream(new File(filepath))){
            byte[] bytes = new byte[1024];
            while (inputStream.read(bytes)!=-1){
                content.append(new String(bytes,0, bytes.length));
            }
            System.out.println(content);
        }
        catch (IOException e){
            throw new RuntimeException(e.getMessage(), e);
        }
        return content.toString();
    }

}
