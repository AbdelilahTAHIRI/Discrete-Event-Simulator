package main;

import java.util.ArrayList;

import kernel.Scheduler;
import librairie.Buffer;
import librairie.Adder;
import librairie.AtomicComponent;
import librairie.Generator;
import librairie.Processor;
import librairie.Step;

public class Main {

	public static void main(String[] args) 
	{
		//AtomicComponent P=new Processor();
		//AtomicComponent G=new Generator();
		//AtomicComponent B=new Buffer();
		
		AtomicComponent step1= new Step(1.0,-3.0, 0.65,"step0");
		AtomicComponent step2= new Step(0.0, 1.0, 0.35,"step1");
		AtomicComponent step3= new Step(0.0, 1.0, 1.0 ,"step2");
		AtomicComponent step4= new Step(0.0, 4.0, 1.5 ,"step3");
		
		AtomicComponent adder= new Adder(4,"step");
		
		ArrayList<AtomicComponent> Modele=new ArrayList<AtomicComponent>();
		
		//Modele.add(G);
		//Modele.add(B);
		//Modele.add(P);
		
		Modele.add(step1);
		Modele.add(step2);
		Modele.add(step3);
		Modele.add(step4);
		Modele.add(adder);
		
		Scheduler ordonanceur=new Scheduler();
		ordonanceur.run(Modele);
	}

}
