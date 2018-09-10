import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.plaf.ActionMapUIResource;

public class Battle 
{
	private static Team MyTeam;
	private static String StatusGod1, StatusGod2, NameGod1, NameGod2;
	private static int LifeGod1, LifeGod2; 
	private static int NegativeTurnsGod1 = 0, NegativeTurnsGod2 = 0;
	private static boolean GameOver = false;
	private static String PlayerName;
	private static String GuestName;
	private static boolean NaoRecebi = true;
	
	private static String EnemyTeamGod1Name;
	private static int EnemyTeamGod1Life = 500;
	private static int EnemyTeamGod1Art = 50;
	private static int EnemyTeamGod1MAtk = 60;
	
	private static String EnemyTeamGod2Name;
	private static int EnemyTeamGod2Life = 500;
	private static int EnemyTeamGod2Art = 50;
	private static int EnemyTeamGod2MAtk = 60;
	
	private static int DanoEnviar = -1;
	private static String MyFrontStatus = "Normal";
	private static String NameFront;
	private static int LifeFront;
	
	public Battle(Team MeuTime) 
	{
		this.addGod(MeuTime);
		this.getNameGod0();
		this.getStatusGod0();
		this.getLifeGod0();
		this.getNameGod1();
		this.getStatusGod1();
		this.getLifeGod1();	
	}

	public boolean getGameOver()
	{
		return GameOver;
	}
	
