package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 节点id
     */
    private Integer nodeid;

    /**
     * 权限（4位二进制的十进制值）
     */
    private Integer authority;

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
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取节点id
     *
     * @return nodeid - 节点id
     */
    public Integer getNodeid() {
        return nodeid;
    }

    /**
     * 设置节点id
     *
     * @param nodeid 节点id
     */
    public void setNodeid(Integer nodeid) {
        this.nodeid = nodeid;
    }

    /**
     * 获取权限（4位二进制的十进制值）
     *
     * @return authority - 权限（4位二进制的十进制值）
     */
    public Integer getAuthority() {
        return authority;
    }

    /**
     * 设置权限（4位二进制的十进制值）
     *
     * @param authority 权限（4位二进制的十进制值）
     */
    public void setAuthority(Integer authority) {
        this.authority = authority;
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
		return "Authority [id=" + id + ", uid=" + uid + ", nodeid=" + nodeid + ", authority=" + authority + ", ctime="
				+ ctime + ", updatetime=" + updatetime + "]";
	}
}