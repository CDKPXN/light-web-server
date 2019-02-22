package com.company.project.vo;


/**
 * 故障指示对象
 * @author xcj
 * @date 2019年1月8日 下午3:33:32
 * @todo TODO
 *
 */
public class Faultindication
{
	private Integer fault;
	private Integer count;
	
	public Faultindication () {
		
	}
	
	public Faultindication (Integer fault,Integer count) {
		this.fault = fault;
		this.count = count;
	}
	
	public Integer getFault()
	{
		return fault;
	}
	public void setFault(Integer fault)
	{
		this.fault = fault;
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
		return "Faultindication [fault=" + fault + ", count=" + count + "]";
	}
	
}
