package librairie;

import java.util.HashMap;

public class Step extends AtomicComponent {

	protected double xi;
	protected double xf;
	protected double ts;
	protected String NameOutput;
	public Step() 
	{
		super();
		tr=0.0;
		this.xi=0;
		this.xf=1;
		this.ts=1;
	}
	public Step(double xi,double xf,double ts, String NameOutput) {
		super();
		this.xi=xi;
		this.xf=xf;
		this.ts=ts;
		tr=0.0;
		this.NameOutput=NameOutput;
		Outputs.put(NameOutput, 0.0);
	}
	
	public void external()// fonction de transition externe
	{
		return;
	}

	public void internal()// fonction de transition interne
	{
		if(State == 0)
		{
			State=1;
		}
		else if(State == 1)
		{
			State=2;
		}
		else if(State==2)
		{
			State=2;
		}		
	}
	
	public HashMap<String, Double> lambda()// fonction de sortie
	{
		HashMap<String,Double> s=new HashMap<String,Double>();
		if(State==0)
			s.put(NameOutput, xi);
		else if(State==1)
			s.put(NameOutput, xf);
		return s;
	}
	public double avancement()// fonction d'avancement
	{
		if(State==0)
			return 0.0;
		else if(State==1)
			return ts;
		else if(State==2)
		{
			return Double.MAX_VALUE;
		}
		else
			return Double.MAX_VALUE;
	}
}