package cn.stylefeng.guns.modular.api;

import cn.stylefeng.guns.modular.question.service.IQuestionService;
import cn.stylefeng.guns.modular.system.model.JsonQuestBean;
import cn.stylefeng.guns.modular.system.model.Question;
import cn.stylefeng.roses.core.base.controller.BaseController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题目管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-15 09:40:09
 */
@RestController
@RequestMapping("/api/question")
public class QuestionApiController extends BaseController {

    @Autowired
    private IQuestionService questionService;

    /**
     * 获取题目管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String type) {
        EntityWrapper<Question> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("type={0}", type);
        List<Question> questions = questionService.selectList(entityWrapper);
        JsonQuestBean jsonQuestBean = new JsonQuestBean();
        if (questions != null) {
            jsonQuestBean.setCode("200");
            jsonQuestBean.setStatus("ok");
            jsonQuestBean.setMessages(questions);
        } else {
            jsonQuestBean.setCode("400");
            jsonQuestBean.setStatus("error");
            jsonQuestBean.setMessages(questions);
        }
        return jsonQuestBean;
    }
}
