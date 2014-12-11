package model;

import java.util.Arrays;

import paintobject.PaintObject;
import client.NetpaintClient;

/**
 * Update Command
 * 
 * <p> This command is sent from the server to a client and updates
 * the client with the current set of PaintObjects to display <p>
 * 
 * This command is an example of a fully implemented command.
 * 
 * @author Gabriel Kishi
 *
 */

public class UpdateMessageCommand extends Command<NetpaintClient> {
	private static final long serialVersionUID = -3341043551061432227L;
	
	String message;

	
	public UpdateMessageCommand(String source, String message) {
		super(source);

		this.message = message;

	
	}

	public void execute(NetpaintClient executeOn) {
		if(message != null){
			executeOn.appendMessage(message);
		}
	
	}
	
	// updates cannot be undone
}