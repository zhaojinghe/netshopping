package com.school.controller.pre;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PayMoneyController {
	
	
	@RequestMapping("/payali")
	public String pay(@RequestParam("trade_no")String trade_no,
			@RequestParam("totalmoney")Float totalmoney,Model model){
		
		System.out.println("=========trade_no========="+trade_no);
		System.out.println("=========totalmoney========="+totalmoney);
		model.addAttribute("trade_no", trade_no);
		model.addAttribute("totalmoney", totalmoney);
		
		return "pay/index";
	}

}
