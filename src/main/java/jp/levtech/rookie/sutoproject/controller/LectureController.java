package jp.levtech.rookie.sutoproject.controller;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/lecture")
public class LectureController {
	
	@Value("${google.api.key}")
	private String apiKey;
	/**
	 * ToDo一覧画面を扱う
	 * 
	 * @param model モデル
	 * @return テンプレート
	 */
	
	@GetMapping("/display")
	public String index(Model model) {
		LocalDate today= LocalDate.now();
			List<String> week = getOneWeek(today);
			model.addAttribute("weeklyDate", week);
			
			model.addAttribute("googleApiKey",apiKey);
			
			
		return "map/display";
	}
	
	@GetMapping("/fragment/items")
	public String getItemFragment(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate date, Model model) {
		
		List<String> week = getOneWeek(date);
		model.addAttribute("weeklyDate", week);
		
		return "map/fragment :: fragment";
	}
	
	private List<String> getOneWeek(LocalDate startDay) {
		List<String> week = new ArrayList<>();
		for(int i= 0; i<7; i++) {
			week.add(startDay.plusDays(i).toString());
		}
		return week;
	}
	

}
