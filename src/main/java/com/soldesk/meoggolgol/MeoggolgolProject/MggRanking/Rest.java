package com.soldesk.meoggolgol.MeoggolgolProject.MggRanking;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rest {
	private String rr_restaurantname;
	private BigDecimal average_score;
	private BigInteger ranking;
}
