package com.jeremy.dilexit.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class PlayerControlSystem extends IteratingSystem {

	public PlayerControlSystem() {
		super(Family.all(PlayerComponent.class, PhysicsComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		final PhysicsComponent physicsComponent = Mappers.PHYSICS.get(entity);
		final Body body = physicsComponent.body;
		final Vector2 input = new Vector2();
		if (Gdx.input.isKeyPressed(Keys.W)) input.y += 1.0f;
		if (Gdx.input.isKeyPressed(Keys.S)) input.y -= 1.0f;
		if (Gdx.input.isKeyPressed(Keys.D)) input.x += 1.0f;
		if (Gdx.input.isKeyPressed(Keys.A)) input.x -= 1.0f;
		input.nor();
		body.setLinearVelocity(input.scl(3.0f));
	}

}
