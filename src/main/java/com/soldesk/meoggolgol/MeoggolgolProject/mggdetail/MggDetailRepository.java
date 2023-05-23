package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.soldesk.meoggolgol.MeoggolgolProject.Member.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MggDetailRepository {
	private final JdbcTemplate jdbc;
	
	public SelectMgg check(double lo, double la) {
		String query = "select FCLTY_NM, RDNMADR_NM, NEARBY_PBTRNSP_NM, PARKNG_POSBL_AT from Alley_information where FCLTY_LO = ? and FCLTY_LA = ?";
		return jdbc.queryForObject(query, (rs, rowNum) -> {
			SelectMgg selectmgg = new SelectMgg();
			selectmgg.setFCLTY_NM(rs.getString("FCLTY_NM"));
			selectmgg.setRDNMADR_NM(rs.getString("RDNMADR_NM"));
			selectmgg.setNEARBY_PBTRNSP_NM(rs.getString("NEARBY_PBTRNSP_NM"));
			selectmgg.setPARKNG_POSBL_AT(rs.getString("PARKNG_POSBL_AT"));
			return selectmgg;
		}, lo, la);
	}
	
}
