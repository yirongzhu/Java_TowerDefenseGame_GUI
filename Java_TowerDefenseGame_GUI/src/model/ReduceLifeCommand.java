package model;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import model.Command;
import paintobject.PaintObject;
import server.NetpaintServer;

public class ReduceLifeCommand extends Command<NetpaintServer>{

	int i;
	// construct
	public ReduceLifeCommand(String source, int i) {
		super(source);
		this.i = i;
	}

	@Override
	public void execute(NetpaintServer executeOn) {
		executeOn.reduceLife(i);

		
	}
}