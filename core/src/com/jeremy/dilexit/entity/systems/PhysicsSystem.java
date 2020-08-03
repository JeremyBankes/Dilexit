package com.jeremy.dilexit.entity.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class PhysicsSystem extends EntitySystem {

	private World world;

	public PhysicsSystem() {
		world = new World(new Vector2(), true);
	}

	@Override
	public void update(float deltaTime) {
		world.step(deltaTime, 3, 8);
	}

	public World getWorld() {
		return world;
	}

}
