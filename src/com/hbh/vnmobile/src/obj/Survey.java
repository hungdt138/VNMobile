package com.hbh.vnmobile.src.obj;

public class Survey {
	public int id;
	public String name, create_date, modified_date, created_by, location,
			comment;
	public boolean visibility, sale_any_item_competitor, has_posm, branch,
			friendly;

	public Survey(int id, String name, String create_date,
			String modified_date, String created_by, String location,
			String comment, boolean visibility,
			boolean sale_any_item_competitor, boolean has_posm, boolean branch,
			boolean friendly) {
		super();
		this.id = id;
		this.name = name;
		this.create_date = create_date;
		this.modified_date = modified_date;
		this.created_by = created_by;
		this.location = location;
		this.comment = comment;
		this.visibility = visibility;
		this.sale_any_item_competitor = sale_any_item_competitor;
		this.has_posm = has_posm;
		this.branch = branch;
		this.friendly = friendly;
	}

	//

}
