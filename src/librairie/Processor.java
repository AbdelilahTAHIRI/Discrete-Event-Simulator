package librairie;
import java.util.HashMap;

public class Processor extends AtomicComponent 
{
	public Processor()
	{
		super();
		tr=Double.MAX_VALUE;
		Inputs.put("req",0.0);
		Outputs.put("done",0.0);
		
	}
	public void external()//fonction de transition esterne
	{
		if(State == 0 && Inputs.get("req")==1)
		{
			State=1;
			//tr=3;
			this.setInputs("req",0.0);
		}
	}
	public void internal()//fonction dde transition interne
	{
		if(State == 1)
		{
			State=0;
			//tr=Double.MAX_VALUE;
		}
			
	}
	
	public HashMap<String,Double> lambda()//fonction de sortie 
	{
		HashMap<String,Double> s=new HashMap<String,Double>();
		if(State==0)
			s.put("done",0.0);
		else
			s.put("done",1.0);
		return s;
	}
	public double avancement()//fonction d'avancement 
	{
		if(State==0)
			return Double.MAX_VALUE;
		else
			return 3.0;
	}
}
