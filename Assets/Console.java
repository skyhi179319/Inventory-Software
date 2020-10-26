package Assets;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class Console{
	public static  ArrayList<String> ConsoleArray = new ArrayList<String>();
	// Program Booleans
	public static boolean AdminAccess = false;
	public static boolean AdminFullAccess = false;
	public static boolean KeepAdminAccessOn = false;
	public static boolean TableLogin = false;
	// Commands For All Classes
	public static void ConsoleWriteLine(String text) {
		ConsoleArray.add(text);
		System.out.print(ConsoleArray);
	}
	public static class Commands{
		public static class Actions{
			public static void OnOff(boolean Bool,String Write) {
				if(Bool == true) {
					Bool = false;
					ConsoleWriteLine(Write + " is false");
				}
				if(Bool == false) {
					Bool = true;
					ConsoleWriteLine(Write + " is true");
				}
			}
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
			else if(Command.equals("Admin Access")) {
				Actions.OnOff(AdminAccess,"Admin Access");
			}
			else if(Command.equals("Admin Full Access")) {
				Actions.OnOff(AdminFullAccess,"Admin Full Access");
			}
			else if(Command.equals("Table Login")) {
				Actions.OnOff(TableLogin,"Table Login"); 
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