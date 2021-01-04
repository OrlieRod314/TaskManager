package dataStructures;

/**
 * Class representing a Task for the TaskManager
 * @author Orlando Rodriguez
 *
 */
public class Task implements Comparable<Task>{
	public String name;
	public String description;
	public int priority;
	
	public Task() {
		this.name = null;
		this.description = null;
		this.priority = Integer.MAX_VALUE;
	}
	
	public Task(String name, String description, int priority) {
		this.name = name;
		this.description = description;
		this.priority = priority;
	}
	
	public String toString() {
		return "(" + priority + ") " + name + ": " + description; 
	}

	@Override
	/**
	 * Used for implementation in Priority Queue
	 * @return int 
	 */
	public int compareTo(Task t) {
		return Integer.compare(this.priority, t.priority);
	}
	
	/**
	 * Checks if two tasks are equivalent
	 * @return boolean
	 */
	public boolean equals(Object o) {
		if (o instanceof Task) {
			Task other = (Task) o;
			if (other.name.equals(this.name) && other.description.equals(this.description) && other.priority == this.priority)
				return true;
			return false;
		}
		return false;
	}
}