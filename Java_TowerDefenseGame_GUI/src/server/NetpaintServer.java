package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingDeque;

import paintobject.PaintObject;
import client.NetpaintClient;
import model.Command;
//import model.DisconnectCommand;
import model.UndoLastCommand;
import model.UpdateCommand;
import model.UpdateFighterCommand;
import model.UpdateLifeCommand;
import model.UpdateMessageCommand;
import model.UpdatePlayerScoreCommand;

/**
 * Netpaint Server
 * 
 * <p>
 * This class is the server side of Netpaint. It is responsible for managing
 * connections to clients, sending and executing commands, and holds the list of
 * all PaintObjects on the shared canvas.
 * <P>
 * 
 * @author Gabriel Kishi
 * 
 */

public class NetpaintServer {
	private ServerSocket socket;
	private List<PaintObject> objects;

	private Map<String, Deque<Command<NetpaintServer>>> histories;
	private Map<String, ObjectInputStream> inputs;
	private Map<String, ObjectOutputStream> outputs;
	private int num;
	private int life;
	private int player1Score;
	private int player2Score;
	private String message;
	private int typeFighter;
	private int typeTower;
	private int player;
	private int x;
	private int y;
	private boolean n;
	private int map;
	

	/**
	 * ClientHandler
	 * 
	 * This class handles executing command from a single client. It manages the
	 * queue of pending commands and maintains a history of executed commands
	 * that can be undone.
	 */
	private class ClientHandler implements Runnable {

		private String clientId; // name of the client
		private Deque<Command<NetpaintServer>> history; // history of executed
														// commands
		private ObjectInputStream input; // input stream to read command from

		public ClientHandler(String id, Deque<Command<NetpaintServer>> history) {
			clientId = id;
			this.history = history;
			input = inputs.get(id);

			num++;
			System.out.println("New Client " + id + " connected");

			updateClients();
		}

		public void run() {
			while (true) {
				try {
					Object ob = input.readObject();
					if (ob instanceof Command<?>) {
						Command<NetpaintServer> command = (Command<NetpaintServer>) ob;
						command.execute(NetpaintServer.this);

						if (!(command instanceof UndoLastCommand))
							history.push(command);

					}
				} catch (Exception e) {
					// System.err.println("In Client Handler:");
					// e.printStackTrace();
					break;
				}
			}
		}
	}

	/**
	 * ClientAccepter
	 * 
	 * This class is responsible for listening for new clients and subsequently
	 * setting up a ClientHandler for the new client.
	 */
	private class ClientAccepter implements Runnable {
		public void run() {
			while (true) {
				try {
					Socket s = socket.accept(); // wait for a new client

					// grab the output and input streams for the new client
					ObjectOutputStream output = new ObjectOutputStream(
							s.getOutputStream());
					ObjectInputStream input = new ObjectInputStream(
							s.getInputStream());

					String name;
					do {
						// read the client's name
						name = (String) input.readObject();

						// if that name is already connected, reject
						if (outputs.containsKey(name))
							output.writeObject("reject");
					} while (outputs.containsKey(name));

					// tell the client their name is accepted
					output.writeObject("accept " + num);

					// add the output, input streams to the correct maps
					outputs.put(name, output);
					inputs.put(name, input);

					// create a command history queue for the new client
					histories.put(name,
							new LinkedBlockingDeque<Command<NetpaintServer>>());

					// start a new ClientHandler for this new client
					new Thread(new ClientHandler(name, histories.get(name)))
							.start();
				} catch (Exception e) {
					System.err.println("In Client Accepter:");
					e.printStackTrace();
					break;
				}
			}
		}
	}

	public NetpaintServer(int port) {
		try {
			life = 200;
			num = 0;
			player1Score = 0;
			player2Score = 0;
			message = "";
			map = 3;

			socket = new ServerSocket(port); // create a new server

			// setup hashmaps
			histories = new ConcurrentHashMap<String, Deque<Command<NetpaintServer>>>();
			inputs = new ConcurrentHashMap<String, ObjectInputStream>();
			outputs = new ConcurrentHashMap<String, ObjectOutputStream>();

			// create the list of PaintObjects
			objects = Collections
					.synchronizedList(new LinkedList<PaintObject>());

			System.out.println("Server started on port " + port);

			// begin accepting clients
			new Thread(new ClientAccepter()).start();
		} catch (Exception e) {
			System.err.println("Error creating server:");
			e.printStackTrace();
		}
	}

