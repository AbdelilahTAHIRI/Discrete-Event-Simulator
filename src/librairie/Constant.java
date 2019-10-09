package librairie;

import java.util.HashMap;

public class Constant extends AtomicComponent {
	public Constant()
	{
		super();
		tr=0;
		Outputs.put("g",0.0);
	}
	public void external()//fonction de transition esterne
	{
		return;
	}
	public void internal()//fonction dde transition interne
	{	
		if(State==0)
			State=1;
	}
	
	public HashMap<String,Double> lambda()//fonction de sortie 
	{
		HashMap<String,Double> s=new HashMap<String,Double>();
		if(State==0)
			s.put("g",-9.81);
		return s;
	}
	
	public double avancement()//fonction d'avancement 
	{
		if(State==0)
			return 0;
		else
			return Double.MAX_VALUE;
	}	

}
