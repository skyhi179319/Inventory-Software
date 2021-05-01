import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ComponentSampleModel;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.*;

import Colors.colors;
import Components.Bundle;
import Components.Bundle.InfoForm;
import Components.Menu.menuItem;
import Components.Button;
import Components.Label;
import Components.Table;
import Language.Console;

public class Program {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int code = Language.Security.Initialize.newCode(100);
		String[] Columns = {"Firstname","Lastname"};
		TreeMap<String,String> Data = new TreeMap<String,String>();
		JPanel DataTable = new Components.Table.Tables(Columns, Data, Colors.colors.darkgoldenrod, Colors.colors.white, Colors.colors.darkgreen);
		JPanel DataEntry = new Components.Bundle.InfoForm( new MouseAdapter() {
			@Override
		  	public void mouseClicked(MouseEvent e) {
				/* Has an array of 4 JTextFields but only getting 2 of the 4 */
				JTextField Firstname = Components.Bundle.InfoForm.TextfieldComponents.get(0);
				JTextField Lastname = Components.Bundle.InfoForm.TextfieldComponents.get(1);
				Data.put(Firstname.getText(),Lastname.getText());
				Language.Application.updateTable(Components.Table.Tables.model, Data);
				Language.Application.clearText(Firstname);
				Language.Application.clearText(Lastname);
			}
		});
		String[] TabsString = {"Data Entry","Table","Table Data","Time"};
		JPanel Edit = new JPanel();
		Edit.add(new Components.Button(26, "Delete", true, Colors.colors.Black, Colors.colors.silver, new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JDialog content = new JDialog();
				content.setBounds(150, 150, 500, 300);
				content.setTitle("Security");
				JPanel codeInputPane = new JPanel();
				JTextField codeInput = new Components.Textfield(10, Colors.colors.Black, Colors.colors.silver);
				codeInputPane.add(new Components.Label("Code",Colors.colors.Black));
				codeInputPane.add(codeInput);
				codeInputPane.add(new Components.Button(26, "Verify", true, Colors.colors.Black, Colors.colors.silver, new MouseAdapter() {
					@Override
				  	public void mouseClicked(MouseEvent e) {
						if(Language.Security.Verify(code, codeInput.getText()) == true) {
							Language.Application.deleteRow(Components.Table.Tables.table.get(0), Components.Table.Tables.model);
							content.dispose();
						}
						else {
							content.dispose();
						}
					}
				}));
			    content.getContentPane().add(codeInputPane, BorderLayout.CENTER);
			    content.show();
			}
		}));
		Edit.add(new Components.Button(26, "Export", true, Colors.colors.Black, Colors.colors.silver, new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object[] firstname = Data.keySet().toArray();
				ArrayList<String> Lastname = new ArrayList<String>();
				ArrayList<String> Name = new ArrayList<String>();
				for (String i : Data.keySet()) {
					Lastname.add(Data.get(i));
				}
				for(int i = 0; i < firstname.length; i++) {
					Name.add(firstname[i] + " " + Lastname.get(i));
				}
				Object[] data = Name.toArray();
				Language.Application.writeFile("test.txt", data);
				Language.Console.Log(Language.Application.readFile("test.txt"));
			}
		}));
		JPanel[] Tabs = {DataEntry,DataTable,Edit,new Bundle.TimeInfo()};
		Color[] Foreground = {Colors.colors.Black,Colors.colors.Black,Colors.colors.Black,Colors.colors.Black};
		Components.Tabs Tab = new Components.Tabs(TabsString, Tabs, Foreground, 500, 500);
		JFrame win = new Components.Window(0, 0, 1000, 600, "Title", new JPanel(), new JPanel(), new JPanel(), new JPanel(),Tab,new WindowAdapter() {
	         @Override
	         public void windowOpened(WindowEvent e) {
	        	 new Components.Dialog(0, 0, 250, 200, "Security", Integer.toString(code), new MouseAdapter() {
	        		 @Override
					 public void mouseClicked(MouseEvent e) {
	        			 Components.Dialog.dialog.get(0).dispose();
	        		 }
	        	 });
	         }
	    });
		/* JMenu */
		Components.Menu.menuItem[] options = {new menuItem("Add Data", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog content = new JDialog();
				content.setBounds(150, 150, 500, 300);
				content.setTitle("Add");
			    content.getContentPane().add(new Components.Bundle.InfoForm( new MouseAdapter() {
					@Override
				  	public void mouseClicked(MouseEvent e) {
						/* Has an array of 4 JTextFields but only getting 2 of the 4 */
						JTextField Firstname = Components.Bundle.InfoForm.TextfieldComponents.get(2);
						JTextField Lastname = Components.Bundle.InfoForm.TextfieldComponents.get(3);
						Data.put(Firstname.getText(),Lastname.getText());
						Language.Application.updateTable(Components.Table.Tables.model, Data);
						content.dispose();
					}
				}), BorderLayout.CENTER);
			    content.show();
			}
		}),new menuItem("Delete Row",new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog content = new JDialog();
				content.setBounds(150, 150, 500, 300);
				content.setTitle("Security");
				JPanel codeInputPane = new JPanel();
				JTextField codeInput = new Components.Textfield(10, Colors.colors.Black, Colors.colors.silver);
				codeInputPane.add(new Components.Label("Code",Colors.colors.Black));
				codeInputPane.add(codeInput);
				codeInputPane.add(new Components.Button(26, "Verify", true, Colors.colors.Black, Colors.colors.silver, new MouseAdapter() {
					@Override
				  	public void mouseClicked(MouseEvent e) {
						if(Language.Security.Verify(code, codeInput.getText()) == true) {
							Language.Application.deleteRow(Components.Table.Tables.table.get(0), Components.Table.Tables.model);
							content.dispose();
						}
						else {
							content.dispose();
						}
					}
				}));
			    content.getContentPane().add(codeInputPane, BorderLayout.CENTER);
			    content.show();
			}
		}),new menuItem("Export Data",new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] firstname = Data.keySet().toArray();
				ArrayList<String> Lastname = new ArrayList<String>();
				ArrayList<String> Name = new ArrayList<String>();
				for (String i : Data.keySet()) {
					Lastname.add(Data.get(i));
				}
				for(int i = 0; i < firstname.length; i++) {
					Name.add(firstname[i] + " " + Lastname.get(i));
				}
				Object[] data = Name.toArray();
				Language.Application.writeFile("test.txt", data);
				Language.Console.Log(Language.Application.readFile("test.txt"));
			}
			
		}),new menuItem("Exit",new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog confirm = new Components.Dialog(150, 150, 200, 100, "Confirm", "Do you want to exit", new MouseAdapter() {
					@Override
				  	public void mouseClicked(MouseEvent e) {
						Language.Application.exitApplication();
					}
				}, new MouseAdapter() {
					@Override
				  	public void mouseClicked(MouseEvent e) {
						
					}
				} ,true);
				confirm.show();
			}
		})};
		Components.Menu.menuItem[] table = {new menuItem("Add Data", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog content = new JDialog();
				content.setBounds(150, 150, 500, 300);
				content.setTitle("Add");
			    content.getContentPane().add(new Components.Bundle.InfoForm( new MouseAdapter() {
					@Override
				  	public void mouseClicked(MouseEvent e) {
						/* Has an array of 4 JTextFields but only getting 2 of the 4 */
						JTextField Firstname = Components.Bundle.InfoForm.TextfieldComponents.get(2);
						JTextField Lastname = Components.Bundle.InfoForm.TextfieldComponents.get(3);
						Data.put(Firstname.getText(),Lastname.getText());
						Language.Application.updateTable(Components.Table.Tables.model, Data);
						content.dispose();
					}
				}), BorderLayout.CENTER);
			    content.show();
			}
		}),new menuItem("Delete Row",new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JDialog content = new JDialog();
				content.setBounds(150, 150, 500, 300);
				content.setTitle("Security");
				JPanel codeInputPane = new JPanel();
				JTextField codeInput = new Components.Textfield(10, Colors.colors.Black, Colors.colors.silver);
				codeInputPane.add(new Components.Label("Code",Colors.colors.Black));
				codeInputPane.add(codeInput);
				codeInputPane.add(new Components.Button(26, "Verify", true, Colors.colors.Black, Colors.colors.silver, new MouseAdapter() {
					@Override
				  	public void mouseClicked(MouseEvent e) {
						if(Language.Security.Verify(code, codeInput.getText()) == true) {
							Language.Application.deleteRow(Components.Table.Tables.table.get(0), Components.Table.Tables.model);
							content.dispose();
						}
						else {
							content.dispose();
						}
					}
				}));
			    content.getContentPane().add(codeInputPane, BorderLayout.CENTER);
			    content.show();
				
			}
		}),new menuItem("Export Data",new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] firstname = Data.keySet().toArray();
				ArrayList<String> Lastname = new ArrayList<String>();
				ArrayList<String> Name = new ArrayList<String>();
				for (String i : Data.keySet()) {
					Lastname.add(Data.get(i));
				}
				for(int i = 0; i < firstname.length; i++) {
					Name.add(firstname[i] + " " + Lastname.get(i));
				}
				Object[] data = Name.toArray();
				Language.Application.writeFile("test.txt", data);
				Language.Console.Log(Language.Application.readFile("test.txt"));
			}
			
		})};
		Components.Menu.menu[] items = {new Components.Menu.menu("Options", options),new Components.Menu.menu("Table", table)};
		win.setJMenuBar(new Components.Menu.menuBar(items));
	}
}