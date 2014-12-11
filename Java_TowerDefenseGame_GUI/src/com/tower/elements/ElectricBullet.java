package com.tower.elements;

import com.tower.core.Bullete;
import com.tower.core.Fighter;

public class ElectricBullet extends Bullete {
	private int bulleteType=2;
	private int power=50;

	public ElectricBullet(int tower_x, int tower_y,Fighter fighter) {
		setBulletex(tower_x);
		setBulletey(tower_y);
		setBulleteType(bulleteType);
		setPower(power);
		setFighter(fighter);
		
	}

}
