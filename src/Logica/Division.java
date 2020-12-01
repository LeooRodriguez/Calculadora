package Logica;

public class Division implements PluginFunction {
	
	protected double num1;
	protected double num2;

	@Override
	public String getPluginName() {
		return "Division";
	}

	@Override
	public void setParameters(double num1, double num2) {
		this.num1 = num1;
		this.num2 = num2;
		
	}

	@Override
	public double getResult() {
		double result = 0;
		if(num2 != 0) {
			result = num1/num2;
		}
		return result;
	}

	@Override
	public boolean hasError() {
		boolean toRet;
		if(num2 == 0) {
			toRet = true;
		}
		else {
			toRet = false;
		}
		return toRet;
	}

}