package scripts;

import org.tribot.api2007.Game;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.types.RSNPC;
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
				
				for (int j = 0; j < 9; j++) {
					System.out.println(xVals[j] + "," + yVals[j]);
					if (xVals[j + 1] == 0 && yVals[j + 1] == 0) {
						break;
					}
					
					if (currLastCoord == (xVals[j] + "," + yVals[j])) {
						threshold = true;
					}
					
					if (threshold || totalCycles == 0) {
						currCoordList.add(xVals[j] + "," + yVals[j]);
						
						try {
							System.out.println("Test");
							File file = new File("C:/Users/hsxu/graph_test/filename.txt");
							file.getParentFile().mkdirs();
							System.out.println("Test2");
							// if file doesnt exists, then create it
							if (!file.exists()) {
								System.out.println("Test10");
								System.out.println(file.getAbsoluteFile());
								file.createNewFile();
								System.out.println("Test3");
							}
							System.out.println("Test4");
							FileWriter fw = new FileWriter(file.getAbsoluteFile());
							BufferedWriter bw = new BufferedWriter(fw);
							bw.write(xVals[j] + "," + yVals[j]);
							bw.close();

							System.out.println("Done");

						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
					}
				}
				coords.remove("NPC" + i);
				coords.put("NPC" + i, currCoordList);
				
				i++;
				threshold = false;
			}
			sleep(2000);
			i = 0;
			totalCycles++;
		}
	}
}
