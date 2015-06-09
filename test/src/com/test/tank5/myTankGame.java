/*verison  5.0
 * �� j �� �����ӵ�
 * ���е��ˣ��ӵ��͵�����ʧ
 * ��ֹ̹���ص���
 * I/O����ͣ����
 *�ֹأ�//��һ����ʼ��壻
 *��ʾ��¼������ͳ������ֵ��
 *���win lose ��壻
 *���Իָ��ϴ���Ϸ��¼
 * */

package com.test.tank5;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.naming.InitialContext;
import javax.swing.*;

public class myTankGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TankGame tankGame = new TankGame();
	}

}

class TankGame {
	public TankGame() {
		Thread thread = new Thread(new myFrame());
		thread.start();
	}
}

class myFrame extends JFrame implements ActionListener, Runnable {
	private WinOrLose winOrLose = null;
	private mypanel mp = null;//ս�����
	private Thread mythread = null;
	private StartPanel startPanel = null;

	//�˵�
	private JMenu jMenu = null;
	private JMenuBar jMenuBar = null;
	private JMenuItem jMenuItemNew = null;
	private JMenuItem jMenuItemEnd = null;
	private JMenuItem jMenuItemExit = null;
	private JMenuItem jMenuItemContinueBefore = null;


	
	public myFrame() {
		// mp = new mypanel();
		// this.addKeyListener(mp);// �¼�������ע��
		// this.add(mp);

		this.InitialMenu();

		startPanel = new StartPanel();
		mythread = new Thread(startPanel);
		this.add(startPanel);
		this.setSize(320, 450);
		this.setVisible(true);
		this.setLocation(512, 150);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// mythread = new Thread(mp);
		mythread.start();
	}

	//��ʼ���˵�
	private void InitialMenu(){
		jMenuItemNew = new JMenuItem("��ʼ����Ϸ��N��");
		jMenuItemNew.setMnemonic('N');

		jMenuItemNew.addActionListener(this);
		jMenuItemNew.setActionCommand("new game");

		
		jMenuItemContinueBefore = new JMenuItem("�����ϴ���Ϸ��C��");
		jMenuItemContinueBefore.setMnemonic('C');

		jMenuItemContinueBefore.addActionListener(this);
		jMenuItemContinueBefore.setActionCommand("continue game");
		
		jMenuItemEnd = new JMenuItem("����������Ϸ��E��");
		jMenuItemEnd.setMnemonic('E');

		jMenuItemEnd.addActionListener(this);
		jMenuItemEnd.setActionCommand("end game");

		jMenuItemExit = new JMenuItem("�����˳���Q��");
		jMenuItemExit.setMnemonic('Q');

		jMenuItemExit.addActionListener(this);
		jMenuItemExit.setActionCommand("exit game");
		
		jMenu = new JMenu("��Ϸ (G)");
		jMenu.setMnemonic('G');

		jMenu.add(jMenuItemNew);
		jMenu.add(jMenuItemContinueBefore);
		jMenu.add(jMenuItemEnd);
		jMenu.add(jMenuItemExit);

		jMenuBar = new JMenuBar();
		jMenuBar.add(jMenu);
		this.setJMenuBar(jMenuBar);
	}
	
	//ɾ�������
	public void removeAllPanel() {
		if (null != mp) {
			this.remove(mp);
		}
		if (null != startPanel) {
			this.remove(startPanel);
		}
		if (null != winOrLose) {
			this.remove(winOrLose);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("new game")) {

			// if (null != mp) {
			// this.remove(mp);
			// }

			//ɾ�������
			this.removeAllPanel();

			Recorder.reset();
			
			mp = new mypanel();
			this.addKeyListener(mp);// �¼�������ע��

			// ��ɾ���ɵ���壻
			// if (null != startPanel) {
			// this.remove(startPanel);
			// }
			// if (null != winOrLose) {
			// this.remove(winOrLose);
			// }

			this.add(mp);

			// ����ս��ǰ�����Լ��͵�������ֵ��������СΪ6
//			Recorder.setEnemyNum(6);
//			Recorder.setHeroLife(3);
//			Recorder.setAllDefeatedEnemyNum(0);

			
			
			mythread = new Thread(mp);
			mythread.start();
			this.setVisible(true);
			this.setSize(500, 520);
		} 
		else if (e.getActionCommand().equals("end game")) {

			// if (null != winOrLose) {
			// this.remove(winOrLose);
			// }

			// this.remove(mp);

			// ��ɨս������ԭ�����Լ��͵�������ֵ������ʼ����winorlose�������
//			Recorder.setEnemyNum(6);
//			Recorder.setHeroLife(3);
//			Recorder.setAllDefeatedEnemyNum(0);
			Recorder.reset();

			this.removeAllPanel();

			this.add(startPanel);
			this.setVisible(true);
			this.setSize(320, 450);
		}
		else if (e.getActionCommand().equals("exit game")) {
			//�˳�ǰ������Ҵ�ʱ��Ϸ��¼
			if (null != mp) {
				Recorder.setEnemies(mp.enemis);
				Recorder.keepCurrentRecords();
			}
			
			//�˳�
			System.exit(0);
		}
		else if (e.getActionCommand().equals("continue game")) {
			
			Recorder.readCurrentRecords();
			this.removeAllPanel();

			mp = new mypanel("continue");
			this.addKeyListener(mp);// �¼�������ע��

			this.add(mp);

			mythread = new Thread(mp);
			mythread.start();
			this.setVisible(true);
			this.setSize(500, 520);
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			// System.out.println("run");
			if (0 == Recorder.getEnemyNum()) {
				this.removeAllPanel();

				winOrLose = new Win();
				// this.remove(mp);
				this.add(winOrLose);
				this.setVisible(true);
				this.setSize(320, 450);
				// System.out.println("0 == Recorder.getEnemyNum()");
				mythread = new Thread(winOrLose);
				mythread.start();
				// break;
			}

			if (0 == Recorder.getHeroLife()) {
				this.removeAllPanel();
				winOrLose = new Lose();
				// this.remove(mp);
				this.add(winOrLose);
				this.setVisible(true);
				this.setSize(320, 450);
				// System.out.println("0 == Recorder.getHeroLife()");
				mythread = new Thread(winOrLose);
				mythread.start();
				// break;
			}
		}

	}
}
