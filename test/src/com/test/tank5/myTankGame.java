/*verison  5.0
 * 按 j 键 发射子弹
 * 打中敌人，子弹和敌人消失
 * 防止坦克重叠；
 * I/O，暂停功能
 *分关；//做一个开始面板；
 *显示记录即可以统计生命值；
 *添加win lose 面板；
 *可以恢复上次游戏记录
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
	private mypanel mp = null;//战场面板
	private Thread mythread = null;
	private StartPanel startPanel = null;

	//菜单
	private JMenu jMenu = null;
	private JMenuBar jMenuBar = null;
	private JMenuItem jMenuItemNew = null;
	private JMenuItem jMenuItemEnd = null;
	private JMenuItem jMenuItemExit = null;
	private JMenuItem jMenuItemContinueBefore = null;


	
	public myFrame() {
		// mp = new mypanel();
		// this.addKeyListener(mp);// 事件监听，注册
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

	//初始化菜单
	private void InitialMenu(){
		jMenuItemNew = new JMenuItem("开始新游戏（N）");
		jMenuItemNew.setMnemonic('N');

		jMenuItemNew.addActionListener(this);
		jMenuItemNew.setActionCommand("new game");

		
		jMenuItemContinueBefore = new JMenuItem("继续上次游戏（C）");
		jMenuItemContinueBefore.setMnemonic('C');

		jMenuItemContinueBefore.addActionListener(this);
		jMenuItemContinueBefore.setActionCommand("continue game");
		
		jMenuItemEnd = new JMenuItem("结束本次游戏（E）");
		jMenuItemEnd.setMnemonic('E');

		jMenuItemEnd.addActionListener(this);
		jMenuItemEnd.setActionCommand("end game");

		jMenuItemExit = new JMenuItem("存盘退出（Q）");
		jMenuItemExit.setMnemonic('Q');

		jMenuItemExit.addActionListener(this);
		jMenuItemExit.setActionCommand("exit game");
		
		jMenu = new JMenu("游戏 (G)");
		jMenu.setMnemonic('G');

		jMenu.add(jMenuItemNew);
		jMenu.add(jMenuItemContinueBefore);
		jMenu.add(jMenuItemEnd);
		jMenu.add(jMenuItemExit);

		jMenuBar = new JMenuBar();
		jMenuBar.add(jMenu);
		this.setJMenuBar(jMenuBar);
	}
	
	//删除旧面板
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

			//删除旧面板
			this.removeAllPanel();

			Recorder.reset();
			
			mp = new mypanel();
			this.addKeyListener(mp);// 事件监听，注册

			// 先删除旧的面板；
			// if (null != startPanel) {
			// this.remove(startPanel);
			// }
			// if (null != winOrLose) {
			// this.remove(winOrLose);
			// }

			this.add(mp);

			// 启动战场前设置自己和敌人生命值，敌人最小为6
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

			// 打扫战场，还原设置自己和敌人生命值，否则开始面板和winorlose交替出现
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
			//退出前保存玩家此时游戏记录
			if (null != mp) {
				Recorder.setEnemies(mp.enemis);
				Recorder.keepCurrentRecords();
			}
			
			//退出
			System.exit(0);
		}
		else if (e.getActionCommand().equals("continue game")) {
			
			Recorder.readCurrentRecords();
			this.removeAllPanel();

			mp = new mypanel("continue");
			this.addKeyListener(mp);// 事件监听，注册

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
