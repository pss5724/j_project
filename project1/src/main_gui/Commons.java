package main_gui;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;

public class Commons {
	
	public static Font getFont() {
		Font font = new Font("¸¼Àº °íµñ", Font.BOLD, 12);
		return font;
	}
	
	public static Font getSmallFont() {
		Font font = new Font("¸¼Àº °íµñ", Font.BOLD, 10);
		return font;
	}
	
	public static Font getContentFont() {
		Font font = new Font("¸¼Àº °íµñ", Font.PLAIN, 12);
		return font;
	}
	
	
	public static Font getTitleFont() {
		Font font = new Font("¸¼Àº °íµñ", Font.BOLD, 14);
		return font;
	}
	
	public static JButton getJButton(String name) {
		Font font = new Font("¸¼Àº °íµñ", Font.BOLD, 14);
		JButton button = new JButton(name);
		button.setFont(font);
		return button;
	}

	/** ¸Þ½ÃÁö Ãâ·Â **/
	public static Label getMsg(String msg) {
		Font font = new Font("¸¼Àº °íµñ", Font.BOLD, 12);
		Label label = new Label(msg);
		label.setFont(font);		
		return label;
	}
	
	
	
	
	
}
