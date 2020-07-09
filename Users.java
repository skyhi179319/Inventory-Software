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
    public void SwitchUser(String Name) {
        try {
            String Name_Setup = "Users\\Main-Name.txt";
            File UsernameFile = new File(Name_Setup);
            UsernameFile.delete();
            if(!UsernameFile.exists()) {
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
        java.net.URL imgURL = Users.class.getResource("\\Assets\\img\\icon.jpg");
        ImageIcon Icon = new ImageIcon(imgURL);
        frmUsers.setIconImage(Icon.getImage());
    }
    private void Form(){
        JPanel UserFormPanel = new JPanel();
        frmUsers.getContentPane().add(UserFormPanel, BorderLayout.NORTH);
        Label NameLabel = new Label("Name");
        UserFormPanel.add(NameLabel);
        NameTextField = new JTextField();
        NameTextField.setColumns(10);
        UserFormPanel.add(NameTextField);

        Button AddButton = new Button("Switch");
        AddButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String name = NameTextField.getText();
                SwitchUser(name);
            }
        });
        UserFormPanel.add(AddButton);
    }
}