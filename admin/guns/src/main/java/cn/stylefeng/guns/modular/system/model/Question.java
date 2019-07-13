package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 题目表
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-22
 */
@TableName("question")
public class Question extends Model<Question> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 题目类型
     */
    private String type;
    /**
     * 标题
     */
    private String title;
    /**
     * 选项A
     */
    private String optiona;
    /**
     * 选项B
     */
    private String optionb;
    /**
     * 选项C
     */
    private String optionc;
    /**
     * 选项D
     */
    private String optiond;
    /**
     * 提示
     */
    private String tips;
    /**
     * 答案
     */
    private String answer;
    /**
     * 解析
     */
    private String explain;
    /**
     * 科目
     */
    private String kind;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Question{" +
        ", id=" + id +
        ", type=" + type +
        ", title=" + title +
        ", optiona=" + optiona +
        ", optionb=" + optionb +
        ", optionc=" + optionc +
        ", optiond=" + optiond +
        ", tips=" + tips +
        ", answer=" + answer +
        ", explain=" + explain +
        ", kind=" + kind +
        "}";
    }
}
