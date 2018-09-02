package planetGenerator.generators.arguments.argumentTypes;

public class IntArg {
	int value;
	boolean hasValue;
	
	public void setValue(int value){
		this.value = value;
		hasValue = true;
	}
	
	public int getValue(){
		return value;
	}
	
	public boolean hasValue(){
		return hasValue;
	}

}
