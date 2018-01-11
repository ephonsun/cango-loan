package com.cangoonline.loan.batch.task.units.bjbank;

import com.cangoframework.task.ExecuteUnit;
import com.cangoframework.task.TaskConstants;

/**
 * Created by huangchengkang on 2018/1/10.
 * 财务放款单元 - 目前针对北京的放款
 */
public class FinancePutoutUnit extends ExecuteUnit {
    @Override
    public int execute() {

        //1.从putout_info中取出待财务放款的放款数据

        //2.生成放款文件通过邮件的形式给财务专员
        //TODO 文件格式、邮件地址、邮件格式暂定

        //TODO 针对处理LoanResult中某些异常情况的放款数据（放款处理失败），怎么处理

        return TaskConstants.ES_SUCCESSFUL;
    }
}
