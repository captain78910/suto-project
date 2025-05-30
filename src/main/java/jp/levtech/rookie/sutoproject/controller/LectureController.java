package jp.levtech.rookie.sutoproject.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.levtech.rookie.sutoproject.model.SutoMap;
import jp.levtech.rookie.sutoproject.repository.mybatis.MapRepository;


@Controller
@RequestMapping("/lecture")
public class LectureController {
	
	
	/**
	 * タスクを管理するリポジトリ
	 */
	private final MapRepository mapRepository;
	
	
	public LectureController (MapRepository mapRepository) {
		this.mapRepository = mapRepository;
	}
	
	
	@Value("${google.api.key}")
	private String apiKey;
	/*
	 * 登録店舗マップ画面
	 * @param model モデル
	 * @return テンプレート
	 */
	
	@GetMapping("/display")
	public String index(Model model) {
		model.addAttribute("googleApiKey",apiKey);
		
		List<SutoMap>storeInformation = mapRepository.findAll();
		model.addAttribute("storeInformation",storeInformation);
		
		return "map/display";			

	}
	
	/*
	 * 新規店舗登録マップ画面
	 * @param model モデル
	 * @return テンプレート
	 */
	@GetMapping("/newstore")
	public String newstore(Model model) {
		model.addAttribute("googleApiKey",apiKey);
		
		List<SutoMap>storeInformation = mapRepository.findAll();
		model.addAttribute("storeInformation",storeInformation);
		
		return "map/newstore";			

	}
	
	/**
	 * メモを更新する。
	 *
	 * @param memo メモ
	 * @param placeId プレイスID
	 * @return テンプレート/リダイレクト
	 */
	@PostMapping("/update")
	public String update(@RequestParam("memo") String memo,@RequestParam("placeId") String placeId) {
		mapRepository.memoUpdate(memo,placeId);
		// リダイレクトするためのHTTPレスポンスを返す。
		return "redirect:/lecture/display";
		
		
	}
	
	/**
	 * 評価を更新する。
	 *
	 * @param taskId タスクID
	 * @param updateTaskForm タスクを更新するためのフォーム
	 * @param bindingResult バリデーションの結果
	 * @return テンプレート/リダイレクト
	 */
	@PostMapping("/rankupdate")
	public String evalationupdate(@RequestParam("evalation") int evalation,@RequestParam("placeId") String placeId) {
		mapRepository.evalationUpdate(evalation,placeId);
		// リダイレクトするためのHTTPレスポンスを返す。
		return "redirect:/lecture/display";
		
		
	}
	
	/**
	 * 新規店舗を登録する。
	 *
	 * @param storelat 緯度
	 * @param storelng 経度
	 * @param placeId プレイスID
	 * @return テンプレート/リダイレクト
	 */
	@PostMapping("/storeRegister")
	public String storeRegister(@RequestParam("lat") double storeLat,@RequestParam("lng") double storeLng,@RequestParam("placeId") String placeId) {
		mapRepository.storeRegister(storeLat,storeLng,placeId);
		// リダイレクトするためのHTTPレスポンスを返す。
		return "redirect:/lecture/display";
		
		
	}
	
}
