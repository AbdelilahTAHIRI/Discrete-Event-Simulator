package librairie;
import java.util.HashMap;

public class Buffer extends AtomicComponent{
	
	protected int q;
	public Buffer()
	{
		super();
		tr=Double.MAX_VALUE;
		Inputs.put("job", 0.0);
		Inputs.put("done", 0.0);
		Outputs.put("req", 0.0);

	}
	public void external() {
		if(State==0 && Inputs.get("job")==1)
		{
			State=1;
			q++;
			tr=0;
			this.setInputs("job",0.0);
		}
		else if(State==1 && Inputs.get("job")==1)
		{	
			State=1;
			tr=0;
			q++;
			this.setInputs("job",0.0);
		}
		else if(State==2 && Inputs.get("job")==1)
		{	
			State=2;
			tr=Double.MAX_VALUE;
			q++;
			this.setInputs("job",0.0);
		}
		else if(State==2 && Inputs.get("done")==1 && q>0)
		{	
			State=1;
			tr=0;
			this.setInputs("done",0.0);
		}
		else if(State==2 && Inputs.get("done")==1 && q==0)
		{	
			State=0;
			tr=Double.MAX_VALUE;
			this.setInputs("done",0.0);
		}
	}

	public void internal() {
		// TODO Auto-generated method stub
		if(State==1)
		{
			State=2;
			q--;
			tr=Double.MAX_VALUE;
		}
	}

	public HashMap<String, Double> lambda() {
		// TODO Auto-generated method stub
		HashMap<String,Double> s=new HashMap<String,Double>();
		if(State==0 || State==2)
			s.put("req", 0.0);
		else if(State==1)
			s.put("req", 1.0);
		return s;
	}

	public double avancement() 
	{
		// TODO Auto-generated method stub
		if(State==0 || State==2)
			return Double.MAX_VALUE;
		else
			return 0.0;
	}

	public int getQ() {
		return q;
	}
	public void setQ(int q) {
		this.q = q;
	}

}
