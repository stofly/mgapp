package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.question.service.IQuestionService;
import cn.stylefeng.guns.modular.system.model.JsonclectorBean;
import cn.stylefeng.guns.modular.system.model.Question;
import cn.stylefeng.guns.modular.system.model.Uq;
import cn.stylefeng.guns.modular.system.model.Userinfo;
import cn.stylefeng.guns.modular.uq.service.IUqService;
import cn.stylefeng.guns.modular.userinfo.service.IUserinfoService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 错题集管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-22 21:31:44
 */
@Controller
@RequestMapping("/api/uq")
public class UqApiController extends BaseController {

    @Autowired
    private IUqService uqService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IUserinfoService userinfoService;

    /**
     * 新增错题集管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(String useNmame, int qid) {
        Integer id = null;
        //首先获取用户的id
        EntityWrapper<Userinfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("username={0}", useNmame);
        List<Userinfo> userinfos = userinfoService.selectList(entityWrapper);
        for (int i = 0; i < userinfos.size(); i++) {
            id = userinfos.get(i).getId();
        }
        Uq uq = new Uq();
        uq.setQid(qid);
        uq.setUiid(id);
        uqService.insert(uq);
        return SUCCESS_TIP;
    }
    // /**
    //     * 1、获取前台传过来的用户名
    //     * 2、根据用户名获取用户题目表中的题目的id，放入list集合中；
    //     * 3、根据获取的题目，分类存入其余集合中，将集合的size获取；
    //     * 4、建立一个vol类，将对应数据放入；
    //     * 5、放回前台
    //     * /

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String useNmame) {
        JsonclectorBean jsonclectorBean = new JsonclectorBean();
        //存id
        ArrayList arrayList = new ArrayList();
        //存单选
        ArrayList<Question> chooseList = new ArrayList<>();
        //存多选
        ArrayList<Question> choosesList = new ArrayList<>();
        //存判断
        ArrayList<Question> judgList = new ArrayList<>();
        //存简答
        ArrayList<Question> discrList = new ArrayList<>();

        Integer id = null;
        EntityWrapper<Userinfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("username={0}", useNmame);
        List<Userinfo> userinfos = userinfoService.selectList(entityWrapper);
        for (int i = 0; i < userinfos.size(); i++) {
            id = userinfos.get(i).getId();
        }
        EntityWrapper<Uq> entityWrapper2 = new EntityWrapper<>();
        entityWrapper2.where("uiid={0}", id);
        List<Uq> uqs = uqService.selectList(entityWrapper2);
        if (uqs != null) {
            for (int i = 0; i < uqs.size(); i++) {
                arrayList.add(uqs.get(i).getQid());
            }
            System.out.println(arrayList);

            for (int i = 0; i < arrayList.size(); i++) {
                EntityWrapper<Question> entityWrapper3 = new EntityWrapper<>();
                entityWrapper3.where("id={0}", arrayList.get(i));
                Question questions = questionService.selectList(entityWrapper3).get(0);
                if (questions.getType().equals("1")) {
                    chooseList.add(questions);
                } else if (questions.getType().equals("2")) {
                    judgList.add(questions);
                } else if (questions.getType().equals("3")) {
                    discrList.add(questions);
                } else if (questions.getType().equals("4")) {
                    choosesList.add(questions);
                }
            }
            jsonclectorBean.setCode("200");
            jsonclectorBean.setStatus("ok");
            jsonclectorBean.setChooseData(chooseList);
            jsonclectorBean.setChoosesData(choosesList);
            jsonclectorBean.setJudgData(judgList);
            jsonclectorBean.setDiscrData(discrList);
        } else {
            jsonclectorBean.setCode("200");
            jsonclectorBean.setStatus("ok");
            jsonclectorBean.setChooseData(null);
            jsonclectorBean.setChoosesData(null);
            jsonclectorBean.setJudgData(null);
            jsonclectorBean.setDiscrData(null);
        }
        return jsonclectorBean;
    }


    /**
     * 新增错题集管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(String useNmame, int qid) {
        Integer id = null;
        //首先获取用户的id
        EntityWrapper<Userinfo> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("username={0}", useNmame);
        List<Userinfo> userinfos = userinfoService.selectList(entityWrapper);
        for (int i = 0; i < userinfos.size(); i++) {
            id = userinfos.get(i).getId();
        }
        EntityWrapper<Uq> entityWrapper2 = new EntityWrapper<>();
        entityWrapper2.where("uiid={0}", id);
        entityWrapper2.and("qid={0}", qid);
        uqService.delete(entityWrapper2);
        return SUCCESS_TIP;
    }
}




