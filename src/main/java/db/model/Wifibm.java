package db.model;

public class Wifibm {
	String id;
	String name;
	String city;
	String street;
	String address;
	String lat;
	String lnt;
	
	public Wifibm() {
		
	}
	
	public Wifibm(String id, String name, String city, String street, String address, String lat, String lnt) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.street = street;
		this.address = address;
		this.lat = lat;
		this.lnt = lnt;
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
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
