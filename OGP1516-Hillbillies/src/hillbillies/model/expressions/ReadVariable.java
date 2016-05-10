package hillbillies.model.expressions;


public class ReadVariable extends Expression {
public  ReadVariable(String v){
	this.name=v;
	
}

private String name;
	
public double evaluate(){
if(name=="insertvariable")
		return 0;
else{
	return 0;
}
}}
