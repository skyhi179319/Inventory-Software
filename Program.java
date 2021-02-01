import Assets.Update;
import Assets.colors.Colors;
import Assets.Console;
import Assets.UI;
import Assets.ButtonUI.size;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Program {
   private JFrame frmInventory;
   private JTextField AddBarcodeTextField;
   private JLabel InventoryCount = new JLabel("Nothing Scanned");
   private String LogFile = "Main-Log.txt";
   private DefaultTableModel model = new DefaultTableModel();
   /*
        Allows Sub-Admin to use code
        Lines 319,349,352,532,533,542,543,1084,1090,1101,1104,1115,1121,1310,1348,1434
    */
   		// AdminAccess
   /*
        Allows Full Admin to be logged in and out
        Lines 462,464,772,803,811,841,849,1310,1388,1434,1438
    */
   		//AdminFullAccess
   /*
        Turns On/off AdminAccess
        Lines 348,351,534,544
    */
   		// KeepAdminAccessOn
   /*
         Turns On/Off Table Login
         Lines 569,576,1310,1434
   */
   		// TableLogin

   TreeMap<Integer,Integer> MainInventory = new TreeMap<Integer,Integer>();
   TreeMap<LocalTime,String> LogInfo = new TreeMap<LocalTime,String>();
   ArrayList<String> UserPathName = new ArrayList<String>();
   private int parseInt(Integer integer) {
      // TODO Auto-generated method stub
      return integer;
   }
   private void AddToInventory(int barcode,int amount){
      if (MainInventory.containsKey(barcode)) {
         int total = parseInt(MainInventory.get(barcode)) + amount;
         MainInventory.replace(barcode, total);
      }
      else {
         MainInventory.put(barcode, amount);
      }
   }
   private void PrintInventory(){
      for (int i : MainInventory.keySet()) {
         String text = "Barcode: " + i + " Amount: " + MainInventory.get(i) + ";" ;
         InventoryCount.setText(text);
         System.out.println(text);
      }
   }
   private void ExportInventory(String filename){
      try {
         LocalDate date = LocalDate.now();
         Random rand = new Random();
         int version = rand.nextInt(1001);
         String file = date + "-" + version + "-" + filename;
         FileWriter myWriter = new FileWriter(file);
         for (int i : MainInventory.keySet()) {
            String text = "Barcode: " + i + " Amount: " + MainInventory.get(i);
            myWriter.write(text + "\r\n");
            myWriter.write("-----------------------------");
            myWriter.write("\r\n");
         }
         myWriter.close();
      } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
   }
   private void close(JFrame frame){
      frame.dispose();
   }
   private void Log(String text){
      LocalTime time = LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
      LogInfo.put(time,text);
      System.out.println(time);
   }
   private void CompleteLog(String filename){
      try {
         LocalDate date = LocalDate.now();
         String Log_File_Dir = "Logs\\";
         File File_Dir = new File(Log_File_Dir);
         if(File_Dir.exists()){
            System.out.println("Logs Directory Found");
            String DateFolder = Log_File_Dir + date + "\\";
            File Date_Folder = new File(DateFolder);
            if(!Date_Folder.exists()){
               Date_Folder.mkdir();
            }
         }
         else{
            File_Dir.mkdir();
         }
         Random rand = new Random();
         int version = rand.nextInt(1001);
         String FolderDir = Log_File_Dir + date + "\\";
         String file = FolderDir + date + "-" + version + "-" + filename;
         File check_file = new File(file);
         FileWriter myWriter = new FileWriter(file);
         if(check_file.exists()){
            for (LocalTime i : LogInfo.keySet()) {
               String InfoText = LogInfo.get(i) + " - " + i;
               myWriter.write(InfoText + "\r\n");
            }
            myWriter.close();
         }

      } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
   }
   private void VerifyCode(String function, String code){
	  UI.Program.VerifyCodeGUI CustomUI = new UI.Program.VerifyCodeGUI();
      if(function.equals("New") && code.equals("")){
         Log("Created A New Code");
         JFrame NewCode = new JFrame();
         NewCode.setTitle("New Code");
         NewCode.setBounds(150, 150, 664, 150);
         NewCode.setVisible(true);
         java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
         ImageIcon Icon = new ImageIcon(imgURL);
         NewCode.setIconImage(Icon.getImage());
         JPanel Panel = new JPanel();
         NewCode.getContentPane().add(Panel, BorderLayout.CENTER);
         int NewCodeInt = new Random().nextInt(100);
         JLabel CodeLabel = new JLabel(Integer.toString(NewCodeInt));
         CodeLabel.setForeground(CustomUI.Label.Foreground);
         Panel.add(CodeLabel);
         String File_Dir = "Users\\Admin";
         try {
            File File_Dir_File = new File(File_Dir);
            if(File_Dir_File.exists()){
                System.out.println("Code Directory Found");
            }
            else{
                File_Dir_File.mkdir();
            }
            String file = File_Dir + "\\Code.txt";
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(CodeLabel.getText());
            myWriter.close();

        } catch (IOException f) {
            System.out.println("An error occurred.");
            f.printStackTrace();
        }
       }
      if(function.equals("New Self Code") && !code.equals("")){
         try {
            String File_Dir = "Users\\Admin";
            String file = File_Dir + "\\Code.txt";
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(code);
            myWriter.close();

         } catch (IOException f) {
            System.out.println("An error occurred.");
            f.printStackTrace();
         }
      }
      if(function.equals("New Random Code")){
         try {
            String File_Dir = "Users\\Admin";
            String file = File_Dir + "\\Code.txt";
            int NewCodeInt = new Random().nextInt(100);
            FileWriter myWriter = new FileWriter(file);
            myWriter.write(String.valueOf(NewCodeInt));
            myWriter.close();

         } catch (IOException f) {
            System.out.println("An error occurred.");
            f.printStackTrace();
         }
      }
      if(function.equals("Verify")) {
         try{
            int codeToString = Integer.valueOf(code);
            String File_Dir = "Users\\Admin";
            String file = File_Dir + "\\Code.txt";
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int Verified_Code = Integer.valueOf(reader.readLine());
            if(codeToString == Verified_Code){
               Console.AdminAccess = true;
               Log("Admin Logged In");
            }
            else{
               VerifyCode("Retry","");
               Log("User Attempted To Login As Admin");
            }
            reader.close();
         }
         catch (IOException f){
            System.out.println("An error occurred.");
            f.printStackTrace();
         }
      }
      if(function.equals("Retry") && code.equals("")){
         JFrame Alert = new JFrame();
         Alert.setTitle("Wrong Code");
         Alert.setBounds(150, 150, 664, 100);
         Alert.setVisible(true);
         java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
         ImageIcon Icon = new ImageIcon(imgURL);
         Alert.setIconImage(Icon.getImage());
         JPanel Panel = new JPanel();
         Alert.getContentPane().add(Panel, BorderLayout.CENTER);
         JLabel label = new JLabel();
         label.setForeground(CustomUI.Label.Foreground);
         label.setText("Wrong Code. Please Re-enter The Code");
         Panel.add(label);
      }
      if(function.equals("Verified") && Console.KeepAdminAccessOn == false){
    	  Console.AdminAccess = false;
      }
      if(function.equals("Verified") && Console.KeepAdminAccessOn == true){
    	  Console.AdminAccess = true;
      }
   }
   private void VerifyCodeGUI(){
	  UI.Program.VerifyCodeGUI CustomUI = new UI.Program.VerifyCodeGUI();
      JFrame GUIFrame = new JFrame();
      GUIFrame.setTitle("Verify");
      GUIFrame.setBounds(150, 150, 664, 150);
      GUIFrame.setVisible(true);
      java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
      ImageIcon Icon = new ImageIcon(imgURL);
      GUIFrame.setIconImage(Icon.getImage());
      JPanel Form = new JPanel();
      GUIFrame.getContentPane().add(Form, BorderLayout.CENTER);
      JLabel CodeLabel = new JLabel("Code:");
      CodeLabel.setForeground(CustomUI.Label.Foreground);
      Form.add(CodeLabel);
      JTextField Code = new JTextField();
      Code.setColumns(CustomUI.TextField.Columns);
      Code.setForeground(CustomUI.TextField.Foreground);
      Code.setBorder(CustomUI.TextField.Border);
      Form.add(Code);
      JButton Verify =  size.newUINumber(26,"Verify",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);
      Verify.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            VerifyCode("Verify",Code.getText());
            GUIFrame.dispose();
         }
      });
      Form.add(Verify);
   }
   private void VerifyUser(String function, String username, String password){
	  UI.Program.VerifyUser CustomUI = new UI.Program.VerifyUser(); 
      if(function.equals("New") && username == "" && password == ""){
         JFrame NewAdmin = new JFrame();  
         NewAdmin.setTitle("New Admin");
         NewAdmin.setBounds(150, 150, 664, 150);
         NewAdmin.setVisible(true);
         java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
         ImageIcon Icon = new ImageIcon(imgURL);
         NewAdmin.setIconImage(Icon.getImage());
         JPanel Panel = new JPanel();
         NewAdmin.getContentPane().add(Panel, BorderLayout.CENTER);
         JLabel UsernameLabel = new JLabel("Username:");
         UsernameLabel.setForeground(CustomUI.Label.Foreground);
         JTextField UsernameText = new JTextField();
         UsernameText.setForeground(CustomUI.TextField.Foreground);
         UsernameText.setColumns(CustomUI.TextField.Columns);
         UsernameText.setBorder(CustomUI.TextField.Border);
         JLabel PasswordLabel = new JLabel("Password:");
         PasswordLabel.setForeground(CustomUI.Label.Foreground);
         JTextField PasswordText = new JTextField();
         PasswordText.setForeground(CustomUI.TextField.Foreground);
         PasswordText.setColumns(CustomUI.TextField.Columns);
         PasswordText.setBorder(CustomUI.TextField.Border);
         Panel.add(UsernameLabel);
         Panel.add(UsernameText);
         Panel.add(PasswordLabel);
         Panel.add(PasswordText);
         JButton AddAdmin = new JButton("Add");
         AddAdmin.setForeground(CustomUI.Button.Foreground);
         String File_Dir = "Users\\Admin";
         String UsernameFile = File_Dir + "\\Username.txt";
         String PasswordFile = File_Dir + "\\Password.txt";
         File UsernameFileCreate = new File(UsernameFile);
         File PasswordFileCreate = new File(PasswordFile);
         AddAdmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               try {
                  File File_Dir_File = new File(File_Dir);
                  if(File_Dir_File.exists()){
                     System.out.println("Admin Directory Found");
                  }
                  else{
                     File_Dir_File.mkdir();
                  }
                  if(!UsernameFileCreate.exists()){
                     UsernameFileCreate.createNewFile();
                     FileWriter UsernameFileWriter = new FileWriter(UsernameFile);
                     UsernameFileWriter.write(UsernameText.getText());
                     UsernameFileWriter.close();
                  }
                  if(!PasswordFileCreate.exists()){
                     PasswordFileCreate.createNewFile();
                     FileWriter PasswordFileWriter = new FileWriter(PasswordFile);
                     PasswordFileWriter.write(PasswordText.getText());
                     PasswordFileWriter.close();
                  }
                  else{
                     NewAdmin.dispose();
                  }
               } catch (IOException f) {
                  System.out.println("An error occurred.");
                  f.printStackTrace();
               }
            }
         });
         Panel.add(AddAdmin);
         if(UsernameFileCreate.exists() && PasswordFileCreate.exists()){
            NewAdmin.dispose();
         }
      }
      if(function.equals("Verify")){
         String File_Dir = "Users\\Admin";
         String UsernameFile = File_Dir + "\\Username.txt";
         String PasswordFile = File_Dir + "\\Password.txt";
         // Verifying User
         try{
            BufferedReader Username = new BufferedReader(new FileReader(UsernameFile));
            BufferedReader Password = new BufferedReader(new FileReader(PasswordFile));
            String CheckUsername = Username.readLine();
            String CheckPassword = Password.readLine();
            if(username.equals(CheckUsername) && password.equals(CheckPassword) && Console.AdminFullAccess == false){
               Log("Admin Logged In And Has Access");
               Console.AdminFullAccess = true;
            }
            else{
               VerifyUser("Retry","","");
               Log("User Attempted To Login As Admin");
            }
            Username.close();
            Password.close();
         }
         catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
         }
      }
      if(function.equals("Retry")){
         JFrame LoginAlert = new JFrame();
         LoginAlert.setTitle("Wrong Code");
         LoginAlert.setBounds(150, 150, 664, 100);
         LoginAlert.setVisible(true);
         java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
         ImageIcon Icon = new ImageIcon(imgURL);
         LoginAlert.setIconImage(Icon.getImage());
         JPanel Panel = new JPanel();
         LoginAlert.getContentPane().add(Panel, BorderLayout.CENTER);
         JLabel label = new JLabel();
         label.setForeground(CustomUI.Label.Foreground);
         label.setText("Wrong Login Information.");
         Panel.add(label);
      }
   }
   private void AdminPortalArea(){
	  UI.Program.AdminPortalArea AdminPortal = new UI.Program.AdminPortalArea();
      JFrame AdminArea = new JFrame();
      AdminArea.setTitle("Admin");
      AdminArea.setBounds(150, 150, 664, 150);
      AdminArea.setVisible(true);
      java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
      ImageIcon Icon = new ImageIcon(imgURL);
      AdminArea.setIconImage(Icon.getImage());
      JPanel ButtonArea = new JPanel();
      JPanel ActionInfo = new JPanel();
      AdminArea.getContentPane().add(ButtonArea, BorderLayout.CENTER);
      AdminArea.getContentPane().add(ActionInfo, BorderLayout.SOUTH);
      JLabel Action = new JLabel();
      Action.setForeground(AdminPortal.Label.Foreground);
      ActionInfo.add(Action);
      JButton AdminAccessSwitch = size.newUINumber(26, 25,"Admin Access", AdminPortal.Button.Foreground, Colors.Palettes.Blue.Darkblue);
      AdminAccessSwitch.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame OnOff = new JFrame();
            OnOff.setTitle("Access Point");
            OnOff.setBounds(175,175,300,100);
            OnOff.setVisible(true);
            java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
            ImageIcon Icon = new ImageIcon(imgURL);
            OnOff.setIconImage(Icon.getImage());
            JPanel AccessButtons = new JPanel();
            OnOff.getContentPane().add(AccessButtons, BorderLayout.CENTER);
            JButton on = size.newUINumber(43,0,"On",AdminPortal.AccessPoint.Button.Foreground , Colors.Palettes.Blue.Darkblue);
            JButton off = size.newUINumber(43,0,"Off",AdminPortal.AccessPoint.Button.Foreground , Colors.Palettes.Blue.Darkblue);
            JButton TableLoginButton = size.newUINumber(30,25,"Table Login",AdminPortal.AccessPoint.Button.Foreground,
            		Colors.Palettes.Blue.Darkblue);
            on.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  if(Console.AdminAccess == false){
                	 Console.AdminAccess = true;
                	 Console.KeepAdminAccessOn = true;
                     Action.setText("Admin Access On");
                  }
               }
            });
            off.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  if(Console.AdminAccess == true){
                	 Console.AdminAccess = false;
                	 Console.KeepAdminAccessOn = false;
                     Action.setText("Admin Access Off");
                  }
               }
            });
            TableLoginButton.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  JFrame TableOnOff = new JFrame();
                  TableOnOff.setTitle("Access Point");
                  TableOnOff.setBounds(175,175,300,100);
                  TableOnOff.setVisible(true);
                  java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
                  ImageIcon Icon = new ImageIcon(imgURL);
                  TableOnOff.setIconImage(Icon.getImage());
                  JPanel TableButtons = new JPanel();
                  TableOnOff.getContentPane().add(TableButtons, BorderLayout.CENTER);
                  JButton ButtonOn = size.newUINumber(43,0,"On",AdminPortal.AccessPoint.Button.Foreground , Colors.Palettes.Blue.Darkblue);
                  JButton ButtonOff = size.newUINumber(43,0,"Off",AdminPortal.AccessPoint.Button.Foreground , Colors.Palettes.Blue.Darkblue);
                  ButtonOn.addMouseListener(new MouseAdapter() {
                     @Override
                     public void mouseClicked(MouseEvent e) {
                        Action.setText("Table Login On");
                        Console.TableLogin = true;
                     }
                  });
                  ButtonOff.addMouseListener(new MouseAdapter() {
                     @Override
                     public void mouseClicked(MouseEvent e) {
                        Action.setText("Table Login Off");
                        Console.TableLogin = false;
                     }
                  });
                  TableButtons.add(ButtonOn);
                  TableButtons.add(ButtonOff);
                  TableButtons.updateUI();
               }
            });
            AccessButtons.add(on);
            AccessButtons.add(off);
            AccessButtons.add(TableLoginButton);
            AccessButtons.updateUI();
         }
      });
      ButtonArea.add(AdminAccessSwitch);
      JButton ChangeCode = size.newUINumber(28,24,"Change Code", AdminPortal.Button.Foreground, Colors.Palettes.Blue.Darkblue);
      ChangeCode.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame CodeWindow = new JFrame();
            CodeWindow.setTitle("Change Code");
            CodeWindow.setBounds(150, 150, 664, 150);
            CodeWindow.setVisible(true);
            java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
            ImageIcon Icon = new ImageIcon(imgURL);
            CodeWindow.setIconImage(Icon.getImage());
            JPanel CodeButtonsArea = new JPanel();
            JPanel CurrentCodeArea = new JPanel();
            CodeWindow.getContentPane().add(CodeButtonsArea, BorderLayout.CENTER);
            CodeWindow.getContentPane().add(CurrentCodeArea, BorderLayout.SOUTH);
            JLabel CurrentCode = new JLabel();
            CurrentCode.setForeground(AdminPortal.ChangeCode.Label.Foreground);
            try{
               String File_Dir = "Users\\Admin";
               String file = File_Dir + "\\Code.txt";
               BufferedReader reader = new BufferedReader(new FileReader(file));
               int Code = Integer.valueOf(reader.readLine());
               CurrentCode.setText(String.valueOf(Code));
               reader.close();
            }
            catch (IOException f){
               System.out.println("An error occurred.");
               f.printStackTrace();
            }
            CurrentCodeArea.add(CurrentCode);
            JButton SelfCode = size.newUINumber(32,24, "Enter Code", AdminPortal.ChangeCode.Button.Foreground, Colors.Palettes.Blue.Darkblue);
            JButton RandomCode = size.newUINumber(27,26, "Random Code", AdminPortal.ChangeCode.Button.Foreground, Colors.Palettes.Blue.Darkblue);
            SelfCode.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  // new window
                  JFrame self = new JFrame();
                  self.setTitle("New Code");
                  self.setBounds(150, 150, 664, 150);
                  self.setVisible(true);
                  java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
                  ImageIcon Icon = new ImageIcon(imgURL);
                  self.setIconImage(Icon.getImage());
                  JPanel panel = new JPanel();
                  self.getContentPane().add(panel, BorderLayout.CENTER);
                  TextField code = new TextField();
                  code.setColumns(AdminPortal.ChangeCode.TextField.Columns);
                  code.setForeground(AdminPortal.ChangeCode.TextField.Foreground);
                  JButton Submit = size.newUINumber(36,0,"Submit", AdminPortal.ChangeCode.Button.Foreground, Colors.Palettes.Blue.Darkblue);
                  Submit.addMouseListener(new MouseAdapter() {
                     @Override
                     public void mouseClicked(MouseEvent e) {
                      VerifyCode("New Self Code",code.getText());
                        try{
                           String File_Dir = "Users\\Admin";
                           String file = File_Dir + "\\Code.txt";
                           BufferedReader reader = new BufferedReader(new FileReader(file));
                           int Code = Integer.valueOf(reader.readLine());
                           CurrentCode.setText(String.valueOf(Code));
                           reader.close();
                        }
                        catch (IOException f){
                           System.out.println("An error occurred.");
                           f.printStackTrace();
                        }
                        self.dispose();
                     }
                  });
                  panel.add(code);
                  panel.add(Submit);
                  panel.updateUI();
               }
            });
            RandomCode.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  VerifyCode("New Random Code","");
                  try{
                     String File_Dir = "Users\\Admin";
                     String file = File_Dir + "\\Code.txt";
                     BufferedReader reader = new BufferedReader(new FileReader(file));
                     int Code = Integer.valueOf(reader.readLine());
                     CurrentCode.setText(String.valueOf(Code));
                     reader.close();
                  }
                  catch (IOException f){
                     System.out.println("An error occurred.");
                     f.printStackTrace();
                  }
               }
            });
            CodeButtonsArea.add(SelfCode);
            CodeButtonsArea.add(RandomCode);
            CodeButtonsArea.updateUI();
         }
      });
      ButtonArea.add(ChangeCode);
      JButton ChangeLoginButton = size.newUINumber(27,25, "Change Login", AdminPortal.Button.Foreground, Colors.Palettes.Blue.Darkblue);
      ChangeLoginButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame CurrentLogin = new JFrame();
            CurrentLogin.setTitle("Current Login");
            CurrentLogin.setBounds(150, 150, 664, 150);
            CurrentLogin.setVisible(true);
            java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
            ImageIcon Icon = new ImageIcon(imgURL);
            CurrentLogin.setIconImage(Icon.getImage());
            JPanel CurrentForm = new JPanel();
            CurrentLogin.getContentPane().add(CurrentForm, BorderLayout.CENTER);
            JLabel UsernameText = new JLabel("Username:");
            JLabel PasswordText = new JLabel("Password:");
            JTextField Username = new JTextField();
            JTextField Password = new JTextField();
            JButton TryChange = size.newUINumber(36,0,"Change", AdminPortal.ChangeLogin.Button.Foreground, Colors.Palettes.Blue.Darkblue);
            UsernameText.setForeground(AdminPortal.ChangeLogin.Label.Foreground);
            PasswordText.setForeground(AdminPortal.ChangeLogin.Label.Foreground);
            Username.setColumns(AdminPortal.ChangeLogin.TextField.Columns);
            Username.setForeground(AdminPortal.ChangeLogin.TextField.Foreground);
            Password.setColumns(AdminPortal.ChangeLogin.TextField.Columns);
            Password.setForeground(AdminPortal.ChangeLogin.TextField.Foreground);
            Username.setBorder(AdminPortal.ChangeLogin.TextField.Border);
            Password.setBorder(AdminPortal.ChangeLogin.TextField.Border);
            TryChange.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  try{
                     String File_Dir = "Users\\Admin";
                     String UsernameFile = File_Dir + "\\Username.txt";
                     String PasswordFile = File_Dir + "\\Password.txt";
                     BufferedReader UsernameReader = new BufferedReader(new FileReader(UsernameFile));
                     BufferedReader PasswordReader = new BufferedReader(new FileReader(PasswordFile));
                     String CheckUsername = UsernameReader.readLine();
                     String CheckPassword = PasswordReader.readLine();
                     String ErrorUsername =  Username.getText();
                     String ErrorPassword =  Password.getText();
                     if(ErrorUsername.equals(CheckUsername) && ErrorPassword.equals(CheckPassword)){
                        Log("Admin Is Changing Login Credentials");
                        CurrentLogin.dispose();
                        JFrame newLogin = new JFrame();
                        newLogin.setTitle("New Login");
                        newLogin.setBounds(150, 150, 664, 150);
                        newLogin.setVisible(true);
                        java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
                        ImageIcon Icon = new ImageIcon(imgURL);
                        newLogin.setIconImage(Icon.getImage());
                        JPanel NewForm = new JPanel();
                        newLogin.getContentPane().add(NewForm, BorderLayout.CENTER);
                        JLabel NewUsernameText = new JLabel("Username:");
                        JLabel NewPasswordText = new JLabel("Password:");
                        JTextField NewUsername = new JTextField();
                        JTextField NewPassword = new JTextField();
                        JButton Change = size.newUINumber(40,0,"Login", AdminPortal.ChangeLogin.Button.Foreground, Colors.Palettes.Blue.Darkblue);
                        NewUsernameText.setForeground(AdminPortal.ChangeLogin.Label.Foreground);
                        NewPasswordText.setForeground(AdminPortal.ChangeLogin.Label.Foreground);
                        NewUsername.setColumns(AdminPortal.ChangeLogin.TextField.Columns);
                        NewUsername.setForeground(AdminPortal.ChangeLogin.TextField.Foreground);
                        NewPassword.setColumns(AdminPortal.ChangeLogin.TextField.Columns);
                        NewPassword.setForeground(AdminPortal.ChangeLogin.TextField.Foreground);
                        NewUsername.setBorder(AdminPortal.ChangeLogin.TextField.Border);
                        NewPassword.setBorder(AdminPortal.ChangeLogin.TextField.Border);
                        NewForm.add(NewUsernameText);
                        NewForm.add(NewUsername);
                        NewForm.add(NewPasswordText);
                        NewForm.add(NewPassword);
                        Change.addMouseListener(new MouseAdapter() {
                           @Override
                           public void mouseClicked(MouseEvent e) {
                              File UsernameFileCreate = new File(UsernameFile);
                              File PasswordFileCreate = new File(PasswordFile);
                              try {
                                 FileWriter UsernameFileWriter = new FileWriter(UsernameFileCreate);
                                 UsernameFileWriter.write(NewUsername.getText());
                                 UsernameFileWriter.close();
                                 FileWriter PasswordFileWriter = new FileWriter(PasswordFileCreate);
                                 PasswordFileWriter.write(NewPassword.getText());
                                 PasswordFileWriter.close();
                              }
                              catch (IOException f) {
                                 System.out.println("An error occurred.");
                                 f.printStackTrace();
                              }
                              Console.AdminFullAccess = false;
                              newLogin.dispose();
                              AdminArea.dispose();
                              Log("Admin Has Changed Login Credentials");
                           }
                        });
                        NewForm.add(Change);
                        NewForm.updateUI();
                     }
                     UsernameReader.close();
                     PasswordReader.close();
                  }
                  catch (IOException f){
                     System.out.println("An error occurred.");
                     f.printStackTrace();
                  }
               }
            });
            CurrentForm.add(UsernameText);
            CurrentForm.add(Username);
            CurrentForm.add(PasswordText);
            CurrentForm.add(Password);
            CurrentForm.add(TryChange);
            CurrentForm.updateUI();
         }
      });
      ButtonArea.add(ChangeLoginButton);
      JButton Logout = size.newUINumber(37,0, "Logout", AdminPortal.Button.Foreground, Colors.Palettes.Blue.Darkblue);
      Logout.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            // Logs out user fully
        	Console.AdminFullAccess = false;
            AdminArea.dispose();
         }
      });
      ButtonArea.add(Logout);
      JButton InfoButton = size.newUINumber(42,0,"Info", AdminPortal.Button.Foreground, Colors.Palettes.Blue.Darkblue);
      InfoButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseClicked(MouseEvent e) {
    		  JFrame self = new JFrame();
              self.setTitle("Info");
              self.setBounds(150, 150, 664, 150);
              self.setVisible(true);
              java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
              ImageIcon Icon = new ImageIcon(imgURL);
              self.setIconImage(Icon.getImage());
              JPanel InfoButtonsPanel = new JPanel();
              JPanel InfoPanel = new JPanel();
              self.getContentPane().add(InfoButtonsPanel, BorderLayout.CENTER);
              self.getContentPane().add(InfoPanel, BorderLayout.SOUTH);
              JLabel InfoLabel = new JLabel("");
              InfoLabel.setForeground(AdminPortal.Info.Label.Foreground);
              InfoPanel.add(InfoLabel);
              // Buttons
              JButton VersionButton = size.newUINumber(36,0,"Version", AdminPortal.Info.Button.Foreground, Colors.Palettes.Blue.Darkblue);
              VersionButton.addMouseListener(new MouseAdapter() {
            	  @Override
                  public void mouseClicked(MouseEvent e) {
                     Update.Commands.DoCommand("version", InfoLabel);
                  }
              });
              InfoButtonsPanel.add(VersionButton);
              JButton UpdateInfoButton = size.newUINumber(36,0,"Updates", AdminPortal.Info.Button.Foreground, Colors.Palettes.Blue.Darkblue);
              UpdateInfoButton.addMouseListener(new MouseAdapter() {
            	  @Override
                  public void mouseClicked(MouseEvent e) {
            		 Update.Commands.DoCommand("Info Concat", InfoLabel);
                  }
              });
              InfoButtonsPanel.add(UpdateInfoButton);
              InfoButtonsPanel.updateUI();
    	  }
      });
      ButtonArea.add(InfoButton);
      JButton ConsoleWindowButton = size.newUINumber(36,0,"Console", AdminPortal.Button.Foreground, Colors.Palettes.Blue.Darkblue);
      ConsoleWindowButton.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseClicked(MouseEvent e) {
    		  Console.Start();
    		  JFrame ConsoleWindow = new JFrame();
    		  ConsoleWindow.setTitle("Console");
    		  ConsoleWindow.setBounds(300, 200, 664, 400);
    		  ConsoleWindow.setVisible(true);
              java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
              ImageIcon Icon = new ImageIcon(imgURL);
              ConsoleWindow.setIconImage(Icon.getImage());
              JPanel CommandChoicePanel = new JPanel();
              ConsoleWindow.getContentPane().add(CommandChoicePanel, BorderLayout.SOUTH);
              Choice ExtraCommands = new Choice();
              ExtraCommands.add("Command");
              ExtraCommands.add("Table");
              ExtraCommands.add("Search Inventory");
              ExtraCommands.add("Delete Inventory");
              ExtraCommands.add("Export Inventory"); 
              ExtraCommands.select(0);
              ExtraCommands.setForeground(AdminPortal.ConsoleArea.Choice.Foreground);
              ExtraCommands.setBackground(AdminPortal.ConsoleArea.Choice.Background);
              CommandChoicePanel.add(ExtraCommands);
              int rows = AdminPortal.ConsoleArea.TextArea.Rows;
              int columns = AdminPortal.ConsoleArea.TextArea.Columns;
              JTextArea ConsoleTextArea = new JTextArea("",rows,columns);
              ConsoleTextArea.setForeground(AdminPortal.ConsoleArea.TextArea.Foreground);
              ConsoleTextArea.setBackground(AdminPortal.ConsoleArea.TextArea.Background);
              ConsoleTextArea.setBorder(AdminPortal.ConsoleArea.TextArea.Border);
              JPanel CommandPanel = new JPanel();
              ConsoleWindow.getContentPane().add(CommandPanel, BorderLayout.NORTH);
              JTextField Command = new JTextField();
              Command.setColumns(AdminPortal.ConsoleArea.TextField.Columns);
              Command.setForeground(AdminPortal.ConsoleArea.TextField.Foreground);
              Command.setBackground(AdminPortal.ConsoleArea.TextField.Background);
              Command.setBorder(AdminPortal.ConsoleArea.TextField.Border);
              Command.addKeyListener(new KeyAdapter() {
                  @Override
                  public void keyPressed(KeyEvent e) {
                     if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                       Console.Run.Run(Command,ExtraCommands,ConsoleTextArea,MainInventory,model);
                     }
                  }
              });
              Command.addKeyListener(new KeyAdapter() {
            	  @Override
                  public void keyPressed(KeyEvent e) {
                     if(e.getKeyCode() == KeyEvent.VK_UP) {
                       Console.Move.MoveUp(Command);
                     }
                  }
              });
              Command.addKeyListener(new KeyAdapter() {
            	  @Override
                  public void keyPressed(KeyEvent e) {
                     if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                       Console.Move.MoveDown(Command);
                     }
                  }
              });
              ExtraCommands.addItemListener(new ItemListener() {
  				@Override
  				public void itemStateChanged(ItemEvent e) {
  					Console.Commands.Actions.Focus(Command);
  				}
              });
              CommandPanel.add(Command);
              JPanel ConsolePanel = new JPanel();
              ConsoleWindow.getContentPane().add(ConsolePanel, BorderLayout.CENTER);
              ConsoleTextArea.setEditable(AdminPortal.ConsoleArea.TextArea.Editable);
              JScrollPane ConsolePane = new JScrollPane(ConsoleTextArea);
              ConsolePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
              ConsolePanel.add(ConsolePane);
    	  }
      });
      ButtonArea.add(ConsoleWindowButton);
      ButtonArea.updateUI();
   }
   private void VerifyUserGUI(){
	  UI.Program.VerifyUserGUI CustomUI = new UI.Program.VerifyUserGUI();
      // if statements allows admin to be kept logged in
      if(Console.AdminFullAccess == false){
         JFrame LoginGUIFrame = new JFrame();
         LoginGUIFrame.setTitle("Login");
         LoginGUIFrame.setBounds(150, 150, 664, 150);
         LoginGUIFrame.setVisible(true);
         java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
         ImageIcon Icon = new ImageIcon(imgURL);
         LoginGUIFrame.setIconImage(Icon.getImage());
         JPanel Form = new JPanel();
         LoginGUIFrame.getContentPane().add(Form, BorderLayout.CENTER);
         JLabel UsernameLabel = new JLabel("Username:");
         JLabel PasswordLabel = new JLabel("Password:");
         JTextField Username = new JTextField();
         JTextField Password = new JTextField();
         JButton Login = size.newUINumber(27, "Login",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);;
         UsernameLabel.setForeground(CustomUI.Label.Foreground);
         PasswordLabel.setForeground(CustomUI.Label.Foreground);
         Username.setColumns(CustomUI.TextField.Columns);
         Username.setForeground(CustomUI.TextField.Foreground);
         Password.setColumns(CustomUI.TextField.Columns);
         Password.setForeground(CustomUI.TextField.Foreground);
         Username.setBorder(CustomUI.TextField.Border);
         Password.setBorder(CustomUI.TextField.Border);
         Form.add(UsernameLabel);
         Form.add(Username);
         Form.add(PasswordLabel);
         Form.add(Password);
         Login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               VerifyUser("Verify",Username.getText(),Password.getText());
               if(Console.AdminFullAccess == true){
                  AdminPortalArea();
               }
               LoginGUIFrame.dispose();
            }
         });
         Form.add(Login);
      }
      if(Console.AdminFullAccess == true){
         AdminPortalArea();
      }
   }
   // Internal Applications
   // Applications
   private void Warn(JFrame JFrame) {
	   JFrame.hide();
	   frmInventory.hide();
	   UI.Modals.WarningModal Modal = new UI.Modals.WarningModal();
       java.net.URL imgURL = Program.class.getResource(Modal.Frame.Icon.toString());
       java.net.URL WURL = Program.class.getResource(Modal.ImagePanel.Icon.toString());
       ImageIcon Icon = new ImageIcon(imgURL);
       JDialog Warning = new JDialog();
       Warning.setTitle("Warning");
       Warning.setBounds(150,150,Modal.Frame.FrameWidth,Modal.Frame.FrameHeight);
       Warning.setIconImage(Icon.getImage());
       Warning.setVisible(true);
       JPanel WarningMessage = new JPanel();
       JPanel WarningButtons = new JPanel();
       Warning.getContentPane().add(WarningMessage, BorderLayout.CENTER);
       Warning.getContentPane().add(WarningButtons, BorderLayout.SOUTH);
       WarningMessage.setLayout(null);
       JPanel IconPanel = new JPanel();
       IconPanel.setBounds(Modal.ImagePanel.X, Modal.ImagePanel.Y, Modal.ImagePanel.PanelWidth, Modal.ImagePanel.PanelHeight);
       JPanel MessagePanel = new JPanel();
       JLabel Image = new JLabel(new ImageIcon(WURL));
       Image.setBounds(Modal.ImagePanel.X, Modal.ImagePanel.Y, 10, 10);
       IconPanel.add(Image);
       MessagePanel.setBounds(Modal.MessagePanel.X, Modal.MessagePanel.Y, Modal.MessagePanel.PanelWidth, Modal.MessagePanel.PanelHeight);
       JLabel message = new JLabel(Modal.MessagePanel.Message);
       WarningMessage.add(IconPanel);
       MessagePanel.add(message);
       WarningMessage.add(MessagePanel);
       JButton yes = Modal.Buttons.YesBtn;
       JButton no = Modal.Buttons.NoBtn;
       message.setForeground(Modal.MessagePanel.MessageForeground);
       yes.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
        	 Warning.dispose();
             Warning.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                  frmInventory.show();
                   JFrame.show();
                }
             }); 
          }
       });
       WarningButtons.add(yes);
       no.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
        	 Warning.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                   
                }
             });
        	 Warning.dispose();
        	 JFrame.dispose();
        	 frmInventory.show();
          }
       });
       WarningButtons.add(no);
       WarningMessage.updateUI();
   }
   private void TableWindow(){
	  UI.Program.TableWindow CustomUI = new UI.Program.TableWindow();
      // Frame
      JFrame window = new JFrame();
      window.setTitle("Table");
      window.setVisible(true);
      window.setBounds(150, 150, 664, 520);
      java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
      ImageIcon Icon = new ImageIcon(imgURL);
      window.setIconImage(Icon.getImage());
      window.addWindowListener(new WindowAdapter() {
         @Override
         public void windowOpened(WindowEvent e) {
            Log("Opened Table Window");
         }
      });
      window.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            Log("Closing Table Window");
         }
      });
      if(MainInventory.isEmpty()){
    	 Warn(window);
         java.net.URL ErrorImgURL = Program.class.getResource(UI.Files.Warning);
         ImageIcon ErrorIcon = new ImageIcon(ErrorImgURL);
         window.setIconImage(ErrorIcon.getImage());
      }
      // Content
      // Objects
      JLabel Header = new JLabel();
      JTable j;
      // Table Panel
      JPanel TableHeaderPanel = new JPanel();
      window.getContentPane().add(TableHeaderPanel, BorderLayout.NORTH);
      Header.setText("Welcome To The Inventory Table");
      Header.setForeground(CustomUI.Label.Foreground);
      TableHeaderPanel.add(Header);
      // User Panel
      JPanel UserInfoPanel = new JPanel();
      window.getContentPane().add(UserInfoPanel, BorderLayout.SOUTH);
      UserInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      String LabelText = UserPathName.get(0);
      JLabel UserLabel = new JLabel(LabelText);
      UserLabel.setForeground(CustomUI.Username);
      UserInfoPanel.add(UserLabel);
      // Table
      JPanel TablePanel = new JPanel();
      TablePanel.setSize(300,100);
      window.getContentPane().add(TablePanel, FlowLayout.CENTER);
      // Initializing the JTable
      
      j = new JTable(model){
         public boolean editCellAt(int row, int column, java.util.EventObject e) {
            return false;
         }
      };
      // Add Columns
      model.addColumn("Barcode");
      model.addColumn("Amount");
      // Add Data
      for (int i : MainInventory.keySet()) {
         Object[] set = {i,MainInventory.get(i)};
         model.addRow(set);
      }
      j.setGridColor(CustomUI.Table.GridColor);
      j.setBackground(CustomUI.Table.Background);
      j.setForeground(CustomUI.Table.Foreground);
      // adding it to JScrollPane
      JScrollPane sp = new JScrollPane(j);
      sp.getVerticalScrollBar().setBackground(CustomUI.ScrollBar.Background);
      TablePanel.add(sp);
      Log("Built Table");
      // Toolbar
      String UISpaces = "    ";
      JToolBar ToolBar = new JToolBar();
      ToolBar.setSize(new Dimension(CustomUI.Toolbar.Width, CustomUI.Toolbar.Height));
      ToolBar.setOrientation(SwingConstants.VERTICAL);
      ToolBar.setBackground(SystemColor.menu);
      ToolBar.setFloatable(CustomUI.Toolbar.Floatable);
      window.getContentPane().add(ToolBar, BorderLayout.WEST);
      JButton Refresh = size.newUINumber(23,"Refresh",CustomUI.Toolbar.TBButton.Foreground,Colors.Palettes.Blue.Darkblue);
      int Width = CustomUI.Toolbar.TBButton.Width;
      int Height = CustomUI.Toolbar.TBButton.Height;
      Refresh.setSize(new Dimension(Width,Height));
      Refresh.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            //  Deletes All Rows And Refreshes The Content
            int rowCount = model.getRowCount();
            for (int i = rowCount - 1; i >= 0; i--) {
               model.removeRow(i);
            }
            for (int i : MainInventory.keySet()) {
               Object[] set = {i,MainInventory.get(i)};
               model.addRow(set);
            }
            Log("Refreshed Table");
         }
      });
      ToolBar.add(Refresh);
      ToolBar.addSeparator(new Dimension(CustomUI.Toolbar.Separtor.Width,CustomUI.Toolbar.Separtor.Height));
      JButton Count = size.newUINumber(25, "Count",CustomUI.Toolbar.TBButton.Foreground,Colors.Palettes.Blue.Darkblue);
      Count.setSize(new Dimension(Width, Height));
      Count.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame dialog = new JFrame();
            dialog.setTitle("Inventory Count");
            dialog.setBounds(200,200,300,200);
            dialog.setVisible(true);
            dialog.setIconImage(Icon.getImage());
            dialog.addWindowListener(new WindowAdapter() {
               @Override
               public void windowOpened(WindowEvent e) {
                  Log("Opened Inventory Count");
               }
            });
            dialog.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  Log("Closing Inventory Count");
               }
            });
            // Info Panel
            JPanel Info = new JPanel();
            dialog.getContentPane().add(Info, BorderLayout.NORTH);
            int RowCount = model.getRowCount();
            int Sum = 0;
            // Count Data
            for (int i = 0; i < model.getRowCount(); i++){
               Object obj = model.getValueAt(i,1);
               int ColSum = Integer.parseInt(obj.toString());
               Sum += ColSum;
            }
            JLabel Count = new JLabel();
            Count.setForeground(CustomUI.Toolbar.Count.CountLabel.Foreground);
            String CountText = "Barcodes: " + RowCount + " Items: " + Sum;
            Count.setText(CountText);
            Log("Counted Inventory");
            Info.add(Count);
            if(Sum == 0){
               java.net.URL ErrorImgURL = Program.class.getResource(UI.Files.Warning);
               ImageIcon ErrorIcon = new ImageIcon(ErrorImgURL);
               dialog.setIconImage(ErrorIcon.getImage());     
            }
            // User Info
            JPanel DialogUserInfoPanel = new JPanel();
            dialog.getContentPane().add(DialogUserInfoPanel, BorderLayout.SOUTH);
            DialogUserInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            String DialogLabelText = UserPathName.get(0);
            JLabel DialogUserLabel = new JLabel(DialogLabelText);
            DialogUserLabel.setForeground(CustomUI.Toolbar.Count.CountLabel.UserForeground);
            DialogUserInfoPanel.add(DialogUserLabel);
         }
      });
      ToolBar.add(Count);
      JLabel SearchOptions = new JLabel(UISpaces + "Search" + UISpaces);
      SearchOptions.setForeground(CustomUI.Toolbar.ToolBarLabel.Foreground);
      ToolBar.add(SearchOptions);
      ToolBar.addSeparator(new Dimension(CustomUI.Toolbar.Separtor.Width,CustomUI.Toolbar.Separtor.Height));
      JButton SearchBarcode = size.newUINumber(22,"Barcode",CustomUI.Toolbar.TBButton.Foreground,Colors.Palettes.Blue.Darkblue);
      SearchBarcode.setSize(new Dimension(Width, Height));
      SearchBarcode.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame Search = new JFrame();
            Search.setTitle("Barcode Search");
            Search.setBounds(200,200,300,100);
            Search.setVisible(true);
            java.net.URL IconURL = Program.class.getResource(UI.Files.Icon);
            ImageIcon Icon = new ImageIcon(IconURL);
            Search.setIconImage(Icon.getImage());
            Search.addWindowListener(new WindowAdapter() {
               @Override
               public void windowOpened(WindowEvent e) {
                  Log("Opened Search Window");
               }
            });
            Search.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  Log("Closing Search Window");
               }
            });
            // Info Panel
            JPanel BarcodeSearchPanel = new JPanel();
            Search.getContentPane().add(BarcodeSearchPanel, BorderLayout.NORTH);
            JLabel BarcodeSearchLabel = new JLabel("Barcode:");
            BarcodeSearchLabel.setForeground(CustomUI.Toolbar.SearchBarcode.Label.Foreground);
            BarcodeSearchPanel.add(BarcodeSearchLabel);
            JTextField BarcodeSearch = new JTextField();
            BarcodeSearch.setColumns(CustomUI.Toolbar.SearchBarcode.TextField.Columns);
            BarcodeSearch.setForeground(CustomUI.Toolbar.SearchBarcode.TextField.Foreground);
            BarcodeSearch.setBorder(CustomUI.Toolbar.SearchBarcode.TextField.Border);
            JPanel BarcodeResult = new JPanel();
            Search.getContentPane().add(BarcodeResult,BorderLayout.CENTER);
            JLabel BarcodeLabel = new JLabel();
            BarcodeLabel.setText("Search For Barcode");
            BarcodeLabel.setForeground(CustomUI.Toolbar.SearchBarcode.Label.Foreground);
            BarcodeResult.add(BarcodeLabel);
            BarcodeSearch.addKeyListener(new KeyAdapter() {
               @Override
               public void keyPressed(KeyEvent e) {
                  if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                     int barcodeRef = Integer.valueOf(BarcodeSearch.getText());
                     int barcode = Integer.valueOf(MainInventory.get(barcodeRef));
                     if(MainInventory.containsValue(barcode)){
                        String MainText = "Barcode Found: " + barcodeRef + ". Scanned: " + barcode + "." ;
                        BarcodeLabel.setText(MainText);
                     }
                     BarcodeSearch.setText("");
                  }
               }
            });
            BarcodeSearchPanel.add(BarcodeSearch);
         }
      });
      ToolBar.add(SearchBarcode);
      JLabel AdminLabel = new JLabel(" " + UISpaces + "Admin" + UISpaces + " ");
      AdminLabel.setForeground(CustomUI.Toolbar.ToolBarLabel.Foreground);
      JLabel AdminDeleteLabel = new JLabel(" " + UISpaces + "Delete" + UISpaces + " ");
      AdminDeleteLabel.setForeground(CustomUI.Toolbar.ToolBarLabel.Foreground);
      ToolBar.add(AdminLabel);
      ToolBar.add(AdminDeleteLabel);
      ToolBar.addSeparator(new Dimension(CustomUI.Toolbar.Separtor.Width,CustomUI.Toolbar.Separtor.Height));
      JButton DeleteButton = size.newUINumber(27,"Row",CustomUI.Toolbar.TBButton.Foreground,Colors.Palettes.Blue.Darkblue);
      DeleteButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            // Verifying Code or Allows Function to work
            if(Console.AdminAccess == true){
               if(j.getSelectedRow() != -1) {
                  // remove selected row from the model
                  model.removeRow(j.getSelectedRow());
               }
            }
            if(Console.AdminAccess == false){
               VerifyCodeGUI();
            }
            VerifyCode("Verified","");
         }
      });
      ToolBar.add(DeleteButton);
      j.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2){
               if(Console.AdminAccess == true){
                  model.removeRow(j.getSelectedRow());
               }
               if(Console.AdminAccess == false){
                  VerifyCodeGUI();
               }
            }
            VerifyCode("Verified","");
         }
      });
      j.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_DELETE){
               if(Console.AdminAccess == true){
                  int[] row = j.getSelectedRows();
                  for (int i = row.length - 1; i >= 0; i--) {
                     model.removeRow(row[i]);
                  }
               }
               if(Console.AdminAccess == false){
                  VerifyCodeGUI();
               }
            }
            VerifyCode("Verified","");
         }
      });
   }
   private void HelpWindow(){
	  UI.Program.HelpWindow CustomUI = new UI.Program.HelpWindow();
      // Frame
      JFrame window = new JFrame();
      window.setTitle("Help");
      window.setVisible(true);
      window.setBounds(175, 175, 664, 329);
      URL imgURL = Program.class.getResource(UI.Files.Icon);
      ImageIcon Icon = new ImageIcon(imgURL);
      window.setIconImage(Icon.getImage());
      window.addWindowListener(new WindowAdapter() {
         @Override
         public void windowOpened(WindowEvent e) {
            Log("Opened Help Window");
         }
      });
      window.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            Log("Closing Help Window");
         }
      });
      // User Panel
      JPanel UserPanel = new JPanel();
      window.getContentPane().add(UserPanel, BorderLayout.NORTH);
      UserPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      String LabelText = UserPathName.get(0);
      JLabel UserLabel = new JLabel(LabelText);
      UserLabel.setForeground(CustomUI.Label.UserForeground);
      UserPanel.add(UserLabel);
      // Info Panel
      JTabbedPane InfoPanel = new JTabbedPane();
      InfoPanel.setUI(new UI.Program.HelpWindow.DefaultTabs.TabDesign());
      window.getContentPane().add(InfoPanel,BorderLayout.CENTER);
      // Info Panel - JPanels
      JPanel AboutInfo = new JPanel();
      JPanel ProgramInfo = new JPanel();
      JPanel SetupInfo = new JPanel();
      JPanel UsersInfo = new JPanel();
      // Info Panel - Info
      // Info Panel - Info - About
      JLabel AboutInfoLabel = new JLabel();
      String AboutInfoText = "<html>" + "This is a open-source pre-inventory software." + "<html>";
      AboutInfoLabel.setText(AboutInfoText);
      AboutInfoLabel.setForeground(CustomUI.Label.Foreground);
      AboutInfo.add(AboutInfoLabel);
      // Info Panel - Info - Program
      JLabel ProgramInfoLabel = new JLabel();
      String ProgramInfoText = "<html>" + "This is the main program" + "<html>";
      ProgramInfoLabel.setText(ProgramInfoText);
      ProgramInfoLabel.setForeground(CustomUI.Label.Foreground);
      ProgramInfo.add(ProgramInfoLabel);
      // Info Panel - Info - Setup
      JLabel SetupInfoLabel = new JLabel();
      String SetupInfoText = "<html>" + "This is where you set up all the users." + "<html>";
      SetupInfoLabel.setText(SetupInfoText);
      SetupInfoLabel.setForeground(CustomUI.Label.Foreground);
      SetupInfo.add(SetupInfoLabel);
      // Info Panel - Info - Users
      JLabel UsersInfoLabel = new JLabel();
      String UsersInfoText = "<html>" + "This is where you switch users when there is multiple." + "<html>";
      UsersInfoLabel.setText(UsersInfoText);
      UsersInfoLabel.setForeground(CustomUI.Label.Foreground);
      UsersInfo.add(UsersInfoLabel);
      // Info Panel - Tabs
      InfoPanel.add("About",AboutInfo);
      InfoPanel.add("Program",ProgramInfo);
      InfoPanel.add("Setup",SetupInfo);
      InfoPanel.add("Users", UsersInfo);
      InfoPanel.setForegroundAt(0,CustomUI.Label.Foreground);
      InfoPanel.setForegroundAt(1,CustomUI.Label.Foreground);
      InfoPanel.setForegroundAt(2,CustomUI.Label.Foreground);
      InfoPanel.setForegroundAt(3,CustomUI.Label.Foreground);
   }
   private void Exit() {
	   frmInventory.hide();
	   UI.Modals.ExitModal Modal = new UI.Modals.ExitModal();
       java.net.URL imgURL = Program.class.getResource(Modal.Frame.Icon.toString());
       java.net.URL QURL = Program.class.getResource(Modal.ImagePanel.Icon.toString());
       ImageIcon Icon = new ImageIcon(imgURL);
       JDialog closing = new JDialog();
       closing.setTitle("Closing");
       closing.setBounds(150,150,Modal.Frame.FrameWidth,Modal.Frame.FrameHeight);
       closing.setIconImage(Icon.getImage());
       closing.setVisible(true);
       JPanel closingMessage = new JPanel();
       JPanel closingButtons = new JPanel();
       closing.getContentPane().add(closingMessage, BorderLayout.CENTER);
       closing.getContentPane().add(closingButtons, BorderLayout.SOUTH);
       closingMessage.setLayout(null);
       JPanel IconPanel = new JPanel();
       IconPanel.setBounds(Modal.ImagePanel.X, Modal.ImagePanel.Y, Modal.ImagePanel.PanelWidth, Modal.ImagePanel.PanelHeight);
       JPanel MessagePanel = new JPanel();
       JLabel Image = new JLabel(new ImageIcon(QURL));
       Image.setBounds(Modal.ImagePanel.X, Modal.ImagePanel.Y, 10, 10);
       IconPanel.add(Image);
       MessagePanel.setBounds(Modal.MessagePanel.X, Modal.MessagePanel.Y, Modal.MessagePanel.PanelWidth, Modal.MessagePanel.PanelHeight);
       JLabel message = new JLabel(Modal.MessagePanel.Message);
       closingMessage.add(IconPanel);
       MessagePanel.add(message);
       closingMessage.add(MessagePanel);
       JButton yes = Modal.Buttons.YesBtn;
       JButton no = Modal.Buttons.NoBtn;
       message.setForeground(Modal.MessagePanel.MessageForeground);
       yes.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
             Log("Attempted To Close Program");
             close(frmInventory);
             frmInventory.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                   Log("Closing Program");
                   CompleteLog(LogFile);
                   System.exit(0);
                }
             });
             //frmInventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             closing.dispose();
             frmInventory.dispose();
          }
       });
       closingButtons.add(yes);
       no.addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
             closing.dispose();
             closing.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                   System.out.println("Window Closed Properly");
                   frmInventory.show();
                }
             });
          }
       });
       closingButtons.add(no);
       closingMessage.updateUI();
   }
   public Program(){
      prepareGUI();
   }
   public static void main(String[] args){
      Program  Layouts = new Program();
      Layouts.InfoContainer();
      Layouts.Toolbar();
      Layouts.InventoryContainer();
      Layouts.UserInfo();
   }

   private void prepareGUI(){
      frmInventory = new JFrame();
      frmInventory.setTitle("Inventory");
      frmInventory.setBounds(100, 100, 664, 329);
      frmInventory.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      frmInventory.setVisible(true);
      java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
      ImageIcon Icon = new ImageIcon(imgURL);
      frmInventory.setIconImage(Icon.getImage());
      Log("Opened Program");
      VerifyCode("New","");
      VerifyUser("New","","");
      frmInventory.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            Exit();
         }
      });
      Update.AutoStart();
   }
   private void InfoContainer(){
	  UI.Program.InfoContainer CustomUI = new UI.Program.InfoContainer();
      JPanel AddPanel = new JPanel();
      frmInventory.getContentPane().add(AddPanel, BorderLayout.NORTH);
      AddPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      JLabel AddBarcodeLabel = new JLabel("Barcode:");
      AddBarcodeLabel.setForeground(CustomUI.Label.Foreground);
      AddPanel.add(AddBarcodeLabel);
      AddBarcodeTextField = new JTextField();
      AddBarcodeTextField.setColumns(CustomUI.TextField.Columns);
      AddBarcodeTextField.setForeground(CustomUI.TextField.Foreground);
      AddBarcodeTextField.setBorder(CustomUI.TextField.Border);
      AddBarcodeTextField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
               int barcode = Integer.valueOf(AddBarcodeTextField.getText());
               if (MainInventory.containsKey(barcode)) {
            	   AddToInventory(barcode,1);
            	   AddBarcodeTextField.setBorder(CustomUI.TextField.Effects.setOldLineBorder());
                }
                else {
                    AddToInventory(barcode,1);
                    AddBarcodeTextField.setBorder(CustomUI.TextField.Effects.setNewLineBorder());
                }
               PrintInventory();
               AddBarcodeTextField.setText("");
               Log("Added Barcode - Cache - " + barcode);
            }
         }
      });
      AddPanel.add(AddBarcodeTextField);
   }
   private void Toolbar(){
	  UI.Program.Toolbar CustomUI = new UI.Program.Toolbar();
	  UI.Modals.ConfirmModal Modal = new UI.Modals.ConfirmModal();
      JToolBar ButtonToolBar = new JToolBar();
      ButtonToolBar.setSize(new Dimension(210, 0));
      ButtonToolBar.setOrientation(SwingConstants.VERTICAL);
      ButtonToolBar.setBackground(SystemColor.menu);
      ButtonToolBar.setFloatable(CustomUI.Floatable);
      frmInventory.getContentPane().add(ButtonToolBar, BorderLayout.WEST);
      JButton Barcode100 = size.newUINumber(27, "100",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);
      Barcode100.setSize(new Dimension(CustomUI.Button.Width, CustomUI.Button.Height));
      Barcode100.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            AddToInventory(100,1);
            Log("Added Barcode - Cache - 100");
            PrintInventory();
         }
      });
      ButtonToolBar.add(Barcode100);
      ButtonToolBar.addSeparator(new Dimension(CustomUI.Separator.Width,CustomUI.Separator.Height));
      JButton ClearResults = size.newUINumber(26,"Clear",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);
      ClearResults.setSize(new Dimension(CustomUI.Button.Width,CustomUI.Button.Height));
      ClearResults.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
        	 frmInventory.hide();
	         java.net.URL imgURL = Program.class.getResource(Modal.Frame.Icon.toString());
	         java.net.URL QURL = Program.class.getResource(Modal.ImagePanel.Icon.toString());
	         ImageIcon Icon = new ImageIcon(imgURL);
	         JDialog Confirm = new JDialog();
	         Confirm.setTitle("Confirm");
	         Confirm.setBounds(150,150,Modal.Frame.FrameWidth,Modal.Frame.FrameHeight);
	         Confirm.setIconImage(Icon.getImage());
	         Confirm.setVisible(true);
	         JPanel ConfirmMessage = new JPanel();
	         JPanel ConfirmButtons = new JPanel();
	         Confirm.getContentPane().add(ConfirmMessage, BorderLayout.CENTER);
	         Confirm.getContentPane().add(ConfirmButtons, BorderLayout.SOUTH);
	         ConfirmMessage.setLayout(null);
	         JPanel IconPanel = new JPanel();
	         IconPanel.setBounds(Modal.ImagePanel.X, Modal.ImagePanel.Y, Modal.ImagePanel.PanelWidth, Modal.ImagePanel.PanelHeight);
	         JPanel MessagePanel = new JPanel();
	         JLabel Image = new JLabel(new ImageIcon(QURL));
	         Image.setBounds(Modal.ImagePanel.X, Modal.ImagePanel.Y, 10, 10);
	         IconPanel.add(Image);
	         MessagePanel.setBounds(Modal.MessagePanel.X, Modal.MessagePanel.Y, Modal.MessagePanel.PanelWidth, Modal.MessagePanel.PanelHeight);
	         JLabel message = new JLabel(Modal.MessagePanel.Message);
	         ConfirmMessage.add(IconPanel);
	         MessagePanel.add(message);
	         ConfirmMessage.add(MessagePanel);
	         String UISpaces = "               ";
	         JButton yes = Modal.Buttons.YesBtn;
	         JButton no = Modal.Buttons.NoBtn;
	         message.setForeground(Modal.MessagePanel.MessageForeground);
	         yes.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
			        MainInventory.clear();
			        InventoryCount.setText("Nothing Scanned");
			        InventoryCount.setForeground(CustomUI.Label.Foreground);
			        AddBarcodeTextField.grabFocus();
			        Log("Cleared All Barcodes");
	               	Confirm.dispose();
	               	frmInventory.show();
	            }
	         });
	         ConfirmButtons.add(yes);
	         no.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	               Confirm.dispose();
	               frmInventory.show();
	            }
	         });
	         ConfirmButtons.add(no);
	         ConfirmMessage.updateUI();
         }
      });
      ButtonToolBar.add(ClearResults);
      ButtonToolBar.addSeparator(new Dimension(CustomUI.Separator.Width,CustomUI.Separator.Height));
      JButton ExportInventory = size.newUINumber(25,"Export",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);
      ExportInventory.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            ExportInventory("InventoryExport.txt");
            Log("Exported Inventory");
         }
      });
      ButtonToolBar.add(ExportInventory);
      ButtonToolBar.addSeparator(new Dimension(CustomUI.Separator.Width,CustomUI.Separator.Height));
      JButton TableButton = size.newUINumber(26, "Table",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);
      TableButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
        	JFrame prompt = new JFrame();
            if(Console.AdminAccess || Console.AdminFullAccess == false && Console.TableLogin == false){
               java.net.URL imgURL = Program.class.getResource(UI.Files.Icon);
               ImageIcon Icon = new ImageIcon(imgURL);
               prompt.setTitle("Pre-Verify");
               prompt.setBounds(100, 100, 300, 100);
               prompt.setVisible(true);
               prompt.setIconImage(Icon.getImage());
               JPanel ButtonArea = new JPanel();
               prompt.getContentPane().add(ButtonArea, BorderLayout.CENTER);
               JButton code = size.newUINumber(27,"Code",CustomUI.Verifty.Button.Foreground,Colors.Palettes.Blue.Darkblue);
               JButton password = size.newUINumber(19,"Password",CustomUI.Verifty.Button.Foreground,Colors.Palettes.Blue.Darkblue);
               JButton skip = size.newUINumber(27,"Skip",CustomUI.Verifty.Button.Foreground,Colors.Palettes.Blue.Darkblue);
               code.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mouseClicked(MouseEvent e) {
                     JFrame verify = new JFrame();
                     verify.setTitle("Verify");
                     verify.setBounds(100, 100, 300, 100);
                     verify.setVisible(true);
                     verify.setIconImage(Icon.getImage());
                     JPanel LoginArea = new JPanel();
                     verify.getContentPane().add(LoginArea, BorderLayout.CENTER);
                     JLabel CodeLabel = new JLabel("Code:");
                     CodeLabel.setForeground(CustomUI.Verifty.Label.Foreground);
                     JTextField Code = new JTextField();
                     Code.setColumns(CustomUI.Verifty.TextField.Columns);
                     Code.setForeground(CustomUI.Verifty.TextField.Foreground);
                     Code.setBorder(CustomUI.Verifty.TextField.Border);
                     LoginArea.add(CodeLabel);
                     LoginArea.add(Code);
                     JButton LoginButton = size.newUINumber(26,"Login",CustomUI.Verifty.Button.Foreground,Colors.Palettes.Blue.Darkblue);
                     LoginButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                           VerifyCode("Verify",Code.getText());
                           if(Console.AdminAccess == true){
                              prompt.dispose();
                              verify.dispose();
                              TableWindow();
                           }
                        }
                     });
                     LoginArea.add(LoginButton);
                  }
               });
               password.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mouseClicked(MouseEvent e) {
                     JFrame verify = new JFrame();
                     verify.setTitle("Verify");
                     verify.setBounds(100, 100, 450, 100);
                     verify.setVisible(true);
                     verify.setIconImage(Icon.getImage());
                     JPanel LoginArea = new JPanel();
                     verify.getContentPane().add(LoginArea, BorderLayout.CENTER);
                     JLabel UsernameLabel = new JLabel("Username:");
                     UsernameLabel.setForeground(CustomUI.Verifty.Label.Foreground);
                     JTextField UsernameText = new JTextField();
                     UsernameText.setColumns(CustomUI.Verifty.TextField.Columns);
                     UsernameText.setForeground(CustomUI.Verifty.TextField.Foreground);
                     UsernameText.setBorder(CustomUI.Verifty.TextField.Border);
                     LoginArea.add(UsernameLabel);
                     LoginArea.add(UsernameText);
                     JLabel PasswordLabel = new JLabel("Password:");
                     PasswordLabel.setForeground(CustomUI.Verifty.Label.Foreground);
                     JTextField PasswordText = new JTextField();
                     PasswordText.setColumns(CustomUI.Verifty.TextField.Columns);
                     PasswordText.setForeground(CustomUI.Verifty.TextField.Foreground);
                     PasswordText.setBorder(CustomUI.Verifty.TextField.Border);
                     LoginArea.add(PasswordLabel);
                     LoginArea.add(PasswordText);
                     JButton LoginButton = size.newUINumber(26,"Login",CustomUI.Verifty.Button.Foreground,Colors.Palettes.Blue.Darkblue);
                     LoginButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                           VerifyUser("Verify",UsernameText.getText(),PasswordText.getText());
                           if(Console.AdminFullAccess == true){
                              prompt.dispose();
                              verify.dispose();
                              TableWindow();
                           }
                        }
                     });
                     LoginArea.add(LoginButton);
                  }
               });
               skip.addMouseListener(new MouseAdapter() {
                  @Override
                  public void mouseClicked(MouseEvent e) {
                	 frmInventory.hide();
         	         java.net.URL imgURL = Program.class.getResource(Modal.Frame.Icon.toString());
         	         java.net.URL QURL = Program.class.getResource(Modal.ImagePanel.Icon.toString());
         	         ImageIcon Icon = new ImageIcon(imgURL);
         	         JDialog Confirm = new JDialog();
         	         Confirm.setTitle("Confirm");
         	         Confirm.setBounds(150,150,Modal.Frame.FrameWidth,Modal.Frame.FrameHeight);
         	         Confirm.setIconImage(Icon.getImage());
         	         Confirm.setVisible(true);
         	         JPanel ConfirmMessage = new JPanel();
         	         JPanel ConfirmButtons = new JPanel();
         	         Confirm.getContentPane().add(ConfirmMessage, BorderLayout.CENTER);
         	         Confirm.getContentPane().add(ConfirmButtons, BorderLayout.SOUTH);
         	         ConfirmMessage.setLayout(null);
         	         JPanel IconPanel = new JPanel();
         	         IconPanel.setBounds(Modal.ImagePanel.X, Modal.ImagePanel.Y, Modal.ImagePanel.PanelWidth, Modal.ImagePanel.PanelHeight);
         	         JPanel MessagePanel = new JPanel();
         	         JLabel Image = new JLabel(new ImageIcon(QURL));
         	         Image.setBounds(Modal.ImagePanel.X, Modal.ImagePanel.Y, 10, 10);
         	         IconPanel.add(Image);
         	         MessagePanel.setBounds(Modal.MessagePanel.X, Modal.MessagePanel.Y, Modal.MessagePanel.PanelWidth, Modal.MessagePanel.PanelHeight);
         	         JLabel message = new JLabel(Modal.MessagePanel.Message);
         	         ConfirmMessage.add(IconPanel);
         	         MessagePanel.add(message);
         	         ConfirmMessage.add(MessagePanel);
         	         String UISpaces = "               ";
         	         JButton yes = Modal.Buttons.YesBtn;
         	         JButton no = Modal.Buttons.NoBtn;
         	         message.setForeground(Modal.MessagePanel.MessageForeground);
         	         yes.addMouseListener(new MouseAdapter() {
         	            @Override
         	            public void mouseClicked(MouseEvent e) {
         	               	Confirm.dispose();
         	               	prompt.dispose();
         	               	frmInventory.show();
         	               	TableWindow();
         	            }
         	         });
         	         ConfirmButtons.add(yes);
         	         no.addMouseListener(new MouseAdapter() {
         	            @Override
         	            public void mouseClicked(MouseEvent e) {
         	            	prompt.dispose(); 
         	            	Confirm.dispose();
         	            	frmInventory.show();
         	            }
         	         });
         	         ConfirmButtons.add(no);
         	         ConfirmMessage.updateUI();
                  }
               });
               ButtonArea.add(code);
               ButtonArea.add(password);
               ButtonArea.add(skip);
            }
            if(Console.AdminAccess || Console.AdminFullAccess == false && Console.TableLogin == true){
               TableWindow();
               Log("Attempted To Opened Table Window");
            }
            if(Console.AdminFullAccess == true) {
            	TableWindow();
            	prompt.dispose();
            }
         }
      });
      ButtonToolBar.add(TableButton);
      ButtonToolBar.addSeparator(new Dimension(CustomUI.Separator.Width,CustomUI.Separator.Height));
      JButton HelpButton = size.newUINumber(27, "Help",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);
      HelpButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            HelpWindow();
            Log("Attempted To Opened Help Window");
         }
      });
      ButtonToolBar.add(HelpButton);
      ButtonToolBar.addSeparator(new Dimension(CustomUI.Separator.Width,CustomUI.Separator.Height));
      JButton VerifyButton = size.newUINumber(25, "Verify",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);
      VerifyButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            VerifyCodeGUI();
         }
      });
      ButtonToolBar.add(VerifyButton);
      ButtonToolBar.addSeparator(new Dimension(CustomUI.Separator.Width,CustomUI.Separator.Height));
      JButton AdminButton = size.newUINumber(25, "Admin",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);
      AdminButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            VerifyUserGUI();
         }
      });
      ButtonToolBar.add(AdminButton);
      ButtonToolBar.addSeparator(new Dimension(CustomUI.Separator.Width,CustomUI.Separator.Height));
      JButton Exit = size.newUINumber(27, "Exit",CustomUI.Button.Foreground,Colors.Palettes.Blue.Darkblue);
      Exit.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
        	Exit();
         }
      });
      ButtonToolBar.add(Exit);
   }
   private void InventoryContainer(){
	  UI.Program.InventoryContainer CustomUI = new UI.Program.InventoryContainer();
      JPanel InventoryCountPanel = new JPanel();
      frmInventory.getContentPane().add(InventoryCountPanel, BorderLayout.CENTER);
      InventoryCount.setForeground(CustomUI.Label.Foreground);
      InventoryCountPanel.add(InventoryCount);
   }
   private void UserInfo(){
	  UI.Program.UserInfo CustomUI = new UI.Program.UserInfo();
      JPanel UserPanel = new JPanel();
      frmInventory.getContentPane().add(UserPanel, BorderLayout.SOUTH);
      UserPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      String Name = "Users\\Main-Name.txt";
      File text = new File(Name);
      try{
         Scanner sc = new Scanner(text);
         while (sc.hasNextLine()){
            String read = sc.nextLine();
            String Check_User_File_Dir = "Users\\" + read;
            File Users_Dir = new File(Check_User_File_Dir);
            if(Users_Dir.exists()) {
               String CheckUserNameFile = Check_User_File_Dir + "\\" + read + "-Name.txt";
               File Users_Name = new File(CheckUserNameFile);
               if(Users_Name.exists()){
                  UserPathName.add(read);
                  String LabelText = UserPathName.get(0);
                  JLabel UserLabel = new JLabel(LabelText);
                  UserLabel.setForeground(CustomUI.Label.Correct);
                  UserPanel.add(UserLabel);
                  Log("User Found");
               }
            }
            else{
               JLabel UserLabel = new JLabel("User Not Found");
               UserLabel.setForeground(CustomUI.Label.InCorrect);
               UserPanel.add(UserLabel);
               Log("User Not Found");
            }
         }
         sc.close();
      }
      catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
   }
}