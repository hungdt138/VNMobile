package com.hbh.vnmobile.src.obj;

public class Service {

	public int id;
	public String service, datetime, quantity, number, modified, created_by;

	public Service(int id, String service, String datetime, String quantity,
			String number, String modified, String created_by) {
		super();
		this.id = id;
		this.service = service;
		this.datetime = datetime;
		this.quantity = quantity;
		this.number = number;
		this.modified = modified;
		this.created_by = created_by;
	}

}
