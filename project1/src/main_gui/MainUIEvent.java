package main_gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;



public class MainUIEvent implements ActionListener{
	MainUI main;
	
	public MainUIEvent(MainUI main) {
		this.main = main;
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String name = e.getActionCommand().trim();
			
		if(obj == main.btnlist.get(3)) {	//�˻�
				new SearchUI(main);	
		}else if(obj==main.btnlist.get(1)) {	//�Խ��� ��ư ������
				new CategoryUI(main);
		}else if(obj==main.btnlist.get(0)) {	//ä��
			new ChatUI(main.member);
		}else if(obj==main.logout) {	//�α׾ƿ�
			if(JOptionPane.showConfirmDialog(null, "�α׾ƿ� �Ͻðڽ��ϱ�?")==JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(null, "�α׾ƿ� �Ǿ����ϴ�.");
				main.jf.dispose();
				new LoginUI();
			}
		}else if(obj == main.btnlist.get(5)) {	//����
			int result = JOptionPane.showConfirmDialog(null,
					Commons.getMsg("������ �����Ͻðڽ��ϱ�?"));
			if(result ==0) {
				main.system.close();
				System.exit(0);
			}
		}else if(obj == main.btnlist.get(4)) {
			if(main.btnlist.get(4).getText().equals("ȸ������")) {
				if(main.member.getId().equals("admin")){
					new MemberManagementUI(main);				
				}else {
					JOptionPane.showMessageDialog(null,
							Commons.getMsg("�����ڸ� ���� �����մϴ�"));
				}
			}else {
				new MyUI(main);
			}
				
		}else if(obj==main.btnlist.get(2)) {	//���۰���
			new MyPageUI(main);
		}
	}
	
}














