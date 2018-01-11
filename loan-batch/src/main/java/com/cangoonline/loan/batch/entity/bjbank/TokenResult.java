package com.cangoonline.loan.batch.entity.bjbank;

/**
 * Created by cango on 2018/1/11.
 */
public class TokenResult {

    private String result;
    private String accessToken;
    private String description;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
