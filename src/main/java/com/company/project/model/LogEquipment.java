package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "log_equipment")
public class LogEquipment extends Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 日志编号
     */
    private String num;

    /**
     * 日志内容
     */
    private String content;

    /**
     * 灯具编号
     */
    private String lampnum;

    /**
     * 灯具名称
     */
    private String lampname;

    /**
     * 更新时间
     */
    private Date ctime;

    /**
     * 操作结果
     */
    private String result;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取日志编号
     *
     * @return num - 日志编号
     */
    public String getNum() {
        return num;
    }

    /**
     * 设置日志编号
     *
     * @param num 日志编号
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * 获取日志内容
     *
     * @return content - 日志内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置日志内容
     *
     * @param content 日志内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取灯具编号
     *
     * @return lampnum - 灯具编号
     */
    public String getLampnum() {
        return lampnum;
    }

    /**
     * 设置灯具编号
     *
     * @param lampnum 灯具编号
     */
    public void setLampnum(String lampnum) {
        this.lampnum = lampnum;
    }

    /**
     * 获取灯具名称
     *
     * @return lampname - 灯具名称
     */
    public String getLampname() {
        return lampname;
    }

    /**
     * 设置灯具名称
     *
     * @param lampname 灯具名称
     */
    public void setLampname(String lampname) {
        this.lampname = lampname;
    }

    /**
     * 获取更新时间
     *
     * @return ctime - 更新时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置更新时间
     *
     * @param ctime 更新时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取操作结果
     *
     * @return result - 操作结果
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置操作结果
     *
     * @param result 操作结果
     */
    public void setResult(String result) {
        this.result = result;
    }
}