package db.model;

public class Wifilocate {
	String id;
	String name;
	String lat;
	String lnt;
	
	public Wifilocate(String id, String name, String lat, String lnt) {
		this.id = id;
		this.name = name;
		this.lat = lat;
		this.lnt = lnt;
	}

	public Wifilocate() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLnt() {
		return lnt;
	}

	public void setLnt(String lnt) {
		this.lnt = lnt;
	}
	
	
}
