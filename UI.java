package Assets;
import Assets.ButtonUI.size;
import Assets.colors.Colors;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;

public class UI {
	public static class Files{
		public static String Icon = "\\Assets\\img\\icon.jpg";
		public static String Warning = "\\Assets\\img\\Warning.jpg";
	}
	public static class Themes{
		public static Color Text = Colors.Palettes.Default.Lightblue;
		public static Color CorrectUserText = Colors.Palettes.Default.Darkgreen;
		public static Color WrongUserText = Colors.Palettes.Default.Firebrick;
		public static class Defaults{
			public static class JLabel{
				public static Color Foreground = Text;
			}
			public static class JTextField{
				public static int Columns = 10;
				public static Color Foreground = Text;
				public static class CustomBorder{
					public static Color BorderColor = Colors.Palettes.Default.Info.darker();
					public static Border Border = BorderFactory.createLineBorder(BorderColor);
					public static Border setBorderColor(Color Color) {
						Border = BorderFactory.createLineBorder(Color);
						return Border;
					}
				}
				public static class Console{
					private static int Columns = ProgramUI.AdminPortal.ConsoleArea.JTextFieldClass.Columns;
					private static Color Foreground = ProgramUI.AdminPortal.ConsoleArea.JTextFieldClass.Foreground;
					private static Color Background = ProgramUI.AdminPortal.ConsoleArea.JTextFieldClass.Background;
					private static Border Border = ProgramUI.AdminPortal.ConsoleArea.JTextFieldClass.Border;
					public static int setColumns(int columns) {
						Columns = columns;
						return Columns;
					}
					public static Color setForeground(Color foreground) {
						Foreground = foreground;
						return Foreground;
					}
					public static Color setBackground(Color background) {
						Background = background;
						return Background;
					}
					public static Border setBorderColor(Color Color) {
						Border = BorderFactory.createLineBorder(Color);
						return Border;
					}
					public static class Effects{
						public static Border setErrorLineBorder() {
							return BorderFactory.createLineBorder(Colors.Palettes.Default.Lightred);
						}
						public static Border setCorrectLineBorder() {
							return BorderFactory.createLineBorder(Colors.Palettes.Default.Lightgreen);
						}
						public static Border setDefaultBorder() {
							return Functions.setLineBorder(Border);
						}
					}
					public static Effects Effects = new Effects();
				}
				public static CustomBorder Border = new CustomBorder();
				public static Console setConsole = new Console();
			}
			public static class JTextArea{
				public static int Rows = 10;
				public static int Columns = 20;
				public static class CustomArea{
					public static Color Background = Colors.Palettes.Default.Info;
					public static Color Foreground = Colors.Palettes.Default.Lightblue.darker();
					public static Border Border = BorderFactory.createLineBorder(Colors.Palettes.Default.Darkgoldenrod);
					public static int setRows(int rows) {
						Rows = rows;
						return Rows;
					}
					public static int setColumns(int columns) {
						Columns = columns;
						return Columns;
					}
					public static Color setBackground(Color background) {
						Background = background;
						return Background;
					}
					public static Color setForeground(Color foreground) {
						Foreground = foreground;
						return Foreground;
					}
					public static Border setBorderColor(Color Color) {
						Border = BorderFactory.createLineBorder(Color);
						return Border;
					}
				}
				public static class Console{
					private static int Rows = ProgramUI.AdminPortal.ConsoleArea.JTextAreaClass.Rows;
					private static int Columns = ProgramUI.AdminPortal.ConsoleArea.JTextAreaClass.Columns;
					private static Color Foreground = ProgramUI.AdminPortal.ConsoleArea.JTextAreaClass.Foreground;
					private static Color Background = ProgramUI.AdminPortal.ConsoleArea.JTextAreaClass.Background;
					private static Border Border = ProgramUI.AdminPortal.ConsoleArea.JTextAreaClass.Border;
					public static int setRows(int rows) {
						Rows = rows;
						return Rows;
					}
					public static int setColumns(int columns) {
						Columns = columns;
						return Columns;
					}
					public static Color setForeground(Color foreground) {
						Foreground = foreground;
						return Foreground;
					}
					public static Color setBackground(Color background) {
						Background = background;
						return Background;
					}
					public static Border setBorderColor(Color Color) {
						Border = BorderFactory.createLineBorder(Color);
						return Border;
					}
				}
				public static CustomArea Area = new CustomArea();
				public static Console setConsole = new Console();
			}
			public static class JButton{
				public static Color Foreground = Text;
			}
			public static class Functions{
				public static int setRows(int rows) {
					return rows;
				}
				public static int setColumns(int columns) {
					return columns;
				}
				public static int setWidth(int width) {
					return width;
				}
				public static int setHeight(int height) {
					return height;
				}
				public static Color setForeground(Color color) {
					return color;
				}
				public static Color setBackground(Color color) {
					return color;
				}
				public static Border setLineBorder(Color color) {
					return BorderFactory.createLineBorder(color);
				}
				public static Border setLineBorder(Color color, int px) {
					return BorderFactory.createLineBorder(color,px);
				}
				public static Border setLineBorder(Border border) {
					return border;
				}
			}
			public static JLabel Label = new JLabel();
			public static JTextField TextField = new JTextField();
			public static JTextArea TextArea = new JTextArea();
			public static JButton Button = new JButton();
			public static Functions Function = new Functions();
		}
		public static Defaults Defaults = new Defaults();
	}
	public static class Properties extends Themes{
		public static class Old{
			public static class Defaults{
				public static Color Label = Defaults.Label.Foreground;
				public static Color TextField = Defaults.TextField.Foreground;
				public static Color Button = Defaults.Button.Foreground;
			}
			public static Color Label = Defaults.Label.Foreground;
			public static Color TextField = Defaults.TextField.Foreground;
			public static Color Button = Defaults.Button.Foreground;
		}
		public static class New extends Old.Defaults{
			public static class Foreground{
				public static Color[] Defaults = {Label,TextField,Button};
				public static Color[] PurplePalette = {Colors.Palettes.Purple.Purple,Colors.Palettes.Purple.Lightpurple,Colors.Palettes.Purple.Darkpurple};
			}
			public static Foreground Foreground = new Foreground();
		}
		public static class Effects{
			private static Color Brightgreen = Colors.Palettes.Green.Brightgreen;
			private static Color Yellow = Colors.Palettes.Yellow.Yellow;
			public static Color One = Brightgreen;
			public static Color Two = Yellow;
			public static Border BorderOne = Defaults.TextField.setConsole.Effects.setCorrectLineBorder();
			public static Border BorderTwo = Defaults.TextField.setConsole.Effects.setErrorLineBorder();
		}
		public static Old Old = new Old();
		public static New New = new New();
		public static Effects Effects = new Effects();
	}
	public static class Prototype extends Themes{
		public static class Images extends Files{
			public static String QuestionMark = "\\Assets\\img\\Question.png";
			public static String WarningMark = "\\Assets\\img\\Warning.png";
		}
		private static class Colors extends Properties.New{
			private static Color Darkpurple = Foreground.PurplePalette[2];
		}
		public static class Functions{
			public static int SetX(int X) {
				return X;
			}
			public static int SetY(int Y) {
				return Y;
			}
			public static String setText(String Text) {
				return Text;
			}
		}
		public static Images Images = new Images();
		public static Functions Functions = new Functions();
		public static Colors ModalColors = new Colors();
	}
	public static class Modals extends Prototype{
		public static class ExitModal{
			private static ImageIcon Image = new ImageIcon(Images.QuestionMark);
			private static ImageIcon IconImg = new ImageIcon(Images.Icon);
			public static class JFrame{
				public static ImageIcon Icon = IconImg;
				public static int FrameWidth = 400;
				public static int FrameHeight =  160;
			}
			public static class ImagePanel{
				public static ImageIcon Icon = Image;
				public static int PanelWidth = JFrame.FrameWidth / 4; // 100px
				public static int PanelHeight = JFrame.FrameHeight / 2; // 80px
				public static int X = Functions.SetX(PanelWidth - 50); // 50px
				private static int YY = PanelHeight / 2; // 40px
				public static int Y = Functions.SetY(YY - 15); // 25px
				/*
				 	* Width = 1:4 Ratio OR 1 PER 100px
				 	* Height =  1:2 Ratio
				 	* May Have To Edit To Fix UI
				 */
			}
			public static class MessagePanel{
				public static int PanelWidth = JFrame.FrameWidth - ImagePanel.PanelWidth - 20; //380
				public static int PanelHeight = JFrame.FrameHeight / 2; // 80px
				public static int X = Functions.SetX(JFrame.FrameWidth - PanelWidth - 15);
				private static int YY = PanelHeight / 2;
				public static int Y = Functions.SetY(YY - 5);
				public static String Message = Functions.setText("Are You Sure You Want To Exit");
				public static Color MessageForeground = ModalColors.Darkpurple;
				/*
				 	* Width = Rest Of Panel
				 	* Height = 1:2 Ratio
				 	* May Have To Edit To Fix UI 
				 */
			}
			public static class Buttons extends Program.Toolbar.Closing{
				public static Color[] borderColor = {Colors.Palettes.Red.Lightred,Colors.Palettes.Green.Brightgreen};
				public static javax.swing.JButton YesBtn = size.newUINumber(29, "Yes", Colors.Palettes.Red.Firebrick,borderColor[0]);
				public static javax.swing.JButton NoBtn = size.newUINumber(29, "No", Colors.Palettes.Blue.Lightblue,borderColor[1]);
			}
			public static JFrame Frame = new JFrame();
			public static ImagePanel ImagePanel = new ImagePanel();
			public static MessagePanel MessagePanel = new MessagePanel();
			public static Buttons Buttons = new Buttons();
			public static Functions Functions = new Functions();
		}
		public static class WarningModal{
			private static ImageIcon Image = new ImageIcon(Images.WarningMark);
			private static ImageIcon IconImg = new ImageIcon(Images.Icon);
			public static class JFrame{
				public static ImageIcon Icon = IconImg;
				public static int FrameWidth = 400;
				public static int FrameHeight =  160;
			}
			public static class ImagePanel{
				public static ImageIcon Icon = Image;
				public static int PanelWidth = JFrame.FrameWidth / 4; // 100px
				public static int PanelHeight = JFrame.FrameHeight / 2; // 80px
				public static int X = Functions.SetX(PanelWidth - 50); // 50px
				private static int YY = PanelHeight / 2; // 40px
				public static int Y = Functions.SetY(YY - 15); // 25px
				/*
				 	* Width = 1:4 Ratio OR 1 PER 100px
				 	* Height =  1:2 Ratio
				 	* May Have To Edit To Fix UI
				 */
			}
			public static class MessagePanel{
				public static int PanelWidth = JFrame.FrameWidth - ImagePanel.PanelWidth - 20; //480
				public static int PanelHeight = JFrame.FrameHeight / 2; // 80px
				public static int X = Functions.SetX(JFrame.FrameWidth - PanelWidth - 15);
				private static int YY = PanelHeight / 2;
				public static int Y = Functions.SetY(YY - 5);
				public static String Message = Functions.setText("Do You Want To Continue");
				public static Color MessageForeground = ModalColors.Darkpurple;
				/*
				 	* Width = Rest Of Panel
				 	* Height = 1:2 Ratio
				 	* May Have To Edit To Fix UI 
				 */
			}
			public static class Buttons extends Program.TableWindow{
				public static Color[] borderColor = {Colors.Palettes.Yellow.Gold,Colors.Palettes.Green.Brightgreen};
				public static javax.swing.JButton YesBtn = size.newUINumber(29, "Yes", Colors.Palettes.Blue.Lightblue,borderColor[1]);
				public static javax.swing.JButton NoBtn = size.newUINumber(29, "No", Colors.Palettes.Blue.Lightblue,borderColor[0]);
			}
			public static JFrame Frame = new JFrame();
			public static ImagePanel ImagePanel = new ImagePanel();
			public static MessagePanel MessagePanel = new MessagePanel();
			public static Buttons Buttons = new Buttons();
			public static Functions Functions = new Functions();
		}
		public static class ConfirmModal{
			private static ImageIcon Image = new ImageIcon(Images.QuestionMark);
			private static ImageIcon IconImg = new ImageIcon(Images.Icon);
			public static class JFrame{
				public static ImageIcon Icon = IconImg;
				public static int FrameWidth = 400;
				public static int FrameHeight =  160;
			}
			public static class ImagePanel{
				public static ImageIcon Icon = Image;
				public static int PanelWidth = JFrame.FrameWidth / 4; // 100px
				public static int PanelHeight = JFrame.FrameHeight / 2; // 80px
				public static int X = Functions.SetX(PanelWidth - 55); // 45px
				private static int YY = PanelHeight / 2; // 40px
				public static int Y = Functions.SetY(YY - 15); // 25px
				/*
				 	* Width = 1:4 Ratio OR 1 PER 100px
				 	* Height =  1:2 Ratio
				 	* May Have To Edit To Fix UI
				 */
			}
			public static class MessagePanel{
				public static int PanelWidth = JFrame.FrameWidth - ImagePanel.PanelWidth - 20; //380
				public static int PanelHeight = JFrame.FrameHeight / 2; // 80px
				public static int X = Functions.SetX(JFrame.FrameWidth - PanelWidth - 15);
				private static int YY = PanelHeight / 2;
				public static int Y = Functions.SetY(YY - 5);
				public static String Message = Functions.setText("Are You Sure You Want To Do This");
				public static Color MessageForeground = ModalColors.Darkpurple;
				/*
				 	* Width = Rest Of Panel
				 	* Height = 1:2 Ratio
				 	* May Have To Edit To Fix UI 
				 */
			}
			public static class Buttons extends Program.Toolbar{
				public static Color[] borderColor = {Colors.Palettes.Yellow.Gold,Colors.Palettes.Green.Brightgreen};
				public static javax.swing.JButton YesBtn = size.newUINumber(29, "Yes", Colors.Palettes.Blue.Lightblue,borderColor[0]);
				public static javax.swing.JButton NoBtn = size.newUINumber(29, "No", Colors.Palettes.Blue.Lightblue,borderColor[0]);
			}
			public static JFrame Frame = new JFrame();
			public static ImagePanel ImagePanel = new ImagePanel();
			public static MessagePanel MessagePanel = new MessagePanel();
			public static Buttons Buttons = new Buttons();
			public static Functions Functions = new Functions();
		}
		public static ExitModal Exit = new ExitModal();
		public static WarningModal Warning = new WarningModal();
		public static ConfirmModal Confirm = new ConfirmModal();
	}
	public static class Program{
		public static class VerifyCode{
			public static class JLabel{
				// OLD COLOR : Theme.Defaults.Label.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[2]);
			}
			public static JLabel Label = new JLabel();
		}
		public static class VerifyCodeGUI{
			public static class JLabel{
				// OLD COLOR : Theme.Defaults.Label.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[2]);
			}
			public static class JTextField{
				public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
				// OLD COLOR : Theme.Defaults.TextField.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[0]);
				public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
			}
			public static class JButton extends Themes.Defaults.JButton{
				// OLD COLOR : Theme.Defaults.Button.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[1]);
			}
			public static JLabel Label = new JLabel();
			public static JTextField TextField = new JTextField();
			public static JButton Button = new JButton();
		}
		public static class VerifyUser{
			public static class JLabel{
				// OLD COLOR : Theme.Defaults.Label.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[2]);
			}
			public static class JTextField{
				public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
				// OLD COLOR : Theme.Defaults.TextField.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[0]);
				public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
			}
			public static class JButton extends Themes.Defaults.JButton {
				// OLD COLOR : Theme.Defaults.Button.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[1]);
			}
			public static JLabel Label = new JLabel();
			public static JTextField TextField = new JTextField();
			public static JButton Button = new JButton();
		}
		public static class AdminPortalArea{
			public static class JLabel{
				public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
			}
			public static class JButton extends Themes.Defaults.JButton{
				public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Button.Foreground);
			}
			public static class AccessPoint{
				public static class JButton{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Button.Foreground);
				}
				public static JButton Button = new JButton();
			}
			public static class ChangeCode{
				public static class JLabel{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
				}
				public static class JTextField{
					public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.TextField.Foreground);
					public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
				}
				public static class JButton extends Themes.Defaults.JButton{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Button.Foreground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
				public static JTextField TextField = new JTextField();
			}
			public static class ChangeLogin{
				public static class JLabel{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
				}
				public static class JTextField{
					public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.TextField.Foreground);
					public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
				}
				public static class JButton extends Themes.Defaults.JButton{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Button.Foreground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
				public static JTextField TextField = new JTextField();
			}
			public static class InfoArea{
				public static class JLabel{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
				}
				public static class JButton extends Themes.Defaults.JButton{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Button.Foreground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
			}
			public static class ConsoleArea{
				public static Console.UI.JTextAreaUI JTextAreaClass = new Console.UI.JTextAreaUI();
				public static Console.UI.JTextFieldUI JTextFieldClass = new Console.UI.JTextFieldUI();
				public static Console.UI.Choice ChoiceClass = new Console.UI.Choice();
				public static class JTextAreaUI{
					public static int Rows = Theme.Defaults.TextArea.setConsole.setRows(JTextAreaClass.Rows);
					public static int Columns = Theme.Defaults.TextArea.setConsole.setColumns(JTextAreaClass.Columns);
					public static Color Foreground = Theme.Defaults.TextArea.setConsole.setForeground(JTextAreaClass.Foreground);
					public static Color Background = Theme.Defaults.TextArea.setConsole.setBackground(JTextAreaClass.Background);
					public static Border Border = Theme.Defaults.TextArea.setConsole.setBorderColor(Colors.darkgoldenrod);
					// to make editable switch to true
					public static boolean Editable = false;
				}
				public static class JTextFieldUI{
					public static int Columns = Theme.Defaults.TextField.setConsole.setColumns(JTextFieldClass.Columns);
					public static Color Foreground = Theme.Defaults.TextField.setConsole.setForeground(JTextFieldClass.Foreground);
					public static Color Background = Theme.Defaults.TextField.setConsole.setBackground(JTextFieldClass.Background);
					public static Border Border = Theme.Defaults.TextField.setConsole.setBorderColor(Colors.darkgoldenrod);
					public static Border BorderOne = Properties.Effects.BorderOne;
					public static Border BorderTwo = Properties.Effects.BorderTwo;
				}
				public static class Choice{
					public static Color Foreground = Theme.Defaults.Function.setForeground(ChoiceClass.Foreground);
					public static Color Background = Theme.Defaults.Function.setBackground(ChoiceClass.Background);
				}
				public static JTextFieldUI TextField = new JTextFieldUI();
				public static JTextAreaUI TextArea = new JTextAreaUI();
				public static Choice Choice = new Choice();
			}
			public static JButton Button = new JButton();
			public static JLabel Label = new JLabel();
			public static AccessPoint AccessPoint = new AccessPoint();
			public static ChangeCode ChangeCode = new ChangeCode();
			public static ChangeLogin ChangeLogin = new ChangeLogin();
			public static InfoArea Info = new InfoArea();
			public static ConsoleArea ConsoleArea = new ConsoleArea();
		}
		public static class VerifyUserGUI{
			public static class JLabel{
				// OLD COLOR : Theme.Defaults.Label.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[2]);
			}
			public static class JTextField{
				public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
				// OLD COLOR : Theme.Defaults.TextField.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[0]);
				public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
			}
			public static class JButton extends Themes.Defaults.JButton{
				// OLD COLOR : Theme.Defaults.Button.Foreground
				public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[1]);
			}
			public static JLabel Label = new JLabel();
			public static JButton Button = new JButton();
			public static JTextField TextField = new JTextField();
		}
		public static class TableWindow{
			public static Color Username = Theme.CorrectUserText;
			public static class JLabel{
				public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
			}
			public static class JButton extends Themes.Defaults.JButton{
				public static Color Foreground =  Theme.Defaults.Function.setForeground(Theme.Defaults.Button.Foreground);
			}
			public static class Table{
				public static Color GridColor = Colors.Palettes.Default.Info.darker();
				public static Color Background = Colors.Palettes.Default.Gold.brighter();
				public static Color Foreground = Colors.Palettes.Default.Lightblue.darker();	
			}
			public static class ScrollBar{
				public static Color Background = Colors.Palettes.Default.Lightblue;
			}
			public static class Toolbar{
				// Switch To True To Make Floatable
				public static boolean Floatable = false;
				public static class Separator{
					public static int Width = 5;
					public static int Height = 5;
				}
				public static int Width = Theme.Defaults.Function.setWidth(210);
				public static int Height = Theme.Defaults.Function.setHeight(0);
				public static class JButton extends Themes.Defaults.JButton{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Button.Foreground);
					public static int Width = Theme.Defaults.Function.setWidth(200);
					public static int Height = Theme.Defaults.Function.setHeight(20);
				}
				public static class JLabel{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
				}
				public static class Count{
					public static class JLabel{
						public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
						public static Color UserForeground = Theme.Defaults.Function.setForeground(Username);
					}
					public static JLabel CountLabel = new JLabel();
				}
				public static class SearchBarcode{
					public static class JLabel{
						public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
					}
					public static class JTextField{
						public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
						public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.TextField.Foreground);
						public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
					}
					public static JLabel Label = new JLabel();
					public static JTextField TextField = new JTextField();
				}
				public static Separator Separtor = new Separator();
				public static JLabel ToolBarLabel = new JLabel();
				public static JButton TBButton = new JButton();
				public static Count Count = new Count();
				public static SearchBarcode SearchBarcode = new SearchBarcode();
			}
			public static JLabel Label = new JLabel();
			public static JButton Button = new JButton();
			public static Table Table = new Table();
			public static ScrollBar ScrollBar = new ScrollBar();
			public static Toolbar Toolbar = new Toolbar();
		}
		public static class HelpWindow{
			private static Color Username = Theme.CorrectUserText;
			public static class JLabel{
				public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
				public static Color UserForeground = Theme.Defaults.Function.setForeground(Username);
			}
			public static class DefaultTabs{
				// Change Colors In Order To Change Gradient Pattern
				public static Color select = new Color(Colors.Palettes.Gradients.Yellow.primary.getRGB());
				public static Color deselect = new Color(Colors.Palettes.Gradients.Yellow.secondary.getRGB());
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
				         selectColor = select;
				         deSelectColor = deselect;
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
				         GradientPaint DeSelectedgradientShadow = null;
				         GradientPaint SelectedgradientShadow = null;
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
				            SelectedgradientShadow = new GradientPaint(0, y + h / 2, selectColor, 0, y + h / 3, Colors.Palettes.Default.Info);
				            g2D.setPaint(SelectedgradientShadow);
				         } else {
				            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
				               DeSelectedgradientShadow = new GradientPaint(0, y + h / 2, deSelectColor, 0, y + h / 3, Colors.Palettes.Default.Info);
				               g2D.setPaint(DeSelectedgradientShadow);
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
				            v.paint(g, textRect);
				         }
				      }
				      @Override
				      protected int calculateTabWidth(int tabPlacement, int tabIndex, FontMetrics metrics) {
				         return 20 + inclTab + super.calculateTabWidth(tabPlacement, tabIndex, metrics);
				      }
				   }
			}
			public static JLabel Label = new JLabel();
			public static DefaultTabs Tabs = new DefaultTabs();
		}
		public static class InfoContainer{
			public static class JLabel{
				public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
			}
			public static class JTextField{
				public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
				public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.TextField.Foreground);
				public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
				public static class Effects{
					public static Border setNewLineBorder() {
						return BorderFactory.createLineBorder(Properties.Effects.One);
					}
					public static Border setOldLineBorder() {
						return BorderFactory.createLineBorder(Properties.Effects.Two);
					}
				}
				public static Effects Effects = new Effects();
			}
			public static JLabel Label = new JLabel();
			public static JTextField TextField = new JTextField();
		}
		public static class Toolbar{
			// Turn To True To Make Floatable
			public static boolean Floatable = false;
			public static class Separator{
				public static int Width = 5;
				public static int Height = 5;
			}
			public static class JButton extends Themes.Defaults.JButton{
				public static int Width = Theme.Defaults.Function.setWidth(200);
				public static int Height = Theme.Defaults.Function.setHeight(20);
				public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Button.Foreground);
			}
			public static class JLabel{
				public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
			}
			public static class Verify{
				public static class JLabel{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
				}
				public static class JTextField{
					public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.TextField.Foreground);
					public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
				}
				public static class JButton extends Themes.Defaults.JButton{
					public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Button.Foreground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
				public static JTextField TextField = new JTextField();
			}
			public static class Closing extends Modals.ExitModal{
				public static Color LabelForeground = Theme.Defaults.Label.Foreground;
				public static Color ButtonForeground = Theme.Defaults.Button.Foreground;
				public static class JLabel{
					// Theme.Defaults.Label.Foreground
					public static Color Foreground = Theme.Defaults.Function.setForeground(LabelForeground);
				}
				public static class JButton{
					// Theme.Defaults.Button.Foreground
					public static Color Foreground = Theme.Defaults.Function.setForeground(ButtonForeground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
			}
			public static Separator Separator = new Separator();
			public static JButton Button = new JButton();
			public static JLabel Label = new JLabel();
			public static Verify Verifty = new Verify();
			public static Closing Closing = new Closing();
		}
		public static class InventoryContainer{
			public static class JLabel{
				public static Color Foreground = Theme.Defaults.Function.setForeground(Theme.Defaults.Label.Foreground);
			}
			public static JLabel Label = new JLabel();
		}
		public static class UserInfo{
			public static class JLabel{
				public static Color Correct = Theme.CorrectUserText;
				public static Color InCorrect = Theme.WrongUserText;
			}
			public static JLabel Label = new JLabel();
		}
		public static VerifyCodeGUI VerifyCodeGUI = new VerifyCodeGUI();
		public static VerifyCode VerifyCode = new VerifyCode();
		public static VerifyUser VerifyUser = new VerifyUser();
		public static AdminPortalArea AdminPortal = new AdminPortalArea();
		public static VerifyUserGUI VerifyUserGUI = new VerifyUserGUI();
		public static TableWindow TableWindow = new TableWindow();
		public static HelpWindow HelpWindow = new HelpWindow();
		public static InfoContainer InfoContainer = new InfoContainer();
		public static Toolbar Toolbar = new Toolbar();
		public static InventoryContainer Inventory = new InventoryContainer();
		public static UserInfo UserInfo = new UserInfo();
	}
	public static class Users{
		public static class JLabel{
			// OLD COLOR : Theme.Defaults.Label.Foreground
			public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[2]);
		}
		public static class JTextField{
			public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
			// OLD COLOR : Theme.Defaults.TextField.Foreground
			public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[0]);
			public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
			public static class Effects{
				public static Border setNewBorder() {
					return BorderFactory.createLineBorder(Properties.Effects.One);
				}
				public static Border setOldLineBorder() {
					return BorderFactory.createLineBorder(Properties.Effects.Two);
				}
			}
			public static Effects Effects = new Effects();
		}
		public static class JButton extends Themes.Defaults.JButton{
			// OLD COLOR : Theme.Defaults.Button.Foreground
			public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[1]);
		}
		public static JLabel Label = new JLabel();
		public static JTextField TextField = new JTextField();
		public static JButton Button = new JButton();
	}
	public static class Setup{
		public static class JLabel{
			// OLD COLOR : Theme.Defaults.Label.Foreground
			public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[2]);
		}
		public static class JTextField{
			public static int Columns = Theme.Defaults.Function.setColumns(Theme.Defaults.TextField.Columns);
			// OLD COLOR : Theme.Defaults.TextField.Foreground
			public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[0]);
			public static Border Border = Theme.Defaults.Function.setLineBorder(Theme.Defaults.TextField.Border.Border);
			public static class Effects{
				public static Border setNewLineBorder() {
					return BorderFactory.createLineBorder(Properties.Effects.One);
				}
				public static Border setOldLineBorder() {
					return BorderFactory.createLineBorder(Properties.Effects.Two);
				}
			}
			public static Effects Effects = new Effects();
		}
		public static class JButton extends Themes.Defaults.JButton{
			// OLD COLOR : Theme.Defaults.Button.Foreground
			public static Color Foreground = Theme.Defaults.Function.setForeground(Properties.New.Foreground.PurplePalette[1]);
		}
		public static JLabel Label = new JLabel();
		public static JTextField TextField = new JTextField();
		public static JButton Button = new JButton();
	}
	public static Files Files = new Files();
	public static Themes Theme = new Themes();
	public static Properties Properties = new Properties();
	public static Prototype Prototype = new Prototype();
	public static Modals Modal = new Modals();
	public static Program ProgramUI = new Program();
	public static Users UsersUI = new Users();
	public static Setup SetupUI = new Setup();	
}