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
public class Users {
    private JFrame frmUsers;
    private JTextField NameTextField;
    private void SwitchUser(String Name) {
        try {
            String Name_Setup = "Users\\Main-Name.txt";
            File UsernameFile = new File(Name_Setup);
            UsernameFile.delete();
            if(!UsernameFile.exists()) {
                FileWriter Create = new FileWriter(Name_Setup);
                Create.write(Name);
                Create.close();
                NameTextField.setBorder(UI.UsersUI.TextField.Effects.setNewBorder());
            }
            else {
                System.out.println("Failed to create file!");
                NameTextField.setBorder(UI.UsersUI.TextField.Effects.setOldLineBorder());
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public Users(){
        prepareGUI();
    }

    public static void main(String[] args){
        Users  Layouts = new Users();
        Layouts.Form();
    }

    private void prepareGUI(){
        frmUsers = new JFrame();
        frmUsers.setTitle("Users");
        frmUsers.setBounds(100, 100, 664, 329);
        frmUsers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmUsers.setVisible(true);
        java.net.URL imgURL = Users.class.getResource(UI.Files.Icon);
        ImageIcon Icon = new ImageIcon(imgURL);
        frmUsers.setIconImage(Icon.getImage());
    }
    private void Form(){
        JPanel UserFormPanel = new JPanel();
        frmUsers.getContentPane().add(UserFormPanel, BorderLayout.NORTH);
        Label NameLabel = new Label("Name");
        NameLabel.setForeground(UI.UsersUI.Label.Foreground);
        UserFormPanel.add(NameLabel);
        NameTextField = new JTextField();
        NameTextField.setColumns(UI.UsersUI.TextField.Columns);
        NameTextField.setForeground(UI.UsersUI.TextField.Foreground);
        NameTextField.setBorder(UI.UsersUI.TextField.Border);
        UserFormPanel.add(NameTextField);

        Button SwitchButton = new Button("Switch");
        SwitchButton.setForeground(UI.UsersUI.Button.Foreground);
        SwitchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = NameTextField.getText();
                SwitchUser(name);
            }
        });
        UserFormPanel.add(SwitchButton);
    }
}