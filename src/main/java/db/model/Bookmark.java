package db.model;

import java.sql.Date;

public class Bookmark {
	int id;
	String name;
	int sequence;
	Date reg_date;
	Date mod_date;
	
	public Bookmark() {
		
	}
	
	public Bookmark(int id, String name, int sequence, Date reg_date, Date mod_date) {
		super();
		this.id = id;
		this.name = name;
		this.sequence = sequence;
		this.reg_date = reg_date;
		this.mod_date = mod_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getMod_date() {
		return mod_date;
	}

	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
	
	
}
