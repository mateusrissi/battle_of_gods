import javax.swing.JOptionPane;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class Network implements OuvidorProxy {

	private Player Player;
	private Proxy Proxy;
	private Boolean MyTurn = false;
	private static Boolean Connected = false;

	
	public Network(Player player)
	{
		super();
		this.Player = player;
		player.setNetwork(this);
		Proxy = Proxy.getInstance();
		Proxy.addOuvinte(this);
	}
	
	public boolean getConnected() 
	{
		return Connected;
	}
	
	public void Connect(String name, String server)
	{
		try {
			Proxy.conectar(server, name);
			Connected = true;
		} catch (JahConectadoException e) {
			JOptionPane.showMessageDialog(null , e.getMessage());
			e.printStackTrace();
		} catch (NaoPossivelConectarException e) {
			JOptionPane.showMessageDialog(null , e.getMessage());
			e.printStackTrace();
		} catch (ArquivoMultiplayerException e) {
			JOptionPane.showMessageDialog(null , e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void StartGame()
	{
		try {
			Proxy.iniciarPartida(2);
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(null , e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void SendMove(Move lance)
	{
		try {
			Proxy.enviaJogada(lance);
		} catch (NaoJogandoException e) {
			JOptionPane.showMessageDialog(null , e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void Disconnect()
	{
		try {
			Proxy.desconectar();
			Connected = false;
		} catch (NaoConectadoException e) {
			JOptionPane.showMessageDialog(null , e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getOpponent() 
	{
		String Name;
		if(MyTurn)
		{
			Name = Proxy.obterNomeAdversario(2);
		} else {
			Name = Proxy.obterNomeAdversario(1);
		}
		return Name;
	}
	
	public void iniciarNovaPartida(Integer posicao) 
	{
		if(posicao == 1)
		{
			MyTurn = true;
		} else if(posicao == 2) {
			MyTurn = false;
		}
		Player.StartNetworkGame(MyTurn);
	}

	public void receberJogada(Jogada jogada) 
	{
			JOptionPane.showMessageDialog(null, "Your turn");
			Move lance = (Move) jogada;
			Player.ReceiveMove(lance);
	}
	
	public void finalizarPartidaComErro(String message) 
	{
		JOptionPane.showMessageDialog(null, "Opponent has left the game");
		Connected = false;
	}

	public void receberMensagem(String msg) {}

	public void tratarConexaoPerdida() 
	{
		JOptionPane.showMessageDialog(null, "Lost connection");
		Connected = false;
	}

	public void tratarPartidaNaoIniciada(String message) 
	{
		JOptionPane.showMessageDialog(null, "Unnable to start game");
		Connected = false;
	}
	
}