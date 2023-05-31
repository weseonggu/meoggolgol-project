package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	private int id;
	private String place_name;
	private String road_address_name;
	private String category_name;
	private String phone;
	private String place_url;
	private String x;
	private String y;
}
