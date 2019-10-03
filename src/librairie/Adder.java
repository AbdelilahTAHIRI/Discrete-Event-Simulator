package librairie;

import java.util.HashMap;
import java.util.Map.Entry;

public class Adder extends AtomicComponent 
{
	protected double sum;
	public Adder(int NumInputs, String NameInputs)
	{
		super();
		tr=Double.MAX_VALUE;
		int i;
		for(i=0;i<NumInputs;i++)
		{
			Inputs.put(NameInputs+i, 0.0);	
		}
		Outputs.put("sum", 0.0);
	}
	
	public void external()// fonction de transition externe
	{
		double Value;
		String Key;
		if(State==0)
		{
			for (Entry<String, Double> entry: Inputs.entrySet()) 
			{
			   Key = entry.getKey();
			   Value = entry.getValue();
				if(Inputs.get(Key)!=0)
				{
					State=1;
					sum+=Value;
					this.setInputs(Key,0.0);
				}
			}
		}
	}
	public void internal()// fonction de transition interne
	{
		if(State == 1)
		{
			State=0;
		}
	}
	
	public HashMap<String, Double> lambda()// fonction de sortie
	{
		HashMap<String,Double> s=new HashMap<String,Double>();
		s.put("sum", sum);
		return s;
	}
	
	public double avancement()// fonction d'avancement
	{
		if(State == 0)
		{
			return Double.MAX_VALUE;
		}
		else
		{
			return 0;
		}
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
}
