package xlr.com.mgapp.constants;

/**
 * @ 描述：配置常量
 */

public class Config {
    //是否开启调试模式
    public static final boolean DEBUG = true;
    //Log 打印的 tag
    public static final String LogTag = "xxx";

    //主机 host
    public static final String HOST = "http://101.132.145.89/api/";
//    public static final String HOST = "http://10.6.24.118/api/";
    //登录网址
    public static final String URL_LOGIN = HOST + "userinfo/login";
    //注册网址
    public static final String URL_SIGNUP = HOST + "userinfo/sign";

    //获取问题网址
    public static final String URL_GET_QUESTION = HOST + "question/list";
    //获取公告
//    public static final String URL_GET_ANNO = HOST + "announcement/list";
    //获取时评
    public static final String URL_GET_Review = HOST + "review/list";
    //收藏添加
    public static final String URL_GET_Collect = HOST + "uq/add";
    //收藏删除
    public static final String URL_GET_noCollect = HOST + "uq/delete";
    //收藏展示
    public static final String URL_GET_CollectL = HOST + "uq/list";
    //版本更新
    public static final String URL_GET_Version = HOST + "appversion/list";
}