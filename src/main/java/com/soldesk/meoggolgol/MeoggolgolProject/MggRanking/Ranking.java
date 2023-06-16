package com.soldesk.meoggolgol.MeoggolgolProject.MggRanking;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ranking {
	private String mgg_name;
	private BigDecimal average_score;
	private BigInteger ranking;
	private ArrayList<Rest> rest;
}
