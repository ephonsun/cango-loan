package com.cangoonline.loan.common;

/**
 * Created by cango on 2018/1/5.
 */
public class BjConstants {

    public static final String BJ_BASE_HOME = "";
    public static final String CG_BASE_HOME = "";

    public static final String BASE_PATH = "";

    //upload
    public static final String UPLOAD_LOANAPPLY = BASE_PATH + "upload/LoanApply/${date}/${creditNo}/LoanResult_1.txt";
    public static final String UPLOAD_REPAYMENTLISTREPORT = BASE_PATH + "upload/RepaymentListReport/${date}/RepaymentListReport.txt";
    public static final String UPLOAD_PREPAYMENT = BASE_PATH + "upload/Repayment/${date}/Repayment.txt";

    //download
    public static final String DOWNLOAD_LOANRESULT = BASE_PATH + "download/LoanResult/${date}/";
    public static final String DOWNLOAD_REPAYMENTLIST = BASE_PATH + "download/RepaymentList/${date}/";
    public static final String DOWNLOAD_REPAYMENTLISTREPORTRESULT = BASE_PATH + "download/RepaymentListReportResult/${date}/";
    public static final String DOWNLOAD_REPAYMENTRESULT = BASE_PATH + "download/RepaymentResult/${date}/";
    public static final String DOWNLOAD_PREPAYMENTREPORT = BASE_PATH + "download/PrepaymentReport/${date}/";
    public static final String DOWNLOAD_COMPPREPAYMENTLIST = BASE_PATH + "download/CompPrepaymentList/${date}/";

    //reupload
    public static final String REUPLOAD_FILEREUPLOAD = BASE_PATH + "reupload/FileReupload/${date}/${creditNo}/FileReupload.txt";


    //BJ返回的错误码
    public static final String RESULT_CODE_E0001 = "E0001";
    public static final String RESULT_CODE_E0002 = "E0002";
    public static final String RESULT_CODE_E0003 = "E0003";
    public static final String RESULT_CODE_E0004 = "E0004";
    public static final String RESULT_CODE_E0005 = "E0005";
    public static final String RESULT_CODE_E0006 = "E0006";
    public static final String RESULT_CODE_E0007 = "E0007";
    public static final String RESULT_CODE_E0008 = "E0008";
    public static final String RESULT_CODE_E0009 = "E0009";
    public static final String RESULT_CODE_E0010 = "E0010";

    public static final String RESULT_MESSAGE_E0001 = "登录超时";
    public static final String RESULT_MESSAGE_E0002 = "accessToken不正确或accessToken超时";
    public static final String RESULT_MESSAGE_E0003 = "接口内容为空";
    public static final String RESULT_MESSAGE_E0004 = "接口不符合规范";
    public static final String RESULT_MESSAGE_E0005 = "接口参数错误";
    public static final String RESULT_MESSAGE_E0006 = "认证平台内部错误";
    public static final String RESULT_MESSAGE_E0007 = "数据服务无响应";
    public static final String RESULT_MESSAGE_E0008 = "数据服务结果异常";
    public static final String RESULT_MESSAGE_E0009 = "总行系统无响应";
    public static final String RESULT_MESSAGE_E0010 = "总行系统处理结果异常";


    public static final String RESULT_CODE_0 = "0";
    public static final String RESULT_MESSAGE_0 = "成功";
    public static final String RESULT_CODE_1 = "1";
    public static final String RESULT_MESSAGE_1 = "文件无法获取";
    public static final String RESULT_CODE_2 = "2";
    public static final String RESULT_MESSAGE_2 = "文件MD5校验不一致";
    public static final String RESULT_CODE_3 = "3";
    public static final String RESULT_MESSAGE_3 = "文件内容数据错误";
    public static final String RESULT_CODE_99 = "99";
    public static final String RESULT_MESSAGE_99 = "系统异常";

}
