package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MggDetailController {
    private final MggDetailService mggDetailService;
    
    @GetMapping("/mgg-detail")
    public String goMggDetail(@RequestParam double lo, @RequestParam double la, Model model) {
                
        model.addAttribute("selectmgg", mggDetailService.getMggInfo(lo, la));// 먹자골목 정보
//        model.addAttribute("restaurantList",mggDetailService.searchRestaurants(la, lo, 1, 15));// 위도, 경도, 현재 페이지,식당 수(최대 15개)
        
        return "mgg-detail";
    }
}
