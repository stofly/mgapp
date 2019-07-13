package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.appversion.service.IAppversionService;
import cn.stylefeng.guns.modular.system.model.Appversion;
import cn.stylefeng.guns.modular.system.model.JsonVersionBean;
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
 * 应用版本管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-26 21:30:16
 */
@Controller
@RequestMapping("/api/appversion")
public class AppversionApiController extends BaseController {


    @Autowired
    private IAppversionService appversionService;

    /**
     * 获取应用版本管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        List<Appversion> appversions = appversionService.selectList(null);
        JsonVersionBean jsonVersionBean = new JsonVersionBean();
        if(appversions.size()!=0){
            jsonVersionBean.setCode("200");
            jsonVersionBean.setStatus("ok");
            jsonVersionBean.setAppversion(appversions.get(0));
        }else{
            jsonVersionBean.setCode("400");
            jsonVersionBean.setStatus("error");
            jsonVersionBean.setAppversion(null);
        }
        return  jsonVersionBean;
    }
}
