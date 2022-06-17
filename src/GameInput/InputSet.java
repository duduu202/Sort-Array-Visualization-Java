package GameInput;

import java.util.ArrayList;

public class InputSet {
	
	private ArrayList<Integer> inputsInt = new ArrayList<Integer>();
	public void add(Integer in) {
		if(!inputsInt.contains(in)) {
			inputsInt.add(in);
			//System.out.println("Tecla pressionada: " + in);
		}
	}
	public void remove(Integer in) {
		if(inputsInt.contains(in)) {
			inputsInt.remove(in);
			//System.out.println("Adicionado: " + in);
		}
	}
	public boolean isPressed(Integer in) {
		return inputsInt.contains(in);
	}
}	
