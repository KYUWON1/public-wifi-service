package db.model;

public class Wifidetail {
	String id;
	String city;
	String name;
	String street;
	String address;
	String floor;
	String bulid_type;
	String bulider;
	String service_type;
	String wifi_type;
	String set_date;
	String inout_door;
	String wifi_environ;
	String word_date;
	
	public Wifidetail(String id, String city, String name, String street, String address, String floor,
			String bulid_type, String bulider, String service_type, String wifi_type, String set_date,
			String inout_door, String wifi_environ, String word_date) {
		this.id = id;
		this.city = city;
		this.name = name;
		this.street = street;
		this.address = address;
		this.floor = floor;
		this.bulid_type = bulid_type;
		this.bulider = bulider;
		this.service_type = service_type;
		this.wifi_type = wifi_type;
		this.set_date = set_date;
		this.inout_door = inout_door;
		this.wifi_environ = wifi_environ;
		this.word_date = word_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getBulid_type() {
		return bulid_type;
	}
	public void setBulid_type(String bulid_type) {
		this.bulid_type = bulid_type;
	}
	public String getBulider() {
		return bulider;
	}
	public void setBulider(String bulider) {
		this.bulider = bulider;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getWifi_type() {
		return wifi_type;
	}
	public void setWifi_type(String wifi_type) {
		this.wifi_type = wifi_type;
	}
	public String getSet_date() {
		return set_date;
	}
	public void setSet_date(String set_date) {
		this.set_date = set_date;
	}
	public String getInout_door() {
		return inout_door;
	}
	public void setInout_door(String inout_door) {
		this.inout_door = inout_door;
	}
	public String getWifi_environ() {
		return wifi_environ;
	}
	public void setWifi_environ(String wifi_environ) {
		this.wifi_environ = wifi_environ;
	}
	public String getWord_date() {
		return word_date;
	}
	public void setWord_date(String word_date) {
		this.word_date = word_date;
	}
	
	
	@Override
	public String toString() {
		return "Wifidetail [id=" + id + ", city=" + city + ", name=" + name + ", street=" + street + ", address="
				+ address + ", floor=" + floor + ", bulid_type=" + bulid_type + ", bulider=" + bulider
				+ ", service_type=" + service_type + ", wifi_type=" + wifi_type + ", set_date=" + set_date
				+ ", inout_door=" + inout_door + ", wifi_environ=" + wifi_environ + ", word_date=" + word_date + "]";
	}
	
	
}
