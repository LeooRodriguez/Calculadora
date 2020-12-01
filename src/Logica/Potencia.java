package Logica;

public class Potencia implements PluginFunction{
	
	protected double num1;
	protected double num2;

	@Override
	public String getPluginName() {
		return "Potencia";
	}

	@Override
	public void setParameters(double num1, double num2) {
		this.num1 = num1;
		this.num2 = num2;
		
	}

	@Override
	public double getResult() {
		double total=1;
		for(int i=0;i<=num1;i++) {
			total=total*num2;
		}
		return total;
	}

	@Override
	public boolean hasError() {
		return false;
	}


}
