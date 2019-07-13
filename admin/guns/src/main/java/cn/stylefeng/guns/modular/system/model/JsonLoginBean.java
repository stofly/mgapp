package cn.stylefeng.guns.modular.system.model;

/**
 * @author 青铜骑士
 * @ClassName: JsonLoginBean
 * @ProjectName guns
 * @Description: TODO
 * @date 2019/6/2221:43
 */
public class JsonLoginBean {

    /**
     * username : 001
     * flage : 1、登录成功，2.用或密码错误
     */

    private String username;
    private int flage;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFlage() {
        return flage;
    }

    public void setFlage(int flage) {
        this.flage = flage;
    }
}
