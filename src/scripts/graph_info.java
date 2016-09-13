package scripts;

import org.tribot.api2007.Game;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class graph_info extends Script {
	
	Hashtable<String, ArrayList<String>> coords = 
			new Hashtable<String, ArrayList<String>>();

	@Override
	public void run() {
		RSNPC[] npcs = NPCs.find(4364);
		int i = 0;
		int totalCycles = 0;
		
		Writer writer = null;
		
		while (true) {
			for (RSNPC npc : npcs) {
				if (!coords.containsKey("NPC" + i)) {
					coords.put("NPC" + i, new ArrayList<String>());
				}
				
				int[] xVals = npc.getWalkingQueueX();
				int[] yVals = npc.getWalkingQueueY();
				ArrayList<String> currCoordList = coords.get("NPC" + i);
				String currLastCoord = "";
				boolean threshold = false;
				
				if (currCoordList.size() != 0) {
					currLastCoord = currCoordList.get(currCoordList.size() - 1);
				} 
				
				RSTile tile = npc.getPosition();
				currCoordList.add(tile.getX() + "," + tile.getY());		
				System.out.println(tile.getX() + "," + tile.getY());

				coords.remove("NPC" + i);
				coords.put("NPC" + i, currCoordList);
				
				i++;
				threshold = false;
			}
			sleep(200);
			i = 0;
			totalCycles++;
		}
	}
}
