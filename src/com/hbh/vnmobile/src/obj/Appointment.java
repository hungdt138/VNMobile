package com.hbh.vnmobile.src.obj;

public class Appointment {
	public int id;
	public String contact_name, address, type, date, time, status, description;

	public Appointment(int id, String contact_name, String address,
			String type, String date, String time, String status,
			String description) {
		super();
		this.id = id;
		this.contact_name = contact_name;
		this.address = address;
		this.type = type;
		this.date = date;
		this.time = time;
		this.status = status;
		this.description = description;
	}

	//

}
