package main;

import java.util.ArrayList;

import kernel.Scheduler;
import librairie.Buffer;
import librairie.AtomicComponent;
import librairie.Generator;
import librairie.Processor;

public class Main {

	public static void main(String[] args) {
		AtomicComponent P=new Processor();
		AtomicComponent G=new Generator();
		AtomicComponent B=new Buffer();
	
		ArrayList<AtomicComponent> Modele=new ArrayList<AtomicComponent>(3);
		
		Modele.add(G);
		Modele.add(B);
		Modele.add(P);
		
		Scheduler ordonanceur=new Scheduler();
		ordonanceur.run(Modele);

	}

}
