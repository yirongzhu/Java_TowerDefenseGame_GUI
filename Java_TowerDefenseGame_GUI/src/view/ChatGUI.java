package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import model.AddObjectCommand;
import model.AppendMessageCommand;
import paintobject.DrawString;
import paintobject.PaintObject;

import com.tower.core.MapChoise;
import com.tower.core.Stage;
import com.tower.core.Welcome;
import com.tower.elements.MiniMap;

public class ChatGUI extends JFrame {

	private JPanel chatPanel;
	private MiniMap miniMap;
	private Stage stage;
	private MapChoise mapChoise;
	private Welcome welcome;

	private String clientName;
	private int player;
	private List<PaintObject> objects;

	private JTextField inputfield;
	private JTextArea chattingMessage;
	private JScrollPane sp;

	private String message;
	private ObjectOutputStream output;

	public ChatGUI(String client, ObjectOutputStream outstream, String response) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(640, 1000);
		setLayout(null);
		userLogin();

		if (response.equals("accept 0"))
			player = 1;
		else if (response.equals("accept 1"))
			player = 2;
		else
			player = 0;

		output = outstream;
		clientName = client;

		setTitle("Chat (" + clientName + ")");
		setSize(200, 480);

		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(660, 100);

		if (player == 2)
			setLocation(1140, 100);

	}

	// user login
	private void userLogin() {

		chatPanel = new JPanel();
		chatPanel.setLayout(null);
		chatPanel.setSize(200, 480);

		chattingMessage = new JTextArea();
		chattingMessage.setSize(100, 160);
		chattingMessage.setLocation(20, 55);
		chattingMessage.setEnabled(false);
		chattingMessage.setLineWrap(true);

		sp = new JScrollPane(chattingMessage);
		sp.setSize(200, 420);
		sp.setLocation(0, 0);

		inputfield = new JTextField("");
		inputfield.setSize(200, 20);
		inputfield.setLocation(0, 430);
		inputfield.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				message = inputfield.getText();

					if (player != 0) {
						try {
							output.writeObject(new AppendMessageCommand(
									clientName, clientName + ": " + message));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						inputfield.setText("");
					}
			}
		});
		chatPanel.add(sp);
		chatPanel.add(inputfield);
		this.add(chatPanel);

	}

	public void append(String message1) {
		this.message = message1;
		chattingMessage.append(message + "\n");
		message = null;
		
		sp.getVerticalScrollBar().setValue(
				sp.getVerticalScrollBar().getMaximum());

	}

}
