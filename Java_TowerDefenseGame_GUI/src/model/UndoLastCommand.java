package model;

import java.util.List;

import paintobject.PaintObject;
import server.NetpaintServer;

public class UndoLastCommand extends Command<NetpaintServer>{

	// construct
	public UndoLastCommand(String source) {
		super(source);
	}

	@Override
	public void execute(NetpaintServer executeOn) {
		executeOn.undoLast(getSource());
	}
}
