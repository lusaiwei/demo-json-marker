package com.ihoment.demojsonmarker.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Saiwei.Lu
 * 2018/10/15
 */
public interface Const {
    String SAVE_PATH = "/Users/saiwei/Desktop/temp";

    String HOST_REG = "https://[^\\/]*/";
    String URL_PARAMS_REG = "\\?\\S*";

    Map<String,String> SENSITIVE_DATA = new HashMap<String,String>(){{
        put("\"imageUrl\":\"[^\"]*\"","\"imageUrl\":\"https://images-na.ssl-images-amazon.com/images/G/01/kindle/merch/2017/ECHO/FSCompProd/fs-ro-224x225-v.3._CB501064883_.png\"");
        put("\"listingUrl\":\"[^\"]*\"","\"listingUrl\":\"https://www.amazon.fr/dp/B06XY151YV\"");
        put("\"sku\":\"[^\"]*\"","\"sku\":\"H********\"");
        put("\"asin\":\"[^\"]*\"","\"asin\":\"B07HVYR91K\"");
        put("\"title\":\"[^\"]*\"","\"title\":\"示例商品\"");
        put("\"shopName\":\"[^\"]*\"","\"shopName\":\"Amazon_US\"");
        put("\"shopNames\":\\[[^\\]]*\\]","\"shopNames\":[\"Amazon_US\"]");
    }};
}
