package cn.stylefeng.guns.modular.userinfo.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Userinfo;
import cn.stylefeng.guns.modular.userinfo.service.IUserinfoService;

/**
 * 体验者管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-22 21:32:26
 */
@Controller
@RequestMapping("/userinfo")
public class UserinfoController extends BaseController {

    private String PREFIX = "/userinfo/userinfo/";

    @Autowired
    private IUserinfoService userinfoService;

    /**
     * 跳转到体验者管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userinfo.html";
    }

    /**
     * 跳转到添加体验者管理
     */
    @RequestMapping("/userinfo_add")
    public String userinfoAdd() {
        return PREFIX + "userinfo_add.html";
    }

    /**
     * 跳转到修改体验者管理
     */
    @RequestMapping("/userinfo_update/{userinfoId}")
    public String userinfoUpdate(@PathVariable Integer userinfoId, Model model) {
        Userinfo userinfo = userinfoService.selectById(userinfoId);
        model.addAttribute("item",userinfo);
        LogObjectHolder.me().set(userinfo);
        return PREFIX + "userinfo_edit.html";
    }

    /**
     * 获取体验者管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return userinfoService.selectList(null);
    }

    /**
     * 新增体验者管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Userinfo userinfo) {
        userinfoService.insert(userinfo);
        return SUCCESS_TIP;
    }

    /**
     * 删除体验者管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer userinfoId) {
        userinfoService.deleteById(userinfoId);
        return SUCCESS_TIP;
    }

    /**
     * 修改体验者管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Userinfo userinfo) {
        userinfoService.updateById(userinfo);
        return SUCCESS_TIP;
    }

    /**
     * 体验者管理详情
     */
    @RequestMapping(value = "/detail/{userinfoId}")
    @ResponseBody
    public Object detail(@PathVariable("userinfoId") Integer userinfoId) {
        return userinfoService.selectById(userinfoId);
    }
}
