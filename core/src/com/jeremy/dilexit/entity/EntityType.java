package com.jeremy.dilexit.entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public enum EntityType {

	PLAYER(world -> {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1.0f / 2, 1.0f / 2);
		fixtureDef.shape = shape;
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.25f;
		fixtureDef.density = 30.0f;
		body.createFixture(fixtureDef);
		return body;
	});

	private BodyCreator bodyCreator;

	private EntityType(BodyCreator bodyCreator) {
		this.bodyCreator = bodyCreator;
	}

	public Body createBody(World world) {
		return bodyCreator.create(world);
	}

	private static interface BodyCreator {

		public abstract Body create(World world);

	}

}
