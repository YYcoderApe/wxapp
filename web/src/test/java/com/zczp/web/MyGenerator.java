package com.zczp.web;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//编写java启动类，执行逆向工程的配置文件，生成数据库中指定表的model和dao层：
// （注意：启动类中创建File中的路径是从本项目中开始的，需要找到从项目开始写起，直到找到配置文件的路径即可）
public class MyGenerator {
    public void generate() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("./web/src/main/resources/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    public static void main(String[] args) throws Exception {
        try {
            MyGenerator myGenerator = new MyGenerator();
            myGenerator.generate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
