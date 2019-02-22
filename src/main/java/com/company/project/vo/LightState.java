package com.company.project.vo;

/**
 * 灯具状态对象
 * @author xcj
 * @date 2019年1月8日 下午3:30:54
 * @todo TODO
 *
 */
public class LightState
{
	private Integer dayornight;
	private Integer state;
	private Integer count;
	
	public LightState () {}
	
	public LightState (Integer dayornight, Integer state, Integer count) {
		this.dayornight = dayornight;
		this.state = state;
		this.count = count;
	}
	
	
	public Integer getDayornight()
	{
		return dayornight;
	}
	public void setDayornight(Integer dayornight)
	{
		this.dayornight = dayornight;
	}
	public Integer getState()
	{
		return state;
	}
	public void setState(Integer state)
	{
		this.state = state;
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
		return "LightState [dayornight=" + dayornight + ", state=" + state + ", count=" + count + "]";
	}
	
}
