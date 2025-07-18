package jp.levtech.rookie.sutoproject.repository.mybatis;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import jp.levtech.rookie.sutoproject.model.LoginUser;

/**
 * ログインユーザーのマッパー
 */
@Mapper
public interface LoginUserMapper {
	/**
	 * ユーザー名からログインユーザーを検索する。
	 * 
	 * @param userName ユーザー名
	 * @return ログインユーザー
	 */
	
	Optional<LoginUser> findByUserName(String userName) ;
	
	/**
	 * ログインユーザーを登録する
	 * 
	 * @param loginUser ログインユーザー
	 */
	
	void register(LoginUser loginUser);

}
