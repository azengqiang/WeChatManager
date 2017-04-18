package pre.my.test.robot.dto.user;

/**
 * Author:qiang.zeng on 2017/4/10.
 */
public class GroupTree {
    private String text;
    private String[] tags;
    private GroupTree[] nodes;

    public GroupTree() {

    }

    public GroupTree(String text, String[] tags, GroupTree[] nodes) {
        this.text = text;
        this.tags = tags;
        this.nodes = nodes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public GroupTree[] getNodes() {
        return nodes;
    }

    public void setNodes(GroupTree[] nodes) {
        this.nodes = nodes;
    }
}
