package pre.my.test.robot.dto.mass;

/**
 * Author:qiang.zeng on 2017/4/26.
 */
public class Filter {
    private boolean is_to_all;

    private String group_id;

    public Filter() {
    }

    public Filter(boolean is_to_all, String group_id) {
        this.is_to_all = is_to_all;
        this.group_id = group_id;
    }

    public boolean is_to_all() {
        return is_to_all;
    }

    public void setIs_to_all(boolean is_to_all) {
        this.is_to_all = is_to_all;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
