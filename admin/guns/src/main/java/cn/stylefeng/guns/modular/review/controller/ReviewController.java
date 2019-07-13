package cn.stylefeng.guns.modular.review.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Review;
import cn.stylefeng.guns.modular.review.service.IReviewService;

/**
 * 时评管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-24 10:47:54
 */
@Controller
@RequestMapping("/review")
public class ReviewController extends BaseController {

    private String PREFIX = "/review/review/";

    @Autowired
    private IReviewService reviewService;

    /**
     * 跳转到时评管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "review.html";
    }

    /**
     * 跳转到添加时评管理
     */
    @RequestMapping("/review_add")
    public String reviewAdd() {
        return PREFIX + "review_add.html";
    }

    /**
     * 跳转到修改时评管理
     */
    @RequestMapping("/review_update/{reviewId}")
    public String reviewUpdate(@PathVariable Integer reviewId, Model model) {
        Review review = reviewService.selectById(reviewId);
        model.addAttribute("item",review);
        LogObjectHolder.me().set(review);
        return PREFIX + "review_edit.html";
    }

    /**
     * 获取时评管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return reviewService.selectList(null);
    }

    /**
     * 新增时评管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Review review) {
        reviewService.insert(review);
        return SUCCESS_TIP;
    }

    /**
     * 删除时评管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer reviewId) {
        reviewService.deleteById(reviewId);
        return SUCCESS_TIP;
    }

    /**
     * 修改时评管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Review review) {
        reviewService.updateById(review);
        return SUCCESS_TIP;
    }

    /**
     * 时评管理详情
     */
    @RequestMapping(value = "/detail/{reviewId}")
    @ResponseBody
    public Object detail(@PathVariable("reviewId") Integer reviewId) {
        return reviewService.selectById(reviewId);
    }
}
