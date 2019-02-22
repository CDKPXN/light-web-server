package com.company.project.vo;

import java.util.List;

public class LightStatisticsVo {
	
	private List<Lightfrequency> lightfrequencies;
	private List<LightState> lightStates;
	private List<Buzzerstate> buzzerstates;
	private List<Faultindication> faultindications;
	
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
	public String toString()
	{
		return "LightStatisticsVo [lightfrequencies=" + lightfrequencies + ", lightStates=" + lightStates
				+ ", buzzerstates=" + buzzerstates + ", faultindications=" + faultindications + "]";
	}
	
}
