import br.ufsc.inf.leobr.cliente.Jogada;

public class Move implements Jogada 
{
	//Qualquer tipo
	protected Hability Hability ;
	protected String NameGodOne, NameGodTwo,StatusAtacante,StatusAplicado;
	protected int TotalDamage;
	protected Boolean GameOver;
	
	Move(Hability hability, String onegod, String twogod,String statusAtacante,int damageRecebido,String statusFRecebido, Boolean over)
	{
		super();
		this.Hability = hability;
		this.NameGodOne = onegod;
		this.NameGodTwo = twogod;
		this.StatusAtacante = statusAtacante;
		this.TotalDamage = damageRecebido;
		this.StatusAplicado = statusFRecebido;
		this.GameOver = over;
	}

	public Hability getMove()
	{
		return Hability;
	}
	public String getNameone()
	{
		return NameGodOne;
	}
	public String getNametwo()
	{
		return NameGodTwo;
	}
	public String statusAtacante(){
		return StatusAtacante;
	}
	public int getTotalDamage()
	{
		return TotalDamage;
	}
	public String FrontlineStatus()
	{
		return StatusAplicado;
	}
	public Boolean getOver(){
		return GameOver;
	}
	
}
