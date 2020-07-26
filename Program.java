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
import javax.swing.table.DefaultTableModel;


public class Program {
   private JFrame frmInventory;
   private JTextField AddBarcodeTextField;
   private JLabel InventoryCount = new JLabel("Nothing Scanned");
   private String LogFile = "Main-Log.txt";
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
         }
         else{
            File_Dir.mkdir();
         }
         Random rand = new Random();
         int version = rand.nextInt(1001);
         String file = Log_File_Dir + date + "-" + version + "-" + filename;
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
      j = new JTable(model);
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
            java.net.URL ErrorImgURL = Program.class.getResource("\\Assets\\img\\Warning.jpg");
            ImageIcon ErrorIcon = new ImageIcon(ErrorImgURL);
            Search.setIconImage(ErrorIcon.getImage());
            Search.addWindowListener(new WindowAdapter() {
               @Override
               public void windowOpened(WindowEvent e) {
                  Log("Opened Search Window");
                  Log("Opened Beta Feature");
               }
            });
            Search.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosing(WindowEvent e) {
                  Log("Closing Search Window");
                  Log("Closing Beta Feature");
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
                     Object key = MainInventory.get(barcode);
                     if(MainInventory.containsValue(barcode)){
                        String MainText = "Barcode Found: " + barcodeRef + ". Scanned: " + barcode + "." ;
                        BarcodeLabel.setText(MainText);
                     }
                     else if(key == null){
                        String MainText = "Error";
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
      InfoPanel.setBackgroundAt(0,Colors.info);
      InfoPanel.setBackgroundAt(1,Colors.info);
      InfoPanel.setBackgroundAt(2,Colors.info);
      InfoPanel.setBackgroundAt(3,Colors.info);
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
            TableWindow();
            Log("Attempted To Opened Table Window");
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