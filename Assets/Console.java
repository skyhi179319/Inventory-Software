package Assets;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Console{
	public static  ArrayList<String> ConsoleArray = new ArrayList<String>();
	public static class Commands{
		public static void ConsoleWriteLine(String text) {
			ConsoleArray.add(text);
			System.out.print(ConsoleArray);
		}
		public static void ClearMainInventory(TreeMap<Integer, Integer> array) {
			array.clear();
		}
		public static void UpdateConsole(JTextArea Console) {
			String CurrentText = Console.getText();
			for (int i = 0; i < ConsoleArray.size(); i++) {
				String Changed = CurrentText + ConsoleArray.get(i) + "\n";
				Console.setText(Changed);
			}
		}
		public static void ClearConsole(JTextArea Console) {
			Console.setText("");
			ConsoleArray.clear();
		}
		public static void Run(JTextField Command,JTextArea Console,TreeMap<Integer, Integer> MainInventory) {
			String CommandText = Command.getText();
			if(CommandText.equals("Clear Inventory")){
				ConsoleWriteLine("Cleared inventory");
				ClearMainInventory(MainInventory);
			}
			else if(CommandText.equals("Version")) {
				ConsoleWriteLine(Update.Commands.version);
			}
			else if(CommandText.equals("Update Info")) {
				String info = Update.Commands.update1;
	       		if(!Update.Commands.update2.equals("")) {
	       			info = Update.Commands.update1 + " " + Update.Commands.update2;
	       		}
	       		if(!Update.Commands.update3.equals("")) {
	       			info = Update.Commands.update1 + " " + Update.Commands.update2 + " " + Update.Commands.update3;
	       		}
	       		ConsoleWriteLine(info);
			}
			else if(CommandText.equals("Clear")) {
				ClearConsole(Console);
			}
			else {
				ConsoleWriteLine("Error: Unvalid Command");
			}
			UpdateConsole(Console);
		}
	}
} 