package xlr.com.mgapp.bean;

import java.util.List;


public class ReviewListBean {

    private String status;
    private String code;
    private List<ReviewBean> reviews;

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

    public List<ReviewBean> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewBean> reviews) {
        this.reviews = reviews;
    }
}
