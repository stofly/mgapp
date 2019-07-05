package xlr.com.mgapp.bean;

import java.util.List;

/**
 * @author 青铜骑士
 * @ClassName: AnnListBean
 * @ProjectName mgapp
 * @Description: TODO
 * @date 2019/6/2222:00
 */
public class AnnListBean {

    private String status;
    private String code;
    private List<AnnouncementBean> announcements;

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

    public List<AnnouncementBean> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<AnnouncementBean> announcements) {
        this.announcements = announcements;
    }
}
