package com.hbh.vnmobile.src.obj;

public class Order {

	public int id;
	public String outlet, create_date, modified, created_by, card10k, card20k,
			card50k, card100k, card3gusb, description, status;

	public Order(int id, String outlet, String create_date, String modified,
			String created_by, String card10k, String card20k, String card50k,
			String card100k, String card3gusb, String description, String status) {
		super();
		this.id = id;
		this.outlet = outlet;
		this.create_date = create_date;
		this.modified = modified;
		this.created_by = created_by;
		this.card10k = card10k;
		this.card20k = card20k;
		this.card50k = card50k;
		this.card100k = card100k;
		this.card3gusb = card3gusb;
		this.description = description;
		this.status = status;
	}

}
