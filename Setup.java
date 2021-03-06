import Assets.UI;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Setup {
    private JFrame frmSetup;
    private JTextField NameTextField;
    public void AddUser(String Name) {
    	File Users_Directory = new File("Users");
    	String User_File_Dir = "Users\\" + Name;
    	File Users = new File(User_File_Dir);
    	if(!Users_Directory.exists()) {
    		if (Users_Directory.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
    	}
    	if(!Users.exists()) {
    		if (Users.mkdir()) {
                System.out.println("Directory is created!");
                NameTextField.setBorder(UI.SetupUI.TextField.Effects.setNewLineBorder());
            } else {
                System.out.println("Failed to create directory!");
                NameTextField.setBorder(UI.SetupUI.TextField.Effects.setOldLineBorder());
            }
    	}
    	try {
    		String name_file = User_File_Dir + "\\" + Name + "-Name.txt";
    		String Name_Setup = "Users\\Main-Name.txt"; 
		    FileWriter nameWriter = new FileWriter(name_file);
		    nameWriter.write(Name);
		    nameWriter.close();
		    File SetupName = new File(Name_Setup);
		    if(!SetupName.exists()) {
		    	FileWriter Create = new FileWriter(Name_Setup);
		    	Create.write(Name);
		    	Create.close();
		    }
		    else {
		    	System.out.println("Failed to create file!");
		    }
    	} 
    	catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
    	}
    }
    public Setup(){
        prepareGUI();
    }

    public static void main(String[] args){
        Setup  Layouts = new Setup();
        Layouts.Form();
    }

    private void prepareGUI(){
        frmSetup = new JFrame();
        frmSetup.setTitle("Setup");
        frmSetup.setBounds(100, 100, 664, 329);
        frmSetup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmSetup.setVisible(true);
		java.net.URL imgURL = Setup.class.getResource(UI.Files.Icon);
		ImageIcon Icon = new ImageIcon(imgURL);
		frmSetup.setIconImage(Icon.getImage());
    }
    private void Form(){
    	JPanel FormPanel = new JPanel();
		frmSetup.getContentPane().add(FormPanel, BorderLayout.NORTH);
		Label NameLabel = new Label("Name");
		NameLabel.setForeground(UI.SetupUI.Label.Foreground);
		FormPanel.add(NameLabel);
		NameTextField = new JTextField();
		NameTextField.setColumns(UI.SetupUI.TextField.Columns);
		NameTextField.setForeground(UI.SetupUI.TextField.Foreground);
		NameTextField.setBorder(UI.SetupUI.TextField.Border);
		FormPanel.add(NameTextField);
		Button AddButton = new Button("Add");
		AddButton.setForeground(UI.SetupUI.Button.Foreground);
		AddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String name = NameTextField.getText();
				AddUser(name);
			}
		});
		FormPanel.add(AddButton);
    }
}