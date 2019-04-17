package com.company.project.vo;

import java.util.List;

public class LightStatisticsVo {
	
	private List<Lightfrequency> lightfrequencies;
	private List<LightState> lightStates;
	private List<Buzzerstate> buzzerstates;
	private List<Faultindication> faultindications;
	
	private Integer counts; // 总数
	private Integer offLine; // 离线
	
	public Integer getOffLine() {
		return offLine;
	}
	public void setOffLine(Integer offLine) {
		this.offLine = offLine;
	}
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public List<Lightfrequency> getLightfrequencies()
	{
		return lightfrequencies;
	}
	public void setLightfrequencies(List<Lightfrequency> lightfrequencies)
	{
		this.lightfrequencies = lightfrequencies;
	}
	public List<LightState> getLightStates()
	{
		return lightStates;
	}
	public void setLightStates(List<LightState> lightStates)
	{
		this.lightStates = lightStates;
	}
	public List<Buzzerstate> getBuzzerstates()
	{
		return buzzerstates;
	}
	public void setBuzzerstates(List<Buzzerstate> buzzerstates)
	{
		this.buzzerstates = buzzerstates;
	}
	public List<Faultindication> getFaultindications()
	{
		return faultindications;
	}
	public void setFaultindications(List<Faultindication> faultindications)
	{
		this.faultindications = faultindications;
	}
	@Override
	public String toString() {
		return "LightStatisticsVo [lightfrequencies=" + lightfrequencies + ", lightStates=" + lightStates
				+ ", buzzerstates=" + buzzerstates + ", faultindications=" + faultindications + ", counts=" + counts
				+ ", offLine=" + offLine + "]";
	}
	
}
