package jp.levtech.rookie.sutoproject.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.levtech.rookie.sutoproject.model.SutoMap;

/**
 * タスクのマッパー
 */
@Mapper
public interface MapMapper {
	
	/**
	 * placeIdを検索する。
	 * 
	 * @return placeId
	 */
	
	List<SutoMap> findAll();
	
	
	/**
	 * memoを更新する。
	 * 
	 */
	
	void memoUpdate(String memo,String placeId);
	
	/**
	 * 評価を更新する。
	 * 
	 */
	
	void evalationUpdate(int evalation,String placeId);
	
	/**
	 * 新規店舗を登録する。
	 * 
	 */
	void storeRegister(double storeLat,double storeLng,String placeId);
	
	
}
