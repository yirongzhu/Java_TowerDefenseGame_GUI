package com.tower.elements;

import com.tower.core.Bullete;
import com.tower.core.Fighter;

public class SolwBullete extends Bullete{
	
	private int bulleteType=8;

	public SolwBullete(int x, int y,Fighter fighter) {
		setBulletex(x);
		setBulletey(y);
		setBulleteType(bulleteType);
		setFighter(fighter);
	}
}
