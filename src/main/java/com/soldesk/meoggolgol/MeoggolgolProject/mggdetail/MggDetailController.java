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
        
        return "mgg-detail";
    }
}
