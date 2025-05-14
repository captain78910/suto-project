package jp.levtech.rookie.sutoproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import jp.levtech.rookie.sutoproject.model.Task;
import jp.levtech.rookie.sutoproject.repository.mybatis.TaskMapper;

/**
 * タスクをデータベースで管理するリポジトリ
 */


@Primary
@Repository
public class DatabaseTaskRepositoryImpl implements TaskRepository{
	
	/**
	 * タスクのマッパー
	 */
	private final TaskMapper taskMapper;
	
	/**
	 * タスクをデータベースで管理するリポジトリのコンストラクタ
	 * 
	 * @param taskMapper タスクのマッパー
	 */
	
	public DatabaseTaskRepositoryImpl(TaskMapper taskMapper) {
		//タスクのマッパーを初期化する。
		
		this.taskMapper = taskMapper;
	}
	
	/**
	 * すべてのタスクをデータベースから検索する。
	 */
	@Override
	public List<Task> findAll() {
		return taskMapper.findAll();
		
	}
	
	/**
	 * タスクIDからデータベース上のタスクを検索する。
	 */
	@Override
	public Optional<Task> findById(long TaskId) {
		return taskMapper.findById(TaskId);
	}
	
	/**
	 * タスクをデータベースに登録する。
	 */
	
	@Override
	public void register(Task task) {
		taskMapper.register(task);
	}
	
	/**
	 * データベース上のタスクを更新する。
	 */
	@Override
	public void update(Task task) {
		taskMapper.update(task);
	}
	
	/**
	 * データベース上のタスクを削除する
	 */
	@Override
	public void delete(Task task) {
		taskMapper.delete(task);
	}
	
	

}
