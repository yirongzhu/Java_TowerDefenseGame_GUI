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

public class UpdateCommand extends Command<NetpaintClient> {
	private static final long serialVersionUID = -3341043551061432227L;
	
	PaintObject[] objects;
	
	public UpdateCommand(String source, PaintObject[] objects) {
		super(source);
		this.objects = objects;
	}

	public void execute(NetpaintClient executeOn) {
		executeOn.update(Arrays.asList(objects));
		
	}
	
	// updates cannot be undone
}
