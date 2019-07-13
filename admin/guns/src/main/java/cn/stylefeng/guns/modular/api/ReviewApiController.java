package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.review.service.IReviewService;
import cn.stylefeng.guns.modular.system.model.JsonReviewBean;
import cn.stylefeng.guns.modular.system.model.Review;
import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 时评管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-24 10:47:54
 */
@Controller
@RequestMapping("/api/review")
public class ReviewApiController extends BaseController {



    @Autowired
    private IReviewService reviewService;


    /**
     * 获取时评管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Review> reviews = reviewService.selectList(null);
        JsonReviewBean jsonReviewBean = new JsonReviewBean();
        if (reviews != null) {
            jsonReviewBean.setCode("200");
            jsonReviewBean.setStatus("ok");
            jsonReviewBean.setReviews(reviews);
        }else{
            jsonReviewBean.setCode("400");
            jsonReviewBean.setStatus("error");
            jsonReviewBean.setReviews(reviews);
        }
        return jsonReviewBean;
    }
}
