package Assets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

import Assets.colors.Colors;

public class ButtonUI {
	public static class size{
		public static class newButtonUI extends BasicButtonUI{
			static Color Color;
			static int Width;
			public newButtonUI(int width,Color color) {
				Color = color;
				Width = width;
			}
			public static newButtonUI MyButton = new newButtonUI(Width,Color);
			protected Border m_borderRaised = UIManager.getBorder("Button.border");
			protected Border m_borderLowered = UIManager.getBorder("Button.borderPressed");
			protected Color m_backgroundNormal = UIManager.getColor("Button.background");
			protected Color m_backgroundPressed = UIManager.getColor("Button.pressedBackground");
			protected Color m_foregroundNormal = UIManager.getColor("Button.foreground");
			protected Color m_foregroundActive = UIManager.getColor("Button.activeForeground");
			protected Color m_focusBorder = UIManager.getColor("Button.focusBorder");
			public static ComponentUI createUI(JComponent c) {
				return MyButton;
			}
			public void installUI(JComponent c) {
			  super.installUI(c);
			}
			public void uninstallUI(JComponent c) {
			  super.uninstallUI(c);
			}
			public void paintComponent(Graphics g,JComponent c) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    AbstractButton b = (AbstractButton) c;
				g2.setFont(c.getFont());
				g2.setColor(Color);
				String caption = b.getText();
				int x = b.getWidth() / 2 - b.getWidth() + Width;
				int y = b.getHeight() / 2 + 4;
				g2.drawString(caption, x, y);
			}
			public Dimension getPreferredSize(JComponent c) {
			    Dimension d = super.getPreferredSize(c);
			    if (m_borderRaised != null) {
			      Insets ins = m_borderRaised.getBorderInsets(c);
			      int x = d.width/ 2 - d.width + Width + 20;
			      d.setSize(new Dimension(68,20));
			    }
			    return d;
			}
		}
		public static class newButtonUIExtend extends BasicButtonUI{
			static Color Color;
			static int Width;
			static int Dimension;
			public newButtonUIExtend(int width,int dimension,Color color) {
				Color = color;
				Width = width;
				Dimension = dimension;
			}
			public static newButtonUIExtend MyButton = new newButtonUIExtend(Width,Dimension,Color);
			protected Border m_borderRaised = UIManager.getBorder("Button.border");
			protected Border m_borderLowered = UIManager.getBorder("Button.borderPressed");
			protected Color m_backgroundNormal = UIManager.getColor("Button.background");
			protected Color m_backgroundPressed = UIManager.getColor("Button.pressedBackground");
			protected Color m_foregroundNormal = UIManager.getColor("Button.foreground");
			protected Color m_foregroundActive = UIManager.getColor("Button.activeForeground");
			protected Color m_focusBorder = UIManager.getColor("Button.focusBorder");
			public static ComponentUI createUI(JComponent c) {
				return MyButton;
			}
			public void installUI(JComponent c) {
			  super.installUI(c);
			}
			public void uninstallUI(JComponent c) {
			  super.uninstallUI(c);
			}
			public void paintComponent(Graphics g,JComponent c) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    AbstractButton b = (AbstractButton) c;
				g2.setFont(c.getFont());
				g2.setColor(Color);
				String caption = b.getText();
				int x = b.getWidth() / 2 - b.getWidth() + Width;
				int y = b.getHeight() / 2 + 4;
				g2.drawString(caption, x, y);
				g2.dispose();
			}
			public Dimension getPreferredSize(JComponent c) {
			    Dimension d = super.getPreferredSize(c);
			    if (m_borderRaised != null) {
			      //Insets ins = m_borderRaised.getBorderInsets(c);
			      //int x = d.width/ 2 - d.width + Width[0] + 20;
			      int DWidth = 68 + Dimension;
			      d.setSize(new Dimension(DWidth,20));
			    }
			    return d;
			}
		}
		// 22 = 4 Words
		// 25 = 4 Words
		// 27 = 3 Words, 4 Words, 5 Words
		// 29 = 6 Words
		// 30 = 5 Words
		// 31 = 6 Words
		public static class UI22 extends javax.swing.JButton{
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + 22;
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			}
		}
		public static class UI25 extends javax.swing.JButton{
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + 25;
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			}
		}
		public static class UI27 extends javax.swing.JButton{
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + 27;
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			}
		}
		public static class UI29 extends javax.swing.JButton{
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + 29;
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			}
		}
		public static class UI30 extends javax.swing.JButton{
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + 30;
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			}
		}
		public static class UI31 extends javax.swing.JButton{
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + 31;
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			}
		}
		private static class newUI extends javax.swing.JButton{
			int Width;
			public newUI(int width) {
				Width = width;
			}
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + Width * 2;
			    setUI(new newButtonUI(Width,Colors.Palettes.Blue.Darkblue));
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			}
		}
		private static class newUIText extends javax.swing.JButton{
			int Width;
			String Text;
			public newUIText(int width,String text) {
				Width = width;
				Text = text;
			}
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + Width * 2;
			    setText(Text);
			    setUI(new newButtonUI(Width,Colors.Palettes.Blue.Darkblue));
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			}
		}
		private static class newUITextForeground extends javax.swing.JButton{
			int Width;
			String Text;
			Color Foreground;
			public newUITextForeground(int width,String text,Color foreground) {
				Width = width;
				Text = text;
				Foreground = foreground;
			}
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    setForeground(Foreground);
			    //setBorder(new LineBorder(Colors.brightblue));
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + Width * 2;
			    setText(Text);
			    setUI(new newButtonUI(Width,Colors.Palettes.Blue.Darkblue));
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			    
			}
		}
		private static class newUITextForegroundBorder extends javax.swing.JButton{
			int Width;
			String Text;
			Color Foreground;
			Color Border;
			public newUITextForegroundBorder(int width,String text,Color foreground,Color border) {
				Width = width;
				Text = text;
				Foreground = foreground;
				Border = border;
			}
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    setForeground(Foreground);
			    setBorder(new LineBorder(Border));
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + Width * 2;
			    setText(Text);
			    setUI(new newButtonUI(Width,Foreground));
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();
			    
			}
		}
		private static class newUITextForegroundBorderExtend extends javax.swing.JButton{
			int Width;
			int Dimension;
			String Text;
			Color Foreground;
			Color Border;
			public newUITextForegroundBorderExtend(int width,int dimension,String text,Color foreground,Color border) {
				Width = width;
				Dimension = dimension;
				Text = text;
				Foreground = foreground;
				Border = border;
			}
			@Override
			public void paintComponent(Graphics g) {
				// y = height
				// x = width
			    Graphics2D g2 = (Graphics2D) g.create();
			    g2.setPaint(new GradientPaint(new Point(0, 0), Colors.Palettes.White.Whitesmoke, new Point(0,getHeight()), Colors.Palettes.White.White));
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
			    setForeground(Foreground);
			    setBorder(new LineBorder(Border));
			    g2.setPaint(getForeground());
			    int cac = getWidth()/ 2 - getWidth() + Width * 2;
			    setText(Text);
			    setUI(new newButtonUIExtend(Width,Dimension,Foreground));
			    g2.drawString(getText(), cac, getHeight() / 2 + 4);
			    g2.dispose();   
			}
		}
		public static javax.swing.JButton newUINumber(int Width){
			return new newUI(Width);
		}
		public static javax.swing.JButton newUINumber(int Width,String Text){
			return new newUIText(Width,Text);
		}
		public static javax.swing.JButton newUINumber(int Width,String Text,Color Foreground){
			return new newUITextForeground(Width,Text,Foreground);
		}
		public static javax.swing.JButton newUINumber(int Width,String Text,Color Foreground,Color Border){
			return new newUITextForegroundBorder(Width,Text,Foreground,Border);
		}
		public static javax.swing.JButton newUINumber(int Width,int dimension,String Text,Color Foreground,Color Border){
			return new newUITextForegroundBorderExtend(Width,dimension,Text,Foreground,Border);
		}
	}
}