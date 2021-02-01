package Assets;
import Assets.colors.Colors;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;

public class UI {
	public static class Themes{
		public static Color Text = Colors.lightblue;
		public static Color CorrectUserText = Colors.darkgreen;
		public static Color WrongUserText = Colors.firebrick;
		public static class Defualts{
			public static class JLabel{
				public static Color Foreground = Text;
			}
			public static class JTextField{
				public static int Columns = 10;
				public static Color Foreground = Text;
				public static class CustomBorder{
					public static Color BorderColor = Colors.info.darker();
					public static Border border = BorderFactory.createLineBorder(BorderColor);
					public static Border setBorderColor(Color Color) {
						border = BorderFactory.createLineBorder(Color);
						return border;
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
					
				}
				public static CustomBorder Border = new CustomBorder();
				public static Console setTextField = new Console();
			}
			public static class JTextArea{
				public static int Rows = 10;
				public static int Columns = 20;
				public static class CustomArea{
					public static Color Background = Colors.info;
					public static Color Foreground = Colors.lightblue.darker();
					public static Border Border = BorderFactory.createLineBorder(Colors.darkgoldenrod);
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
			}
			public static JLabel Label = new JLabel();
			public static JTextField TextField = new JTextField();
			public static JTextArea TextArea = new JTextArea();
			public static JButton Button = new JButton();
			public static Functions Function = new Functions();
		}
		public static Defualts Defualts = new Defualts();
	}
	public static class Files{
		public static String Icon = "\\Assets\\img\\icon.jpg";
		public static String Warning = "\\Assets\\img\\Warning.jpg";
	}
	public static class Program{
		public static class VerifyCode{
			public static class JLabel{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
			}
			public static JLabel Label = new JLabel();
		}
		public static class VerifyCodeGUI{
			public static class JLabel{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
			}
			public static class JTextField{
				public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
			}
			public static class JButton{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
			}
			public static JLabel Label = new JLabel();
			public static JTextField TextField = new JTextField();
			public static JButton Button = new JButton();
		}
		public static class VerifyUser{
			public static class JLabel{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
			}
			public static class JTextField{
				public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
			}
			public static class JButton{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
			}
			public static JLabel Label = new JLabel();
			public static JTextField TextField = new JTextField();
			public static JButton Button = new JButton();
		}
		public static class AdminPortalArea{
			public static class JLabel{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
			}
			public static class JButton{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
			}
			public static class AccessPoint{
				public static class JButton{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
				}
				public static JButton Button = new JButton();
			}
			public static class ChangeCode{
				public static class JLabel{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
				}
				public static class JTextField{
					public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
				}
				public static class JButton{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
				public static JTextField TextField = new JTextField();
			}
			public static class ChangeLogin{
				public static class JLabel{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
				}
				public static class JTextField{
					public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
				}
				public static class JButton{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
				public static JTextField TextField = new JTextField();
			}
			public static class InfoArea{
				public static class JLabel{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
				}
				public static class JButton{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
			}
			public static class ConsoleArea{
				public static Console.UI.JTextAreaUI JTextAreaClass = new Console.UI.JTextAreaUI();
				public static Console.UI.JTextFieldUI JTextFieldClass = new Console.UI.JTextFieldUI();
				public static Console.UI.Choice ChoiceClass = new Console.UI.Choice();
				public static class JTextAreaUI{
					public static int Rows = Theme.Defualts.TextArea.setConsole.setRows(JTextAreaClass.Rows);
					public static int Columns = Theme.Defualts.TextArea.setConsole.setColumns(JTextAreaClass.Columns);
					public static Color Foreground = Theme.Defualts.TextArea.setConsole.setForeground(JTextAreaClass.Foreground);
					public static Color Background = Theme.Defualts.TextArea.setConsole.setBackground(JTextAreaClass.Background);
					public static Border Border = Theme.Defualts.TextArea.setConsole.setBorderColor(Colors.darkgoldenrod);
					// to make editable switch to true
					public static boolean Editable = false;
				}
				public static class JTextFieldUI{
					public static int Columns = Theme.Defualts.TextField.setTextField.setColumns(JTextFieldClass.Columns);
					public static Color Foreground = Theme.Defualts.TextField.setTextField.setForeground(JTextFieldClass.Foreground);
					public static Color Background = Theme.Defualts.TextField.setTextField.setBackground(JTextFieldClass.Background);
					public static Border Border = Theme.Defualts.TextField.setTextField.setBorderColor(Colors.darkgoldenrod);
				}
				public static class Choice{
					public static Color Foreground = Theme.Defualts.Function.setForeground(ChoiceClass.Foreground);
					public static Color Background = Theme.Defualts.Function.setBackground(ChoiceClass.Background);
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
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
			}
			public static class JTextField{
				public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
			}
			public static class JButton{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
			}
			public static JLabel Label = new JLabel();
			public static JButton Button = new JButton();
			public static JTextField TextField = new JTextField();
		}
		public static class TableWindow{
			public static Color Username = Theme.CorrectUserText;
			public static class JLabel{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
			}
			public static class JButton{
				public static Color Foreground =  Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
			}
			public static class Table{
				public static Color GridColor = Colors.info.darker();
				public static Color Background = Colors.gold.brighter();
				public static Color Foreground = Colors.lightblue.darker();	
			}
			public static class ScrollBar{
				public static Color Background = Colors.lightblue;
			}
			public static class Toolbar{
				// Switch To True To Make Floatable
				public static boolean Floatable = false;
				public static int Width = Theme.Defualts.Function.setWidth(210);
				public static int Height = Theme.Defualts.Function.setHeight(0);
				public static class JButton{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
					public static int Width = Theme.Defualts.Function.setWidth(200);
					public static int Height = Theme.Defualts.Function.setHeight(20);
				}
				public static class JLabel{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
				}
				public static class Count{
					public static class JLabel{
						public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
						public static Color UserForeground = Theme.Defualts.Function.setForeground(Username);
					}
					public static JLabel CountLabel = new JLabel();
				}
				public static class SearchBarcode{
					public static class JLabel{
						public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
					}
					public static class JTextField{
						public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
						public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
					}
					public static JLabel Label = new JLabel();
					public static JTextField TextField = new JTextField();
				}
				public static JLabel ToolBarLabel = new JLabel();
				public static JButton ToolBarButton = new JButton();
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
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
				public static Color UserForeground = Theme.Defualts.Function.setForeground(Username);
			}
			public static class DefualtTabs{
				// Change Colors In Order To Change Gradient Pattern
				public static Color select = new Color(Colors.gold.getRGB());
				public static Color deselect = new Color(Colors.LightYellow.getRGB());
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
				            SelectedgradientShadow = new GradientPaint(0, y + h / 2, selectColor, 0, y + h / 3, Colors.info);
				            g2D.setPaint(SelectedgradientShadow);
				         } else {
				            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
				               DeSelectedgradientShadow = new GradientPaint(0, y + h / 2, deSelectColor, 0, y + h / 3, Colors.info);
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
			public static DefualtTabs Tabs = new DefualtTabs();
		}
		public static class InfoContainer{
			public static class JLabel{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
			}
			public static class JTextField{
				public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
				public static Border Border = Theme.Defualts.TextField.Border.border; /* Make Method Later*/
			}
			public static JLabel Label = new JLabel();
			public static JTextField TextField = new JTextField();
		}
		public static class Toolbar{
			// Turn To True To Make Floatable
			public static boolean Floatable = false;
			public static class JButton{
				public static int Width = Theme.Defualts.Function.setWidth(200);
				public static int Height = Theme.Defualts.Function.setHeight(20);
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
			}
			public static class JLabel{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
			}
			public static class Verify{
				public static class JLabel{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
				}
				public static class JTextField{
					public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
				}
				public static class JButton{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
				public static JTextField TextField = new JTextField();
			}
			public static class Closing{
				public static class JLabel{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
				}
				public static class JButton{
					public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
				}
				public static JLabel Label = new JLabel();
				public static JButton Button = new JButton();
			}
			public static JButton Button = new JButton();
			public static JLabel Label = new JLabel();
			public static Verify Verifty = new Verify();
			public static Closing Closing = new Closing();
		}
		public static class InventoryContainer{
			public static class JLabel{
				public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
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
			public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
		}
		public static class JTextField{
			public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
			public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
		}
		public static class JButton{
			public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
		}
		public static JLabel Label = new JLabel();
		public static JTextField TextField = new JTextField();
		public static JButton Button = new JButton();
	}
	public static class Setup{
		public static class JLabel{
			public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Label.Foreground);
		}
		public static class JTextField{
			public static int Columns = Theme.Defualts.Function.setColumns(Theme.Defualts.TextField.Columns);
			public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.TextField.Foreground);
		}
		public static class JButton{
			public static Color Foreground = Theme.Defualts.Function.setForeground(Theme.Defualts.Button.Foreground);
		}
		public static JLabel Label = new JLabel();
		public static JTextField TextField = new JTextField();
		public static JButton Button = new JButton();
	}
	public static Themes Theme = new Themes();
	public static Files Files = new Files();
	public static Program ProgramUI = new Program();
	public static Users UsersUI = new Users();
	public static Setup SetupUI = new Setup();
}