package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

import java.util.ArrayList;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantInfo {
	private String mapURL;
	private String operation;
	private String businessHours;
	private ArrayList<Menu> restMenu;
	private String locationDetail;
	private ArrayList<FacilityInfo> facilityInfos;
}
