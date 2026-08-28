package larp;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the collection of tasks tracked by Larp.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing the supplied tasks.
     *
     * @param tasks Initial tasks to place in the list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    public Task markAsDone(int index) {
        Task task = get(index);
        task.markAsDone();
        return task;
    }

    public Task markAsNotDone(int index) {
        Task task = get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Returns a copy of the tasks for saving or display.
     *
     * @return Copy of the current tasks.
     */
    public List<Task> getTasks() {
        return List.copyOf(this.tasks);
    }
}
