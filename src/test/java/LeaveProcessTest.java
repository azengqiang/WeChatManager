import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.Assert;


/**
 * Author:qiang.zeng@hand-china.com on 2017/1/4.
 */
public class LeaveProcessTest {
    @Test
    public void test() {
        //创建流程引擎，使用内存数据库
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();
        //部署流程定义文件
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("leave.bpmn20.xml").deploy();
        //验证已部署的流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .singleResult();
        Assert.assertEquals("leave" , processDefinition.getKey());
        //启动流程并返回流程实例
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave");
        Assert.assertNotNull(processInstance);
        System.out.println("pid="+processInstance.getId()+",pdid="+processInstance.getProcessDefinitionId());

    }
}
