package librairie;
import java.util.HashMap;
public abstract class AtomicComponent 
{

	protected double e;
	protected double tl;
	protected double tr;
	protected double tn;
	protected int State;
	protected HashMap<String, Double> Inputs;
	protected HashMap<String, Double> Outputs;
	protected boolean ins;

	public AtomicComponent() {
		Inputs = new HashMap<String, Double>();
		Outputs = new HashMap<String, Double>();
		e = 0.0d;
		tl = 0.0d;
		tn = 0;
		State = 0;
		ins = false;
	}

	public abstract void external();// fonction de transition externe

	public abstract void internal();// fonction de transition interne

	public abstract HashMap<String, Double> lambda();// fonction de sortie

	public abstract double avancement();// fonction d'avancement

	public double getE() {
		return e;
	}
	public void setE(double e) {
		this.e = e;
	}

	public double getTl() {
		return tl;
	}

	public void setTl(double tl) {
		this.tl = tl;
	}

	public double getTr() {
		return tr;
	}

	public void setTr(double tr) {
		this.tr = tr;
	}

	public double getTn() {
		return tn;
	}

	public void setTn(double tn) {
		this.tn = tn;
	}

	public HashMap<String, Double> getInputs() {
		return Inputs;
	}

	public void setInputs(String Key, Double Value) {
		Inputs.put(Key, Value);
	}

	public HashMap<String, Double> getOutputs() {
		return Outputs;
	}

	public void setOutputs(String Key, Double Value) {
		Outputs.put(Key, Value);
	}

	public boolean isIns() {
		return ins;
	}

	public void setIns(boolean ins) 
	{
		this.ins = ins;
	}

	public void conflict() 
	{
		System.out.println("conflit");
	}
}
