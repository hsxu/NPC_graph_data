package scripts;

import org.tribot.api2007.Game;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;
import org.tribot.script.Script;

public class graph_info extends Script {

	@Override
	public void run() {
		RSNPC[] npcs = NPCs.find(2096);
		int c = 0;
		
		while (true) {
			for (RSNPC npc : npcs) {
				System.out.println("NPC" + c);
				int[] xVals = npc.getWalkingQueueX();
				int[] yVals = npc.getWalkingQueueY();
				
				for (int i = 0; i < 10; i++) {
					System.out.println(
							xVals[i] 
							//+ Game.getBaseX() 
							+ "," 
							+ yVals[i] 
						    //+ Game.getBaseY()
						    );
				}
				c++;
			}
			sleep(2000);
			c = 0;
		}
	}
}
