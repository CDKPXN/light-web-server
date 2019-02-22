package com.company.project.vo;

/**
 * 蜂鸣器状态对象
 * @author xcj
 * @date 2019年1月8日 下午3:31:48
 * @todo TODO
 *
 */
public class Buzzerstate
{
	private Integer dayornight;
	private Integer buzzerstate;
	private Integer count;
	
	public Buzzerstate() {
	}
	
	public Buzzerstate (Integer dayornight,Integer buzzerstate, Integer count) {
		this.dayornight = dayornight;
		this.count = count;
		this.buzzerstate = buzzerstate;
	}
	public Integer getDayornight()
	{
		return dayornight;
	}
	public void setDayornight(Integer dayornight)
	{
		this.dayornight = dayornight;
	}
	public Integer getBuzzerstate()
	{
		return buzzerstate;
	}
	public void setBuzzerstate(Integer buzzerstate)
	{
		this.buzzerstate = buzzerstate;
	}
	public Integer getCount()
	{
		return count;
	}
	public void setCount(Integer count)
	{
		this.count = count;
	}
	@Override
	public String toString()
	{
		return "Buzzerstate [dayornight=" + dayornight + ", buzzerstate=" + buzzerstate + ", count=" + count + "]";
	}
	
}
