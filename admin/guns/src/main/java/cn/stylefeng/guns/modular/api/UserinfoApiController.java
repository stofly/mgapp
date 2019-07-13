package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.modular.system.model.JsonLoginBean;
import cn.stylefeng.guns.modular.system.model.JsonSignupBean;
import cn.stylefeng.guns.modular.system.model.Userinfo;
import cn.stylefeng.guns.modular.userinfo.service.IUserinfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * App用户管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-15 09:41:34
 */
@RestController
@RequestMapping("/api/userinfo")
public class UserinfoApiController extends BaseController {

    @Autowired
    private IUserinfoService userinfoService;

    /**
     * App用户注册
     * 1、先查询注册的用户的学号是否已经注册过了，如果注册过了就显示该用户已经注册
     * 2、如果没有注册，则返回注册成功
     */
    @RequestMapping(value = "/sign")
    @ResponseBody
    public Object sign(String username, String password) {
        JsonSignupBean jsonSignupBean = new JsonSignupBean();
        EntityWrapper<Userinfo> entityWrapper=new EntityWrapper<>();
        entityWrapper.where("username={0}",username);
        List<Userinfo> userinfos = userinfoService.selectList(entityWrapper);
        if(!userinfos.isEmpty()){
            jsonSignupBean.setMsg("exsit");
            jsonSignupBean.setUsername(username);
        }else{
            Userinfo userinfo = new Userinfo();
            userinfo.setUsername(username);
            userinfo.setPassword(password);
            userinfoService.insert(userinfo);
            jsonSignupBean.setMsg("ok");
            jsonSignupBean.setUsername(username);
        }
        return jsonSignupBean ;
    }


    /**
     * App用户管理登录
     * 1、接手过来的用户名和密码，与数据库进行比对
     * 2、如果存在，且用户名密码正确，则成功登录
     * 3、如果不存在，则显示失败
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(String username, String password) {
        JsonLoginBean jsonLoginBean  =new JsonLoginBean();
        EntityWrapper<Userinfo> entityWrapper=new EntityWrapper<>();
        entityWrapper.where("username={0}",username).and("password={0}",password);
        List<Userinfo> userinfos = userinfoService.selectList(entityWrapper);
        if(!userinfos.isEmpty()){
            //如果不为空，则证明用户名和密码匹配，登录成功
            jsonLoginBean.setUsername(username);
            jsonLoginBean.setFlage(1);
        }else{
            //否则登录失败
            jsonLoginBean.setUsername(username);
            jsonLoginBean.setFlage(2);
        }
        return jsonLoginBean ;
    }

}
