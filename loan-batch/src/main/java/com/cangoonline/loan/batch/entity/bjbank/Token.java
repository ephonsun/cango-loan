package com.cangoonline.loan.batch.entity.bjbank;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cangoframework.base.utils.DateUtils;
import com.cangoframework.base.utils.HttpUtils;
import com.cangoonline.loan.batch.commons.BjBankConsts;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by cango on 2018/1/11.
 */
public class Token {
    private String channelId = BjBankConsts.CHANNEL_ID;
    private String timestamp = DateUtils.YYYYMMDDHHMMSS.format(new Date());
    private String secretKey = BjBankConsts.TOKEN_PWD + timestamp;

    private Token() {
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public static TokenResult getToken() throws IOException {
        Token token = new Token();
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(token));
        String result = HttpUtils.sendPost("", jsonObject);
        return JSONObject.parseObject(result, TokenResult.class);
    }

}
