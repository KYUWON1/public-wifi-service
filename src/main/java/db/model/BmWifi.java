package db.model;

import java.sql.Date;

public class BmWifi {
	int id;
	String bmName;
	String wifiName;
	String wifiId;
	Date reg_date;
	
	public BmWifi() {
		
	}
	
	public BmWifi(int id, String bmName, String wifiName,String wifiId, Date reg_date) {
		super();
		this.id = id;
		this.bmName = bmName;
		this.wifiName = wifiName;
		this.wifiId = wifiId;
		this.reg_date = reg_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBmName() {
		return bmName;
	}

	public void setBmName(String bmName) {
		this.bmName = bmName;
	}

	public String getWifiName() {
		return wifiName;
	}

	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getWifiId() {
		return wifiId;
	}

	public void setWifiId(String wifiId) {
		this.wifiId = wifiId;
	}

	
	
}
