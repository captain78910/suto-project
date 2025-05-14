package jp.levtech.rookie.sutoproject.model;

import lombok.Value;

/**
 * タスクを表すモデル
 */
@Value
public class Task {
	/**
	 * タスクIDを表すプロパティ
	 */
	private final long taskId;
	
	/**
	 * タスク名を表すプロパティ
	 */
	private final String taskName;
	
	/**
	 * タスクが完了したか表すプロパティ
	 */
	
	private final boolean completed;

}
