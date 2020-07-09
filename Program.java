import java.util.TreeMap;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.Random;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
            myWriter.write("-------------------------");
            myWriter.write("\r\n");
         }
         myWriter.close();
      } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
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
      ClearResults.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         }
      });
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