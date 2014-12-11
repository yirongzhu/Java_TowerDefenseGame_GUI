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

public class UpdateFighterCommand extends Command<NetpaintClient> {
	private static final long serialVersionUID = -3341043551061432227L;
	
	int player;
	int typeFighter;
	
	
	public UpdateFighterCommand(String source, int typeFighter, int player) {
		super(source);
		this.typeFighter = typeFighter;
		this.player = player;
	
	}

	public void execute(NetpaintClient executeOn) {
		executeOn.addFighter(typeFighter,player);

	}
	
	// updates cannot be undone
}