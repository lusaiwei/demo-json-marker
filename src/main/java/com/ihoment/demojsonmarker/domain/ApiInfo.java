package com.ihoment.demojsonmarker.domain;

/**
 * @author Saiwei.Lu
 * 2018/10/15
 */
public class ApiInfo {

    private String url;
    private String json;

    public ApiInfo(String url, String json) {
        this.url = url;
        this.json = json;
    }

    public ApiInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "ApiInfo{" +
                "url='" + url + '\'' +
                ", json='" + json + '\'' +
                '}';
    }
}
