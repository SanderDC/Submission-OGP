package hillbillies.model;

import java.util.List;
import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
/** TO BE ADDED TO CLASS HEADING
 * @invar  The property_name_Eng of each object_name_Eng must be a valid property_name_Eng for any
 *         object_name_Eng.
 *       | isValidPropertyName_Java(getPropertyName_Java())
 */
public class Scheduler {
		public Scheduler(Faction faction ){
			
		}
		

/**
 * Return the tasks of this List<Task>.
 */
@Basic @Raw
public List<Task> getTasks() {
	return this.tasks;
}

/**
 * Check whether the given tasks is a valid tasks for
 * any List<Task>.
 *  
 * @param  tasks
 *         The tasks to check.
 * @return 
 *       | result == 
*/
public static boolean isValidTasks(List tasks) {
	return false;
}



/**
 * Variable registering the property_name_Eng of this List<Task>.
 */
private List <Task> tasks=new ArrayList<>();



public void AddOneTask(Task task){
	this.tasks.add(task);
}
public void RemoveOneTask(Task task) {
	this.tasks.remove(task);
}

public void AssignTaskToUnit(Unit unit, Task task){
	if (!task.isBeingExecuted()) {
		task.AssignTaskToUnit(unit);
		unit.setTask(task);
	}
	
}
public void unAssignTaskOfUnit(Unit unit) {
	unit.getTask().AssignTaskToUnit(null);
	unit.setTask(null);
	
}

public Task getTopPriorityTask(){
	return null;
}
public List<Task> getConditionalTask(){
	return null;
}
public void replaceTask(){
	
}

}
