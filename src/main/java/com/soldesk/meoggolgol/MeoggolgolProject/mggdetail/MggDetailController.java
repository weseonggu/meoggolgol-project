package com.soldesk.meoggolgol.MeoggolgolProject.mggdetail;

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
        mggDetailService.searchRestaurants(la, lo);	//mggDetailService로 la, lo 넘겨주기
        model.addAttribute("la", la);
        model.addAttribute("lo", lo);
        return "mgg-detail";
    }
}
