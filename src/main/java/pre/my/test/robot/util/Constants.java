package pre.my.test.robot.util;

/**
 * 字符串常量类
 * Author:qiang.zeng on 2017/1/18.
 */
public class Constants {
    /**
     * 消息类型：文本
     */
    public static final String MSG_TYPE_TEXT = "text";
    /**
     * 消息类型：图片
     */
    public static final String MSG_TYPE_IMAGE = "image";
    /**
     * 消息类型：音频
     */
    public static final String MSG_TYPE_VOICE = "voice";
    /**
     * 消息类型：视频
     */
    public static final String MSG_TYPE_VIDEO = "video";
    /**
     * 消息类型：小视频
     */
    //  public static final String MSG_TYPE_SHORT_VIDEO = "shortvideo";
    /**
     * 消息类型：推送
     */
    public static final String MSG_TYPE_EVENT = "event";
    /**
     * 消息类型：链接
     */
    public static final String MSG_TYPE_LINK = "link";
    /**
     * 消息类型：地理位置
     */
    public static final String MSG_TYPE_LOCATION = "location";
    /**
     * 消息类型：订阅
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    /**
     * 消息类型：取消订阅
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
    /**
     * 消息类型：自定义菜单点击事件
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";
    /**
     * 公众号id
     */
    public static final String APP_ID = "wx184d93756c4fd72b";
    /**
     * 公众号secret
     */
    public static final String APP_SECRET = "7b57e03f62dcfad77a78a9cf78cc014e";
    /**
     * 接口调用凭据
     */
    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    /**
     * 创建菜单接口url
     */
    public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * 查询菜单接口url
     */
    public static final String MENU_QUERY_URL = "  https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    /**
     * 删除菜单接口url
     */
    public static final String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    /**
     * click：点击推事件
     */
    public static final String MENU_TYPE_CLICK = "click";
    /**
     * view：跳转URL
     */
    public static final String MENU_TYPE_VIEW = "view";

    /**
     * scancode_push：扫码推事件
     */
    public static final String MENU_TYPE_SCAN_CODE_PUSH = "scancode_push";
    /**
     * scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框
     */
    public static final String MENU_TYPE_SCAN_CODE_WAIT_MSG = "scancode_waitmsg";
    /**
     * pic_sysphoto：弹出系统拍照发图
     */
    public static final String MENU_TYPE_PIC_SYSPHOTO = "pic_sysphoto";
    /**
     * pic_photo_or_album：弹出拍照或者相册发图
     */
    public static final String MENU_TYPE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    /**
     * pic_weixin：弹出微信相册发图器
     */
    public static final String MENU_TYPE_PIC_WEIXIN = "pic_weixin";
    /**
     * location_select：弹出地理位置选择器
     */
    public static final String MENU_TYPE_LOCATION_SELECT = "location_select";
    /**
     * media_id：下发消息（除文本消息）
     */
    public static final String MENU_TYPE_MEDIA_ID = "click";
    /**
     * view_limited：跳转图文消息URL
     */
    public static final String MENU_TYPE_VIEW_LIMITED = "click";
}
