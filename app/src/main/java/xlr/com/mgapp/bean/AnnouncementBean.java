package xlr.com.mgapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-22
 */
public class AnnouncementBean{

    private Integer id;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 公告热度
     */
    private Integer hot;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "Announcement{" +
        ", id=" + id +
        ", content=" + content +
        ", hot=" + hot +
        "}";
    }
}