	/**
	 * This method undoes the last command of a client
	 * 
	 * @param clientName
	 *            name of the client whose command should be undone
	 */
	public void undoLast(String clientName) {
		Deque<Command<NetpaintServer>> commands = histories.get(clientName);
		if (commands.isEmpty())
			return;
		commands.pop().undo(NetpaintServer.this);
	}

	/**
	 * This method updates all connected clients with the current list of
	 * PaintObjects in the world
	 */
	public void updateClients() {
		Command<NetpaintClient> update = new UpdateCommand("server",
				objects.toArray(new PaintObject[objects.size()]));
		
		
		for (ObjectOutputStream out : outputs.values())
			try {
				out.writeObject(update);
			} catch (Exception e) {
				// System.err.println("Error updating clients");
				// e.printStackTrace();
				outputs.remove(out);
			}
	}
	
	
	public void updateLife(){
		Command<NetpaintClient> updateLife = new UpdateLifeCommand("server",life);	
		for (ObjectOutputStream out : outputs.values())
			try {
				out.writeObject(updateLife);
			} catch (Exception e) {
				// System.err.println("Error updating clients");
				// e.printStackTrace();
				outputs.remove(out);
			}
	}

	/**
	 * Adds a PaintObject to the canvas
	 * 
	 * @param object
	 *            a PaintObject to add to the canvas
	 */
	public void addObject(PaintObject object) {
		

		System.out.println("Adding new Object" + object.getClass().toString());
		System.out.println(objects.size());
		objects.add(object);
		updateClients();
	}

	/**
	 * Removes a PaintObject from the canvas
	 * 
	 * @param object
	 *            a PaintObject to be removed
	 */
	public void removeObject(PaintObject object) {
		objects.remove(object);
		updateClients();
	}

	public List<PaintObject> getObjects() {
		return objects;
	}

	public void reduceLife(int reduceLife) {
		life -= reduceLife;
		updateLife();
	}

	public int getlife() {

		return life;
	}

	/**
	 * Disconnects a connected user
	 * 
	 * @param source
	 *            user to disconnect
	 */
	public void disconnect(String source) {
		System.out.printf("Client \'%s\' disconnecting\n", source);
		try {
			inputs.remove(source).close();
			outputs.remove(source).close();
			histories.remove(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new NetpaintServer(9001);
	}

	public void addPlayer1Score(int i) {
		player1Score += i;
		updatePlayerScore();
	}

	private void updatePlayerScore() {
		Command<NetpaintClient> updatePlayerScore = new UpdatePlayerScoreCommand("server",player1Score,player2Score);	
		for (ObjectOutputStream out : outputs.values())
			try {
				out.writeObject(updatePlayerScore);
			} catch (Exception e) {
				// System.err.println("Error updating clients");
				// e.printStackTrace();
				outputs.remove(out);
			}
		
	}

	public void addPlayer2Score(int i) {
		player2Score += i;
		updatePlayerScore();
	}

	public void appendMessage(String s) {
		message = s;
		updateMessage();
	}

	private void updateMessage() {
		Command<NetpaintClient> updateMessage = new UpdateMessageCommand("server",message);	
		for (ObjectOutputStream out : outputs.values())
			try {
				out.writeObject(updateMessage);
			} catch (Exception e) {
				// System.err.println("Error updating clients");
				// e.printStackTrace();
				outputs.remove(out);
		
			}
	}

	public void addFighter(int type, int player) {
		
		this.player = player;
		this.typeFighter = type;
		
		updateFighter();
		
	}

	private void updateFighter() {
		Command<NetpaintClient> updateFighter = new UpdateFighterCommand("server",typeFighter,player);	
		for (ObjectOutputStream out : outputs.values())
			try {
				out.writeObject(updateFighter);
			} catch (Exception e) {
				// System.err.println("Error updating clients");
				// e.printStackTrace();
				outputs.remove(out);
			}
	}
}
