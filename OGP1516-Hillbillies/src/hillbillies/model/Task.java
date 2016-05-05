package hillbillies.model;

import java.util.List;

public class Task {
	public Task(String name, int priority, List activitylist, Unit unit){}
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

}
