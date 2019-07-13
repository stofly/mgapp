package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户题目表
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-22
 */
@TableName("uq")
public class Uq extends Model<Uq> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户题目编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户编号
     */
    private Integer uiid;
    /**
     * 题目编号
     */
    private Integer qid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUiid() {
        return uiid;
    }

    public void setUiid(Integer uiid) {
        this.uiid = uiid;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Uq{" +
        ", id=" + id +
        ", uiid=" + uiid +
        ", qid=" + qid +
        "}";
    }
}
