package cn.stylefeng.guns.modular.system.model;

import java.util.List;

/**
 * @author 青铜骑士
 * @ClassName: JsonQuestBean
 * @ProjectName guns
 * @Description: TODO
 * @date 2019/6/2221:44
 */
public class JsonclectorBean {
    /**
     * status : ok
     * code : 200
     * messages : [{"id":1,"q_type":1,"title":"title","optionA":"选项A","optionB":"选项B","optionC":"选项C","optionD":"选项D","answer":"answer","tips":"tips","explain":"explain"},{"id":2,"q_type":1,"title":"title","optionA":"选项A","optionB":"选项B","optionC":"选项C","optionD":"选项D","answer":"answer","tips":"tips","explain":"explain"},{"id":3,"q_type":1,"title":"1","optionA":"title","optionB":"选项A","optionC":"选项B","optionD":"选项C","answer":"tips","tips":"选项D","explain":"answer"},{"id":4,"q_type":1,"title":"title","optionA":"选项A","optionB":"选项B","optionC":"选项C","optionD":"选项D","answer":"answer","tips":"tips","explain":"explain"},{"id":5,"q_type":1,"title":"title","optionA":"选项A","optionB":"选项B","optionC":"选项C","optionD":"选项D","answer":"answer","tips":"tips","explain":"explain"}]
     */
    //状态
    private String status;
    //状态码
    private String code;
    //单选
    private List<Question> chooseData;
    //多选
    private List<Question> choosesData;
    //判断
    private List<Question> judgData;
    //填空
    private List<Question> discrData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Question> getChooseData() {
        return chooseData;
    }

    public void setChooseData(List<Question> chooseData) {
        this.chooseData = chooseData;
    }

    public List<Question> getChoosesData() {
        return choosesData;
    }

    public void setChoosesData(List<Question> choosesData) {
        this.choosesData = choosesData;
    }

    public List<Question> getJudgData() {
        return judgData;
    }

    public void setJudgData(List<Question> judgData) {
        this.judgData = judgData;
    }

    public List<Question> getDiscrData() {
        return discrData;
    }

    public void setDiscrData(List<Question> discrData) {
        this.discrData = discrData;
    }
}
