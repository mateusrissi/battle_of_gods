import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.sql.Wrapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class Visual extends JFrame implements KeyListener, MouseListener, ActionListener
{
	private static JScrollPane ScrollBattleInfo;
	private static JTextArea BattleInfo;
	static Visual vi;
	private static JLabel Background, Enemy, Enemy2;
	private JMenuBar MenuBar;
	private String AtualScreen = "Start";
	boolean TeamSelected = false;
	private static Team Team;
	private static boolean Comecou = false;
	
	Player Player = new Player(Team);
	Network Net = new Network(Player);
	
	protected Visual(Team team)
	{
		vi = this;
		this.Team = team;
		vi.Team = team;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		vi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		vi.setVisible(true);
		vi.setTitle("Battle Of Gods");
		vi.setSize(1000, 600);
		vi.setResizable(false);
		vi.setLayout(new BorderLayout());
		vi.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		vi.addMouseListener(this);
		vi.addKeyListener(this);
		
		//Menu
		MenuBar = new JMenuBar();
		JMenu Menu = new JMenu("Menu");
		JMenuItem Connect = new JMenuItem("Connect");
		JMenuItem Select = new JMenuItem("Select Team");
		JMenuItem Start = new JMenuItem("Start");
		Menu.add(Connect);
		Connect.addActionListener(this);
		Menu.addSeparator();
		Menu.add(Select);
		Select.addActionListener(this);
		Menu.addSeparator();
		Menu.add(Start);
		Start.addActionListener(this);	
		MenuBar.add(Menu);

		//Background
		Background = new JLabel(new ImageIcon("./Images/startscreen.png"));
		Background.setVisible(true);
		vi.add(Background);
		
		//Battle Info
		BattleInfo = new JTextArea();
		BattleInfo.setEditable(false);
		BattleInfo.setLineWrap(true);
		BattleInfo.setWrapStyleWord(true);
		BattleInfo.setBackground(new Color(170, 170, 170));
		DefaultCaret caret = (DefaultCaret)BattleInfo.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		ScrollBattleInfo = new JScrollPane(BattleInfo);
		ScrollBattleInfo.setAutoscrolls(true);
		ScrollBattleInfo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		vi.revalidate();
	}

	public void StartScreen()
	{
		vi.remove(Background);
		Background = new JLabel(new ImageIcon("./Images/startscreen.png"));
		Background.setLayout(new FlowLayout());
		Background.setVisible(true);
		Background.setSize(1000, 600);
		vi.add(Background);
		vi.add(MenuBar, BorderLayout.NORTH);
		vi.revalidate();
		AtualScreen = "Start";
	}
	
	protected void SelectionScreen()
	{
		vi.remove(Background);
		Background = new JLabel(new ImageIcon("./Images/selection.png"));
		Background.setLayout(new FlowLayout());
		Background.setVisible(true);
		Background.setSize(1000, 600);
		vi.add(Background);
		vi.revalidate();
		AtualScreen = "Selection";
	}
	
	protected void FightScreen() throws IOException
	{
			vi.remove(Background);
			
			Background = new JLabel(new ImageIcon("./Images/arena.png"));
			JLabel image = new JLabel(new ImageIcon("./Images/" + Team.get(1).getName() + ".png"));
			image.setSize(200, 200);
			image.setVisible(true);
			image.setLocation(100, 100);
			JLabel image2 = new JLabel(new ImageIcon("./Images/" + Team.get(0).getName() + ".png"));
			image2.setSize(200, 200);
			image2.setVisible(true);
			image2.setLocation(200, 200);
			Background.add(image);
			Background.add(image2);
			vi.add(Background, BorderLayout.CENTER);
			JPanel BattlePanel = new JPanel(new BorderLayout());
			
			JPanel HabillitiesGod1 = new JPanel();
			HabillitiesGod1.setLayout(new BoxLayout(HabillitiesGod1, BoxLayout.PAGE_AXIS));
			JLabel God1Name = new JLabel(Team.get(0).getName());
			JButton Power1God1 = new JButton(Team.get(0).getHabilities().get(0).getName());
			JButton Power2God1 = new JButton(Team.get(0).getHabilities().get(1).getName());
			JButton Power3God1 = new JButton(Team.get(0).getHabilities().get(2).getName());
			JButton Power4God1 = new JButton(Team.get(0).getHabilities().get(3).getName());
			Dimension D = new Dimension(150, 20);
			
			Power1God1.setMaximumSize(D);
			Power2God1.setMaximumSize(D);
			Power3God1.setMaximumSize(D);
			Power4God1.setMaximumSize(D);
			God1Name.setMaximumSize(D);
			
			HabillitiesGod1.add(God1Name);
			
			Power1God1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(Player.getVenceu()){
						JOptionPane.showMessageDialog(null, "Voce Venceu");
					} else if(Player.getPerdeu()){
						JOptionPane.showMessageDialog(null, "Voce Perdeu");
						Player.enviarVitoria(Team.get(0).getHabilities().get(0));
					} else if(Player.getTurn() && Player.VerifyFirst()) {
						Player.tratarMove(Team.get(0).getHabilities().get(0));
						setBattleText(Team.get(0).getName() + " use " + Team.get(0).getHabilities().get(0).getName() + "!");
					} else if(!Player.VerifyFirst()){
						JOptionPane.showMessageDialog(null, "Seu Primeiro Deus Morreu");
					} else {
						JOptionPane.showMessageDialog(null, "Nao é seu Turno");
					}
				}
			});
			Power2God1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(Player.getVenceu()){
						JOptionPane.showMessageDialog(null, "Voce Venceu");
					} else if(Player.getPerdeu()){
						JOptionPane.showMessageDialog(null, "Voce Perdeu");
						Player.enviarVitoria(Team.get(0).getHabilities().get(0));
					} else if(Player.getTurn() && Player.VerifyFirst()) {
						Player.tratarMove(Team.get(0).getHabilities().get(1));
						setBattleText(Team.get(0).getName() + " use " + Team.get(0).getHabilities().get(1).getName() + "!");
					} else if(!Player.VerifyFirst()){
						JOptionPane.showMessageDialog(null, "Seu Primeiro Deus Morreu");
					} else {
						JOptionPane.showMessageDialog(null, "Nao é seu Turno");
					}
				}
			});
			Power3God1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(Player.getVenceu()){
						JOptionPane.showMessageDialog(null, "Voce Venceu");
					} else if(Player.getPerdeu()){
						JOptionPane.showMessageDialog(null, "Voce Perdeu");
						Player.enviarVitoria(Team.get(0).getHabilities().get(0));
					} else if(Player.getTurn() && Player.VerifyFirst()) {
						Player.tratarMove(Team.get(0).getHabilities().get(2));
						setBattleText(Team.get(0).getName() + " use " + Team.get(0).getHabilities().get(2).getName() + "!");
					} else if(!Player.VerifyFirst()){
						JOptionPane.showMessageDialog(null, "Seu Primeiro Deus Morreu");
					} else {
						JOptionPane.showMessageDialog(null, "Nao é seu Turno");
					}
				}
			});
			Power4God1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(Player.getVenceu()){
						JOptionPane.showMessageDialog(null, "Voce Venceu");
					} else if(Player.getPerdeu()){
						JOptionPane.showMessageDialog(null, "Voce Perdeu");
						Player.enviarVitoria(Team.get(0).getHabilities().get(0));
					} else if(Player.getTurn() && Player.VerifyFirst()) {
						Player.tratarMove(Team.get(0).getHabilities().get(3));
						setBattleText(Team.get(0).getName() + " use " + Team.get(0).getHabilities().get(3).getName() + "!");
					} else if(!Player.VerifyFirst()){
						JOptionPane.showMessageDialog(null, "Seu Primeiro Deus Morreu");
					} else {
						JOptionPane.showMessageDialog(null, "Nao é seu Turno");
					}
				}
			});
			HabillitiesGod1.add(Power1God1);
			HabillitiesGod1.add(Power2God1);
			HabillitiesGod1.add(Power3God1);
			HabillitiesGod1.add(Power4God1);
			
			JPanel HabillitiesGod2 = new JPanel();
			HabillitiesGod2.setLayout(new BoxLayout(HabillitiesGod2, BoxLayout.Y_AXIS));
			JLabel God2Name = new JLabel(Team.get(1).getName());
			JButton Power1God2 = new JButton(Team.get(1).getHabilities().get(0).getName());
			JButton Power2God2 = new JButton(Team.get(1).getHabilities().get(1).getName());
			JButton Power3God2 = new JButton(Team.get(1).getHabilities().get(2).getName());
			JButton Power4God2 = new JButton(Team.get(1).getHabilities().get(3).getName());
			
			Power1God2.setMaximumSize(D);
			Power2God2.setMaximumSize(D);
			Power3God2.setMaximumSize(D);
			Power4God2.setMaximumSize(D);
			God2Name.setMaximumSize(D);
			
			HabillitiesGod2.add(God2Name);
			
			Power1God2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(Player.getVenceu()){
						JOptionPane.showMessageDialog(null, "Voce Venceu");
					} else if(Player.getPerdeu()){
						JOptionPane.showMessageDialog(null, "Voce Perdeu");
						Player.enviarVitoria(Team.get(0).getHabilities().get(0));
					} else if(Player.getTurn()) {
						Player.tratarMove(Team.get(1).getHabilities().get(0));
						setBattleText(Team.get(1).getName() + " use " + Team.get(1).getHabilities().get(0).getName() + "!");
					} else {
						JOptionPane.showMessageDialog(null, "Nao é seu Turno");
					}
				}
			});
			Power2God2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(Player.getVenceu()){
						JOptionPane.showMessageDialog(null, "Voce Venceu");
					} else if(Player.getPerdeu()){
						JOptionPane.showMessageDialog(null, "Voce Perdeu");
						Player.enviarVitoria(Team.get(0).getHabilities().get(0));
					} else if(Player.getTurn()) {
						Player.tratarMove(Team.get(1).getHabilities().get(1));
						setBattleText(Team.get(1).getName() + " use " + Team.get(1).getHabilities().get(1).getName() + "!");
					} else {
						JOptionPane.showMessageDialog(null, "Nao é seu Turno");
					}
				}
			});
			Power3God2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(Player.getVenceu()){
						JOptionPane.showMessageDialog(null, "Voce Venceu");
					} else if(Player.getPerdeu()){
						JOptionPane.showMessageDialog(null, "Voce Perdeu");
						Player.enviarVitoria(Team.get(0).getHabilities().get(0));
					} else if(Player.getTurn()) {
						Player.tratarMove(Team.get(1).getHabilities().get(2));
						setBattleText(Team.get(1).getName() + " use " + Team.get(1).getHabilities().get(2).getName() + "!");
					} else {
						JOptionPane.showMessageDialog(null, "Nao é seu Turno");
					}
				}
			});
			Power4God2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					if(Player.getVenceu()){
						JOptionPane.showMessageDialog(null, "Voce Venceu");
					} else if(Player.getPerdeu()){
						JOptionPane.showMessageDialog(null, "Voce Perdeu");
						Player.enviarVitoria(Team.get(0).getHabilities().get(0));
					} else if(Player.getTurn()) {
						Player.tratarMove(Team.get(1).getHabilities().get(3));
						setBattleText(Team.get(1).getName() + " use " + Team.get(1).getHabilities().get(3).getName() + "!");
					} else {
						JOptionPane.showMessageDialog(null, "Nao é seu Turno");
					}
				}
			});
			HabillitiesGod2.add(Power1God2);
			HabillitiesGod2.add(Power2God2);
			HabillitiesGod2.add(Power3God2);
			HabillitiesGod2.add(Power4God2);
			
			BattlePanel.add(HabillitiesGod1, BorderLayout.WEST);
			BattlePanel.add(ScrollBattleInfo, BorderLayout.CENTER);
			BattlePanel.add(HabillitiesGod2, BorderLayout.EAST);
			vi.add(BattlePanel, BorderLayout.SOUTH);
			vi.revalidate();
			AtualScreen = "Fight";
	}
	
	public static void setBattleText(String Text)
	{
		if(BattleInfo.getText().isEmpty())
		{
			BattleInfo.append(Text);
			BattleInfo.revalidate();
		} else {
			BattleInfo.append("\n" + Text);
			BattleInfo.revalidate();			
		}
		BattleInfo.revalidate();
	}
	
	public String getAtualScreen()
	{
		return AtualScreen;
	}
	
	private void VerifyTeam(String GodName)
	{
		if(Team.size() == 1)
		{
			JOptionPane.showMessageDialog(this, "First God Selected: " + GodName);
		} else if(vi.Team.get(0).getName() == GodName) {
			JOptionPane.showMessageDialog(this, "This God is already in your team");
			vi.Team.remove(1);
		} else {
			JOptionPane.showMessageDialog(this, "Second God Selected: " + GodName);
			StartScreen();
			TeamSelected = true;
		}
	}

	public void keyPressed(KeyEvent e) {}

	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	public void actionPerformed(ActionEvent act) 
	{
		if(act.getActionCommand() == "Start" && !Comecou)
		{
			Net.StartGame();
		} 
		else if(act.getActionCommand() == "Select Team" && AtualScreen != "Fight")
		{
			if(TeamSelected)
			{
				String God1 = vi.Team.get(0).getName();
				String God2 = vi.Team.get(1).getName();
				String Message = "Team already selected.\nGods Selected: " + God1 + " and " + God2 + "\nChoose new Gods ?";
				int Yes = new JOptionPane().showConfirmDialog(this, Message, "New Team", JOptionPane.YES_NO_OPTION);
				if(Yes == JOptionPane.YES_OPTION)
				{
					vi.Team.remove(1);
					vi.Team.remove(0);
					SelectionScreen();
				}
			} else {
				SelectionScreen();
			}
		}
		else if(act.getActionCommand() == "Connect" && Net.getConnected() == false && TeamSelected && AtualScreen != "Fight")
		{
			String Name = JOptionPane.showInputDialog("Player name: ");
			Player.setName(Name);
			Player.setTeam(Team);
			String server = JOptionPane.showInputDialog("Server", "localhost");
			Net.Connect(Name, server);
			try {
				FightScreen();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if(act.getActionCommand() == "Start" && Comecou) {
			JOptionPane.showMessageDialog(this, "O Jogo ja foi Iniciado");
		} else if(act.getActionCommand() == "Connect" && AtualScreen == "Fight") {
			JOptionPane.showMessageDialog(this, "Ja esta Conectado");
		} else if(act.getActionCommand() == "Select Team" && AtualScreen == "Fight") {
			JOptionPane.showMessageDialog(this, "Na Tela de Jogo\nNao e Possivel Mudar o Time");
		} 
	}
	
	public void mouseClicked(MouseEvent mevt) {}
	
	public void mouseEntered(MouseEvent mevt) {}
	
	public void mouseExited(MouseEvent mevt) {}
	
	public void mousePressed(MouseEvent mevt) 
	{
		int coordY = mevt.getY();
		int coordX = mevt.getX();
		if(AtualScreen == "Selection")
		{
			//Primeira Coluna
			if(coordX > 180 && coordX < 330)
			{
				if(coordY > 80 && coordY < 221)
				{
					vi.Team.addGod("Zeus");
					VerifyTeam("Zeus");
					
				} else if (coordY > 250 && coordY < 390) {
					vi.Team.addGod("Cthulhu");
					VerifyTeam("Cthulhu");
					
				} else if (coordY > 427 && coordY < 571) {
					vi.Team.addGod("Rá");
					VerifyTeam("Rá");
					
				}
				//Segunda Coluna
			} else if(coordX > 427 && coordX < 570) {
				if(coordY > 80 && coordY < 221)
				{
					vi.Team.addGod("Anúbis");
					VerifyTeam("Anúbis");
					
				} else if (coordY > 250 && coordY < 390) {
					vi.Team.addGod("Poseidon");
					VerifyTeam("Poseidon");
					
				} else if (coordY > 427 && coordY < 571) {
					vi.Team.addGod("Odin");
					VerifyTeam("Odin");
					
				}
				//Terceira Coluna
			} else if(coordX > 670 && coordX < 815) {
				if(coordY > 80 && coordY < 221)
				{
					vi.Team.addGod("Thor");
					VerifyTeam("Thor");
					
				} else if (coordY > 250 && coordY < 390) {
					vi.Team.addGod("Azathoth");
					VerifyTeam("Azathoth");
					
				} else if (coordY > 427 && coordY < 571) {
					vi.Team.addGod("Hades");
					VerifyTeam("Hades");				
				}
			}
		} 
	}

	public void mouseReleased(MouseEvent mevt) {}
	

	public static void setComecou() 
	{
		Comecou = true;
		
	}

	public static void setEnemy(JLabel image, int x, int y) 
	{
		image.setSize(200, 200);
		image.setVisible(true);
		image.setLocation(x, y);
		Enemy = image;
	}
	
	public static void setEnemy2(JLabel image, int x, int y) 
	{
		image.setSize(200, 200);
		image.setVisible(true);
		image.setLocation(x, y);
		Enemy2= image;
	}
	
	public static void drawEnemy()
	{
		Background.add(Enemy);
		Background.add(Enemy2);
		vi.repaint();
		vi.revalidate();
	}
}