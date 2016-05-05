package hillbillies.model;

import java.awt.List;
import java.util.HashSet;
import java.util.Set;

public class Task {
		public Task(String name, int priority, List activitylist, Unit unit){
			this.priority=priority;
		}
		private int priority;
		private String name;
	private Set Schedulers= new HashSet<>();
	private boolean inExecution=false;
	private Unit unit=null;
	public boolean isBeingExecuted(){
		return inExecution;
	}
	public void SetInexecution( boolean b) {
		this.inExecution=b;
	}
	
	public void AssignTaskToUnit(Unit u){
		
		if (u==null) {
			this.unit=null;
		}
		if (unit==null) {
			this.unit=u;
		}
		
	}
	public Unit getUnit(){
		return this.unit;
	}
	private boolean isValidPriority(int i){
		if (i<0) {
			return false;
		}
		else {
			return true;
		}
	}
	/**
	 * Return the priority of this Task.
	 */
	public int getPriority() {
		return priority;
	}

/**
 * Return the name of this Task.
 */
public String getName(){
	return this.name;
}
/**
 * Return the Schedulers  this Task is part of
 */
	public Set<Scheduler> getSchedulers() {
		return this.Schedulers;
	}
	
	
	public void addScheduler(Scheduler scheduler){
		Schedulers.add(scheduler);
	}
}
