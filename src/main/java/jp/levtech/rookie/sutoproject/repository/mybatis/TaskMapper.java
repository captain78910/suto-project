package jp.levtech.rookie.sutoproject.repository.mybatis;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import jp.levtech.rookie.sutoproject.model.Task;


/**
 * タスクのマッパー
 */
@Mapper
public interface TaskMapper {
	
	/**
	 * すべてのタスクを検索する。
	 * 
	 * @return タスク一覧
	 */
	
	List<Task> findAll();
	
	/**
	 * タスクIDからタスクを検索する。
	 * 
	 * @param taskId タスクID
	 * @return タスク
	 */
 	Optional<Task> findById(long taskId);
 	
 	/**
 	 * タスクを登録する。
 	 * 
 	 * @param task タスク
 	 */
 	
 	void register(Task task);
 	
 	/**
 	 * タスクを更新する。
 	 * 
 	 * @param task タスク
 	 */
 	
 	void update(Task task);
 	
 	/**
 	 * タスクを削除する・
 	 * 
 	 * @param task タスク
 	 */
 	
 	void delete(Task task);
	
	
}
