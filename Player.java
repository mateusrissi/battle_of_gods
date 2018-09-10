import javax.swing.JOptionPane;

public class Player 
{
	private String Name;
	private Network Network;
	private Team GodTeam;
	private Battle Partida;
	private Boolean MeuTurno;
	private Boolean Venceu = false;
	private Boolean Perdeu = false;
	
	
	Player(Team Team)
	{
		super();
		GodTeam = Team;
	}
	
	public void setNetwork(Network net)
	{
	    if(Network == null)
	    {
	        Network = net;
	    }
	}
	
	public void StartNetworkGame(Boolean myTurn) 
	{
		MeuTurno = myTurn;
		Visual.setBattleText(this.Name + " is logged");
		Visual.setBattleText(Network.getOpponent() + " is logged");
		Visual.setComecou();
		
		Partida = new Battle(this.getTeam());
		if(MeuTurno)
		{
			Partida.addPlayer(Name);
			Partida.addGuest(Network.getOpponent());
			JOptionPane.showMessageDialog(null, "You start \n Deuses do Oponente so aparecem apos receber 1 ataque");
		} else {
			Partida.addGuest(Network.getOpponent());
			Partida.addPlayer(Name);
			JOptionPane.showMessageDialog(null, "Wait your turn \n Deuses do Oponente so aparecem apos receber 1 ataque");
		}
		
	}
	public Team getTeam(){
		return GodTeam;
	}

	public void ReceiveMove(Move lance) 
	{
		if(Partida.NaoRecebiEnemy()) {
			Partida.MontarEnemyTeam(lance.getNameone(), lance.getNametwo());
		}
		if(lance.getTotalDamage() >= 0){
			Partida.MostrarResutadoAtk(lance.getTotalDamage(),lance.FrontlineStatus());
		}
		if(lance.getOver()) {
			Visual.setBattleText("Parabens voce "+ Partida.getPlayer() +" Venceu!!");
			Venceu = lance.getOver();
		}
		Hability Attack = lance.getMove();
		Partida.Fight(Attack,lance.statusAtacante());
		if(Partida.getGameOver()) {
			Visual.setBattleText("Voce Perdeu!! \n o Vencerdor foi:" + Partida.getGuest());
			Perdeu = Partida.getGameOver();
		}
		MeuTurno = true;
	}

	protected void setName(String name) 
	{
		Name = name;
	}

	public String getName() {
		return Name;
	}
	
	public void tratarMove(Hability Attack) {
		Move Lance = new Move(Attack,
				              GodTeam.get(0).getName(),
				              GodTeam.get(1).getName(),
				              Partida.StatusGod(Attack.getNameOfGod()),
				              Partida.EnviarDano(),
				              Partida.StatusFrontGod(),
				              Partida.getGameOver());
		Network.SendMove(Lance);
		MeuTurno = false;
		
	}
	public void enviarVitoria(Hability Attack) {
		Move Lance = new Move(Attack,
	              GodTeam.get(0).getName(),
	              GodTeam.get(1).getName(),
	              Partida.StatusGod(Attack.getNameOfGod()),
	              Partida.EnviarDano(),
	              Partida.StatusFrontGod(),
	              Partida.getGameOver());
		Network.SendMove(Lance);
	}

	public void setTeam(Team team) {
		GodTeam = team;
	}
	
	public Boolean getTurn() {
		return MeuTurno;
	}

	public boolean VerifyFirst() {
		return Partida.VerifyFirst();
	}
	public boolean getVenceu() {
		return Venceu;
	}
	public boolean getPerdeu() {
		return Perdeu;
	}
	
}
