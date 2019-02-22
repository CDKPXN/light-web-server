package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    private String realname;

    /**
     * 职务
     */
    private String duty;

    /**
     * 单位
     */
    private String company;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 备注
     */
    private String comment;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 是否已经被删除（0：未被删除；1已被删除）
     */
    @Column(name = "is_del")
    private Byte isDel;

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
     * 获取姓名
     *
     * @return realname - 姓名
     */
    public String getRealname() {
        return realname;
    }

    /**
     * 设置姓名
     *
     * @param realname 姓名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取职务
     *
     * @return duty - 职务
     */
    public String getDuty() {
        return duty;
    }

    /**
     * 设置职务
     *
     * @param duty 职务
     */
    public void setDuty(String duty) {
        this.duty = duty;
    }

    /**
     * 获取单位
     *
     * @return company - 单位
     */
    public String getCompany() {
        return company;
    }

    /**
     * 设置单位
     *
     * @param company 单位
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取联系方式
     *
     * @return phone - 联系方式
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置联系方式
     *
     * @param phone 联系方式
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取备注
     *
     * @return comment - 备注
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置备注
     *
     * @param comment 备注
     */
    public void setComment(String comment) {
        this.comment = comment;
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
     * 获取更新时间
     *
     * @return updatetime - 更新时间
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新时间
     *
     * @param updatetime 更新时间
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取是否已经被删除（0：未被删除；1已被删除）
     *
     * @return is_del - 是否已经被删除（0：未被删除；1已被删除）
     */
    public Byte getIsDel() {
        return isDel;
    }

    /**
     * 设置是否已经被删除（0：未被删除；1已被删除）
     *
     * @param isDel 是否已经被删除（0：未被删除；1已被删除）
     */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }
}