package com.hbh.vnmobile.src.obj;

public class Kpi {
	public int id;
	public String name, create_date, modified_date, created_by, location,
			card10k, card20k, card50k, card100k, card3gusb;

	public Kpi(int id, String name, String create_date, String modified_date,
			String created_by, String location, String card10k, String card20k,
			String card50k, String card100k, String card3gusb) {
		super();
		this.id = id;
		this.name = name;
		this.create_date = create_date;
		this.modified_date = modified_date;
		this.created_by = created_by;
		this.location = location;
		this.card10k = card10k;
		this.card20k = card20k;
		this.card50k = card50k;
		this.card100k = card100k;
		this.card3gusb = card3gusb;
	}

}
