package kernel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import chart.Chart;
import chart.ChartFrame;
import librairie.Buffer;
import librairie.Adder;
import librairie.Integrator;
import librairie.DEI;
import librairie.AtomicComponent;

public class Scheduler 
{
	public void run(ArrayList<AtomicComponent> modele)
	{
		double t=0;
		double Tfin=3;
		ArrayList<AtomicComponent> Imms=new ArrayList<AtomicComponent>();
		double TrMin=Double.MAX_VALUE;
		HashMap<String,Double> sortie= new HashMap<String,Double>();
		String Key=null;
		Double Value=null;
		//for(AtomicComponent c : modele)
		//{
			//c.setTr(c.getTn());
		//}
		
		ChartFrame gui= new ChartFrame("Steps Adder Integrator","Steps Adder Integrator");
		Chart  cq= new Chart("X");
		Chart  cqDEI= new Chart("XDEI");
		Chart  Sum=new Chart("Sum");
		gui.addToLineChartPane(cq);
		gui.addToLineChartPane(cqDEI);
		gui.addToLineChartPane(Sum);
		
		while(t<=Tfin)
		{
			System.out.println("t="+t);
			//System.out.println("q="+((Buffer)modele.get(1)).getQ());
			cq.addDataToSeries(t,((Integrator)modele.get(5)).getX());
			cqDEI.addDataToSeries(t,((DEI)modele.get(6)).getX());
			Sum.addDataToSeries(t,((Adder)modele.get(4)).getSum());
			//Determiner le Tr min
			for(AtomicComponent c : modele)
			{
				TrMin=Math.min(TrMin, c.getTr());
			}
			System.out.println("TrMin="+TrMin);
			
			//Construction de l'ensemble Imms
			for(AtomicComponent c : modele)
			{
				if(c.getTr()==TrMin)
					Imms.add(c);
			}
			
			//Recupérer les sorties et mettre à jour les entrées
			for(AtomicComponent cImms : Imms)
			{
				//cImms.setTr(TrMin);
				sortie=cImms.lambda();
				for (Entry<String, Double> entry: sortie.entrySet()) 
				{
					   Key = entry.getKey();
					   Value = entry.getValue();
					   //System.out.println(Key+"|"+TrMin);
				}
				
				for(AtomicComponent c : modele)
				{
					if((c.getInputs()).containsKey(Key))
					{
						c.setInputs(Key,Value);
						c.setIns(true);
					}		
				}
				sortie.clear();
			}
			
			for(AtomicComponent c : modele)
			{
				if(Imms.contains(c) && !(c.isIns()))
				{
					//System.out.println("internal");
					
					//internal method updates Tr date
					c.internal();
					c.setTr(c.avancement());
					//mise à jour des autres variables temps
					c.setE(0);
					c.setTl(t+TrMin);
					c.setTn(t+TrMin+c.getTr());
					c.setIns(false);
				}
				else if(!(Imms.contains(c)) && c.isIns())
				{
					//System.out.println("external");
					c.external();
					c.setTr(c.avancement());//external method updates Tr Attribute
					c.setE(0);
					c.setTl(t+TrMin);
					c.setTn(t+TrMin+c.getTr());
					c.setIns(false);
				}
				else if((Imms.contains(c)) && c.isIns())
				{
					c.conflict();
					c.setE(c.getTr()+c.getE());
					c.setTr(0);
					
					//c.setTl(t+TrMin);
					//c.setTn(t+TrMin+c.getTr());
					c.setIns(false);
				}
				else
				{
					//System.out.println("else");
					c.setE(c.getE()+TrMin);
					c.setTr(c.getTr()-TrMin);
					//c.setTl(t+TrMin+c.getTl());
					//c.setTn(t+TrMin+c.getTr());
					c.setIns(false);
				}				
			}
			Imms.clear();
			t=t+TrMin;
			TrMin=Double.MAX_VALUE;
			//System.out.println("----------------------------------------------");
			//System.out.println("----------------------------------------------");
		}
	}
}
