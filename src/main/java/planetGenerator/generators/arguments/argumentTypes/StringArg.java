package planetGenerator.generators.arguments.argumentTypes;

public class StringArg {
	String value;
	boolean hasValue;
	
	public void setValue(String value){
		this.value = value;
		hasValue = true;
	}
	
	public String getValue(){
		return value;
	}
	
	public boolean hasValue(){
		return hasValue;
	}

}
