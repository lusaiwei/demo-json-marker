package com.ihoment.demojsonmarker.app;

import com.alibaba.fastjson.JSONObject;
import com.ihoment.demojsonmarker.config.Const;
import com.ihoment.demojsonmarker.domain.ApiInfo;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Saiwei.Lu
 * 2018/10/15
 */
public enum MainClient{
    /**单例*/
    INSTANCE;

    public static final String ABANDON_KEYWORD = "c";
    public static final String QUIT_KEYWORD = "q";
    public static final String SAVE_PATH = Const.SAVE_PATH;
    public static final String HOST_REG = Const.HOST_REG;
    public static final String URL_PARAM_REG = Const.URL_PARAMS_REG;
    public static final String URL_SPLIT = "/";

    private Scanner scanner;
    private boolean inited = false;

    public static MainClient init() {
        System.out.println("-------蓝鲸ERP authorBy:Saiwei.Lu-------");
        System.out.println("------10天改8次接口格式一点都不虚的--------");
        System.out.println();

        INSTANCE.scanner = new Scanner(System.in);
        INSTANCE.inited = true;

        return INSTANCE ;
    }

    public void start(){
        if(!inited){
            System.out.println("未初始化");
            return;
        }
        System.out.println("json文件存储地址:"+SAVE_PATH);
        while (true){
            boolean accept = accept();
            if(!accept){
                break;
            }
        }
    }

    /**
     * 返回true继续输入,返回false退出
     */
    private boolean accept() {
        System.out.println("------------------------------");
        System.out.println("输入XHR网址,输入"+QUIT_KEYWORD+"退出程序:");

        String url = readLine();
        if(Objects.equals(QUIT_KEYWORD,url)){
            return false;
        }
        url = parseUrl(url);
        if(url == null){
            System.out.println("url地址不正确");
            return true;
        }

        System.out.println("输入原始json,输入"+ABANDON_KEYWORD+"放弃当前录入:");
        String json = readLine();
        boolean effective = effectiveJson(json);
        if(!effective){
            System.out.println("json格式错误");
            return true;
        }
        if(Objects.equals(json, ABANDON_KEYWORD)){
            System.out.println("已放弃当前输入");
            return true;
        }
        json = parseSensitiveInfo(json);
        ApiInfo apiInfo = new ApiInfo(url, json);
        saveApiInfo(apiInfo);
        return true;
    }

    private String readLine() {
        String s = scanner.nextLine();
        if(StringUtils.isEmpty(s) || Objects.equals(s.trim(),"")){
            return readLine();
        }
        return s.trim();
    }

    private void saveApiInfo(ApiInfo apiInfo) {
        String dirPath = buildDirPath(apiInfo.getUrl());
        File dir = new File(dirPath);
        if(!dir.exists()){
            boolean mkdirs = dir.mkdirs();
            if(!mkdirs){
                System.out.println("创建文件夹失败,检查权限");
                return;
            }
        }

        File jsonFile = new File(dir, "json.ftl");
        try {
            write2File(jsonFile,apiInfo.getJson());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void write2File(File jsonFile, String json) throws Exception{
        BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
        writer.write(json);
        writer.close();
        System.out.println("写入完成,文件地址:"+jsonFile.getAbsolutePath());
    }

    /**替换敏感数据*/
    private static String parseSensitiveInfo(String json) {

        Map<String, String> sensitiveData = Const.SENSITIVE_DATA;
        for (Map.Entry<String, String> entry : sensitiveData.entrySet()) {
            json = json.replaceAll(entry.getKey(),entry.getValue());
        }
        return json;
    }

    private static String parseUrl(String url) {
        url = url.replaceAll(HOST_REG,"");
        url = url.replaceAll(URL_PARAM_REG,"");
        return url;
    }

    private static boolean effectiveJson(String json) {
        try {
            JSONObject.parse(json);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    private static String buildDirPath(String url) {
        if(url.startsWith(URL_SPLIT)){
            url = url.substring(1,url.length());
        }
        if(url.endsWith(URL_SPLIT)){
            url = url.substring(0,url.length() -1);
        }
        return SAVE_PATH + URL_SPLIT + url;
    }

}

