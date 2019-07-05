package xlr.com.mgapp.bean;

import java.io.Serializable;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-22
 */
public class ReviewBean {

    /**
     * 编号
     */
    private Integer id;
    /**
     * 时评标题
     */
    private String rtitle;
    /**
     * 时评作者
     */
    private String rauthor;
    /**
     * 时评来源
     */
    private String fromsour;
    /**
     * 时评时间
     */
    private String rdate;
    /**
     * 时评顺序
     */
    private Integer rorder;
    /**
     * 时评链接
     */
    private String rurl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRtitle() {
        return rtitle;
    }

    public void setRtitle(String rtitle) {
        this.rtitle = rtitle;
    }

    public String getRauthor() {
        return rauthor;
    }

    public void setRauthor(String rauthor) {
        this.rauthor = rauthor;
    }

    public String getFromsour() {
        return fromsour;
    }

    public void setFromsour(String fromsour) {
        this.fromsour = fromsour;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public Integer getRorder() {
        return rorder;
    }

    public void setRorder(Integer rorder) {
        this.rorder = rorder;
    }

    public String getRurl() {
        return rurl;
    }

    public void setRurl(String rurl) {
        this.rurl = rurl;
    }


    @Override
    public String toString() {
        return "Review{" +
                ", id=" + id +
                ", rtitle=" + rtitle +
                ", rauthor=" + rauthor +
                ", fromsour=" + fromsour +
                ", rdate=" + rdate +
                ", rorder=" + rorder +
                ", rurl=" + rurl +
                "}";
    }
}
