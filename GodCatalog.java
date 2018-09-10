import java.util.ArrayList;

public class GodCatalog extends ArrayList<God>
{	
	GodCatalog()
	{
		ArrayList<God> Gods = new ArrayList<God>();
	}
	
	protected void AddGod(God god)
	{
		add(god);
	}
	
	protected God getGod(String Name) 
	{
		for (int i = 0; i < this.size(); i++) {
			God God = get(i);
			if(God.getName() == Name)
			{
				return God;
			}
		}
		return null;
	}
}
