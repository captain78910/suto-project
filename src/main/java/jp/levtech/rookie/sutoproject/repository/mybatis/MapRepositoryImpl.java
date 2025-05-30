package jp.levtech.rookie.sutoproject.repository.mybatis;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

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
	public List<SutoMap> findAll() {
		return mapMapper.findAll();
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
	public void storeRegister(double storeLat,double storeLng,String placeId) {
		mapMapper.storeRegister(storeLat,storeLng,placeId);
	}

}
