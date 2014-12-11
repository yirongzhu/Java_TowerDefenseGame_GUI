package com.tower.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public abstract class SoundUtil {

	private static final Map<String, byte[]> buf = new ConcurrentHashMap<String, byte[]>(); 
	
	private static final ExecutorService executorService = Executors.newFixedThreadPool(50);
	private static boolean play;
	private static PlayerTask playerTask;
	public static void load( String name ) throws IOException {
		load(name,new File(name).toURI().toURL());
	}
	
	public static void load( String name, URL url ) throws IOException {
		ByteArrayOutputStream outputStream = null;
		InputStream inputStream = null;
		try {
			outputStream = new ByteArrayOutputStream();
			inputStream = url.openStream();
			byte[] b = new byte[512];
			int len;
			while( (len=inputStream.read(b))!=-1 ) {
				outputStream.write(b,0,len);
			}
			buf.put(name, outputStream.toByteArray());
		} finally {
			if( outputStream!=null ) {
				outputStream.close();
			}
			if( inputStream!=null ) {
				inputStream.close();
			}
		}
	}
	
	public static Player play( String name ) throws JavaLayerException {
		Player player = new Player(new ByteArrayInputStream(buf.get(name)));
		playerTask=new PlayerTask(player);
		executorService.execute(playerTask);
		return player;
	}
	
	public static void playBackgraund( String name ) throws IOException, JavaLayerException {
		playBackgraund(name,new File(name).toURI().toURL());
	}
	
	public static Player playBackgraund( String name, URL url ) throws JavaLayerException, IOException {
		Player player = new Player(url.openStream());
		playerTask=new PlayerTask(player);
		executorService.execute(playerTask);
		return player;
	}
	public static void  stop(){
		
			Thread	currentThread = new Thread(playerTask);
			currentThread.interrupt();

	}
	
	private static class PlayerTask implements Runnable {

		private Player player;		
		public PlayerTask(Player player) {
			play=true;
			this.player = player;
		}
		

		@Override
		public void run() {
			while(true){
				if(play){
					try {
						player.play();
					} catch (JavaLayerException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}

}
