package Assets;
import java.awt.Choice;
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
		public static class ConsoleCommands{
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
			public static void Write(String text) {
				ConsoleWriteLine(text);
			}
		}
		public static void ClearMainInventory(TreeMap<Integer, Integer> array) {
			array.clear();
		}
		public static void AddMainInventory(TreeMap<Integer, Integer> array,int key, int value) {
			// key = Barcode
			// Value = Product Amount
			array.put(key, value);
			ConsoleCommands.Write("Added Barcode: " + key + " To Inventory");
		}
		
		public static void Run(JTextField Command,JTextArea Console,Choice ExtraCommands,TreeMap<Integer, Integer> MainInventory) {
			String Extra = ExtraCommands.getItem(ExtraCommands.getSelectedIndex()) + " ";
			String CommandText = Extra + Command.getText();
			if(CommandText.equals("Command Clear Inventory")){
				ConsoleCommands.Write("Cleared inventory");
				ClearMainInventory(MainInventory);
			}
			else if(CommandText.equals("Command Admin Access")) {
				Actions.OnOff(AdminAccess,"Admin Access");
			}
			else if(CommandText.equals("Command Admin Full Access")) {
				Actions.OnOff(AdminFullAccess,"Admin Full Access");
			}
			else if(CommandText.equals("Command Table Login")) {
				Actions.OnOff(TableLogin,"Table Login"); 
			}
			else if(CommandText.equals("Command Version")) {
				ConsoleCommands.Write(Update.Commands.version);
			}
			else if(CommandText.equals("Command Update Info")) {
				String info = Update.Commands.update1;
				if(!Update.Commands.update2.equals("")) {
					info = Update.Commands.update1 + " " + Update.Commands.update2;
				}
				if(!Update.Commands.update3.equals("")) {
					info = Update.Commands.update1 + " " + Update.Commands.update2 + " " + Update.Commands.update3;
				}
				ConsoleCommands.Write(info);
			}
			else if(CommandText.equals("Command Clear")) {
				ConsoleCommands.ClearConsole(Console);
			}
			else {
				try 
		        { 
		            int key = Integer.parseInt(Command.getText());
		            int value = 1;
		            AddMainInventory(MainInventory,key,value);
		        }  
		        catch (NumberFormatException e)  
		        { 
		        	ConsoleCommands.Write("Error: Unvalid Command");
		        } 
			}
			ConsoleCommands.UpdateConsole(Console);
		}
	}
} 