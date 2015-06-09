package com.test.tank6;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

//播放声音的类
class PlaySounds extends Thread {

	// public static void main(String[] argr) {
	// PlaySounds ps = new PlaySounds();
	// }

	private String filename = null;

	public PlaySounds(String wavfile) {

		filename = System.getProperty("user.dir")
				+ "/src/com/test/tank6/data/sound/" + wavfile;
		// System.out.println(filename);
	}

	public void myrun() {
		File soundFile = new File(filename);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		auline.start();
		int nBytesRead = 0;
		// 这是缓冲
		byte[] abData = new byte[512];
		try {

			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}

		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}
	}

	public void run() {
		while (true) {
			this.myrun();
		}
	}
}

class MyplaySound extends PlaySounds {
	public MyplaySound(String wavfile) {
		super(wavfile);
	}

	public void run() {
		this.myrun();
	}
}