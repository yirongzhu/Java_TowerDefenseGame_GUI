package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.tower.core.Win;
import com.tower.main.Game;

import model.Command;
import model.UndoLastCommand;
import paintobject.PaintObject;
import view.ChatGUI;
import view.SpawningGUI;

public class NetpaintClient extends JFrame {
	// Netpaint Client
	public NetpaintClient() throws IOException, ClassNotFoundException {
		String host;
		String port;

		try {
			System.out.println("Reached a server");
			System.out.println("Client started");

			reject = true;
			int newClient = 0;
//			host = JOptionPane.showInputDialog("Host address:");
//			port = JOptionPane.showInputDialog("Host port:");
			
			host = "localhost";
			port = "9001";
			s = new Socket(host, Integer.parseInt(port));

			// loop to get new client
			while (reject) {	
				client = JOptionPane.showInputDialog("User name:");
				if (host == null || port == null || client == null)
					return;
				
				
				if (newClient == 0) {
					outstream = new ObjectOutputStream(s.getOutputStream());
					instream = new ObjectInputStream(s.getInputStream());
				}
				
				// write out client
				outstream.writeObject(client);
				Object ob = instream.readObject();
				response =  (String) ob;
//				System.out.println(response);
				
				
				if (response.equals("accept 1") || response.equals("accept 0")) {	
					
					chatGUI = new ChatGUI(client, outstream, response);
					chatGUI.setVisible(true);
					
					spawningGUI = new SpawningGUI(client, outstream, response);
					spawningGUI.setVisible(true);
					
					g = new Game(client, outstream,response);
					g.start();	
					
					  
					// start a thread for handling server events
					new Thread(new ServerHandler()).start();	
					reject = false;
				} 
				else if (response.equals("")){
					JOptionPane.showMessageDialog(null,"Please enter a name");
					newClient = 1;
				}
				
				else if (response.equals("reject")){
					JOptionPane.showMessageDialog(null,"Name already exist");
					newClient = 1;
				}
				else {
					newClient = 1;
					JOptionPane.showMessageDialog(null,"Reach maximum players");
					reject = false;
				}
			
		}

		} catch (Exception e) {
			System.out.println("ex");
		}
	}
	// update
	public void update(List<PaintObject> asList) {
		PaintObject[] objects = new PaintObject[asList.size()];
		for (int i = 0; i < asList.size(); i++) {
			objects[i] = asList.get(i);
		}
		g.update(asList);
		repaint();
	}
	
	// update life
	public void updateLife(int l) {
		life = l;
		g.setLife(l);
		
		if(life < 0){
			g.stop();	
		}
	}
	
	public void updateScore(int s1,int s2) {
		player1Score = s1;
		player2Score = s2;		
		g.updateScore(s1, s2);

	}
	
	
	// ServerHandler
	private class ServerHandler implements Runnable {

		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			try {
				while (true) {
					// read a command from server and execute it
					Command<NetpaintClient> c = (Command<NetpaintClient>) instream
							.readObject();
					c.execute(NetpaintClient.this);
					
				}
			} catch (SocketException e) {
				return; // "gracefully" terminate after disconnect
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static ObjectInputStream instream;
	private static ObjectOutputStream outstream;
	private String client;
//	private DrawingPanel drawingPanel;
	private Socket s;
	private boolean reject;
	private Game g;
	private String response;
	private int life;
	private int player1Score;
	private int player2Score;
	private ChatGUI chatGUI;
	private SpawningGUI spawningGUI;
	private int map;
	
	// main
	public static void main(String[] args) throws UnknownHostException,
			IOException, ClassNotFoundException {
		new NetpaintClient();

	}
	public void appendMessage(String message) {
		chatGUI.append(message);
		message = null;
	}
	public void addFighter(int typeFighter, int player) {
		
		g.addFighter(typeFighter,player);

	}


}
