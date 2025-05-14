package jp.levtech.rookie.sutoproject.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import jp.levtech.rookie.sutoproject.controller.form.CreateTaskForm;
import jp.levtech.rookie.sutoproject.controller.form.UpdateTaskForm;
import jp.levtech.rookie.sutoproject.model.Task;
import jp.levtech.rookie.sutoproject.repository.TaskRepository;


/**
 * ToDOを管理するコントローラー
 */
@Controller
public class ToDoController {
	
	/**
	 * タスクを管理するリポジトリ
	 */
	private final TaskRepository taskRepository;
	
	/**
	 * ToDoを管理するコントローラーのコンストラクタ
	 * 
	 * @param taskRepository タスクを管理するリポジトリ
	 */
	public ToDoController(TaskRepository taskRepository) {
		//タスクを管理するリポジトリを初期化する。
		this.taskRepository = taskRepository;
	}
	
	
	/**
	 * ToDo一覧画面を扱う。
	 * 
	 * @param model モデル
	 * @return テンプレート
	 */
	@GetMapping("/todo/")
	public String index(Model model) {
		//タスク一覧を表す変数tasksを定義する。
		//タスクを管理するリポジトリに、すべてのタスクを検索するよう依頼する。
		List<Task> tasks = taskRepository.findAll();
		//変数tasksをテンプレート側からtasksという名前の変数として利用するための設定を追加。
		
		model.addAttribute("tasks",tasks);
		//レンダリングに利用するテンプレート名を返す。
		return "todo/index";
	}
	
	/**
	 * ToDo詳細画面を扱う。
	 *
	 * @param taskId タスクID
	 * @param model モデル
	 * @return テンプレート
	 */
	@GetMapping("/todo/{taskId}")
	public String details(@PathVariable long taskId, Model model) {
		//タスクを表す変数taskを定義する。
		//タスクを管理するリポジトリにタスクIDからタスクを検索するよう依頼する。
		Optional<Task> task = taskRepository.findById(taskId);
		//タスクが見つからなかった場合は例外を投げる。
		if(task.isEmpty()) {
			throw new TaskNotFoundException();
		}
		//変数taskをテンプレート側からtaskという名前の変数として利用する設定。
		model.addAttribute("task" ,task.get());
		
		return "todo/details";
		
	}
	
	/**
	 * ToDo作成画面を扱う。
	 *
	 * @param model モデル
	 * @return テンプレート
	 */
	@GetMapping("/todo/create")
	public String create(Model model) {
		//フォームを表す変数createTaskFormを定義する。
		CreateTaskForm createTaskForm = new CreateTaskForm();
		//変数createTaskFormをテンプレート側からcreateTaskFormという名前の変数として利用する設定を追加する。
		model.addAttribute("createTaskForm" , createTaskForm);
		//レンダリングに利用するテンプレート名を返す。
		return "todo/create";
	}
	
	/**
	 * タスクを登録する。
	 * 
	 * @param createTaskForm タスクを作成するためのフォーム
	 * @param bindingResult バリデーションの結果
	 * @return テンプレート/リダイレクト
	 */
	@PostMapping("/todo/register")
	public String register(@ModelAttribute @Valid CreateTaskForm createTaskForm, BindingResult bindingResult) {
		//バリデーションエラーがある場合
		if(bindingResult.hasErrors()) {
			//再度作成画面を返す。
			return "todo/create";
		}
		//タスクを表す変数taskを初期化する。
		//taskIdには無効な値として0を設定する。
		//completedには未完了としてfalseを設定する。
		Task task = new Task(0L,createTaskForm.getTaskName(), false);
		//タスクを管理するリポジトリに、タスクを登録するよう依頼する。
		taskRepository.register(task);
		//リダイレクトするためのHTTPレスポンスを返す。
		return "redirect:/todo/";
	}
	
	/**
	 * タスクを編集する。
	 * 
	 * @param taskId タスクID
	 * @param model モデル
	 * @return テンプレート
	 */
	@GetMapping("/todo/{taskId}/edit")
	public String edit(@PathVariable long taskId,  Model model) {
		//タスクを表す変数taskを定義する。
		//タスクを管理するリポジトリにタスクIDからタスクを検索するように依頼する。
		Optional<Task>task = taskRepository.findById(taskId);
		
		
		//タスクが見つからない場合は例外を投げる。
		if(task.isEmpty()) {
			throw new TaskNotFoundException();
		}
		//フォームを表す変数updateTaskFormを定義する
		//修正まえのタスク名とタスクが完了したか設定する。
		UpdateTaskForm updateTaskForm= new UpdateTaskForm(task.get().getTaskName(), task.get().isCompleted());
		// 変数updateTaskFormをテンプレート側からupdateTaskFormという名前の変数として利用するための設定を追加する。
		model.addAttribute("updateTaskForm",updateTaskForm);
		// 変数taskをテンプレート側からtaskという名前の変数として利用するための設定を追加する。
			
				model.addAttribute("taskId", taskId);
				
				 // === モデルに入ってる属性の確認ログ ===
				System.out.println(taskRepository.findById(taskId));
			    System.out.println("=== Model Attributes ===");
			    model.asMap().forEach((key, value) -> {
			        System.out.println(key + " = " + value);
			    });
				
				// レンダリングに利用するテンプレート名を返す。
				return "todo/edit";
	}
	
	/**
	 * タスクを更新する。
	 *
	 * @param taskId タスクID
	 * @param updateTaskForm タスクを更新するためのフォーム
	 * @param bindingResult バリデーションの結果
	 * @return テンプレート/リダイレクト
	 */
	@PostMapping("/todo/{taskId}/update")
	public String update(@PathVariable long taskId, @ModelAttribute @Valid UpdateTaskForm updateTaskForm, BindingResult bindingResult) {
		//バリデーションエラーがある場合。
		if(bindingResult.hasErrors()) {
			//再度編集画面を返す
			return "todo/edit";
		}
		// タスクを表す変数taskを定義する。
		// タスクを管理するリポジトリに、タスクIDからタスクを検索するよう依頼する。
		Optional<Task> task = taskRepository.findById(taskId);
		// タスクが見つからなかった場合は例外を投げる。
		if (task.isEmpty()) {
			throw new TaskNotFoundException();
		}
		// 編集済みのタスクを表す変数editedTaskを定義する。
		Task editedTask = new Task(taskId,  updateTaskForm.getTaskName(), updateTaskForm.isCompleted());
		// タスクを管理するリポジトリに、タスクを更新するよう依頼する。
		taskRepository.update(editedTask);
		// リダイレクトするためのHTTPレスポンスを返す。
		return "redirect:/todo/{taskId}";
		
		
	}
	
	/**
	 * タスクを削除する。
	 * 
	 * @param taskId タスクID
	 * @return リダイレクト
	 */
	@PostMapping("/todo/{taskId}/delete")
	public String delete(@PathVariable long taskId) {
		//タスクを表す変数taskを定義する。
		//タスクを管理するリポジトリにタスクIDからタスクを検索するよう依頼する。
		Optional<Task> task= taskRepository.findById(taskId);
		//タスクが見つからなければ例外
		if (task.isEmpty()) {
			throw new TaskNotFoundException();
		}
		//タスクを管理するリポジトリにタスクを削除するよう依頼する。
		taskRepository.delete(task.get());
		//りだいれくとするためのHTTPレスポンスを返す。
		return "redirect:/todo/";
	}	
	
	/**
	 * タスクが存在しなかったことを表す例外。
	 * 
	 * 404 Not Foundを表示する。
	 */
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	private class TaskNotFoundException extends RuntimeException {
		
	}

}
