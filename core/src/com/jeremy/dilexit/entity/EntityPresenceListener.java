package com.jeremy.dilexit.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.gdx.physics.box2d.World;
import com.jeremy.dilexit.entity.components.PhysicsComponent;
import com.jeremy.dilexit.entity.components.TypeComponent;

public class EntityPresenceListener implements EntityListener {

	private World world;

	public EntityPresenceListener(World world) {
		this.world = world;
	}

	@Override
	public void entityAdded(Entity entity) {
		final TypeComponent typeComponent = Mappers.TYPE.get(entity);
		final PhysicsComponent physicsComponent = Mappers.PHYSICS.get(entity);
		physicsComponent.body = typeComponent.type.createBody(world);
	}

	@Override
	public void entityRemoved(Entity entity) {
		final PhysicsComponent physicsComponent = Mappers.PHYSICS.get(entity);
		world.destroyBody(physicsComponent.body);
	}

}
