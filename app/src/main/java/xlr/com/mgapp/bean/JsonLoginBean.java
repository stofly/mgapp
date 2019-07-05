package xlr.com.mgapp.bean;

/**
 * @ 描述：登录jsonbean类
 */

public class JsonLoginBean {

    /**
     * username : 001
     * flage : 1、登录成功
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
