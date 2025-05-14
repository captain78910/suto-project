package jp.levtech.rookie.sutoproject.model;

import lombok.Value;

/**
 * ログインユーザーを表すモデル
 */
@Value
public class LoginUser {
	/**
	 * ユーザーIDを表すプロパティ
	 */
	private final long userId;
	
	/**
	 * ユーザー名を表すプロパティ
	 */
	private final String userName;
	
	/**
	 * パスワードを表すプロパティ
	 */
	private final String password;
	
	/**
	 * ユーザーが有効か表すプロパティ
	 */
	private final boolean enabled;

}
