package main_gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MemberManagementUI implements ActionListener{
	MainUI main;
	JButton select, update,delete;
	JPanel btn_panel;
	
	
	
	public MemberManagementUI(MainUI main) {
		this.main = main;
		init();
	}
	
	public void init() {
		main.switch_panel(MainUI.MEMBER);
		main.member_panel.removeAll();
		main.member_panel.setLayout(new GridLayout(3,1,3,3));
		btn_panel = new JPanel();
		
		select = new JButton("조회");
		update = new JButton("수정");
		delete = new JButton("삭제");
		
		btn_panel.add(select);
		btn_panel.add(update);
		btn_panel.add(delete);
		
		main.member_panel.add(btn_panel);
		
		main.content_panel.add(main.member_panel);
		main.jf.setVisible(true);
		
		select.addActionListener(this);
		update.addActionListener(this);
		delete.addActionListener(this);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == select) {
			new MemberSelectUI(main);
		}else if(obj == update) {
			new MemberUpdateUI(main);
		}
		else if(obj == delete) {
			new MemberDeleteUI(main);
		}

	}
}
