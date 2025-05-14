package jp.levtech.rookie.sutoproject.controller.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateTaskForm {
	
	/**
	 * タスク名を表すプロパティ
	 */
	@NotBlank(message="タスク名は必須です")
	private String taskName;

}
