package task.demo;

import com.cangoframework.task.TaskRunner;
import com.cangoonline.loan.batch.commons.TaskConsts;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by cango on 2018/1/5.
 */
public class MainTest {

    @Test
    public void testTask(){
//        TaskRunner.runTarget(TaskConsts.TASK_FILE_01,TaskConsts.TARGET_01_01);
        TaskRunner.runTask(TaskConsts.TASK_FILE_01);
    }

    @Test
    public void testMain(){
        String regex = "^[0-24]{2}:[0-59]{2}:[0-59]{2}$";
        System.out.println("23:95:00".matches(regex));

        SimpleDateFormat sf = new SimpleDateFormat("HH:mm:ss");
        try {
            Date parse = sf.parse("23:1511:00");

            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.asList("23:95:00".split("[:]", 3)));
        String[] split = "23:23:59".split("[:]", 3);
        if(Integer.parseInt(split[0])<=23&&
                Integer.parseInt(split[1])<=59&&
                Integer.parseInt(split[2])<=59){
            System.out.println("=========");
        }
    }


}
