package com.wutuobang.score.util;

import java.io.*;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {

    private static Configuration configuration;

    static {
        //通过Freemarker的Configuration读取相应的ftl
        configuration = new Configuration(Configuration.VERSION_2_3_23);//这里是对应的你使用jar包的版本号：<version>2.3.23</version>

        //configuration.setDirectoryForTemplateLoading(new File("/ftl")); //如果是maven项目可以使用这种方式
        //第二个参数 为你对应存放.ftl文件的包名
        //configuration.setClassForTemplateLoading(this.getClass(), "/ftl");

            /*configuration.setDirectoryForTemplateLoading(new File("/ftl"));*/
        //第二个参数 为你对应存放.ftl文件的包名
        configuration.setClassForTemplateLoading(FreeMarkerUtil.class, "/ftl");
    }

    public static Template getTemplate(String name) {
        try {
            Template template = configuration.getTemplate(name,"utf-8");

            return template;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getWriter(String name, Map<String, Object> root) {
        //通过Template可以将模版文件输出到相应的文件流
        Template template = getTemplate(name);
        try {
            //template.process(root, new PrintWriter(System.out));//在控制台输出内容
            //StreamWriter streamWriter = new
            StringWriter stringWriter = new StringWriter();
            template.process(root, stringWriter);//在控制台输出内容

            return stringWriter.getBuffer().toString();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输出HTML文件
     *
     * @param name
     * @param root
     * @param outFile
     */
    public static void fprint(String name, Map<String, Object> root, String outFile) {
        FileWriter out = null;
        try {
            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
            out = new FileWriter(new File("D:/new_works/freeMarker/src/static/" + outFile));
            Template temp = getTemplate(name);
            temp.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
