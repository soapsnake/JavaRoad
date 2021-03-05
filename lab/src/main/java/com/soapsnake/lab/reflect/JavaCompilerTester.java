package com.soapsnake.lab.reflect;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2021-03-05
 */
public class JavaCompilerTester {


    public Map<String, byte[]> tester(String fileName, String source) {
        Map<String, byte[]> results = new HashMap<>();

        //获取java编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        //获取文件管理器
        StandardJavaFileManager stdManager = compiler.getStandardFileManager(null, null, null);
        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {

            //把java源文件(.java后缀)读入内存
            JavaFileObject javaFileObject = manager.makeStringSource(fileName, source);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
            if (task.call()) {
                results = manager.getClassBytes();
            }
            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {

    }
}
