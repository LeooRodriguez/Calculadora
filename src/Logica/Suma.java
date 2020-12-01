package Logica;

public class Suma implements PluginFunction{
	
	protected double num1;
	protected double num2;

	@Override
	public String getPluginName() {
		return "Suma";
	}

	@Override
	public void setParameters(double num1, double num2) {
		this.num1 = num1;
		this.num2= num2;
		
	}

	@Override
	public double getResult() {
		return num1 + num2;
	}

	@Override
	public boolean hasError() {
		return false;
	}

}