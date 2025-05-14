package jp.levtech.rookie.sutoproject.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.levtech.rookie.sutoproject.model.LoginUser;
import jp.levtech.rookie.sutoproject.repository.LoginUserRepository;

/**
 * ログインユーザーの詳細を管理するサービス
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	/**
	 * ログインユーザーを管理するリポジトリ
	 */
	private final LoginUserRepository loginUserRepository;
	
	/**
	 * ログインユーザーの詳細を管理するサービスのコンストラクタ
	 * 
	 * @param loginUserRepository ログインユーザーを管理するリポジトリ
	 */
	public CustomUserDetailsService(LoginUserRepository loginUserRepository) {
		//ログインユーザーを管理するリポジトリを初期化する
		this.loginUserRepository = loginUserRepository;
	}
	
	
	/**
	 * ユーザー名からログインユーザーの詳細を取得する
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//ログインユーザーを表す変数loginUserを定義する
		//ログインユーザーを管理するリポジトリにユーザー名からログインユーザーを検索するよう依頼
		Optional<LoginUser> loginUser = loginUserRepository.findByUserName(username);
		//ログインユーザーがみつからなかった場合は例外を投げる
		if(loginUser.isEmpty()) {
			throw new UsernameNotFoundException("ログインユーザーが見つかりませんでした");
			
		}
		return User
				//ログインユーザーのユーザー名を設定する
				.withUsername(loginUser.get().getUserName())
				//ログインユーザーのパスワードを設定する
				.password(loginUser.get().getPassword())
				//ログインユーザーが無効か設定する
				//有効を反転して無効にしている
				.disabled(!loginUser.get().isEnabled())
				//ログインユーザーのロールを設定する
				.roles("USER")
				.build();
	}

}
