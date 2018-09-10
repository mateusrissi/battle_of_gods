
public class Main 
{
	public static void main(String[] args) 
	{
		God Zeus, Poseidon, Hades, Odin, Thor, Ra, Anubis, Cthulhu, Azathoth;
		//Criação das habilidades
		
		GodCatalog GodCatalog = new GodCatalog();
		Team GodTeam = new Team(GodCatalog);
		Visual Visual = new Visual(GodTeam);
		Habilities ZeusHabilities = new Habilities();
		Habilities PoseidonHabilities = new Habilities();
		Habilities HadesHabilities = new Habilities();
		Habilities OdinHabilities = new Habilities();
		Habilities ThorHabilities = new Habilities();
		Habilities RaHabilities = new Habilities();
		Habilities AnubisHabilities = new Habilities();
		Habilities CthulhuHabilities = new Habilities();
		Habilities AzathothHabilities = new Habilities();
		
		//Zeus
		ZeusHabilities.AddHability(new Hability("Thunder", 100, 80, 1, true, "Normal", "Zeus"));
		ZeusHabilities.AddHability(new Hability("Lightning Spear", 70, 80, 3, true, "Normal", "Zeus"));
		ZeusHabilities.AddHability(new Hability("Hurricane", 120, 75, 1, true, "Normal", "Zeus"));
		ZeusHabilities.AddHability(new Hability("Charge Lightning", 150, 50, 1, true, "Normal", "Zeus"));
		
		//Poseidon
		PoseidonHabilities.add(new Hability("Tsunami", 100, 80, 1, true, "Normal","Poseidon"));
		PoseidonHabilities.add(new Hability("Trident Strike", 150, 70, 1, false, "Normal","Poseidon"));
		PoseidonHabilities.add(new Hability("Shark Attack", 130, 75, 1, false, "Normal","Poseidon"));
		PoseidonHabilities.add(new Hability("Whirlpool", 70, 70, 1, true, "Normal","Poseidon"));
		
		//Hades
		HadesHabilities.add(new Hability("Infernal Fire", 120, 75, 1, true, "Normal","Hades"));
		HadesHabilities.add(new Hability("Cerberus attack", 80, 80, 3, false, "Normal","Hades"));
		HadesHabilities.add(new Hability("Death Touch", 40, 70, 0, true, "Cursed","Hades"));
		HadesHabilities.add(new Hability("Shadow Attack", 100, 80, 1, true, "Normal","Hades"));
		
		//Thor
		ThorHabilities.add(new Hability("Hammer Throw", 100, 80, 1, false, "Normal","Thor"));
		ThorHabilities.add(new Hability("Thunder", 100, 80, 1, true, "Normal","Thor"));
		ThorHabilities.add(new Hability("Lightning Spear", 70, 80, 3, true, "Normal","Thor"));
		ThorHabilities.add(new Hability("Hammer Combo", 65, 70, 4, false, "Normal","Thor"));
		
		//Odin
		OdinHabilities.add(new Hability("Asgardian Army", 100, 80, 1, false, "Normal","Odin"));
		OdinHabilities.add(new Hability("King's Power", 130, 75, 1, true, "Normal","Odin"));
		OdinHabilities.add(new Hability("Power Strike", 150, 70, 1, false, "Normal","Odin"));
		OdinHabilities.add(new Hability("Ragnarok", 150, 70, 1, true, "Normal","Odin"));
		
		//Rá
		RaHabilities.add(new Hability("Solar Flare", 70, 70, 1, true, "Normal","Rá"));
		RaHabilities.add(new Hability("Eclipse", 100, 80, 1, true, "Normal","Rá"));
		RaHabilities.add(new Hability("Sunbeam", 200, 50, 1, true, "Normal","Rá"));
		RaHabilities.add(new Hability("Peck", 100, 80, 1, false, "Normal","Rá"));
		
		//Anúbis
		AnubisHabilities.add(new Hability("Death Touch", 40, 70, 1, true, "Cursed","Anúbis"));
		AnubisHabilities.add(new Hability("Zombie Attack", 80, 80, 3, false, "Normal","Anúbis"));
		AnubisHabilities.add(new Hability("Bite", 100, 80, 1, false, "Normal","Anúbis"));
		AnubisHabilities.add(new Hability("Shadow Strike", 85, 80, 2, true, "Normal","Anúbis"));
		
		//Cthulhu
		CthulhuHabilities.add(new Hability("Tentacle Attack", 70, 70, 1, false, "Normal","Cthulhu"));
		CthulhuHabilities.add(new Hability("Global Destruction", 130, 75, 1, true, "Normal","Cthulhu"));
		CthulhuHabilities.add(new Hability("Madness", 40, 70, 1, true, "Confused","Cthulhu"));
		CthulhuHabilities.add(new Hability("Insanity Strike", 40, 70, 1, false, "Cursed","Cthulhu"));
		
		//Azathoth
		AzathothHabilities.add(new Hability("Tentacle Attack", 70, 70, 1, false, "Normal","Azathoth"));
		AzathothHabilities.add(new Hability("Chaos Explosion", 120, 75, 1, true, "Normal","Azathoth"));
		AzathothHabilities.add(new Hability("Curse", 40, 70, 1, true, "Cursed","Azathoth"));
		AzathothHabilities.add(new Hability("Disable", 40, 70, 1, true, "Confused","Azathoth"));
		
		//Criação dos deuses
		GodCatalog.AddGod(Zeus = new God("Zeus", 500, 50, 60, ZeusHabilities, "Normal"));
		GodCatalog.AddGod(Poseidon = new God("Poseidon", 500, 50, 60, PoseidonHabilities, "Normal"));
		GodCatalog.AddGod(Hades = new God("Hades", 500, 50, 60, HadesHabilities, "Normal"));
		GodCatalog.AddGod(Odin = new God("Odin", 500, 50, 60, OdinHabilities, "Normal"));
		GodCatalog.AddGod(Thor = new God("Thor", 500, 50, 60, ThorHabilities, "Normal"));
		GodCatalog.AddGod(Ra = new God("Rá", 500, 50, 60, RaHabilities, "Normal"));
		GodCatalog.AddGod(Anubis = new God("Anúbis", 500, 50, 60, AnubisHabilities, "Normal"));
		GodCatalog.AddGod(Cthulhu = new God("Cthulhu", 500, 50, 60, CthulhuHabilities, "Normal"));
		GodCatalog.AddGod(Azathoth = new God("Azathoth", 500, 50, 60, AzathothHabilities, "Normal"));

		Visual.StartScreen();
		
	}
}