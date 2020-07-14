package com.jeremy.dilexit.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.jeremy.dilexit.entity.Mappers;
import com.jeremy.dilexit.entity.components.PhysicsComponent;
import com.jeremy.dilexit.entity.components.TransformComponent;
import com.jeremy.dilexit.entity.components.TypeComponent;

public class PhysicSystem extends IteratingSystem {

	public PhysicSystem() {
		super(Family.all(PhysicsComponent.class, TransformComponent.class, TypeComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		PhysicsComponent physicsComponent = Mappers.PHYSICS.get(entity);
		TransformComponent transformComponent = Mappers.TRANFORM.get(entity);
		TypeComponent typeComponent = Mappers.TYPE.get(entity);

		transformComponent.position.set(physicsComponent.body.getPosition());
		transformComponent.size.set(typeComponent.type.size);
		transformComponent.rotation = physicsComponent.body.getAngle();
	}

}