	private static boolean VerifyAlive(String name)
	{
		if(NameGod1.equals(name))
		{
			if(LifeGod1 > 0)
			{
				return true;
			} else {
				return false;
			}
		} else {
			if(LifeGod2 > 0)
			{
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean VerifyFirst()
	{
		if(LifeGod1 > 0)
		{
			return true;
		} else {
			return false;
		}
	}
	
	private static void setStatus(String name, String status)
	{
		if(NameGod1.equals(name))
		{
			if(status.equals("Cursed") || status.equals("Confused"))
			{
				StatusGod1 = status;
				MyFrontStatus = status;
				NegativeTurnsGod1 = 2;
			}
			Visual.setBattleText(NameGod1 +  " is " + StatusGod1);
		} else{
			if(status.equals("Cursed") || status.equals("Confused"))
			{
				StatusGod2 = status;
				MyFrontStatus = status;
				NegativeTurnsGod2 = 2;
			}
			Visual.setBattleText(NameGod2 +  " is " + StatusGod2);
		}
	}
	private static int calcularCurse(String name){
		int iCurse = 0;
		if(NameGod1.equals(name)){
			if(StatusGod1.equals("Cursed"))
			{
					iCurse = 40;
					Visual.setBattleText(NameGod1 + " is curse!");
			}
			
		} else if(StatusGod2.equals("Cursed")) {
				iCurse =  40;
				Visual.setBattleText(NameGod2 + " is curse!");
		}
		return iCurse;
	}
	
	private static void VerifyNegative(String name)
	{
		if(NameGod1.equals(name))
		{
			if(StatusGod1.equals("Cursed"))
			{
				if(NegativeTurnsGod1 == 0)
				{
					StatusGod1 = "Normal";
					MyFrontStatus = "Normal";
				} else {
					NegativeTurnsGod1--;
				}
			} else if(StatusGod1.equals("Confused")) 
			{
				if(NegativeTurnsGod1 == 0){
					StatusGod1 = "Normal";
					MyFrontStatus = "Normal";
				} else {
					NegativeTurnsGod1--;
				}
			} 
		} else {
			if(StatusGod2.equals("Cursed"))
			{
				if(NegativeTurnsGod2 == 0)
				{
					StatusGod2 = "Normal";
					MyFrontStatus = "Normal";
				} else {
					NegativeTurnsGod2--;
				}
			} else if(StatusGod2.equals("Confused")) 
			{
				if(NegativeTurnsGod2 == 0){
					StatusGod2 = "Normal";
					MyFrontStatus = "Normal";
				} else {
					NegativeTurnsGod2--;
				}
			}
		}
	}
	
	private static int getAccurancy(Hability attack,String atkStatus)
	{
		int Accurancy;
		if(atkStatus.equals("Confused"))
		{
			Accurancy = attack.getAccuracy() - 30;
			return Accurancy;
		} else {
			Accurancy = attack.getAccuracy();
			return Accurancy;
		}
	}
	
	private static void Damage(Hability attack,String atkStatus)
	{
		int Damage, Miss, Accurancy, Hits;
		String Status;
		Random rand = new Random();
		Miss = rand.nextInt(100) + 1;
		Hits = rand.nextInt(attack.getHits()) + 1;
		Accurancy = getAccurancy(attack,atkStatus);
		Status = attack.getStatus();
		if(Accurancy > Miss)
		{
			if(VerifyAlive(NameGod1))
			{
				if(attack.getMagic())
				{
					if(attack.getNameOfGod() == EnemyTeamGod1Name) {
						Damage = ((attack.getPower()/2) + EnemyTeamGod1MAtk)*Hits;
					} else {
						Damage = ((attack.getPower()/2) + EnemyTeamGod2MAtk)*Hits;
					}
				} else {
					if(attack.getNameOfGod() == EnemyTeamGod1Name) 
					{
						Damage = ((attack.getPower()/2) + EnemyTeamGod1Art)*attack.getHits();
					} else {
						Damage = ((attack.getPower()/2) + EnemyTeamGod2Art)*attack.getHits();
					}
				}
				Damage = Damage + calcularCurse(NameGod1);
				VerifyNegative(NameGod1);
				setStatus(NameGod1, Status);
				LifeGod1 = LifeGod1 - Damage;
				DanoEnviar = Damage;
				Visual.setBattleText(MyTeam.get(0).getName() + " has taken " + Damage + " damage!");
				if(LifeGod1 <= 0)
				{
					Visual.setBattleText(NameGod1 + " is dead!");
				} else {
					Visual.setBattleText(NameGod1 + " has " + LifeGod1 + "HP");
				}
			} else {
				if(attack.getMagic())
				{
					if(attack.getNameOfGod() == EnemyTeamGod1Name) {
						Damage = ((attack.getPower()/2) + EnemyTeamGod1MAtk)*Hits;
					} else {
						Damage = ((attack.getPower()/2) + EnemyTeamGod2MAtk)*Hits;
					}
				} else {
					if(attack.getNameOfGod() == EnemyTeamGod1Name) 
					{
						Damage = ((attack.getPower()/2) + EnemyTeamGod1Art)*attack.getHits();
					} else {
						Damage = ((attack.getPower()/2) + EnemyTeamGod2Art)*attack.getHits();
					}
				}
				Damage = Damage + calcularCurse(NameGod2);
				VerifyNegative(NameGod2);
				setStatus(NameGod2, Status);
				LifeGod2 = LifeGod2 - Damage;
				DanoEnviar = Damage;
				Visual.setBattleText(NameGod2 + " has taken " + Damage + " damage!");
				if(LifeGod2 <= 0)
				{
					Visual.setBattleText(NameGod2 + " is dead!");
					GameOver = true;
					Visual.setBattleText("Voce Perdeu!!");
				} else {
					Visual.setBattleText(NameGod2 + " has " + LifeGod2 + "HP");
				}
			}
		} else {
			Visual.setBattleText("Attack missed!");
			DanoEnviar = 0;
		}
	}

	public void Fight(Hability attack,String atkStatus) 
	{
		Damage(attack,atkStatus);
	}
	
	public void addGod(Team godTeam)
	{
		MyTeam = godTeam;
	}
	
	public void addPlayer(String name)
	{
		PlayerName = name;
	}
	public void addGuest(String name)
	{
		GuestName = name;
	}
	public String getPlayer()
	{
		return PlayerName;
	}
	public String getGuest()
	{
		return GuestName;
	}
	public String StatusGod(String name) {
		if(NameGod1.equals(name)) {
			return StatusGod1;
		} else{
			return StatusGod2;
		}
	}
	public String StatusFrontGod() {
			return MyFrontStatus;
	}
	
	private void getNameGod0() {
		NameGod1 = MyTeam.get(0).getName();
	}
	private void getStatusGod0() {
		StatusGod1 = MyTeam.get(0).getStatus();
	}
	private void getLifeGod0() {
		LifeGod1 = MyTeam.get(0).getLife();
	}
	private void getNameGod1() {
		NameGod2 = MyTeam.get(1).getName();
	}
	private void getStatusGod1() {
		StatusGod2 = MyTeam.get(1).getStatus();
	}
	private void getLifeGod1() {
		LifeGod2 = MyTeam.get(1).getLife();
	}

	public void MontarEnemyTeam(String God1Name,String God2Name) {
		EnemyTeamGod1Name = God1Name;
		JLabel image = new JLabel(new ImageIcon("./Images/" + EnemyTeamGod1Name + "Inverso.png"));
		Visual.setEnemy(image, 700, 200);
		EnemyTeamGod2Name = God2Name;
		JLabel image2 = new JLabel(new ImageIcon("./Images/" + EnemyTeamGod2Name + "Inverso.png"));
		Visual.setEnemy2(image2, 800, 100);
		Visual.vi.drawEnemy();
		LifeFront = EnemyTeamGod1Life;
		NameFront = EnemyTeamGod1Name;
		NaoRecebi = false;
	}
	public static boolean NaoRecebiEnemy() {
		return NaoRecebi;
	}
	public int EnviarDano(){
		return DanoEnviar;
	}
	public void MostrarResutadoAtk(int totalDamage, String frontlineStatus) {
		if(totalDamage == 0) {
			Visual.setBattleText("Attack missed!");
			Visual.setBattleText(NameFront + " has " + LifeFront + "HP And is" + frontlineStatus);
		} else {
			Visual.setBattleText(NameFront +  " is " + frontlineStatus);
			LifeFront = LifeFront - totalDamage;
			Visual.setBattleText(NameFront + " has taken " + totalDamage + " damage!");
			if(LifeFront <= 0)
			{
				Visual.setBattleText(NameFront + " is dead!");
				NameFront = EnemyTeamGod2Name;
				LifeFront = EnemyTeamGod2Life;
			} else {
				Visual.setBattleText(NameFront + " has " + LifeFront + "HP");
			}
		}
	}
}
