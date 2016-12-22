package pre.my.test.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Author:qiang.zeng@hand-china.com on 2016/12/22.
 */
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
          System.out.println(context.getTrigger().getFinalFireTime() + "triggered time:" + (new Date()));
    }
}
