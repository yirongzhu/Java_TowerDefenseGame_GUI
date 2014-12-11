package com.tower.elements;

import com.tower.core.Bullete;
import com.tower.core.Fighter;

public class FireBullete extends Bullete{

	private int bulleteType=1;
	private int power=35;

	public FireBullete(int tower_x, int tower_y,Fighter fighter) {
		
		setBulletex(tower_x);
		setBulletey(tower_y);
		setBulleteType(bulleteType);
		setPower(power);
		setFighter(fighter);
		
	}

}
