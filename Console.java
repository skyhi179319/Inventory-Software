package Assets;
import java.awt.Choice;
import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import Assets.colors.Colors;
import Assets.UI;
import Assets.UI.Program;
import Assets.UI.Themes;

public class Console{
	public static ArrayList<String> ConsoleArray = new ArrayList<String>();
	public static ArrayList<String> ConsoleCommand = new ArrayList<String>();
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
	public static void Start(){
		ConsoleWriteLine("Started Console");
	}
	public static void Restart(JTextArea Console) {
		ConsoleArray.clear();
		ConsoleCommand.clear();
		Console.setText("");
		Start();
	}
	public static class UI{
		public static class JTextAreaUI{
			public static int Rows = 18;
			public static int Columns = 40;
			public static Color Foreground = Colors.lightblue.darker(); 
			public static Color Background = Colors.info;
			public static Border Border = BorderFactory.createLineBorder(Colors.darkgoldenrod);
		}
		public static class JTextFieldUI{
			public static int Columns = 15;
			public static Color Foreground = Colors.lightblue.darker();
			public static Color Background = Colors.info;
			public static Border Border = BorderFactory.createLineBorder(Colors.darkgoldenrod);
		}
		public static class Choice{
			public static Color Foreground = Colors.lightblue.darker();
			public static Color Background = Colors.info;
		}
	}
	public static class Commands{
		public static class Actions{
			public static void OnOff(boolean Bool,String Write) {
				if(Bool == true) {
					Bool = false;
					ConsoleWriteLine(Write + " is disabled");
				}
				if(Bool == false) {
					Bool = true;
					ConsoleWriteLine(Write + " is enabled");
				}
			}
			public static void ResetField(JTextField Command) {
				Command.setText("");
			}
			public static void SwitchExtraCommand(Choice ExtraCommands,int Pos,String text) {
				ExtraCommands.select(Pos);
				ConsoleCommands.Write(text);
			}
			public static void SwitchToDefault(Choice ExtraCommands) {
				SwitchExtraCommand(ExtraCommands, 0,"Switched To Command - Command");
			}
			public static void Focus(JTextField Command) {
				Command.grabFocus();
			}
			public static void AddToCommands(String Command) {
				if(ConsoleCommand.contains(Command)) {
					ConsoleCommand.remove(Command);
					ConsoleCommand.add(Command);
				}
				else {
					ConsoleCommand.add(Command);
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
			private static int Position;
			public static void MoveUp(JTextField Command) {
				int CurrentPos = Position;
				int PredictedPos = CurrentPos + 1; 
				if(ConsoleCommand.size() == 1) {
					Command.setText(ConsoleCommand.get(0));
				}
				else if(PredictedPos > ConsoleCommand.size()) {
					Position = 0;
					Command.setText(ConsoleCommand.get(Position));
				}
				else {
					int pos = Position + 1;
					Position = pos;
					Command.setText(ConsoleCommand.get(Position));
				}
			}
			public static void MoveDown(JTextField Command) {
				int CurrentPos = Position;
				int PredictedPos = CurrentPos - 1;;
				if(ConsoleCommand.size() == 1) {
					Command.setText(ConsoleCommand.get(0));
				}
				else if(PredictedPos == -1) {
					Position = Position + 1;
					Command.setText(ConsoleCommand.get(Position));
				}
				else {
					int pos = Position - 1;
					Position = pos;
					Command.setText(ConsoleCommand.get(Position));
				}
			}
		}
		public static class Inventory{
			private static int parseInt(Integer integer) {
			      // TODO Auto-generated method stub
			      return integer;
			}
			public static void Search(TreeMap<Integer, Integer> array,int key) {
				// key = Barcode
				// Value = Product Amount
				int barcodeRef = key;
                int barcode = Integer.valueOf(array.get(barcodeRef));
                if(array.containsValue(barcode)){
                   String MainText = "Barcode Found: " + barcodeRef + ". Scanned: " + barcode + "." ;
                   ConsoleCommands.Write(MainText);
                }
			}
			public static void Export(TreeMap<Integer, Integer> array,String filename) {
				try {
			        LocalDate date = LocalDate.now();
			        Random rand = new Random();
			        int version = rand.nextInt(1001);
			        String file = date + "-" + version + "-" + filename;
			        FileWriter myWriter = new FileWriter(file);
			        for (int i : array.keySet()) {
			           String text = "Barcode: " + i + " Amount: " + array.get(i);
			           myWriter.write(text + "\r\n");
			           myWriter.write("-----------------------------");
			           myWriter.write("\r\n");
			        }
			        myWriter.close();
			        ConsoleCommands.Write("Exported Barcodes");
				} 
				catch (IOException e) {
					System.out.println("An error occurred.");
					e.printStackTrace();
				}
			} 
			public static void Add(TreeMap<Integer, Integer> array,int key, int value) {
				// key = Barcode
				// Value = Product Amount
				ConsoleCommands.Write("Added Barcode: " + key + " To Inventory");
				if (array.containsKey(key)) {
			       int total = parseInt(array.get(key)) + value;
			       array.replace(key, total);
			    }
			    else {
			       array.put(key, value);
			    }
			}
			public static void Delete(TreeMap<Integer, Integer> array,int key) {
				// key = Barcode
				// Value = Product Amount
				array.remove(key);
				ConsoleCommands.Write("Deleted Barcode: " + key + " From Inventory");
			}
			public static void Clear(TreeMap<Integer, Integer> array) {
				array.clear();
			}
		}
		public static class Table{
			public static void Refresh(DefaultTableModel model,TreeMap<Integer, Integer> MainInventory) {
				int rowCount = model.getRowCount();
	            for (int i = rowCount - 1; i >= 0; i--) {
	               model.removeRow(i);
	            }
	            for (int i : MainInventory.keySet()) {
	               Object[] set = {i,MainInventory.get(i)};
	               model.addRow(set);
	            }
	            ConsoleCommands.Write("Table Refreshed");
			}
			public static void Count(DefaultTableModel model) {
				int RowCount = model.getRowCount();
	            int Sum = 0;
	            // Count Data
	            for (int i = 0; i < model.getRowCount(); i++){
	               Object obj = model.getValueAt(i,1);
	               int ColSum = Integer.parseInt(obj.toString());
	               Sum += ColSum;
	            }
	            String Print = "Barcodes: " + RowCount + " Items: " + Sum;
	            ConsoleCommands.Write(Print);
			}
			public static void Update(DefaultTableModel model,TreeMap<Integer, Integer> MainInventory) {
				Refresh(model,MainInventory);
				Count(model);
			}
		}
		public static void Run(JTextField Command,Choice ExtraCommands,JTextArea Console,TreeMap<Integer, Integer> MainInventory,DefaultTableModel model) {
			String Extra = ExtraCommands.getItem(ExtraCommands.getSelectedIndex()) + " ";
			String CommandText = Extra + Command.getText();
			// INVENTORY COMMANDS
			if(CommandText.equals("Command Clear Inventory")){
				ConsoleCommands.Write("Cleared inventory");
				Inventory.Clear(MainInventory);
				Actions.AddToCommands("Clear Inventory");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Export Inventory Now")) {
				Inventory.Export(MainInventory, "InventoryExport.txt");
				Actions.AddToCommands("Now");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			// BOOLEAN COMMANDS
			else if(CommandText.equals("Command Admin Access")) {
				Actions.OnOff(AdminAccess,"Admin Access");
				Actions.AddToCommands("Admin Access");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Command Admin Full Access")) {
				Actions.OnOff(AdminFullAccess,"Admin Full Access");
				Actions.AddToCommands("Admin Full Access");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Command Table Login")) {
				Actions.OnOff(TableLogin,"Table Login");
				Actions.AddToCommands("Table Login");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Command Version")) {
				ConsoleCommands.Write(Update.Commands.version);
				Actions.AddToCommands("Version");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
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
				Actions.AddToCommands("Update Info");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			// TABLE COMMMANDS
			else if(CommandText.equals("Table Refresh")) {
				Table.Refresh(model,MainInventory);
				Actions.AddToCommands("Refresh");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Table Count")) {
				Table.Count(model);
				Actions.AddToCommands("Count");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Table Update")) {
				Table.Update(model, MainInventory);
				Actions.AddToCommands("Update");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			// CONSOLE COMMANDS
			else if(CommandText.equals("Command Clear")) {
				ConsoleCommands.ClearConsole(Console);
				Actions.AddToCommands("Clear");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Command Restart")) {
				Actions.ResetField(Command);
				Restart(Console);
				// Switch To Default Color
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			// CONSOLE COMMANDS - COMMAND
			else if(CommandText.equals("Command Switch Table")) {
				Actions.SwitchExtraCommand(ExtraCommands, 1,"Switch To Table - Command");
				Actions.AddToCommands("Switch Table");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Command Switch Search Inventory")) {
				Actions.SwitchExtraCommand(ExtraCommands, 2, "Switch To Search Inventory - Command");
				Actions.AddToCommands("Switch Search Inventory");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Command Switch Delete Inventory")) {
				Actions.SwitchExtraCommand(ExtraCommands, 3, "Switch To Delete Inventory - Command");
				Actions.AddToCommands("Switch Delete Inventory");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			else if(CommandText.equals("Command Switch Export Inventory")) {
				Actions.SwitchExtraCommand(ExtraCommands, 4, "Switch To Export Inventory - Command");
				Actions.AddToCommands("Switch Export Inventory");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			// CONSOLE COMMANDS - TABLE
			else if(CommandText.equals("Table Switch Command")) {
				Actions.SwitchToDefault(ExtraCommands);
				Actions.AddToCommands("Switch Command");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			// CONSOLE COMMANDS - SEARCH INVENTORY
			else if(CommandText.equals("Search Inventory Switch Command")) {
				Actions.SwitchToDefault(ExtraCommands);
				Actions.AddToCommands("Switch Command");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			// CONSOLE COMMANDS - DELETE INVENTORY
			else if(CommandText.equals("Delete Inventory Switch Command")) {
				Actions.SwitchToDefault(ExtraCommands);
				Actions.AddToCommands("Switch Command");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}
			// CONSOLE COMMANDS - EXPORT INVENTORY
			else if(CommandText.equals("Export Inventory Switch Command")) {
				Actions.SwitchToDefault(ExtraCommands);
				Actions.AddToCommands("Switch Command");
				Actions.ResetField(Command);
				Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
			}			
			// BARCODE AND ERROR COMMANDS
			else {
				try 
		        { 
					String ExtraCommandString = ExtraCommands.getItem(ExtraCommands.getSelectedIndex());					
		            int key = Integer.parseInt(Command.getText());
		            int value = 1;
		            String KeyString = ExtraCommandString + " " + key;
		            if (KeyString.equals("Search Inventory " + key)) {
		            	Inventory.Search(MainInventory, key);
		            	Actions.ResetField(Command);
		            }
		            else if(KeyString.equals("Delete Inventory " + key)) {
		            	Inventory.Delete(MainInventory,key);
		            	Actions.ResetField(Command);
		            }
		            else {
		            	Inventory.Add(MainInventory,key,value);
		            	Actions.ResetField(Command);
		            }
		           Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderOne);
		        }  
		        catch (NumberFormatException e)  
		        { 
		        	ConsoleCommands.Write("Error: Unvalid Command");
		        	Actions.ResetField(Command);
		        	Command.setBorder(Program.AdminPortal.ConsoleArea.TextField.BorderTwo);
		        } 
			}
			ConsoleCommands.UpdateConsole(Console);
		}
	}
	public static UI.JTextAreaUI TextArea = new UI.JTextAreaUI();
	public static UI.JTextFieldUI TextField = new UI.JTextFieldUI();
	public static UI.Choice Choice = new UI.Choice();
	public static Commands Run = new Commands();
	public static Commands.ConsoleCommands Move = new Commands.ConsoleCommands();
} 