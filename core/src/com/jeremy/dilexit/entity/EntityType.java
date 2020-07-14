package com.jeremy.dilexit.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public enum EntityType {

	PLAYER(1.0f, 1.0f, world -> {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.fixedRotation = true;
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = rectangle(12, 22, 7, 10, 32, 32);
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.0f;
		body.createFixture(fixtureDef);
		return body;
	}),

	NPC(1.0f, 1.0f, world -> {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.StaticBody;
		bodyDef.fixedRotation = true;
		bodyDef.linearDamping = 0.75f;
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = rectangle(14, 22, 4, 10, 32, 32);
		fixtureDef.friction = 0.5f;
		fixtureDef.restitution = 0.0f;
		body.createFixture(fixtureDef);
		return body;
	});

	public final Vector2 size;

	private BodyCreator bodyCreator;

	private EntityType(float width, float height, BodyCreator bodyCreator) {
		size = new Vector2(width, height);
		this.bodyCreator = bodyCreator;
	}

	public Body createBody(World world) {
		return bodyCreator.create(world);
	}

	private static interface BodyCreator {

		public abstract Body create(World world);

	}

	public static PolygonShape rectangle(int x, int y, int width, int height, int srcWidth, int srcHeight) {
		PolygonShape rectangle = new PolygonShape();
		Vector2 center = new Vector2((x + (float) width / 2) / srcWidth - 0.5f, 0.5f - ((y + (float) height / 2) / srcHeight));
		rectangle.setAsBox((float) width / srcWidth / 2, (float) height / srcHeight / 2, center, 0);
		return rectangle;
	}

}
