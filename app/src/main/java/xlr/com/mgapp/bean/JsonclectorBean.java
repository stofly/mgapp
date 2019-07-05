package xlr.com.mgapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 青铜骑士
 * @ClassName: JsonQuestBean
 * @ProjectName guns
 * @Description: TODO
 * @date 2019/6/2221:44
 */
public class JsonclectorBean implements Serializable {

    //状态
    private String status;
    //状态码
    private String code;
    //单选
    private List<QuestBean2> chooseData;
    //多选
    private List<QuestBean2> choosesData;
    //判断
    private List<QuestBean2> judgData;
    //填空
    private List<QuestBean2> discrData;

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

    public List<QuestBean2> getChooseData() {
        return chooseData;
    }

    public void setChooseData(List<QuestBean2> chooseData) {
        this.chooseData = chooseData;
    }

    public List<QuestBean2> getChoosesData() {
        return choosesData;
    }

    public void setChoosesData(List<QuestBean2> choosesData) {
        this.choosesData = choosesData;
    }

    public List<QuestBean2> getJudgData() {
        return judgData;
    }

    public void setJudgData(List<QuestBean2> judgData) {
        this.judgData = judgData;
    }

    public List<QuestBean2> getDiscrData() {
        return discrData;
    }

    public void setDiscrData(List<QuestBean2> discrData) {
        this.discrData = discrData;
    }

    @Override
    public String toString() {
        return "JsonclectorBean{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", chooseData=" + chooseData +
                ", choosesData=" + choosesData +
                ", judgData=" + judgData +
                ", discrData=" + discrData +
                '}';
    }
}
