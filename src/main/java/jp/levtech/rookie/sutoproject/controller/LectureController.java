package jp.levtech.rookie.sutoproject.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.levtech.rookie.sutoproject.dto.StoreDto;
import jp.levtech.rookie.sutoproject.model.SutoMap;
import jp.levtech.rookie.sutoproject.repository.mybatis.MapRepository;

@Controller
@RequestMapping("/lecture")
public class LectureController {

	/**
	 * タスクを管理するリポジトリ
	 */
	private final MapRepository mapRepository;

	public LectureController(MapRepository mapRepository) {
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
	public String index(Model model, Principal principal) {
		model.addAttribute("googleApiKey", apiKey);

		String userName = principal.getName();
		List<SutoMap> storeInformation = mapRepository.findAll(userName);
		model.addAttribute("storeInformation", storeInformation);
		model.addAttribute("username", userName);

		return "map/display";

	}

	/*
	 * 新規店舗登録マップ画面
	 * @param model モデル
	 * @return テンプレート
	 */
	@GetMapping("/newstore")
	public String newstore(Model model,Principal principal) {
		model.addAttribute("googleApiKey", apiKey);

		String userName = principal.getName();
		List<SutoMap> storeInformation = mapRepository.findAll(userName);
		model.addAttribute("storeInformation", storeInformation);

		return "map/newstore";

	}

	/**
	 * メモを更新する。
	 *
	 * @param memo メモ
	 * @param placeId プレイスID
	 * @return テンプレート/リダイレクト
	 */
	@PostMapping("/updateall")
	public String updateall(@RequestParam("memo") String memo, @RequestParam("evalation") int evalation,
			@RequestParam("placeId") String placeId) {
		mapRepository.memoUpdate(memo, placeId);
		mapRepository.evalationUpdate(evalation, placeId);
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
	public String storeRegister(@RequestParam("lat") double storeLat, @RequestParam("lng") double storeLng,
			@RequestParam("placeId") String placeId,@RequestParam("name") String storeName,Principal principal) {
		String userName = principal.getName();
		mapRepository.storeRegister(storeLat, storeLng, placeId,storeName,userName);
		// リダイレクトするためのHTTPレスポンスを返す。
		return "redirect:/lecture/display";

	}

	/**
	 * 登録店舗を削除する。
	 *  
	 * @param placeId プレイスID
	 * @return テンプレート/リダイレクト
	 */
	@PostMapping("/delete")
	public String delete(@RequestParam("placeId") String placeId) {
		mapRepository.storeDelete(placeId);
		// リダイレクトするためのHTTPレスポンスを返す。
		return "redirect:/lecture/display";

	}

	/**
	 * 登録店舗を検索する。
	 *  
	 * @param 
	 * @return テンプレート/リダイレクト
	 */
	@GetMapping("/search")
	@ResponseBody
	public  List<StoreDto> search(@RequestParam("keyword") String storeName) {
		return mapRepository.storeResearch(storeName);
		
		
	}

}
