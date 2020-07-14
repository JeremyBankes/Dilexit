package com.jeremy.dilexit.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.jeremy.dilexit.entity.components.AnimationComponent;
import com.jeremy.dilexit.entity.components.PhysicsComponent;
import com.jeremy.dilexit.entity.components.PlayerComponent;
import com.jeremy.dilexit.entity.components.TransformComponent;
import com.jeremy.dilexit.entity.components.TypeComponent;

public class Mappers {

	public static final ComponentMapper<TypeComponent> TYPE = ComponentMapper.getFor(TypeComponent.class);
	public static final ComponentMapper<PhysicsComponent> PHYSICS = ComponentMapper.getFor(PhysicsComponent.class);
	public static final ComponentMapper<TransformComponent> TRANFORM = ComponentMapper.getFor(TransformComponent.class);
	public static final ComponentMapper<PlayerComponent> PLAYER = ComponentMapper.getFor(PlayerComponent.class);
	public static final ComponentMapper<AnimationComponent> ANIMATION = ComponentMapper.getFor(AnimationComponent.class);

}
