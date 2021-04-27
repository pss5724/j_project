package main_gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MyPageUI 	implements ActionListener{
MainUI main;
JButton mycomment, mycontent;
JPanel btn_panel;



public MyPageUI(MainUI main) {
	this.main = main;
	init();
}

public void init() {
	main.switch_panel(MainUI.MYCONTENT);
	main.content_panel.removeAll();
	main.content_panel.setLayout(new GridLayout(2,1,3,3));
	btn_panel = new JPanel();
	
	mycontent = new JButton("³»±Û");
	mycomment = new JButton("³»´ñ±Û");
	
	
	btn_panel.add(mycontent);
	btn_panel.add(mycomment);
	
	
	main.mycontent_panel.add(btn_panel);
	
	main.content_panel.add(main.mycontent_panel);
	main.jf.setVisible(true);
	
	
	mycontent.addActionListener(this);
	mycomment.addActionListener(this);
	
}


@Override
public void actionPerformed(ActionEvent e) {
	Object obj = e.getSource();
	if(obj == mycontent) {
		new MyContentUI(main);
	}else if(obj == mycomment) {
		new MyCommentUI(main);
	}

}

}
