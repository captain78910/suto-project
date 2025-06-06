package jp.levtech.rookie.sutoproject.model;

import lombok.Value;

/**
 * タスクを表すモデル
 */
@Value
public class SutoMap {

//	このカラム順番をDBと合わせないとxmlのマッピング時にエラーになる。
	
	/**
	 * 店の緯度を表すプロパティ
	 */
	private final double storelat;
	
	/**
	 * 店の経度を表すプロパティ
	 */
	private final double storelong;
	
	/**
	 * googleのplaceIDを表すプロパティ
	 */
	private final String placeId;
	
	
	/**
	 * 店のメモを表すプロパティ
	 */
	private final String memo;
	
	/**
	 * 訪問の有無を表すプロパティ
	 */
	private final boolean visit;
	
	/**
	 * 店の評価を表すプロパティ
	 */
	private final Integer evalation;
	
	/**
	 * 店名を表すプロパティ
	 */
	private final String storeName;

}
