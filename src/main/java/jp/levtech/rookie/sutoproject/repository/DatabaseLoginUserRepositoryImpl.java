package jp.levtech.rookie.sutoproject.repository;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.levtech.rookie.sutoproject.model.LoginUser;
import jp.levtech.rookie.sutoproject.repository.mybatis.LoginUserMapper;

/**
 * ログインユーザーをデータベースで管理するリポジトリ
 */

@Repository

public class DatabaseLoginUserRepositoryImpl implements LoginUserRepository {
	
	/**
	 * ログインユーザーのマッパー
	 */
	private final LoginUserMapper loginUserMapper;
	
	/**
	 * ログインユーザーをデータベースで管理するリポジトリのコンストラクタ
	 * 
	 * @param loginUserMapper ログインユーザーのマッパー
	 */
	public DatabaseLoginUserRepositoryImpl(LoginUserMapper loginUserMapper) {
		this.loginUserMapper =loginUserMapper;
	}
	
	/**
	 * ユーザー名からデータベース上のログインユーザーを検索する
	 * 
	 */
	@Override
	public Optional<LoginUser> findByUserName(String userName) {
		return loginUserMapper.findByUserName(userName);
	}
	
	/**
	 * ログインユーザーをデータベースに登録する
	 */
	@Override
	public void register(LoginUser loginUser) {
		loginUserMapper.register(loginUser);
	}

}
