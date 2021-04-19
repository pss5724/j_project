package main_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main_dao.MemberDAO; 
import main_system.MainSystem;
import main_vo.MemberVO;

public class JoinUIEvent extends WindowAdapter implements ActionListener {
	// Field
	JoinUI ui;
	MemberDAO mdao = new MemberDAO();

	// 생성자
	public JoinUIEvent() {
	}

	public JoinUIEvent(JoinUI ui) {
		this.ui = ui;

	}

	public void windowClosing(WindowEvent e) {
		System.out.println("회원가입 프로그램 종료");
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == ui.join) {
			if (location_check().equals("")) {
				JOptionPane.showMessageDialog(null, Commons.getMsg("지역을 선택하세요."));
			} else {

				if (form_check()) {
					ArrayList<JTextField> jlist = new ArrayList<JTextField>();
					for (Object tf : ui.list) {
						JTextField jtf = (JTextField) tf;
						jlist.add(jtf);
					}

					MemberVO member = new MemberVO();
					member.setId(jlist.get(0).getText());
					member.setPass(jlist.get(1).getText());
					member.setName(jlist.get(2).getText());
					member.setHp1(jlist.get(3).getText());
					member.setHp2(jlist.get(4).getText());
					member.setHp3(jlist.get(5).getText());
					member.setLocation(location_check());

//					boolean result = main.system.getMemberList().add(member);
					boolean result = ui.system.join(member);

					if (result) {
						JOptionPane.showMessageDialog(null, Commons.getMsg("회원가입 성공"));

						ui.f.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, Commons.getMsg("회원가입 실패"));
					}

				}
			}
		} else if (obj == ui.reset) {
			for (Object obj2 : ui.list) {
				JTextField tf = (JTextField) obj2;
				tf.setText("");
			}
		} else if (obj == ui.check) {
				ArrayList<JTextField> jlist = new ArrayList<JTextField>();
				for (Object tf : ui.list) {
					JTextField jtf = (JTextField) tf;
					jlist.add(jtf);
				}

				String str = new String(jlist.get(0).getText());
				
				boolean result = ui.system.check(str);

				if (result) {
					JOptionPane.showMessageDialog(null, Commons.getMsg("아이디 사용 불가능"));
				} else {
					JOptionPane.showMessageDialog(null, Commons.getMsg("아이디 사용 가능 "));
				}
			}
		

	}

	/** 폼 체크 **/
	public boolean form_check() {
		boolean result = false;

		for (int i = 0; i < ui.list.size(); i++) {
			JTextField tf = (JTextField) ui.list.get(i);

			if (tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, Commons.getMsg(ui.namelist2[i] + "를 입력해주세요"));
				tf.requestFocus();
				i = ui.list.size();
			} else if (i == ui.list.size() - 1) {

				result = true;
			}

		}

		return result;
	}

	public String location_check() {
		String result = "";

		if (ui.rb1.isSelected() == true) {
			result = "서울";
		} else if (ui.rb2.isSelected() == true) {
			result = "경기";
		} else if (ui.rb3.isSelected() == true) {
			result = "충청";
		} else if (ui.rb4.isSelected() == true) {
			result = "전라";
		} else if (ui.rb5.isSelected() == true) {
			result = "경상";
		} else if (ui.rb6.isSelected() == true) {
			result = "강원";
		} else if (ui.rb7.isSelected() == true) {
			result = "제주";
		}

		return result;
	}

}
