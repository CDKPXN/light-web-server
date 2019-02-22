package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 发送人id
     */
    private Integer fromid;

    /**
     * 接收人id
     */
    private Integer toid;

    /**
     * 发送的消息
     */
    private String content;

    /**
     * 创建的id
     */
    private Date ctime;

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
     * 获取发送人id
     *
     * @return fromid - 发送人id
     */
    public Integer getFromid() {
        return fromid;
    }

    /**
     * 设置发送人id
     *
     * @param fromid 发送人id
     */
    public void setFromid(Integer fromid) {
        this.fromid = fromid;
    }

    /**
     * 获取接收人id
     *
     * @return toid - 接收人id
     */
    public Integer getToid() {
        return toid;
    }

    /**
     * 设置接收人id
     *
     * @param toid 接收人id
     */
    public void setToid(Integer toid) {
        this.toid = toid;
    }

    /**
     * 获取发送的消息
     *
     * @return content - 发送的消息
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置发送的消息
     *
     * @param content 发送的消息
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取创建的id
     *
     * @return ctime - 创建的id
     */
    public Date getCtime() {
        return ctime;
    }

    /**
     * 设置创建的id
     *
     * @param ctime 创建的id
     */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}