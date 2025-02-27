package org.example.todoapispring;

import jakarta.persistence.*;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private boolean completed;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private int userId;

	public Todo() {
	}

	public Todo(int id, boolean completed, String title, int userId) {
		this.id = id;
		this.completed = completed;
		this.title = title;
		this.userId = userId;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public boolean isCompleted() { return completed; }
	public void setCompleted(boolean completed) { this.completed = completed; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public int getUserId() { return userId; }
	public void setUserId(int userId) { this.userId = userId; }

	@Override
	public String toString() {
		return "Todo{" +
				"id=" + id +
				", completed=" + completed +
				", title='" + title + '\'' +
				", userId=" + userId +
				'}';
	}
}
