package cn.stylefeng.guns.modular.grade.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.Grade;
import cn.stylefeng.guns.modular.grade.service.IGradeService;

/**
 * 成绩管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-22 21:30:30
 */
@Controller
@RequestMapping("/grade")
public class GradeController extends BaseController {

    private String PREFIX = "/grade/grade/";

    @Autowired
    private IGradeService gradeService;

    /**
     * 跳转到成绩管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "grade.html";
    }

    /**
     * 跳转到添加成绩管理
     */
    @RequestMapping("/grade_add")
    public String gradeAdd() {
        return PREFIX + "grade_add.html";
    }

    /**
     * 跳转到修改成绩管理
     */
    @RequestMapping("/grade_update/{gradeId}")
    public String gradeUpdate(@PathVariable Integer gradeId, Model model) {
        Grade grade = gradeService.selectById(gradeId);
        model.addAttribute("item",grade);
        LogObjectHolder.me().set(grade);
        return PREFIX + "grade_edit.html";
    }

    /**
     * 获取成绩管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return gradeService.selectList(null);
    }

    /**
     * 新增成绩管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Grade grade) {
        gradeService.insert(grade);
        return SUCCESS_TIP;
    }

    /**
     * 删除成绩管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer gradeId) {
        gradeService.deleteById(gradeId);
        return SUCCESS_TIP;
    }

    /**
     * 修改成绩管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Grade grade) {
        gradeService.updateById(grade);
        return SUCCESS_TIP;
    }

    /**
     * 成绩管理详情
     */
    @RequestMapping(value = "/detail/{gradeId}")
    @ResponseBody
    public Object detail(@PathVariable("gradeId") Integer gradeId) {
        return gradeService.selectById(gradeId);
    }
}
