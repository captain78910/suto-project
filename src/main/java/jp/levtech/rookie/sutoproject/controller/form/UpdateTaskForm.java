package jp.levtech.rookie.sutoproject.controller.form;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * タスクを更新するためのフォーム
 */
@Data
@AllArgsConstructor

public class UpdateTaskForm {
	
	/**
	 * タスク名を表すプロパティ
	 */
	@NotBlank(message = "タスク名は必須です。")
	private String taskName;
	
	/**
	 * タスクが完了したか表すプロパティ
	 */
	private boolean completed;

}
