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

public class UpdatePlayerScoreCommand extends Command<NetpaintClient> {
	private static final long serialVersionUID = -3341043551061432227L;

	int player1Score;
	int player2Score;

	public UpdatePlayerScoreCommand(String source,int player1Score, int player2Score) {
		super(source);

		this.player1Score = player1Score;
		this.player2Score = player2Score;
	
		
	}

	public void execute(NetpaintClient executeOn) {
		executeOn.updateScore(player1Score, player2Score);
	}
	
	// updates cannot be undone
}