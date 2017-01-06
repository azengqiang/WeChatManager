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
        //�����������棬ʹ���ڴ����ݿ�
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();
        //�������̶����ļ�
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("leave.bpmn20.xml").deploy();
        //��֤�Ѳ�������̶���
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .singleResult();
        Assert.assertEquals("leave" , processDefinition.getKey());
        //�������̲���������ʵ��
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave");
        Assert.assertNotNull(processInstance);
        System.out.println("pid="+processInstance.getId()+",pdid="+processInstance.getProcessDefinitionId());

    }
}
