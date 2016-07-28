package cn.nonocast.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="task")
public class Task extends ModelBase {
	public interface TaskView extends ModelBase.JsonViewBase {};

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="belongs_to")
    private User belongsTo;

    private String belongsToName;

//	@JsonView(TaskView.class)
	@JsonProperty("user")
	private String belongsToEmail;

    @NotNull
    @JsonView(TaskView.class)
    @JsonProperty("title")
    private String content;

    @Enumerated(EnumType.ORDINAL)
//    @JsonView(TaskView.class)
    private TaskCategory category;

    @Enumerated(EnumType.ORDINAL)
//    @JsonView(TaskView.class)
    private TaskStatus status;

    @Enumerated(EnumType.ORDINAL)
//    @JsonView(TaskView.class)
    private TaskPriority priority;

    private Date closedAt;

    public User getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(User belongsTo) {
        this.belongsTo = belongsTo;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public Date getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Date closedAt) {
        this.closedAt = closedAt;
    }

    public String getBelongsToName() {
        return belongsToName;
    }

    public void setBelongsToName(String belongsToName) {
        this.belongsToName = belongsToName;
    }

	public String getBelongsToEmail() {
		return belongsToEmail;
	}

	public void setBelongsToEmail(String belongsToEmail) {
		this.belongsToEmail = belongsToEmail;
	}

	public Task() {

    }

    public Task(String content) {
        this();
        this.content = content;
    }

    public enum TaskCategory {
        DAILY("当日目标"),          // 0
        SHORTTERM("短期目标"),      // 1
        LONGTERM("长期目标");       // 2

        private String name;

        TaskCategory(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum TaskStatus {
        OPEN("活动"),             // 0
        CLOSE("关闭");            // 1

        private String name;

        TaskStatus(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public enum TaskPriority {
        LOW("低"), // 0
        NORMAL("标准"), // 1
        HIGHER("高"), // 2
        HIGHEST("最高"); // 3

        private String name;

        TaskPriority(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
