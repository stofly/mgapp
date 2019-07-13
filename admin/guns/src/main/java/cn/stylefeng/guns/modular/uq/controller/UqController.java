package cn.stylefeng.guns.modular.uq.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Uq;
import cn.stylefeng.guns.modular.uq.service.IUqService;

/**
 * 错题集管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-22 21:31:44
 */
@Controller
@RequestMapping("/uq")
public class UqController extends BaseController {

    private String PREFIX = "/uq/uq/";

    @Autowired
    private IUqService uqService;

    /**
     * 跳转到错题集管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "uq.html";
    }

    /**
     * 跳转到添加错题集管理
     */
    @RequestMapping("/uq_add")
    public String uqAdd() {
        return PREFIX + "uq_add.html";
    }

    /**
     * 跳转到修改错题集管理
     */
    @RequestMapping("/uq_update/{uqId}")
    public String uqUpdate(@PathVariable Integer uqId, Model model) {
        Uq uq = uqService.selectById(uqId);
        model.addAttribute("item",uq);
        LogObjectHolder.me().set(uq);
        return PREFIX + "uq_edit.html";
    }

    /**
     * 获取错题集管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return uqService.selectList(null);
    }

    /**
     * 新增错题集管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Uq uq) {
        uqService.insert(uq);
        return SUCCESS_TIP;
    }

    /**
     * 删除错题集管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer uqId) {
        uqService.deleteById(uqId);
        return SUCCESS_TIP;
    }

    /**
     * 修改错题集管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Uq uq) {
        uqService.updateById(uq);
        return SUCCESS_TIP;
    }

    /**
     * 错题集管理详情
     */
    @RequestMapping(value = "/detail/{uqId}")
    @ResponseBody
    public Object detail(@PathVariable("uqId") Integer uqId) {
        return uqService.selectById(uqId);
    }
}
