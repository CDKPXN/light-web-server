package com.company.project.vo;

import java.util.List;

import com.company.project.model.Light;
import com.company.project.model.User;

public class LightAndUsersVo extends Light
{
	private List<User> users;

	public List<User> getUsers()
	{
		return users;
	}

	public void setUsers(List<User> users)
	{
		this.users = users;
	}

	@Override
	public String toString()
	{
		return "LightAndUsersVo [users=" + users + ", getId()=" + getId() + ", getAttrName()=" + getAttrName()
				+ ", getAttrNum()=" + getAttrNum() + ", getAttrPhone()=" + getAttrPhone() + ", getAttrDeadtime()="
				+ getAttrDeadtime() + ", getAttrNodeid()=" + getAttrNodeid() + ", getAttrLongitude()="
				+ getAttrLongitude() + ", getAttrLatitude()=" + getAttrLatitude() + ", getGpsLongitude()="
				+ getGpsLongitude() + ", getGpsLatitude()=" + getGpsLatitude() + ", getGpsElevation()="
				+ getGpsElevation() + ", getGpsLock()=" + getGpsLock() + ", get4gSignal()=" + get4gSignal()
				+ ", get4gIccid()=" + get4gIccid() + ", getTotalElectricity()=" + getTotalElectricity()
				+ ", getLampsElectricity()=" + getLampsElectricity() + ", getTotalVoltage()=" + getTotalVoltage()
				+ ", getLampsVoltage()=" + getLampsVoltage() + ", getTemperature()=" + getTemperature()
				+ ", getFaultIndicate()=" + getFaultIndicate() + ", getDayIndicate()=" + getDayIndicate()
				+ ", getHeartfrequency()=" + getHeartfrequency() + ", getLampDayFrequency()=" + getLampDayFrequency()
				+ ", getLampNightFrequency()=" + getLampNightFrequency() + ", getLampDayState()=" + getLampDayState()
				+ ", getLampNightState()=" + getLampNightState() + ", getLampBuzzerDay()=" + getLampBuzzerDay()
				+ ", getLampBuzzerNight()=" + getLampBuzzerNight() + ", getSetDayFrequency()=" + getSetDayFrequency()
				+ ", getSetNightFrequency()=" + getSetNightFrequency() + ", getSetDayState()=" + getSetDayState()
				+ ", getSetNightState()=" + getSetNightState() + ", getSetDayBuzzer()=" + getSetDayBuzzer()
				+ ", getSetNightBuzzer()=" + getSetNightBuzzer() + ", getSetHeartfrequency()=" + getSetHeartfrequency()
				+ ", getCtime()=" + getCtime() + ", getHeartupdatetime()=" + getHeartupdatetime() + ", getUpdatetime()="
				+ getUpdatetime() + ", getIsDel()=" + getIsDel() + "]";
	}
	
}
