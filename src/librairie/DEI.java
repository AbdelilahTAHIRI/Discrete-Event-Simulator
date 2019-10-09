package librairie;

import java.util.HashMap;
public class DEI extends AtomicComponent {
	
		protected double X;//La sortie	
		protected double CurrentXpoint;//la dérivé actuelle
		protected double DeltaQ;


		public double getX() {
			return X;
		}

		public DEI(double DeltaQ)
		{
			super();
			this.DeltaQ=DeltaQ;
			tr=Double.MAX_VALUE;
			Inputs.put("sum",0.0);
			Outputs.put("X",0.0);
		}
		
		public void external()//fonction de transition esterne
		{
			if(State == 0 && Inputs.get("sum")!=CurrentXpoint)
			{
				CurrentXpoint=Inputs.get("sum");
				//System.out.println("e vaut"+e);
				X=X+Inputs.get("sum")*e;
				Inputs.put("sum",0.0);
			}
		}
		public void internal()//fonction dde transition interne
		{	
			X=X+Math.signum(CurrentXpoint)*DeltaQ;
		}
		
		public HashMap<String,Double> lambda()//fonction de sortie 
		{
			HashMap<String,Double> s=new HashMap<String,Double>();
			s.put("X",X);
			return s;
		}
		
		public double avancement()//fonction d'avancement 
		{
			if(CurrentXpoint!=0)
				return DeltaQ/Math.abs(CurrentXpoint);
			else
				return Double.MAX_VALUE;
		}	
}
