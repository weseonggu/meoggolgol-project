package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MggDetailController {
    private final MggDetailRepository mdr;
    private final MggDetailService mggDetailService;

    @GetMapping("/mgg-detail")
    public String goMggDetail(@RequestParam double lo, @RequestParam double la, Model model) {
        ArrayList<Restaurant> list = mggDetailService.searchRestaurants(la, lo);	//mggDetailService로 la, lo 넘겨주기
        SelectMgg selectmgg = mdr.check(lo, la);
        
        System.out.println(list.get(0).getPlace_url());
        mggDetailService.searchImage(list.get(0).getPlace_url());
        
        String parking = "없음";
        if (selectmgg.getPARKNG_POSBL_AT().equals("유")) {
			parking = "있음";
		}
        
        model.addAttribute("fclty_nm", selectmgg.getFCLTY_NM());
        model.addAttribute("rdnmadr_nm", selectmgg.getRDNMADR_NM());
        model.addAttribute("nearby_pbtrnsp_nm", selectmgg.getNEARBY_PBTRNSP_NM());
        model.addAttribute("parkng_posbl_at", parking);
        
        model.addAttribute("la", la);
        model.addAttribute("lo", lo);
        return "mgg-detail";
    }
}
