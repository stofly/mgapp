package xlr.com.mgapp.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.io.Serializable;

/**
 * @ 描述：greenDao 学习，测试生成数据库
 */
@Entity
public class QuestBean {

    @Id(autoincrement = true)
    private long id;
    @NotNull
    private int type;// 题型：0：判断题 1：选择题
    @NotNull
    private String title;// 问题
    private String optiona;// 选项A
    private String optionb;// 选项B
    private String optionc;// 选项C
    private String optiond;// 选项D
    private String tips;//提示
    private String explain;//解释
    private String answer;// 正确答案
    private String myanswer;// 我的答案

    public QuestBean(long id, int type, String title, String optiona, String optionb, String optionc, String optiond, String tips, String explain, String answer) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.optiona = optiona;
        this.optionb = optionb;
        this.optionc = optionc;
        this.optiond = optiond;
        this.tips = tips;
        this.explain = explain;
        this.answer = answer;
    }

    @Generated(hash = 315411171)
    public QuestBean(long id, int type, @NotNull String title, String optiona,
            String optionb, String optionc, String optiond, String tips,
            String explain, String answer, String myanswer) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.optiona = optiona;
        this.optionb = optionb;
        this.optionc = optionc;
        this.optiond = optiond;
        this.tips = tips;
        this.explain = explain;
        this.answer = answer;
        this.myanswer = myanswer;
    }

    @Generated(hash = 1276854522)
    public QuestBean() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptiona() {
        return optiona;
    }

    public void setOptiona(String optiona) {
        this.optiona = optiona;
    }

    public String getOptionb() {
        return optionb;
    }

    public void setOptionb(String optionb) {
        this.optionb = optionb;
    }

    public String getOptionc() {
        return optionc;
    }

    public void setOptionc(String optionc) {
        this.optionc = optionc;
    }

    public String getOptiond() {
        return optiond;
    }

    public void setOptiond(String optiond) {
        this.optiond = optiond;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMyanswer() {
        return myanswer;
    }

    public void setMyanswer(String myanswer) {
        this.myanswer = myanswer;
    }

    @Override
    public String toString() {
        return "QuestBean{" +
                "id=" + id +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", optiona='" + optiona + '\'' +
                ", optionb='" + optionb + '\'' +
                ", optionc='" + optionc + '\'' +
                ", optiond='" + optiond + '\'' +
                ", tips='" + tips + '\'' +
                ", explain='" + explain + '\'' +
                ", answer='" + answer + '\'' +
                ", myanswer='" + myanswer + '\'' +
                '}';
    }
}