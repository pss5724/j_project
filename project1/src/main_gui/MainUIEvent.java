package main_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import main_system.HororogMgmSystem;

public class MainUIEvent implements ActionListener{
	MainUI main;
	
	public MainUIEvent(MainUI main) {
		this.main = main;
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String name = e.getActionCommand().trim();
//		
//		if(obj == main.btnlist.get(0)) {	//���
//			if(HororogMgmSystem.LOGIN_RESULT){
//				new InsertUI(main);				
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("�α����� �ʿ��� ����Դϴ�"));
//			}
//		}else if(obj == main.menulist.get(1)) {	//��ȸ
//			if(HororogMgmSystem.LOGIN_RESULT){
//				new SelectUI(main);				
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("�α����� �ʿ��� ����Դϴ�"));
//			}
//		}else 
			if(obj == main.btnlist.get(3)) {	//�˻�
				new SearchUI(main);	
			}
//			if(true){
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("�α����� �ʿ��� ����Դϴ�"));
//			}
//		}else if(obj == main.menulist.get(3)) {  //����
//			if(HororogMgmSystem.LOGIN_RESULT){
//				new UpdateUI(main);				
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("�α����� �ʿ��� ����Դϴ�"));
//			}
//		}else if(obj == main.menulist.get(4)) {
//			if(HororogMgmSystem.LOGIN_RESULT){
//				new DeleteUI(main);				
//			}else {
//				JOptionPane.showMessageDialog(null,
//						Commons.getMsg("�α����� �ʿ��� ����Դϴ�"));
//			}
//		}else if(obj == main.menulist.get(5)) {	//����
//			int result = JOptionPane.showConfirmDialog(null,
//					Commons.getMsg("������ �����Ͻðڽ��ϱ�?"));
//			if(result ==0) {
//				main.system.close();
//				System.exit(0);
//			}
//		}else if(name.equals("�α���")) {
//			new LoginUI(main);
//		}else if(name.equals("�α׾ƿ�")) {
//			int result = JOptionPane.showConfirmDialog(null,
//					Commons.getMsg("������ �α׾ƿ� �Ͻðڽ��ϱ�?"));
//			if(result==0) {
//				new LoginUI(main);
//				main.btn_login.setText("     �α���      ");
//				HororogMgmSystem.LOGIN_RESULT = false;
//			}
//		}else if(obj == main.btn_join) {
//			new JoinUI(main);
//		}
//		
	}
	
}














