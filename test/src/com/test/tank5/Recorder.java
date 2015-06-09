package com.test.tank5;

import java.io.*;
import java.util.Vector;

import com.test.tank5.object.direction;

class Recorder{//��¼�࣬ͬʱҲ���Ա�����ҵ�����
	private static int heroLife = 3;//���Լ�����������
	private static int enemyNum = 20;//��¼ÿ���ж��ٵ���
	
	private static int allDefeatedEnemyNum = 0;//�����������
	private static String systemPath = System.getProperty("user.dir");
	private static String recordPath = systemPath +"/TankWarRecord.txt";
	
	private static Vector<Enemy> enemies = new Vector<Enemy>(); 
	
	
	public static Vector<Enemy> getEnemies() {
		return enemies;
	}

	public static void setEnemies(Vector<Enemy> enemies) {
		Recorder.enemies = enemies;
	}

	public static void reset() {
		heroLife = 3;
		enemyNum = 20;
		allDefeatedEnemyNum = 0;
	}
	
	public static void readCurrentRecords() {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(recordPath);
			br = new BufferedReader(fr);
			
			//�ȶ�ȡ��һ�� ����Һ͵�������ֵ
			String tmpStr = br.readLine();	
			String []tmpRecord = tmpStr.trim().split(" ");
			if (Integer.parseInt(tmpRecord[0] ) > 0 &&/*����ϴμ�¼�Ѿ�ʤ������ʧ�ܾ�û�в�Ҫ�ָ�*/
					Integer.parseInt(tmpRecord[1] ) > 0 ) {
				heroLife = Integer.parseInt(tmpRecord[0]);
				enemyNum = Integer.parseInt(tmpRecord[1]);
				allDefeatedEnemyNum = Integer.parseInt(tmpRecord[2]);
				
				//���Ŷ�ȡ����̹��λ��
				enemies.clear();
				while(null !=(tmpStr = br.readLine()) && !"".equals(tmpStr)){
					tmpRecord = tmpStr.trim().split(" ");
					Enemy enemy = new Enemy( Integer.parseInt(tmpRecord[0]), Integer.parseInt(tmpRecord[1]) );
					enemy.setDirect((direction.valueOf(tmpRecord[2])));
					enemies.add(enemy);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void keepCurrentRecords() {
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(recordPath);
			bw = new BufferedWriter(fw);
			//��¼��ң���������ֵ
			bw.write(heroLife +" "+enemyNum +" "+allDefeatedEnemyNum+"\r\n");
			
			//��¼����̹��λ��
			for(Enemy enemy:enemies){
				bw.write(enemy.getX() +" "+enemy.getY() +" "+enemy.getDirect()+"\r\n");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void reduceEnemyNum() {
		if (enemyNum > 0) {
			enemyNum --;
			allDefeatedEnemyNum ++;
		}
	}
	public static void reduceHeroLife() {
		if (heroLife > 0) {
			heroLife --;
		}
	}
	
	public static void setAllDefeatedEnemyNum(int allDefeatedEnemyNum) {
		Recorder.allDefeatedEnemyNum = allDefeatedEnemyNum;
	}
	public static int getAllDefeatedEnemyNum() {
		return allDefeatedEnemyNum;
	}
	public static int getEnemyNum() {
		return enemyNum;
	}
	public static void setEnemyNum(int enemyNum) {
		Recorder.enemyNum = enemyNum;
	}
	public static int getHeroLife() {
		return heroLife;
	}
	public static void setHeroLife(int heroLife) {
		Recorder.heroLife = heroLife;
	}
}
