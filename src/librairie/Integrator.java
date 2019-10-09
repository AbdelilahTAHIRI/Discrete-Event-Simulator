package librairie;

import java.util.HashMap;

public class Integrator extends AtomicComponent
{
	protected double hstep;
	protected double CurrentXpoint;//la dérivé actuelle
	protected double X;//La sortie
	
	public double getHstep() {
		return hstep;
	}

	public void setHstep(double hstep) {
		this.hstep = hstep;
	}

	public double getCurrentXpoint() {
		return CurrentXpoint;
	}

	public void setCurrentXpoint(double currentXpoint) {
		CurrentXpoint = currentXpoint;
	}

	public double getX() {
		return X;
	}

	public void setX(double x) {
		X = x;
	}

	public Integrator(double hstep)
	{
		super();
		this.hstep=hstep;
		tr=hstep;
		Inputs.put("sum",0.0);
		Outputs.put("X",0.0);
	}
	
	public void external()//fonction de transition esterne
	{
		if(State == 0 && Inputs.get("sum")!=CurrentXpoint)
		{
			X=X+CurrentXpoint*e;
			CurrentXpoint=Inputs.get("sum");
		}
	}
	public void internal()//fonction dde transition interne
	{
		if(State == 0)
		{
			X=X+CurrentXpoint*hstep;
		}
	}
	
	public HashMap<String,Double> lambda()//fonction de sortie 
	{
		HashMap<String,Double> s=new HashMap<String,Double>();
		s.put("X",X);
		return s;
	}
	
	public double avancement()//fonction d'avancement 
	{
		if(State==0)
			return hstep;
		else
			return Double.MAX_VALUE;
	}
}
