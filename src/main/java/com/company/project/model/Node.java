package com.company.project.model;

import java.util.Date;
import javax.persistence.*;

public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 节点名称
     */
    private String nodename;

    /**
     * 父节点id
     */
    private Integer fid;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 节点等级
     */
    private Integer type;
    /**
     * 更新时间
     */
    private Date updatetime;

    /**
     * 是否被删除（0：未；1：已删除）
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
     * 获取节点名称
     *
     * @return nodename - 节点名称
     */
    public String getNodename() {
        return nodename;
    }

    /**
     * 设置节点名称
     *
     * @param nodename 节点名称
     */
    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    /**
     * 获取父节点id
     *
     * @return fid - 父节点id
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * 设置父节点id
     *
     * @param fid 父节点id
     */
    public void setFid(Integer fid) {
        this.fid = fid;
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
     * 获取是否被删除（0：未；1：已删除）
     *
     * @return is_del - 是否被删除（0：未；1：已删除）
     */
    public Byte getIsDel() {
        return isDel;
    }

    /**
     * 设置是否被删除（0：未；1：已删除）
     *
     * @param isDel 是否被删除（0：未；1：已删除）
     */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", nodename=" + nodename + ", fid=" + fid + ", ctime=" + ctime + ", type=" + type
				+ ", updatetime=" + updatetime + ", isDel=" + isDel + "]";
	}

}