package cn.stylefeng.guns.modular.announcement.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Announcement;
import cn.stylefeng.guns.modular.announcement.service.IAnnouncementService;

/**
 * 公告管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-22 21:30:01
 */
@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends BaseController {

    private String PREFIX = "/announcement/announcement/";

    @Autowired
    private IAnnouncementService announcementService;

    /**
     * 跳转到公告管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "announcement.html";
    }

    /**
     * 跳转到添加公告管理
     */
    @RequestMapping("/announcement_add")
    public String announcementAdd() {
        return PREFIX + "announcement_add.html";
    }

    /**
     * 跳转到修改公告管理
     */
    @RequestMapping("/announcement_update/{announcementId}")
    public String announcementUpdate(@PathVariable Integer announcementId, Model model) {
        Announcement announcement = announcementService.selectById(announcementId);
        model.addAttribute("item",announcement);
        LogObjectHolder.me().set(announcement);
        return PREFIX + "announcement_edit.html";
    }

    /**
     * 获取公告管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return announcementService.selectList(null);
    }

    /**
     * 新增公告管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Announcement announcement) {
        announcementService.insert(announcement);
        return SUCCESS_TIP;
    }

    /**
     * 删除公告管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer announcementId) {
        announcementService.deleteById(announcementId);
        return SUCCESS_TIP;
    }

    /**
     * 修改公告管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Announcement announcement) {
        announcementService.updateById(announcement);
        return SUCCESS_TIP;
    }

    /**
     * 公告管理详情
     */
    @RequestMapping(value = "/detail/{announcementId}")
    @ResponseBody
    public Object detail(@PathVariable("announcementId") Integer announcementId) {
        return announcementService.selectById(announcementId);
    }
}
