package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String userpass;

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
     * 邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String comment;

    /**
     * 是否被删除（0：未被删除；1：已被删除）
     */
    @Column(name = "is_del")
    private Boolean isDel;

    /**
     * 是否是超级管理员（0：不是；1：是）
     */
    @Column(name = "is_admin")
    private Boolean isAdmin;

    /**
     * 消息权限（0:都不能；1:接收；2:可发送；3：都可以）
     */
    @Column(name = "mesg_authority")
    private Integer mesgAuthority;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date updatetime;

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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return userpass - 密码
     */
    public String getUserpass() {
        return userpass;
    }

    /**
     * 设置密码
     *
     * @param userpass 密码
     */
    public void setUserpass(String userpass) {
        this.userpass = userpass;
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
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
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
     * 获取是否被删除（0：未被删除；1：已被删除）
     *
     * @return is_del - 是否被删除（0：未被删除；1：已被删除）
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * 设置是否被删除（0：未被删除；1：已被删除）
     *
     * @param isDel 是否被删除（0：未被删除；1：已被删除）
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取是否是超级管理员（0：不是；1：是）
     *
     * @return is_admin - 是否是超级管理员（0：不是；1：是）
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * 设置是否是超级管理员（0：不是；1：是）
     *
     * @param isAdmin 是否是超级管理员（0：不是；1：是）
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * 获取消息权限（0:都不能；1:接收；2:可发送；3：都可以）
     *
     * @return mesg_authority - 消息权限（0:都不能；1:接收；2:可发送；3：都可以）
     */
    public Integer getMesgAuthority() {
        return mesgAuthority;
    }

    /**
     * 设置消息权限（0:都不能；1:接收；2:可发送；3：都可以）
     *
     * @param mesgAuthority 消息权限（0:都不能；1:接收；2:可发送；3：都可以）
     */
    public void setMesgAuthority(Integer mesgAuthority) {
        this.mesgAuthority = mesgAuthority;
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

	@Override
	public String toString()
	{
		return "User [id=" + id + ", username=" + username + ", userpass=" + userpass + ", realname=" + realname
				+ ", duty=" + duty + ", company=" + company + ", phone=" + phone + ", email=" + email + ", comment="
				+ comment + ", isDel=" + isDel + ", isAdmin=" + isAdmin + ", mesgAuthority=" + mesgAuthority
				+ ", ctime=" + ctime + ", updatetime=" + updatetime + "]";
	}
}