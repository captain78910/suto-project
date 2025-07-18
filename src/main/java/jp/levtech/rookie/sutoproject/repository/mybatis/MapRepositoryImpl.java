package jp.levtech.rookie.sutoproject.repository.mybatis;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import jp.levtech.rookie.sutoproject.dto.StoreDto;
import jp.levtech.rookie.sutoproject.model.SutoMap;

/**
 * タスクを管理するリポジトリ
 */

@Primary
@Repository
public class MapRepositoryImpl implements MapRepository {
	
	
	/**
	 * マッパー
	 */
	private final MapMapper mapMapper;
	
	/**
	 * リポジトリのコンストラクタ
	 * 
	 * @param mapMapper マッパー
	 */
	
	public MapRepositoryImpl(MapMapper mapMapper) {
		//マッパーを初期化する。
		
		this.mapMapper = mapMapper;	}
	
	
	/**
	 * 店情報を読み込む。
	 */
	@Override
	public List<SutoMap> findAll(String userName) {
		return mapMapper.findAll(userName);
	}
	
	/**
	 * memoを更新する。
	 * 
	 */
	@Override
	
	public void memoUpdate(String memo, String placeId){
		mapMapper.memoUpdate(memo, placeId);
	}
	
	/**
	 * 評価を更新する。
	 * 
	 */
	@Override
	
	public void evalationUpdate(int evalation, String placeId){
		mapMapper.evalationUpdate(evalation, placeId);
	}
	
	/**
	 * 新規店舗を登録する。
	 * 
	 */
	@Override
	public void storeRegister(double storeLat,double storeLng,String placeId,String storeName,String userName) {
		mapMapper.storeRegister(storeLat,storeLng,placeId,storeName,userName);
	}
	
	
	/**
	 * 登録店舗を削除する。
	 * 
	 */
	@Override
	public void storeDelete(String placeId) {
		mapMapper.storeDelete(placeId);
	}
	
	
	/**
	 * 登録店舗を検索する。
	 * 
	 */
	@Override
	public List<StoreDto> storeResearch(String storeName) {
		return mapMapper.storeResearch(storeName);
	}

}
