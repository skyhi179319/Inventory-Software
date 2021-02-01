package Assets;

import java.util.ArrayList;
import javax.swing.JLabel;

public class Update {
	public static  ArrayList<String> UpdateInfoArray = new ArrayList<String>();
	public static void UpdateInfo(String version, String update_1, String update_2, String update_3 ){
		UpdateInfoArray.add(version);
		UpdateInfoArray.add(update_1);
		UpdateInfoArray.add(update_2);
		UpdateInfoArray.add(update_3);
		if(update_1.equals("") || update_1.equals("None") ){
			System.out.println(version);
			System.out.println("** No Updates **");
		}
		else{
			System.out.println(version);
			if(!update_1.equals("") || !update_1.equals("None")) {
				System.out.println(update_1);
			}
			if(!update_2.equals("") || !update_2.equals("None")) {
				System.out.println(update_2);
			}
			if(!update_3.equals("") || !update_3.equals("None")) {
				System.out.println(update_3);
			}
			if(update_2.equals("") || update_2.equals("None") || update_3.equals("") || update_3.equals("None")) {
				System.out.println("** No More Updates **");
			}
		}
	}
	public static void AutoStart(){
		String version = "** 3.0 **";
		String update_1 = "** Added New Update Feature **";
		String update_2 = "** Added Commands To Feature **";
		String update_3 = "** Added And Edited UI **";
		UpdateInfo(version,update_1,update_2,update_3);
	}
	public static class Commands{
		public static String version = UpdateInfoArray.get(0);
		public static String update1 = UpdateInfoArray.get(1);
		public static String update2 = UpdateInfoArray.get(2);
		public static String update3 = UpdateInfoArray.get(3);
		public static void DoCommand(String Command,JLabel label) {
			if(Command.equals("version") || Command.equals("Version")) {
				label.setText(version);
			}
			if(Command.equals("Info Concat") || Command.equals("info concat")) {
				String info = update1;
	       		if(!update2.equals("")) {
	       			info = update1 + " " + update2;
	       		}
	       		if(!update3.equals("")) {
	       			info = update1 + " " + update2 + " " + update3;
	       		}
                label.setText(info);
			}
		}
		public static class Actions{
			// Clear Console
		}
	}
} 