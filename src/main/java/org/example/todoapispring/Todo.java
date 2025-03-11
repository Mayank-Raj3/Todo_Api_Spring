package org.example.todoapispring;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean completed;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private int userId;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedAt;

	public Todo() {
	}

	public Todo(int id, boolean completed, String title, int userId) {
		this.id = id;
		this.completed = completed;
		this.title = title;
		this.userId = userId;
	}

    @Override
	public String toString() {
		return "Todo{" +
				"id=" + id +
				", completed=" + completed +
				", title='" + title + '\'' +
				", userId=" + userId +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
