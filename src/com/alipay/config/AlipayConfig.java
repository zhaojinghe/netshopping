package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101000651749";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCfHmP/yUw/wOTJX7vBsTh/dmWXcfzdSXD7QPnIX/IrhZV72z7eOya62SO2A5tuGtZrZNMpxEFqEg8I701Jp/Ps2yzP1MzKzy9NS2airIzJEm5kutMktIchOEZP89khntWDjWDlFpQCRdBhH7rkO78WtWam2J++KbqXrAeYYFcLjAFn6gSEAJFZQNZgYIaK4fu8h68Zb1mM5Ols1mF1NF7C2zuPTOmjalEMXt42PKbjjp2iaoaMKF6yYLumMehBoLRuoBZ5POXVIxJixwj+KSe4AwLGIc6G78O1yDc+2LJJAxn+j0IEgADfGJj8tHmd/kvzto075ZGKj6CRGuhdECnjAgMBAAECggEAXhqUG4eKb7390NasbR4JguCbrph8asbvabkAwK91wqWLRafho36qJiEbTJ8Pgmue2f7UH8Rwx3GMsMUOGXyZ9z+cXgHFaEBmAjmQpq6TyKr83Nfc3b1imZP1iX12sbqFHfs0DehvenSdi4M/fZGULrLz1cMRnl6BewgDqet4L1wbL1JWylsImc04Gd4GKLiibAi6XzCPIMGPUvlez8IsTSRRUUiCZf+5J1WIseeqwtIZXrXbl8k4F1Zha9OO0ylf0ONF79woiGWr2wCOgRDp7J36yMVaklP+RY14/7/yQKUIG0amqGiX2A7zlcrXmF0EHaLMF/qxgRV9IQ3iJ5SaqQKBgQDb/gSDG86q889vEn3oEjJqgVdTYaPwgNlsR4Azytf0zz4FuYNIVwibYgg3iXCINOTy5birrNgjiRj8c7JoFJXBLJVTW5sp/WqFtXH7avwu8U+nuqP32fh9+qV0DisMhCnSQeUXQkAsBArKDR92KGi2zBKRg/cIORAj5N1AVFSiBQKBgQC5Ka/HxW5cl6Q8UaEf3ruvtxApyQpcFgv6Ij9fffuPGHjY4VB4uJN2pcG5I7JZhAJWMgXjKEC0KN/OjxX+/FElvoY6ozBuL1qFLsJtV5OlMayrN48EBUAHN+yXhgi1ovvEIcfvklyuGa7g0txvwI8t5wgmDQY7TXMAltjxdurYxwKBgQClmkRfRbF/cuV14V+yMPCOyCqqc9frFbOWFQTm+A01FkTEHnA7JnkuLs+sruNtRRQL3O31Hbneljee4qEsREKvFhaal1zocQGdAYd6EYEg/TpPMup3izfJ7cJa6MdzNpIc2snPhmm0OJ6+lr5WPfkFkf1wWPy+zeXef0wmPtUu2QKBgFMqgiV8bAFyqvXcxD4IUMyvsLbT8a9Cx8Zls4k0kNTI7R6ctQREAxf7g3OgsO67+EC23yYUYluJMF0ar4qD0f7eAHQ97/bNlh7TzchG2a4NDb0pizcHFKcpWz8DmM3ckrCZa7ATUUVxi7a3fwNCvgykBGjy2KWyN8E//9kUzI7pAoGBAKMoeZAPMbH6BUPLE614F2ZTyl8MDgPDPAnfWQuV8XdtMeTiPi7aMiEqKPgE0GFAL6T+b0Z1JF6aTLfpTdI7Mz4/4xIafYXV153usMtxDALX4RxkkNprwgZNawogT4Xj4p03hClIWd5aYcZBkttmTOeMH59n0xGHdj9JPc7BKPHI";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnx5j/8lMP8DkyV+7wbE4f3Zll3H83Ulw+0D5yF/yK4WVe9s+3jsmutkjtgObbhrWa2TTKcRBahIPCO9NSafz7Nssz9TMys8vTUtmoqyMyRJuZLrTJLSHIThGT/PZIZ7Vg41g5RaUAkXQYR+65Du/FrVmptifvim6l6wHmGBXC4wBZ+oEhACRWUDWYGCGiuH7vIevGW9ZjOTpbNZhdTRewts7j0zpo2pRDF7eNjym446domqGjChesmC7pjHoQaC0bqAWeTzl1SMSYscI/iknuAMCxiHOhu/Dtcg3PtiySQMZ/o9CBIAA3xiY/LR5nf5L87aNO+WRio+gkRroXRAp4wIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8888/mimall/index";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8888/mimall/index";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

