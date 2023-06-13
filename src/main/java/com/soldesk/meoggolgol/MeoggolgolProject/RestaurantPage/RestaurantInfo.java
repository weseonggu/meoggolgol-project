package com.soldesk.meoggolgol.MeoggolgolProject.RestaurantPage;

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
	private String locationDetail;
	private String facilityInfos;
}
