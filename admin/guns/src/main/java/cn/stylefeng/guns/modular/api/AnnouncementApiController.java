package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.announcement.service.IAnnouncementService;
import cn.stylefeng.guns.modular.system.model.Announcement;
import cn.stylefeng.guns.modular.system.model.JsonAnnouBean;
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
 * 公告管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-22 21:30:01
 */
@Controller
@RequestMapping("/api/announcement")
public class AnnouncementApiController extends BaseController {


    @Autowired
    private IAnnouncementService announcementService;


    /**
     * 获取公告管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Announcement> announcements = announcementService.selectList(null);
        JsonAnnouBean jsonAnnouBean = new JsonAnnouBean();
        if (announcements != null) {
            jsonAnnouBean.setCode("200");
            jsonAnnouBean.setStatus("ok");
            jsonAnnouBean.setAnnouncements(announcements);
        }else{
            jsonAnnouBean.setCode("400");
            jsonAnnouBean.setStatus("error");
            jsonAnnouBean.setAnnouncements(announcements);
        }
        return jsonAnnouBean;
    }

}
