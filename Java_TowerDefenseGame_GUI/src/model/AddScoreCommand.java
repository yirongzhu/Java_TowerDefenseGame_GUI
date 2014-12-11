package model;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import model.Command;
import paintobject.PaintObject;
import server.NetpaintServer;

public class AddScoreCommand extends Command<NetpaintServer>{

	int i;
	int player;
	
	public AddScoreCommand(String source, int i,int player) {
		super(source);
		this.i = i;
		this.player = player;
		
	}



	@Override
	public void execute(NetpaintServer executeOn) {
		if(player == 1){
			executeOn.addPlayer1Score(i);
		}
		if(player == 2){
			executeOn.addPlayer2Score(i);
		}
	}
}