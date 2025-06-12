package jp.levtech.rookie.sutoproject.repository.mybatis;

import java.util.List;

import jp.levtech.rookie.sutoproject.dto.StoreDto;
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
	
	public List<SutoMap> findAll(String userName);
	
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
	void storeRegister(double storeLat,double storeLng,String placeId,String storeName,String userName);
	
	/**
	 * 登録店舗を削除する。
	 * 
	 */
	
	void storeDelete(String placeId);
	
	
	/**
	 * 登録店舗を検索する。
	 * 
	 */
	public List<StoreDto> storeResearch(String storeName);
	
	


}
