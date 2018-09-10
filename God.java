import java.util.ArrayList;

public class God 
{
	private String Name, Status;
	private int Life,
				Attack,
				MagicAttack;
	private ArrayList<Hability> Habilities;
	
	protected God(String Name, int Life, int Attack, int MagicAttack, 
					 ArrayList<Hability> Habilities, String Status)
	{
		this.Name = Name;
		this.Life = Life;
		this.Attack = Attack;
		this.MagicAttack = MagicAttack;
		this.Habilities = Habilities;
		this.Status = Status;
	}

	protected String getStatus() {
		return Status;
	}
	
	protected void setStatus(String status) {
		Status = status;
	}

	public String getName() 
	{
		return Name;
	}

	public int getLife() 
	{
		return Life;
	}

	protected void setLife(int life) 
	{
		Life = life;
	}
	
	public int getAttack() 
	{
		Attack = (1+(Attack/100));
		return Attack;
	}

	public int getMagicAttack() 
	{
		MagicAttack = (1+(MagicAttack/100));
		return MagicAttack;
	}
	protected ArrayList<Hability> getHabilities() 
	{
		return Habilities;
	}
}