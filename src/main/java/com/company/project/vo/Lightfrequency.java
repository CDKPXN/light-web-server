package com.company.project.vo;

/**
 * 灯具频率对象
 * @author xcj
 * @date 2019年1月8日 下午3:29:07
 * @todo 
 *
 */
public class Lightfrequency
{
	private Integer dayornight;
	private Integer frequency;
	private Integer count;
	
	public Lightfrequency () {}
	
	public Lightfrequency (Integer dayornight, Integer frequency, Integer count) {
		this.frequency = frequency;
		this.count = count;
		this.dayornight = dayornight;
	}
	
	public Integer getDayornight()
	{
		return dayornight;
	}
	public void setDayornight(Integer dayornight)
	{
		this.dayornight = dayornight;
	}
	public Integer getFrequency()
	{
		return frequency;
	}
	public void setFrequency(Integer frequency)
	{
		this.frequency = frequency;
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
		return "Lightfrequency [dayornight=" + dayornight + ", frequency=" + frequency + ", count=" + count + "]";
	}
	
}
