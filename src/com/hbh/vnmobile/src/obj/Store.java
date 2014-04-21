package com.hbh.vnmobile.src.obj;

public class Store {
	public int id;
	public String name, address, type, province, district, stk, status, contact, mobile,
			description, markerid;
	public double latitude, longitude;

	//
	public Store(int id, String name, String address, String type,
			String province, String district, String contact, String mobile, String stk, String status,
			String description, double latitude, double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.type = type;
		this.province = province;
		this.district = district;
		this.contact = contact;
		this.mobile = mobile;
		this.stk = stk;
		this.status = status;
		this.description = description;
		this.latitude = latitude;
		this.longitude = longitude;
	}

}
