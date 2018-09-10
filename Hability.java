import br.ufsc.inf.leobr.cliente.Jogada;

public class Hability implements Jogada 
{
	private int Power, Accuracy, Hits;
	private String Name, Status, NameOfGod;
	private Boolean Magic;
	
	Hability(String Name, int Power, int Accuracy, int Hits, Boolean Magic, String Status, String NameOfGod)
	{
		this.Name = Name;
		this.Power = Power;
		this.Accuracy = Accuracy;
		this.Hits = Hits;
		this.Magic = Magic;
		this.Status = Status;
	}

	protected int getPower() 
	{
		return Power;
	}

	protected int getAccuracy() 
	{
		return Accuracy;
	}

	protected int getHits() 
	{
		return Hits;
	}

	protected String getName() 
	{
		return Name;
	}

	protected Boolean getMagic() 
	{
		return Magic;
	}
	
	public String getStatus()
	{
		return Status;
	}

	public String getNameOfGod() {
		return NameOfGod;
	}
}