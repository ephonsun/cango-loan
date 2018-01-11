package com.cangoonline.loan.controller.bjbank;

import com.alibaba.fastjson.JSONObject;
import com.cangoframework.api.apps.reader.FileDataReader;
import com.cangoframework.api.apps.reader.IDataReader;
import com.cangoframework.api.apps.sftp.SftpConfig;
import com.cangoframework.api.service.SftpServiceApi;
import com.cangoframework.base.exception.ResultException;
import com.cangoframework.task.TaskRunner;
import com.cangoonline.loan.batch.commons.TaskConsts;
import com.cangoonline.loan.common.BjConstants;
import com.cangoonline.loan.controller.base.BaseController;
import com.cangoonline.loan.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cango on 2018/1/5.
 */
@Controller("bjloan")
public class BjLoanController extends BaseController {

    @Autowired("bjSftpConfig")
    private SftpConfig bjSftpConfig;

    @ResponseBody
    @RequestMapping(value = "/loadResult", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String loadResult(@RequestParam Long channelId, @RequestParam String timestamp,
                             @RequestParam String fileURL, @RequestParam String MD5, @RequestParam String fileVersion) {

        JSONObject responseJson = null;
        try {
            //校验输入参数的正确性

            //登录

            //下载每日放款结果文件 有多个文件时地址如：LoanResult_1.txt地址多个以分号间隔
            List<String> filePathList = downLoadFile(BjConstants.DOWNLOAD_LOANRESULT,BjConstants.DOWNLOAD_LOANRESULT,fileURL.trim());

            //用MD5校验数据的完整性
            String[] md5Array = MD5.split(",");
            if(md5Array.length!=filePathList.size()){
                throw new ResultException(BjConstants.RESULT_CODE_2,BjConstants.RESULT_MESSAGE_2);
            }
            for (int i = 0; i < filePathList.size(); i++) {
                String md5 = MD5Utils.md5(filePathList.get(i));
                if(!md5.equalsIgnoreCase(md5Array[i])){
                    throw new ResultException(BjConstants.RESULT_CODE_2,BjConstants.RESULT_MESSAGE_2);
                }
            }

            //读文件-存到表中
            String filePath = "";
            FileDataReader.readData(new IDataReader() {
                @Override
                public void readLine(int index, String content, List<String> list) {
                    //校验数据


                }
            }, filePath, "UTF-8", "|", null, null, true);

            //异步执行
            TaskRunner.runTarget(TaskConsts.TASK_FILE_01,TaskConsts.TARGET_01_01,true);

            //返回结果
            throw new ResultException(BjConstants.RESULT_CODE_0,BjConstants.RESULT_MESSAGE_0);

        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof ResultException){
                ResultException er = (ResultException) e;
                responseJson = getResponseJson(er);
            }else{
                responseJson = getResponseJson(BjConstants.RESULT_CODE_99,BjConstants.RESULT_MESSAGE_99);
            }
        }
        return responseJson.toJSONString();
    }

    /**
     * 下载文件，返回文件列表
     * @param localBaseURL
     * @param remoteBaseURL
     * @param fileURL
     * @return
     */
    private List<String> downLoadFile(String localBaseURL,String remoteBaseURL,String fileURL) throws Exception {
        List<String> list = new ArrayList<String>();

        localBaseURL = localBaseURL.replace("date","");
        remoteBaseURL = remoteBaseURL.replace("date","");
        String[] files = fileURL.split(",");
        for (String filePath:files){
            if(!StringUtils.isEmpty(filePath)){
                String localPath = localBaseURL+filePath;
                String remotePath = remoteBaseURL+filePath;
                boolean result = SftpServiceApi.downloadFile(localPath, remotePath, bjSftpConfig);
                if(result){
                    list.add(localPath);
                }else{
                    throw new ResultException(BjConstants.RESULT_CODE_1,BjConstants.RESULT_MESSAGE_1);
                }
            }
        }
        return list;
    }

    private JSONObject getResponseJson(ResultException e) {
        return getResponseJson(e.getResult(),e.getDescription());
    }
    private JSONObject getResponseJson(Object result, Object description) {
        JSONObject json = new JSONObject();
        json.put("result", result);
        json.put("description", description);
        return json;
    }

}
