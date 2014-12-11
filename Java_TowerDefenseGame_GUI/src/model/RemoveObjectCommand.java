package model;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

import model.Command;
import paintobject.PaintObject;
import server.NetpaintServer;

public class RemoveObjectCommand extends Command<NetpaintServer>{

	PaintObject object;
	int i;
	// construct
	public RemoveObjectCommand(String source, int i) {
		super(source);
		this.i=i;
	}

	@Override
	public void execute(NetpaintServer executeOn) {
		List<PaintObject> list = executeOn.getObjects();
		
		PaintObject ob = list.get(i);
		executeOn.removeObject(ob);
		
		System.out.println("size "+executeOn.getObjects().size());
			
		

//		
//		if(object != null){
//			executeOn.removeObject(object);
			
		}
	
	
////	@Override
//	public void undo(NetpaintServer executeOn){
//		// get objects
//		List<PaintObject> list = executeOn.getObjects();
//		int last = list.size() - 1;
//		
//		if(list.size()>0){
//			PaintObject ob = list.get(last);
//			// remove object
//			executeOn.removeObject(ob);
//		}
//	}

}