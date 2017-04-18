package pre.my.test.robot.dto.user;

/**
 * Author:qiang.zeng on 2017/4/17.
 */
public class GroupOperate {

    private Group group;

    public GroupOperate(){

    }
    public GroupOperate(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
