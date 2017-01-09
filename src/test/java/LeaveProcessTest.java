import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * Author:qiang.zeng@hand-china.com on 2017/1/4.
 */
public class LeaveProcessTest {
    @Test
    public void test() throws UnsupportedEncodingException, FileNotFoundException {
        //获取流程引擎
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml")
                .buildProcessEngine();
      /*  ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();*/
        Assert.assertNotNull(processEngine);
        //部署流程
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("leave2.bpmn20.xml").deploy();
        //查询流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .singleResult();
        Assert.assertEquals("leave2", processDefinition.getKey());

        //获取流程运行信息
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("applyUser", "employee");
        param.put("days", 3);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave2",param);
        Assert.assertNotNull(processInstance);
        System.out.println("pid=" + processInstance.getId() + ",pdid=" + processInstance.getProcessDefinitionId());

        TaskService taskService = processEngine.getTaskService();
        Task taskOfLeader = taskService.createTaskQuery().taskCandidateGroup("leader").singleResult();
        Assert.assertNotNull(taskOfLeader);
        Assert.assertEquals("leader approve", taskOfLeader.getName());
        taskService.claim(taskOfLeader.getId(), "leaderUser");

        param = new HashMap<String, Object>();
        param.put("approved", true);
        taskService.complete(taskOfLeader.getId(), param);
        taskOfLeader = taskService.createTaskQuery().taskCandidateGroup("leader").singleResult();
      //  Assert.assertNotNull(taskOfLeader);

        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished().count();
        Assert.assertEquals(1,count);







    }
}
