package jp.levtech.rookie.sutoproject.repository.mybatis;

import java.util.List;

import jp.levtech.rookie.sutoproject.model.SutoMap;


/**
 * タスクを管理するリポジトリ
 */

public interface MapRepository {
	/**
	 * placeIdを検索する。
	 * 
	 * @return placeId
	 */
	
	public List<SutoMap> findAll();
	
	/**
	 * memoを更新する。
	 * 
	 */
	
	void memoUpdate(String memo, String placeId);
	
	/**
	 * 評価を更新する。
	 * 
	 */
	
	void evalationUpdate(int evalation, String placeId);
	
	/**
	 * 新規店舗を登録する。
	 * 
	 */
	void storeRegister(double storeLat,double storeLng,String placeId);
	
	
	

}
