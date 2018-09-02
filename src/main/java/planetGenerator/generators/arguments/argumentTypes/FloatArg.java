package planetGenerator.generators.arguments.argumentTypes;

public class FloatArg {
	float value;
	boolean hasValue;
	
	public void setValue(float value){
		this.value = value;
		hasValue = true;
	}
	
	public float getValue(){
		return value;
	}
	
	public boolean hasValue(){
		return hasValue;
	}

}
