package com.test.gui;

import javax.swing.*;

import java.awt.event.*;
import java.io.*;

public class NotePad {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyNotePad myNotePad = new MyNotePad();
	}

}

class MyNotePad extends JFrame implements ActionListener {
	JTextArea jTextArea = null;
	JMenuBar jMenuBar = null;
	JMenu jMenu = null;
	JMenuItem jMenuItem1 = null;
	JMenuItem jMenuItem2 = null;
	
	public MyNotePad(){
		
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jTextArea = new JTextArea();
		this.add(jTextArea);
		
		jMenuBar = new JMenuBar();
		//this.add(jMenuBar);
		this.setJMenuBar(jMenuBar);
		
		jMenu = new JMenu("文件");
		jMenuBar.add(jMenu);
		
		jMenuItem1 = new JMenuItem("打开");
		jMenuItem1.setMnemonic('f');
		jMenuItem1.addActionListener(this);
		jMenuItem1.setActionCommand("open");
		
		jMenuItem2 = new JMenuItem("保存");
		jMenuItem2.addActionListener(this);
		jMenuItem2.setActionCommand("save");
		
		jMenu.add(jMenuItem1);
		jMenu.add(jMenuItem2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("open")) {
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("打开...");
			jfc.showOpenDialog(null);
			jfc.setVisible(true);
			
			String path = jfc.getSelectedFile().getAbsolutePath();
			
			FileReader fr = null;
			BufferedReader br = null;
			
			try {
				fr = new FileReader(path);
				br = new BufferedReader(fr);
				
				String str ="";
				String allStrs = "";
				
				while(null != (str = br.readLine())){
					allStrs +=str +"\r\n";
				}
				this.jTextArea.setText(allStrs);
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			finally{
				
				try {
					br.close();
					fr.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if (e.getActionCommand().equals("save")) {
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("保存...");
			jfc.showSaveDialog(null);
			jfc.setVisible(true);
			
			String path = jfc.getSelectedFile().getAbsolutePath();
			
			FileWriter fw = null;
			BufferedWriter bw = null;
			
			try {
				fw = new FileWriter(path);
				bw = new BufferedWriter(fw);
				
				String str ="";
				
				//要优化
				bw.write(this.jTextArea.getText());
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			finally{
				
				try {
					bw.close();
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}