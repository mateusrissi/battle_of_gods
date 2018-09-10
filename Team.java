import java.awt.Component;
import java.util.ArrayList;

public class Team extends ArrayList<God>{

	//private static String GodName;
	private GodCatalog GodCatalog;
	public ArrayList<God> GodTeam;
	
	Team(GodCatalog godList)
	{
		super();
		this.GodCatalog = godList;
		//ArrayList<God> GodTeam = new ArrayList<God>();
	}

	protected void addGod(String name)
	{
		God God = GodCatalog.getGod(name);
		add(God);
	}

}
