package model;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import model.Command;
import paintobject.PaintObject;
import server.NetpaintServer;

public class AppendMessageCommand extends Command<NetpaintServer>{

	String s;
	// construct
	public AppendMessageCommand(String source, String s) {
		super(source);
		this.s = s;
	}

	@Override
	public void execute(NetpaintServer executeOn) {
		executeOn.appendMessage(s);
	}
}