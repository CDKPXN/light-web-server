package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Devicedata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发送源
     */
    private String source;

    /**
     * 目标
     */
    private String target;

    /**
     * 数据类型（0：上报且发送改变的数据、1：下发的控制指令）
     */
    @Column(name = "data_type")
    private Integer dataType;

    /**
     * 数据内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Date ctime;

    @Column(name = "is_del")
    private Byte isDel;

    /**
     * 数据结果
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
     * 获取发送源
     *
     * @return source - 发送源
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置发送源
     *
     * @param source 发送源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取目标
     *
     * @return target - 目标
     */
    public String getTarget() {
        return target;
    }

    /**
     * 设置目标
     *
     * @param target 目标
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * 获取数据类型（0：上报且发送改变的数据、1：下发的控制指令）
     *
     * @return data_type - 数据类型（0：上报且发送改变的数据、1：下发的控制指令）
     */
    public Integer getDataType() {
        return dataType;
    }

    /**
     * 设置数据类型（0：上报且发送改变的数据、1：下发的控制指令）
     *
     * @param dataType 数据类型（0：上报且发送改变的数据、1：下发的控制指令）
     */
    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取数据内容
     *
     * @return content - 数据内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置数据内容
     *
     * @param content 数据内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /**
     * @return is_del
     */
    public Byte getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取数据结果
     *
     * @return result - 数据结果
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置数据结果
     *
     * @param result 数据结果
     */
    public void setResult(String result) {
        this.result = result;
    }
}