package com.jhilgedick.data;

public class MyGlobalVars {

    private Long responseTimeMS = 0L;
    private Long cpuBurnTimeMS = 0L;
    private Integer responseCode = 200;
    private Integer healthCheck = 200;
    private String responseBody = "";

    private MyGlobalVars() {
    }

    private static class MyInnerSingleton {
        private static final MyGlobalVars INSTANCE = new MyGlobalVars();
    }
 
    public static MyGlobalVars getInstance() {
        return MyInnerSingleton.INSTANCE;
    }

    public void setResponseTimeMS(Long responseTimeMS) {
        this.responseTimeMS = responseTimeMS;
    }

    public void setCpuBurnTimeMS(Long cpuBurnTimeMS) {
        this.cpuBurnTimeMS = cpuBurnTimeMS;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public void setHealthCheck(Integer healthCheck) {
        this.healthCheck = healthCheck;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Long getResponseTimeMS() {
        return this.responseTimeMS;
    }

    public Long getCpuBurnTimeMS() {
        return this.cpuBurnTimeMS;
    }

    public Integer getResponseCode() {
        return this.responseCode;
    }

    public Integer getHealthCheck() {
        return this.healthCheck;
    }

    public String getResponseBody() {
        return this.responseBody;
    }
}