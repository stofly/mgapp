package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 公告表
 * </p>
 *
 * @author stylefeng
 * @since 2019-06-22
 */
@TableName("announcement")
public class Announcement extends Model<Announcement> {

    private static final long serialVersionUID = 1L;

    /**
     * 公告编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 公告热度
     */
    private Integer hot;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Announcement{" +
        ", id=" + id +
        ", content=" + content +
        ", hot=" + hot +
        "}";
    }
}
