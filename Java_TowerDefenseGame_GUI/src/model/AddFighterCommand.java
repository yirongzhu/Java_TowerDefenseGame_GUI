package model;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import model.Command;
import paintobject.PaintObject;
import server.NetpaintServer;

public class AddFighterCommand extends Command<NetpaintServer>{

	int type;
	int player;
	// construct
	public AddFighterCommand(String source, int type,int player) {
		super(source);
		this.type = type;
		this.player = player;
	}

	@Override
	public void execute(NetpaintServer executeOn) {
		executeOn.addFighter(type,player);
	}
}