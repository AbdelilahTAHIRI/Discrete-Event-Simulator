package librairie;
import java.util.HashMap;

public class Generator extends AtomicComponent{
	public Generator()
	{
		super();
		tr=2;
		Outputs.put("job",0.0);
	}
	
	public void external()//fonction de transition esterne
	{
		State=0;
	}
	public void internal()//fonction de transition interne
	{
		State=0;
		tr=2;
	}
	
	public HashMap<String,Double> lambda()//fonction de sortie 
	{
		HashMap<String,Double> s=new HashMap<String,Double>();
		s.put("job", 1.0);
		return s;
	}
	public double avancement() //fonction d'avancement 
	{
		return 2.0d;
	}
}