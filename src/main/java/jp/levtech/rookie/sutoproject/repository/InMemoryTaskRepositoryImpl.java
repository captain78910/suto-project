package jp.levtech.rookie.sutoproject.repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jp.levtech.rookie.sutoproject.model.Task;

/**
 * タスクをインメモリで管理するリポジトリ
 */
@Repository
public class InMemoryTaskRepositoryImpl implements TaskRepository{
	
	/**
	 * インメモリで保持するタスク一覧
	 */
	private final List<Task> tasks = new ArrayList<>(List.of(
			new Task(1, "HTMLについて勉強する", true),
			new Task(2, "CSSについて勉強する", true),
			new Task(3, "JavaScriptについて勉強する", true),
			new Task(4, "Javaについて勉強する", false),
			new Task(5, "Spring Bootについて勉強する", false)
			));
	
	//次のIDを表すプロパティ
	private long nextId = 6;
	
	/**
	 * すべてのタスクをインメモリから検索する。
	 */
	@Override
	public List<Task> findAll() {
		//インメモリで保持しているタスク一覧を返す。
		return new ArrayList<>(tasks);
	}
	
	/**
	 * タスクIDからインメモリ上のタスクを検索する。
	 */
	@Override
	public Optional<Task> findById(long taskId) {
		//タスク一覧の中からタスクIDが等しいタスクを探し出す。
		for(Task task:tasks) {
			//タスクIDが等しいタスクが存在する場合
			if(task.getTaskId()==taskId) {
				return Optional.of(task);						
			}
		}
		//タスクIDが等しいタスクが存在しない場合
		return Optional.empty();
	}
	
	/**
	 * タスクをインメモリに登録する。
	 */
	@Override
	public void register(Task task) {
		//無効なIDが設定されているため、有効なIDを設定する。
		//Taskクラスはイミュータブルなので、新しくインスタンスを生成する必要あり。
		Task newTask = new Task(nextId++, task.getTaskName(), task.isCompleted());
		tasks.add(newTask);
	}
	
	/**
	 * インメモリ上のタスクを更新する。
	 */
	@Override
	public void update(Task task) {
		//タスク一覧の中からタスクIDが等しいタスクを探し出す。
		for (int i=0; i<tasks.size(); i++) {
			Task oldTask = tasks.get(i);
			//タスクIDが等しいタスクが存在する場合はタスクを更新して反復処理を中断する。
			if(oldTask.getTaskId() == task.getTaskId()) {
				tasks.set(i,task);
				break;
			}
		}
	}
	
	/**
	 * インメモリ上のタスクを削除する。
	 */
	
	@Override
	public void delete(Task task) {
		//タスク一覧からタスクIDが等しいタスクを探し出す。
		for(int i=0; i<tasks.size(); i++) {
			Task oldTask = tasks.get(i);
			//タスクIDが等しいタスクが存在する場合はタスクを削除して反復処理を中断
			if(oldTask.getTaskId()==task.getTaskId()) {
				tasks.remove(i);
				break;
			}
		}
	}

	

}
