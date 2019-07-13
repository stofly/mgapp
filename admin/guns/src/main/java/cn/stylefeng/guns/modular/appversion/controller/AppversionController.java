package cn.stylefeng.guns.modular.appversion.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Appversion;
import cn.stylefeng.guns.modular.appversion.service.IAppversionService;

/**
 * 应用版本管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-26 21:30:16
 */
@Controller
@RequestMapping("/appversion")
public class AppversionController extends BaseController {

    private String PREFIX = "/appversion/appversion/";

    @Autowired
    private IAppversionService appversionService;

    /**
     * 跳转到应用版本管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "appversion.html";
    }

    /**
     * 跳转到添加应用版本管理
     */
    @RequestMapping("/appversion_add")
    public String appversionAdd() {
        return PREFIX + "appversion_add.html";
    }

    /**
     * 跳转到修改应用版本管理
     */
    @RequestMapping("/appversion_update/{appversionId}")
    public String appversionUpdate(@PathVariable Integer appversionId, Model model) {
        Appversion appversion = appversionService.selectById(appversionId);
        model.addAttribute("item",appversion);
        LogObjectHolder.me().set(appversion);
        return PREFIX + "appversion_edit.html";
    }

    /**
     * 获取应用版本管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return appversionService.selectList(null);
    }

    /**
     * 新增应用版本管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Appversion appversion) {
        appversionService.insert(appversion);
        return SUCCESS_TIP;
    }

    /**
     * 删除应用版本管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer appversionId) {
        appversionService.deleteById(appversionId);
        return SUCCESS_TIP;
    }

    /**
     * 修改应用版本管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Appversion appversion) {
        appversionService.updateById(appversion);
        return SUCCESS_TIP;
    }

    /**
     * 应用版本管理详情
     */
    @RequestMapping(value = "/detail/{appversionId}")
    @ResponseBody
    public Object detail(@PathVariable("appversionId") Integer appversionId) {
        return appversionService.selectById(appversionId);
    }
}
