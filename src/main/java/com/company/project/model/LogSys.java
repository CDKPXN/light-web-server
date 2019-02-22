package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "log_sys")
public class LogSys extends Log {
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
     * IP地址
     */
    private String ipaddress;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
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
     * 获取IP地址
     *
     * @return ipaddress - IP地址
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * 设置IP地址
     *
     * @param ipaddress IP地址
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /**
     * 获取操作人
     *
     * @return operator - 操作人
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 设置操作人
     *
     * @param operator 操作人
     */
    public void setOperator(String operator) {
        this.operator = operator;
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