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

public class UpdateLifeCommand extends Command<NetpaintClient> {
	private static final long serialVersionUID = -3341043551061432227L;

	int life;


	
	public UpdateLifeCommand(String source, int life) {
		super(source);

		this.life = life;		
	}

	public void execute(NetpaintClient executeOn) {
		executeOn.updateLife(life);
	}
	
	// updates cannot be undone
}