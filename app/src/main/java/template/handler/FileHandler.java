package template.handler;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件作者：余涛
 * 功能描述：描述该文件所实现的功能
 * 创建时间：2019/10/12 15:25
 */
public class FileHandler {

    /**
     * 获取目录下所有文件
     * @param path 指定目录路径
     * @return
     */
    public static List<String> getAllFilesPath(String path) {
        File file=new File(path);
        File[] files=file.listFiles();
        if (files == null){
            Log.e("error","空目录");
            return null;
        }
        List<String> s = new ArrayList<>();
        for(int i =0;i<files.length;i++){
            s.add(files[i].getAbsolutePath());
        }
        return s;
    }

    /**
     * 读取文件内容
     * @param filePath
     * @return
     */
    public static String readFileContent(String filePath) {
        String path = filePath;
        String content = ""; //文件内容字符串
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory()) {
            Log.d("TestFile", "The File doesn't not exist.");
        } else {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null) {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while (( line = buffreader.readLine()) != null) {
                        content += line + "\n";
                    }
                    instream.close();
                }
            }
            catch (java.io.FileNotFoundException e) {
                Log.d("TestFile", "The File doesn't not exist.");
            }
            catch (IOException e) {
                Log.d("TestFile", e.getMessage());
            }
        }
        return content;
    }

    /**
     * 从路径中获取文件名
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        return filePath.substring(filePath.lastIndexOf('/')+1, filePath.lastIndexOf('.'));
    }


}
