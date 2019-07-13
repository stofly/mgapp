package cn.stylefeng.guns.modular.system.model;

import java.util.List;


public class JsonVersionBean {

    private String status;
    private String code;
    private Appversion appversion;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Appversion getAppversion() {
        return appversion;
    }

    public void setAppversion(Appversion appversion) {
        this.appversion = appversion;
    }
}
