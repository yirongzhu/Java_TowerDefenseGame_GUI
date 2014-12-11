package model;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import model.Command;
import paintobject.PaintObject;
import server.NetpaintServer;

public class AddObjectCommand extends Command<NetpaintServer>{

	PaintObject object;
	// construct
	public AddObjectCommand(String source, PaintObject object) {
		super(source);
		this.object = object;
	}

	@Override
	public void execute(NetpaintServer executeOn) {
		// add Object Command
		if(object != null)
			executeOn.addObject(object);
//		List<PaintObject> list = executeOn.getObjects();
	}
	@Override
	public void undo(NetpaintServer executeOn){
		// get objects
		List<PaintObject> list = executeOn.getObjects();
		int last = list.size() - 1;
		
		if(list.size()>0){
			PaintObject ob = list.get(last);
			// remove object
			executeOn.removeObject(ob);
		}
	}

}
