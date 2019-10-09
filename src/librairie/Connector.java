package librairie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Connector {
	protected String InputName;
	protected String OutputName;
	protected AtomicComponent SourceComponent;
	protected ArrayList<AtomicComponent> DestinationComponent;
	protected double Value;
	
	public Connector(String InputName, String OutputName)
	{
		this.InputName=InputName;
		this.OutputName=OutputName;
		Value=0.0;
	}
	
	public void Connect(AtomicComponent SourceComponent,ArrayList<AtomicComponent> DestinationComponent)
	{
		SourceComponent.setOutputs(InputName,Value);
		for(AtomicComponent c : DestinationComponent)
		{
			c.setInputs(OutputName,Value);
		}
	}
	
	public void Transmit()
	{
		Value=(SourceComponent.getOutputs()).get(InputName);
		
		for(AtomicComponent c : DestinationComponent)
		{
			c.setInputs(OutputName,Value);
		}
	}
	
}
