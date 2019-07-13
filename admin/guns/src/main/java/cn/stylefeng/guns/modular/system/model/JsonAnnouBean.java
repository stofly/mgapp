package cn.stylefeng.guns.modular.system.model;

import java.util.List;

/**
 * @author 青铜骑士
 * @ClassName: JsonAnnouBean
 * @ProjectName guns
 * @Description: TODO
 * @date 2019/6/2222:15
 */
public class JsonAnnouBean {
    private String status;
    private String code;
    private List<Announcement> announcements;

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

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }
}
