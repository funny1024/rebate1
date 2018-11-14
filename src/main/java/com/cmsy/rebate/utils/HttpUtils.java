package com.cmsy.rebate.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class HttpUtils {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String doGet(String url, String param) {
        return http(url, param, "GET");
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String doPost(String url, String param) {
       return http(url, param, "POST");
    }


    public static String http(String url, String param, String method) {

        // 获取两种请求方式下的最终请求路径
        String lastUrl = "GET".equals(method) ? url + "?" +  param : url;
        // 返回结果
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            URL realUrl = new URL(lastUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 如果发送POST请求必须设置如下两行
            if ("POST".equals(method)){
                connection.setRequestMethod(method);
                connection.setDoOutput(true);
                connection.setDoInput(true);
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(connection.getOutputStream());
                // 发送请求参数
                out.print(param);
            }
            // 发送GET请求
            else if ("GET".equals(method)){
                connection.setRequestMethod(method);
            }
            // 建立实际的连接
            connection.connect();
            // 成功返回
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                // 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + " : " + map.get(key));
                }
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            }else {
                String errorInfo = "请求方式: " + connection.getRequestMethod()
                                + "\nHTTPResponseCode: " + connection.getResponseCode()
                                + "\nurl:" + url;
                throw new Exception(errorInfo);
            }
        } catch (Exception e) {
            System.out.println("发送请求出现异常:  " + e.getMessage());
            //e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
