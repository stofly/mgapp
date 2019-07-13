package cn.stylefeng.guns.modular.system.model;

import java.util.List;

/**
 * @author 青铜骑士
 * @ClassName: JsonAnnouBean
 * @ProjectName guns
 * @Description: TODO
 * @date 2019/6/2222:15
 */
public class JsonReviewBean {
    private String status;
    private String code;
    private List<Review> reviews;

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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
