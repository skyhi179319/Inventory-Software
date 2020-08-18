import Assets.colors.Colors;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.awt.*;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;


public class Program {
   public static class TabDesign extends BasicTabbedPaneUI {
      private Color selectColor;
      private Color deSelectColor;
      private int inclTab = 4;
      private int FolderWidth = 18;
      private Polygon shape;
      public static ComponentUI createUI(JComponent c) {
         return new TabDesign();
      }
      // installing the defaults
      @Override
      protected void installDefaults() {
         super.installDefaults();
         // default Colors
         selectColor = new Color(Colors.gold.getRGB());
         deSelectColor = new Color(Colors.LightYellow.getRGB());
         tabAreaInsets.right = FolderWidth;
      }
      // drawing the tabs
      @Override
      protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
         if (runCount > 1) {
            int lines[] = new int[runCount];
            for (int i = 0; i < runCount; i++) {
               lines[i] = rects[tabRuns[i]].y + (tabPlacement == TOP ? maxTabHeight : 0);
            }
            Arrays.sort(lines);
            if (tabPlacement == TOP) {
               int row = runCount;
               for (int i = 0; i < lines.length - 1; i++, row--) {
                  Polygon carp = new Polygon();
                  carp.addPoint(0, lines[i]);
                  carp.addPoint(tabPane.getWidth() - 2 * row - 2, lines[i]);
                  carp.addPoint(tabPane.getWidth() - 2 * row, lines[i] + 3);
                  if (i < lines.length - 2) {
                     carp.addPoint(tabPane.getWidth() - 2 * row, lines[i + 1]);
                     carp.addPoint(0, lines[i + 1]);
                  } else {
                     carp.addPoint(tabPane.getWidth() - 2 * row, lines[i] + rects[selectedIndex].height);
                     carp.addPoint(0, lines[i] + rects[selectedIndex].height);
                  }
                  carp.addPoint(0, lines[i]);
                  g.fillPolygon(carp);
                  g.drawPolygon(carp);
               }
            } else {
               int row = 0;
               for (int i = 0; i < lines.length - 1; i++, row++) {
                  Polygon carp = new Polygon();
                  carp.addPoint(0, lines[i]);
                  carp.addPoint(tabPane.getWidth() - 2 * row - 1, lines[i]);
                  carp.addPoint(tabPane.getWidth() - 2 * row - 1, lines[i + 1] - 3);
                  carp.addPoint(tabPane.getWidth() - 2 * row - 3, lines[i + 1]);
                  carp.addPoint(0, lines[i + 1]);
                  carp.addPoint(0, lines[i]);
                  g.fillPolygon(carp);
                  g.drawPolygon(carp);
               }
            }
         }
         super.paintTabArea(g, tabPlacement, selectedIndex);
      }
      // paint tab background
      @Override
      protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
         Graphics2D g2D = (Graphics2D) g;
         GradientPaint gradientShadow = null;
         int xp[] = null;
         int yp[] = null;
         switch (tabPlacement) {
            case LEFT:
               xp = new int[]{x, x, x + w, x + w, x};
               yp = new int[]{y, y + h - 3, y + h - 3, y, y};
               break;
            case RIGHT:
               xp = new int[]{x, x, x + w - 2, x + w - 2, x};
               yp = new int[]{y, y + h - 3, y + h - 3, y, y};
               break;
            case BOTTOM:
               xp = new int[]{x, x, x + 3, x + w - inclTab - 6, x + w - inclTab - 2, x + w - inclTab, x + w - 3, x};
               yp = new int[]{y, y + h - 3, y + h, y + h, y + h - 1, y + h - 3, y, y};
               break;
            case TOP:
            default:
               xp = new int[]{x, x, x + 3, x + w - inclTab - 6, x + w - inclTab - 2, x + w - inclTab, x + w - inclTab, x};
               yp = new int[]{y + h, y + 3, y, y, y + 1, y + 3, y + h, y + h};
               break;
         }
         // ;
         shape = new Polygon(xp, yp, xp.length);
         if (isSelected) {
            g2D.setColor(selectColor);

         } else {
            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
               g2D.setColor(deSelectColor);
               gradientShadow = new GradientPaint(0, 0, deSelectColor, 0, y + h / 2, Colors.info);
               g2D.setPaint(gradientShadow);
            }
         }
         g2D.fill(shape);
      }

      @Override
      protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
         super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
         g.setFont(font);
         View v = getTextViewForTab(tabIndex);
         if (v != null) {
            // html
            v.paint(g, textRect);
         }
      }
      @Override
      protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
         return 20 + inclTab + super.calculateTabWidth(tabPlacement, tabIndex, metrics);
      }
   }
   private JFrame frmInventory;
   private JTextField AddBarcodeTextField;
   private JLabel InventoryCount = new JLabel("Nothing Scanned");
   private String LogFile = "Main-Log.txt";
   /*
        Allows Sub-Admin to use code
        Lines 316,346,349,529,530,539,540,1079,1085,1096,1099,1110,1116,1307,1346
    */
   private boolean AdminAccess = false;
   /*
        Allows Full Admin to be logged in and out
        Lines 459,461,767,798,806,836,844,1307,1386
    */
   private boolean AdminFullAccess = false;
   /*
        Turns On/off AdminAccess
        Lines 345,348,531,541
    */
   private boolean KeepAdminAccessOn = false;
   /*
         Turns On/Off Table Login
         Lines 565,571,1307
    */
   private boolean TableLogin = false;

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
      if(function.equals("New") && code.equals("")){
         Log("Created A New Code");
         JFrame NewCode = new JFrame();
         NewCode.setTitle("New Code");
         NewCode.setBounds(150, 150, 664, 150);
         NewCode.setVisible(true);
         java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
         ImageIcon Icon = new ImageIcon(imgURL);
         NewCode.setIconImage(Icon.getImage());
         JPanel Panel = new JPanel();
         NewCode.getContentPane().add(Panel, BorderLayout.CENTER);
         int NewCodeInt = new Random().nextInt(100);
         JLabel CodeLabel = new JLabel(Integer.toString(NewCodeInt));
         CodeLabel.setForeground(Colors.lightblue);
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
               AdminAccess = true;
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
         java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
         ImageIcon Icon = new ImageIcon(imgURL);
         Alert.setIconImage(Icon.getImage());
         JPanel Panel = new JPanel();
         Alert.getContentPane().add(Panel, BorderLayout.CENTER);
         JLabel label = new JLabel();
         label.setForeground(Colors.lightblue);
         label.setText("Wrong Code. Please Re-enter The Code");
         Panel.add(label);
      }
      if(function.equals("Verified") && KeepAdminAccessOn == false){
         AdminAccess = false;
      }
      if(function.equals("Verified") && KeepAdminAccessOn == true){
         AdminAccess = true;
      }
   }
   private void VerifyCodeGUI(){
      JFrame GUIFrame = new JFrame();
      GUIFrame.setTitle("Verify");
      GUIFrame.setBounds(150, 150, 664, 150);
      GUIFrame.setVisible(true);
      java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
      ImageIcon Icon = new ImageIcon(imgURL);
      GUIFrame.setIconImage(Icon.getImage());
      JPanel Form = new JPanel();
      GUIFrame.getContentPane().add(Form, BorderLayout.CENTER);
      JLabel CodeLabel = new JLabel("Code:");
      CodeLabel.setForeground(Colors.lightblue);
      Form.add(CodeLabel);
      JTextField Code = new JTextField();
      Code.setColumns(10);
      Code.setForeground(Colors.lightblue);
      Form.add(Code);
      JButton Verify = new JButton("Verify");
      Verify.setForeground(Colors.lightblue);
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
      if(function.equals("New") && username == "" && password == ""){
         JFrame NewAdmin = new JFrame();
         NewAdmin.setTitle("New Admin");
         NewAdmin.setBounds(150, 150, 664, 150);
         NewAdmin.setVisible(true);
         java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
         ImageIcon Icon = new ImageIcon(imgURL);
         NewAdmin.setIconImage(Icon.getImage());
         JPanel Panel = new JPanel();
         NewAdmin.getContentPane().add(Panel, BorderLayout.CENTER);
         JLabel UsernameLabel = new JLabel("Username:");
         UsernameLabel.setForeground(Colors.lightblue);
         JTextField UsernameText = new JTextField();
         UsernameText.setForeground(Colors.lightblue);
         UsernameText.setColumns(10);
         JLabel PasswordLabel = new JLabel("Password:");
         PasswordLabel.setForeground(Colors.lightblue);
         JTextField PasswordText = new JTextField();
         PasswordText.setForeground(Colors.lightblue);
         PasswordText.setColumns(10);
         Panel.add(UsernameLabel);
         Panel.add(UsernameText);
         Panel.add(PasswordLabel);
         Panel.add(PasswordText);
         JButton AddAdmin = new JButton("Add");
         AddAdmin.setForeground(Colors.lightblue);
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
            if(username.equals(CheckUsername) && password.equals(CheckPassword) && AdminFullAccess == false){
               Log("Admin Logged In And Has Access");
               AdminFullAccess = true;
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
         java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
         ImageIcon Icon = new ImageIcon(imgURL);
         LoginAlert.setIconImage(Icon.getImage());
         JPanel Panel = new JPanel();
         LoginAlert.getContentPane().add(Panel, BorderLayout.CENTER);
         JLabel label = new JLabel();
         label.setForeground(Colors.lightblue);
         label.setText("Wrong Login Information.");
         Panel.add(label);
      }
   }
   private void AdminPortalArea(){
      JFrame AdminArea = new JFrame();
      AdminArea.setTitle("Admin");
      AdminArea.setBounds(150, 150, 664, 150);
      AdminArea.setVisible(true);
      java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
      ImageIcon Icon = new ImageIcon(imgURL);
      AdminArea.setIconImage(Icon.getImage());
      JPanel ButtonArea = new JPanel();
      JPanel ActionInfo = new JPanel();
      AdminArea.getContentPane().add(ButtonArea, BorderLayout.CENTER);
      AdminArea.getContentPane().add(ActionInfo, BorderLayout.SOUTH);
      JLabel Action = new JLabel();
      Action.setForeground(Colors.lightblue);
      ActionInfo.add(Action);
      JButton AdminAccessSwitch = new JButton("Admin Access");
      AdminAccessSwitch.setForeground(Colors.lightblue);
      AdminAccessSwitch.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame OnOff = new JFrame();
            OnOff.setTitle("Access Point");
            OnOff.setBounds(175,175,300,100);
            OnOff.setVisible(true);
            java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
            ImageIcon Icon = new ImageIcon(imgURL);
            OnOff.setIconImage(Icon.getImage());
            JPanel AccessButtons = new JPanel();
            OnOff.getContentPane().add(AccessButtons, BorderLayout.CENTER);
            JButton on = new JButton("On");
            on.setForeground(Colors.lightblue);
            JButton off = new JButton("Off");
            off.setForeground(Colors.lightblue);
            JButton TableLoginButton = new JButton("Table Login");
            TableLoginButton.setForeground(Colors.lightblue);
            on.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  if(AdminAccess == false){
                     AdminAccess = true;
                     KeepAdminAccessOn = true;
                     Action.setText("Admin Access On");
                  }
               }
            });
            off.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  if(AdminAccess == true){
                     AdminAccess = false;
                     KeepAdminAccessOn = false;
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
                  java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
                  ImageIcon Icon = new ImageIcon(imgURL);
                  TableOnOff.setIconImage(Icon.getImage());
                  JPanel TableButtons = new JPanel();
                  TableOnOff.getContentPane().add(TableButtons, BorderLayout.CENTER);
                  JButton ButtonOn = new JButton("On");
                  ButtonOn.setForeground(Colors.lightblue);
                  JButton ButtonOff = new JButton("Off");
                  ButtonOff.setForeground(Colors.lightblue);
                  ButtonOn.addMouseListener(new MouseAdapter() {
                     @Override
                     public void mouseClicked(MouseEvent e) {
                        TableLogin = true;
                     }
                  });
                  ButtonOff.addMouseListener(new MouseAdapter() {
                     @Override
                     public void mouseClicked(MouseEvent e) {
                        TableLogin = false;
                     }
                  });
                  TableButtons.add(ButtonOn);
                  TableButtons.add(ButtonOff);
               }
            });
            AccessButtons.add(on);
            AccessButtons.add(off);
            AccessButtons.add(TableLoginButton);
         }
      });
      ButtonArea.add(AdminAccessSwitch);
      JButton ChangeCode = new JButton("Change Code");
      ChangeCode.setForeground(Colors.lightblue);
      ChangeCode.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame CodeWindow = new JFrame();
            CodeWindow.setTitle("Change Code");
            CodeWindow.setBounds(150, 150, 664, 150);
            CodeWindow.setVisible(true);
            java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
            ImageIcon Icon = new ImageIcon(imgURL);
            CodeWindow.setIconImage(Icon.getImage());
            JPanel CodeButtonsArea = new JPanel();
            JPanel CurrentCodeArea = new JPanel();
            CodeWindow.getContentPane().add(CodeButtonsArea, BorderLayout.CENTER);
            CodeWindow.getContentPane().add(CurrentCodeArea, BorderLayout.SOUTH);
            JLabel CurrentCode = new JLabel();
            CurrentCode.setForeground(Colors.lightblue);
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
            JButton SelfCode = new JButton("Enter Code");
            JButton RandomCode = new JButton("Random Code");
            SelfCode.setForeground(Colors.lightblue);
            RandomCode.setForeground(Colors.lightblue);
            SelfCode.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                  // new window
                  JFrame self = new JFrame();
                  self.setTitle("New Code");
                  self.setBounds(150, 150, 664, 150);
                  self.setVisible(true);
                  java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
                  ImageIcon Icon = new ImageIcon(imgURL);
                  self.setIconImage(Icon.getImage());
                  JPanel panel = new JPanel();
                  self.getContentPane().add(panel, BorderLayout.CENTER);
                  TextField code = new TextField();
                  code.setColumns(10);
                  code.setForeground(Colors.lightblue);
                  JButton Submit = new JButton("Submit");
                  Submit.setForeground(Colors.lightblue);
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
         }
      });
      ButtonArea.add(ChangeCode);
      JButton ChangeLoginButton = new JButton("Change Login");
      ChangeLoginButton.setForeground(Colors.lightblue);
      ChangeLoginButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame CurrentLogin = new JFrame();
            CurrentLogin.setTitle("Current Login");
            CurrentLogin.setBounds(150, 150, 664, 150);
            CurrentLogin.setVisible(true);
            java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
            ImageIcon Icon = new ImageIcon(imgURL);
            CurrentLogin.setIconImage(Icon.getImage());
            JPanel CurrentForm = new JPanel();
            CurrentLogin.getContentPane().add(CurrentForm, BorderLayout.CENTER);
            JLabel UsernameText = new JLabel("Username:");
            JLabel PasswordText = new JLabel("Password:");
            JTextField Username = new JTextField();
            JTextField Password = new JTextField();
            JButton TryChange = new JButton("Change");
            UsernameText.setForeground(Colors.lightblue);
            PasswordText.setForeground(Colors.lightblue);
            Username.setColumns(10);
            Username.setForeground(Colors.lightblue);
            Password.setColumns(10);
            Password.setForeground(Colors.lightblue);
            TryChange.setForeground(Colors.lightblue);
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
                        java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
                        ImageIcon Icon = new ImageIcon(imgURL);
                        newLogin.setIconImage(Icon.getImage());
                        JPanel NewForm = new JPanel();
                        newLogin.getContentPane().add(NewForm, BorderLayout.CENTER);
                        JLabel NewUsernameText = new JLabel("Username:");
                        JLabel NewPasswordText = new JLabel("Password:");
                        JTextField NewUsername = new JTextField();
                        JTextField NewPassword = new JTextField();
                        JButton Change = new JButton("Login");
                        Change.setForeground(Colors.lightblue);
                        NewUsernameText.setForeground(Colors.lightblue);
                        NewPasswordText.setForeground(Colors.lightblue);
                        NewUsername.setColumns(10);
                        NewUsername.setForeground(Colors.lightblue);
                        NewPassword.setColumns(10);
                        NewPassword.setForeground(Colors.lightblue);
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
                              AdminFullAccess = false;
                              newLogin.dispose();
                              AdminArea.dispose();
                              Log("Admin Has Changed Login Credentials");
                           }
                        });
                        NewForm.add(Change);
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
         }
      });
      ButtonArea.add(ChangeLoginButton);
      JButton Logout = new JButton("Logout");
      Logout.setForeground(Colors.lightblue);
      Logout.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            // Logs out user fully
            AdminFullAccess = false;
            AdminArea.dispose();
         }
      });
      ButtonArea.add(Logout);
   }
   private void VerifyUserGUI(){
      // if statements allows admin to be kept logged in
      if(AdminFullAccess == false){
         JFrame LoginGUIFrame = new JFrame();
         LoginGUIFrame.setTitle("Login");
         LoginGUIFrame.setBounds(150, 150, 664, 150);
         LoginGUIFrame.setVisible(true);
         java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
         ImageIcon Icon = new ImageIcon(imgURL);
         LoginGUIFrame.setIconImage(Icon.getImage());
         JPanel Form = new JPanel();
         LoginGUIFrame.getContentPane().add(Form, BorderLayout.CENTER);
         JLabel UsernameLabel = new JLabel("Username:");
         JLabel PasswordLabel = new JLabel("Password:");
         JTextField Username = new JTextField();
         JTextField Password = new JTextField();
         JButton Login = new JButton("Login");
         UsernameLabel.setForeground(Colors.lightblue);
         PasswordLabel.setForeground(Colors.lightblue);
         Username.setColumns(10);
         Username.setForeground(Colors.lightblue);
         Password.setColumns(10);
         Password.setForeground(Colors.lightblue);
         Login.setForeground(Colors.lightblue);
         Form.add(UsernameLabel);
         Form.add(Username);
         Form.add(PasswordLabel);
         Form.add(Password);
         Login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               VerifyUser("Verify",Username.getText(),Password.getText());
               if(AdminFullAccess == true){
                  AdminPortalArea();
               }
               LoginGUIFrame.dispose();
            }
         });
         Form.add(Login);
      }
      if(AdminFullAccess == true){
         AdminPortalArea();
      }
   }
   // Internal Applications
   // Applications
   private void TableWindow(){
      // Frame
      JFrame window = new JFrame();
      window.setTitle("Table");
      window.setVisible(true);
      window.setBounds(150, 150, 664, 520);
      java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
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
         java.net.URL ErrorImgURL = Program.class.getResource("\\Assets\\img\\Warning.jpg");
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
      Header.setForeground(Colors.lightblue);
      TableHeaderPanel.add(Header);
      // User Panel
      JPanel UserInfoPanel = new JPanel();
      window.getContentPane().add(UserInfoPanel, BorderLayout.SOUTH);
      UserInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      String LabelText = UserPathName.get(0);
      JLabel UserLabel = new JLabel(LabelText);
      UserLabel.setForeground(Colors.darkgreen);
      UserInfoPanel.add(UserLabel);
      // Table
      JPanel TablePanel = new JPanel();
      TablePanel.setSize(300,100);
      window.getContentPane().add(TablePanel, FlowLayout.CENTER);
      // Initializing the JTable
      DefaultTableModel model = new DefaultTableModel();
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
      Color darkgoldenrod = Colors.darkgoldenrod;
      Color gold = Colors.gold;
      Color red = Color.RED;
      j.setGridColor(darkgoldenrod.darker());
      j.setBackground(gold.brighter());
      j.setForeground(red.darker());
      // adding it to JScrollPane
      JScrollPane sp = new JScrollPane(j);
      sp.getVerticalScrollBar().setBackground(Colors.lightblue);
      TablePanel.add(sp);
      Log("Built Table");
      // Toolbar
      JToolBar ToolBar = new JToolBar();
      ToolBar.setSize(new Dimension(210, 0));
      ToolBar.setOrientation(SwingConstants.VERTICAL);
      ToolBar.setBackground(SystemColor.menu);
      ToolBar.setFloatable(false);
      window.getContentPane().add(ToolBar, BorderLayout.WEST);
      JButton Refresh = new JButton("Refresh");
      Refresh.setForeground(Colors.lightblue);
      Refresh.setSize(new Dimension(200, 20));
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
      JButton Count = new JButton("Count");
      Count.setForeground(Colors.lightblue);
      Count.setSize(new Dimension(200, 20));
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
            Count.setForeground(Colors.lightblue);
            String CountText = "Barcodes: " + RowCount + " Items: " + Sum;
            Count.setText(CountText);
            Log("Counted Inventory");
            Info.add(Count);
            if(Sum == 0){
               java.net.URL ErrorImgURL = Program.class.getResource("\\Assets\\img\\Warning.jpg");
               ImageIcon ErrorIcon = new ImageIcon(ErrorImgURL);
               dialog.setIconImage(ErrorIcon.getImage());
            }
            // User Info
            JPanel DialogUserInfoPanel = new JPanel();
            dialog.getContentPane().add(DialogUserInfoPanel, BorderLayout.SOUTH);
            DialogUserInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            String DialogLabelText = UserPathName.get(0);
            JLabel DialogUserLabel = new JLabel(DialogLabelText);
            DialogUserLabel.setForeground(Colors.darkgreen);
            DialogUserInfoPanel.add(DialogUserLabel);
         }
      });
      ToolBar.add(Count);
      JLabel SearchOptions = new JLabel("Search");
      SearchOptions.setForeground(Colors.lightblue);
      ToolBar.add(SearchOptions);
      JButton SearchBarcode = new JButton("Barcode");
      SearchBarcode.setForeground(Colors.lightblue);
      SearchBarcode.setSize(new Dimension(200, 20));
      SearchBarcode.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame Search = new JFrame();
            Search.setTitle("Barcode Search");
            Search.setBounds(200,200,300,100);
            Search.setVisible(true);
            java.net.URL IconURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
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
            BarcodeSearchLabel.setForeground(Colors.lightblue);
            BarcodeSearchPanel.add(BarcodeSearchLabel);
            JTextField BarcodeSearch = new JTextField();
            BarcodeSearch.setColumns(10);
            BarcodeSearch.setForeground(Colors.lightblue);
            JPanel BarcodeResult = new JPanel();
            Search.getContentPane().add(BarcodeResult,BorderLayout.CENTER);
            JLabel BarcodeLabel = new JLabel();
            BarcodeLabel.setText("Search For Barcode");
            BarcodeLabel.setForeground(Colors.lightblue);
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
      JLabel AdminLabel = new JLabel("Admin");
      AdminLabel.setForeground(Colors.lightblue);
      JLabel AdminDeleteLabel = new JLabel("Delete");
      AdminDeleteLabel.setForeground(Colors.lightblue);
      ToolBar.add(AdminLabel);
      ToolBar.add(AdminDeleteLabel);
      JButton DeleteButton = new JButton("Row");
      DeleteButton.setForeground(Colors.lightblue);
      DeleteButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            // Verifying Code or Allows Function to work
            if(AdminAccess == true){
               if(j.getSelectedRow() != -1) {
                  // remove selected row from the model
                  model.removeRow(j.getSelectedRow());
               }
            }
            if(AdminAccess == false){
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
               if(AdminAccess == true){
                  model.removeRow(j.getSelectedRow());
               }
               if(AdminAccess == false){
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
               if(AdminAccess == true){
                  int[] row = j.getSelectedRows();
                  for (int i = row.length - 1; i >= 0; i--) {
                     model.removeRow(row[i]);
                  }
               }
               if(AdminAccess == false){
                  VerifyCodeGUI();
               }
            }
            VerifyCode("Verified","");
         }
      });
   }
   private void HelpWindow(){
      // Frame
      JFrame window = new JFrame();
      window.setTitle("Help");
      window.setVisible(true);
      window.setBounds(175, 175, 664, 329);
      URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
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
      UserLabel.setForeground(Colors.darkgreen);
      UserPanel.add(UserLabel);
      // Info Panel
      JTabbedPane InfoPanel = new JTabbedPane();
      InfoPanel.setUI(new TabDesign());
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
      AboutInfoLabel.setForeground(Colors.lightblue);
      AboutInfo.add(AboutInfoLabel);
      // Info Panel - Info - Program
      JLabel ProgramInfoLabel = new JLabel();
      String ProgramInfoText = "<html>" + "This is the main program" + "<html>";
      ProgramInfoLabel.setText(ProgramInfoText);
      ProgramInfoLabel.setForeground(Colors.lightblue);
      ProgramInfo.add(ProgramInfoLabel);
      // Info Panel - Info - Setup
      JLabel SetupInfoLabel = new JLabel();
      String SetupInfoText = "<html>" + "This is where you set up all the users." + "<html>";
      SetupInfoLabel.setText(SetupInfoText);
      SetupInfoLabel.setForeground(Colors.lightblue);
      SetupInfo.add(SetupInfoLabel);
      // Info Panel - Info - Users
      JLabel UsersInfoLabel = new JLabel();
      String UsersInfoText = "<html>" + "This is where you switch users when there is multiple." + "<html>";
      UsersInfoLabel.setText(UsersInfoText);
      UsersInfoLabel.setForeground(Colors.lightblue);
      UsersInfo.add(UsersInfoLabel);
      // Info Panel - Tabs
      InfoPanel.add("About",AboutInfo);
      InfoPanel.add("Program",ProgramInfo);
      InfoPanel.add("Setup",SetupInfo);
      InfoPanel.add("Users", UsersInfo);
      InfoPanel.setForegroundAt(0,Colors.lightblue);
      InfoPanel.setForegroundAt(1,Colors.lightblue);
      InfoPanel.setForegroundAt(2,Colors.lightblue);
      InfoPanel.setForegroundAt(3,Colors.lightblue);
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
      frmInventory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frmInventory.setVisible(true);
      java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
      ImageIcon Icon = new ImageIcon(imgURL);
      frmInventory.setIconImage(Icon.getImage());
      Log("Opened Program");
      VerifyCode("New","");
      VerifyUser("New","","");
      frmInventory.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            Log("Closing Program");
            CompleteLog(LogFile);
         }
      });
   }
   private void InfoContainer(){
      JPanel AddPanel = new JPanel();
      frmInventory.getContentPane().add(AddPanel, BorderLayout.NORTH);
      AddPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      InventoryCount.setForeground(Colors.lightblue);
      JLabel AddBarcodeLabel = new JLabel("Barcode:");
      AddBarcodeLabel.setForeground(Colors.lightblue);
      AddPanel.add(AddBarcodeLabel);

      AddBarcodeTextField = new JTextField();
      AddBarcodeTextField.setForeground(Colors.lightblue);
      AddBarcodeTextField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
               int barcode = Integer.valueOf(AddBarcodeTextField.getText());
               AddToInventory(barcode,1);
               PrintInventory();
               AddBarcodeTextField.setText("");
               Log("Added Barcode - Cache - " + barcode);
            }
         }
      });
      AddPanel.add(AddBarcodeTextField);
      AddBarcodeTextField.setColumns(10);
   }
   private void Toolbar(){
      JToolBar ButtonToolBar = new JToolBar();
      ButtonToolBar.setSize(new Dimension(210, 0));
      ButtonToolBar.setOrientation(SwingConstants.VERTICAL);
      ButtonToolBar.setBackground(SystemColor.menu);
      ButtonToolBar.setFloatable(false);
      frmInventory.getContentPane().add(ButtonToolBar, BorderLayout.WEST);
      JButton Barcode100 = new JButton("100");
      Barcode100.setSize(new Dimension(200, 20));
      Barcode100.setForeground(Colors.lightblue);
      Barcode100.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            AddToInventory(100,1);
            Log("Added Barcode - Cache - 100");
            PrintInventory();
         }
      });
      ButtonToolBar.add(Barcode100);
      JButton ClearResults = new JButton("Clear");
      ClearResults.setForeground(Colors.lightblue);
      ClearResults.setSize(new Dimension(200, 0));
      ClearResults.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            MainInventory.clear();
            InventoryCount.setText("Nothing Scanned");
            InventoryCount.setForeground(Colors.lightblue);
            AddBarcodeTextField.grabFocus();
            Log("Cleared All Barcodes");
         }
      });
      ButtonToolBar.add(ClearResults);

      JButton ExportInventory = new JButton("Export");
      ExportInventory.setForeground(Colors.lightblue);
      ExportInventory.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            ExportInventory("InventoryExport.txt");
            Log("Exported Inventory");
         }
      });
      ButtonToolBar.add(ExportInventory);
      JButton TableButton = new JButton("Table");
      TableButton.setForeground(Colors.lightblue);
      TableButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            if(AdminAccess || AdminFullAccess == false && TableLogin == false){
               JFrame prompt = new JFrame();
               prompt.setTitle("Pre-Verify");
               prompt.setBounds(100, 100, 300, 100);
               prompt.setVisible(true);
               java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
               ImageIcon Icon = new ImageIcon(imgURL);
               prompt.setIconImage(Icon.getImage());
               JPanel ButtonArea = new JPanel();
               prompt.getContentPane().add(ButtonArea, BorderLayout.CENTER);
               JButton code = new JButton("Code");
               JButton password = new JButton("Password");
               JButton skip = new JButton("Skip");
               code.setForeground(Colors.lightblue);
               password.setForeground(Colors.lightblue);
               skip.setForeground(Colors.lightblue);
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
                     CodeLabel.setForeground(Colors.lightblue);
                     JTextField Code = new JTextField();
                     Code.setColumns(10);
                     Code.setForeground(Colors.lightblue);
                     LoginArea.add(CodeLabel);
                     LoginArea.add(Code);
                     JButton LoginButton = new JButton("Login");
                     LoginButton.setForeground(Colors.lightblue);
                     LoginButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                           VerifyCode("Verify",Code.getText());
                           if(AdminAccess == true){
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
                     UsernameLabel.setForeground(Colors.lightblue);
                     JTextField UsernameText = new JTextField();
                     UsernameText.setColumns(10);
                     UsernameText.setForeground(Colors.lightblue);
                     LoginArea.add(UsernameLabel);
                     LoginArea.add(UsernameText);
                     JLabel PasswordLabel = new JLabel("Password:");
                     PasswordLabel.setForeground(Colors.lightblue);
                     JTextField PasswordText = new JTextField();
                     PasswordText.setColumns(10);
                     PasswordText.setForeground(Colors.lightblue);
                     LoginArea.add(PasswordLabel);
                     LoginArea.add(PasswordText);
                     JButton LoginButton = new JButton("Login");
                     LoginButton.setForeground(Colors.lightblue);
                     LoginButton.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                           VerifyUser("Verify",UsernameText.getText(),PasswordText.getText());
                           if(AdminFullAccess == true){
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
                     JFrame Confirm = new JFrame();
                     Confirm.setTitle("Confirm");
                     Confirm.setBounds(100, 100, 450, 100);
                     Confirm.setVisible(true);
                     Confirm.setIconImage(Icon.getImage());
                     JPanel ConfirmPanel = new JPanel();
                     Confirm.getContentPane().add(ConfirmPanel, BorderLayout.CENTER);
                     JButton ConfirmYes = new JButton("Yes");
                     ConfirmYes.setForeground(Colors.lightblue);
                     JButton ConfirmNo = new JButton("No");
                     ConfirmNo.setForeground(Colors.lightblue);
                     ConfirmYes.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                           prompt.dispose();
                           Confirm.dispose();
                           TableWindow();
                        }
                     });
                     ConfirmNo.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                           Confirm.dispose();
                        }
                     });
                     ConfirmPanel.add(ConfirmYes);
                     ConfirmPanel.add(ConfirmNo);
                  }
               });
               ButtonArea.add(code);
               ButtonArea.add(password);
               ButtonArea.add(skip);
            }
            else{
               TableWindow();
               Log("Attempted To Opened Table Window");
            }
         }
      });
      ButtonToolBar.add(TableButton);
      JButton HelpButton = new JButton("Help");
      HelpButton.setForeground(Colors.lightblue);
      HelpButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            HelpWindow();
            Log("Attempted To Opened Help Window");
         }
      });
      ButtonToolBar.add(HelpButton);
      JButton VerifyButton = new JButton("Verify");
      VerifyButton.setForeground(Colors.lightblue);
      VerifyButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            VerifyCodeGUI();
         }
      });
      ButtonToolBar.add(VerifyButton);
      JButton AdminButton = new JButton("Admin");
      AdminButton.setForeground(Colors.lightblue);
      AdminButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            VerifyUserGUI();
         }
      });
      ButtonToolBar.add(AdminButton);
      JButton Exit = new JButton("Exit");
      Exit.setForeground(Colors.lightblue);
      Exit.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
            ImageIcon Icon = new ImageIcon(imgURL);
            JDialog closing = new JDialog();
            closing.setTitle("Closing");
            closing.setBounds(150,150,300,250);
            closing.setIconImage(Icon.getImage());
            closing.setVisible(true);
            JPanel closingMessage = new JPanel();
            JPanel closingButtons = new JPanel();
            closing.getContentPane().add(closingMessage, BorderLayout.NORTH);
            closing.getContentPane().add(closingButtons, BorderLayout.CENTER);
            JLabel message = new JLabel("Closing");
            JButton yes = new JButton("Yes");
            yes.setForeground(Colors.lightblue);
            JButton no = new JButton("No");
            no.setForeground(Colors.lightblue);
            message.setForeground(Colors.lightblue);
            closingMessage.add(message);
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
                     }
                  });
                  closing.dispose();
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
                     }
                  });
               }
            });
            closingButtons.add(no);
         }
      });
      ButtonToolBar.add(Exit);
   }
   private void InventoryContainer(){
      JPanel InventoryCountPanel = new JPanel();
      frmInventory.getContentPane().add(InventoryCountPanel, BorderLayout.CENTER);
      InventoryCountPanel.add(InventoryCount);
   }
   private void UserInfo(){
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
                  UserLabel.setForeground(Colors.darkgreen);
                  UserPanel.add(UserLabel);
                  Log("User Found");
               }
            }
            else{
               JLabel UserLabel = new JLabel("User Not Found");
               UserLabel.setForeground(Colors.firebrick);
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