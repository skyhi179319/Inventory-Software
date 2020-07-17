import Assets.colors.Colors;
import java.lang.*;
import java.awt.*;
import java.util.TreeMap;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Program {
   private JFrame frmInventory;
   private JTextField AddBarcodeTextField;
   private JLabel InventoryCount = new JLabel("Nothing Scanned");
   TreeMap<Integer,Integer> MainInventory = new TreeMap<Integer,Integer>();
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
   // Internal Application
   private void TableWindow(){
      // Frame
      JFrame window = new JFrame();
      window.setTitle("Table");
      window.setVisible(true);
      window.setBounds(150, 150, 664, 520);
      java.net.URL imgURL = Program.class.getResource("\\Assets\\img\\icon.jpg");
      ImageIcon Icon = new ImageIcon(imgURL);
      window.setIconImage(Icon.getImage());
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
      TableHeaderPanel.add(Header);
      // User Panel
      JPanel UserInfoPanel = new JPanel();
      window.getContentPane().add(UserInfoPanel, BorderLayout.SOUTH);
      UserInfoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
      String LabelText = UserPathName.get(0);
      JLabel UserLabel = new JLabel(LabelText);
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
      // Toolbar
      JToolBar ToolBar = new JToolBar();
      ToolBar.setSize(new Dimension(210, 0));
      ToolBar.setOrientation(SwingConstants.VERTICAL);
      ToolBar.setBackground(SystemColor.menu);
      ToolBar.setFloatable(false);
      window.getContentPane().add(ToolBar, BorderLayout.WEST);

      JButton Refresh = new JButton("Refresh");
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
         }
      });
      ToolBar.add(Refresh);
      JButton Count = new JButton("Count");
      Count.setSize(new Dimension(200, 20));
      Count.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            JFrame dialog = new JFrame();
            dialog.setTitle("Inventory Count");
            dialog.setBounds(200,200,300,200);
            dialog.setVisible(true);
            dialog.setIconImage(Icon.getImage());
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
            String CountText = "Barcodes: " + RowCount + " Items: " + Sum;
            Count.setText(CountText);
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
            DialogUserInfoPanel.add(DialogUserLabel);
         }
      });
      ToolBar.add(Count);
      JLabel SearchOptions = new JLabel("Search");
      ToolBar.add(SearchOptions);
      JButton SearchBarcode = new JButton("Barcode");
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
            // Info Panel
            JPanel BarcodeSearchPanel = new JPanel();
            Search.getContentPane().add(BarcodeSearchPanel, BorderLayout.NORTH);
            JLabel BarcodeSearchLabel = new JLabel("Barcode:");
            BarcodeSearchPanel.add(BarcodeSearchLabel);
            JTextField BarcodeSearch = new JTextField();
            BarcodeSearch.setColumns(10);
            JPanel BarcodeResult = new JPanel();
            Search.getContentPane().add(BarcodeResult,BorderLayout.CENTER);
            JLabel BarcodeLabel = new JLabel();
            BarcodeLabel.setText("Search For Barcode");
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
                  }
               }
            });
            BarcodeSearchPanel.add(BarcodeSearch);
         }
      });
      ToolBar.add(SearchBarcode);
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
   }
   private void InfoContainer(){
      JPanel AddPanel = new JPanel();
      frmInventory.getContentPane().add(AddPanel, BorderLayout.NORTH);
      AddPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

      JLabel AddBarcodeLabel = new JLabel("Barcode:");
      AddPanel.add(AddBarcodeLabel);

      AddBarcodeTextField = new JTextField();
      AddBarcodeTextField.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
               int barcode = Integer.valueOf(AddBarcodeTextField.getText());
               AddToInventory(barcode,1);
               PrintInventory();
               AddBarcodeTextField.setText("");
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
      Barcode100.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            AddToInventory(100,1);
            PrintInventory();
         }
      });
      ButtonToolBar.add(Barcode100);

      JButton ClearResults = new JButton("Clear");
      ClearResults.setSize(new Dimension(200, 0));
      ClearResults.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            MainInventory.clear();
            InventoryCount.setText("Nothing Scanned");
            AddBarcodeTextField.grabFocus();
         }
      });
      ButtonToolBar.add(ClearResults);

      JButton ExportInventory = new JButton("Export");
      ExportInventory.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            ExportInventory("InventoryExport.txt");
         }
      });
      ButtonToolBar.add(ExportInventory);
      JButton TableButton = new JButton("Table");
      TableButton.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            TableWindow();
         }
      });
      ButtonToolBar.add(TableButton);
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
                  UserPanel.add(UserLabel);
               }
            }
            else{
               JLabel UserLabel = new JLabel("User Not Found");
               UserLabel.setForeground(Colors.firebrick);
               UserPanel.add(UserLabel);
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