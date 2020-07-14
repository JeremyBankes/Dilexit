package com.jeremy.dilexit.entity.systems;

import static java.lang.Math.*;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.jeremy.dilexit.entity.Mappers;
import com.jeremy.dilexit.entity.components.AnimationComponent;
import com.jeremy.dilexit.entity.components.PhysicsComponent;
import com.jeremy.dilexit.entity.components.PlayerComponent;
import com.jeremy.dilexit.entity.components.TransformComponent;

public class PlayerControlSystem extends IteratingSystem {

	public PlayerControlSystem() {
		super(Family.all(PlayerComponent.class, PhysicsComponent.class, TransformComponent.class, AnimationComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		final PhysicsComponent physicsComponent = Mappers.PHYSICS.get(entity);
		final PlayerComponent playerComponent = Mappers.PLAYER.get(entity);
		final TransformComponent renderableComponent = Mappers.TRANFORM.get(entity);
		final AnimationComponent animationComponent = Mappers.ANIMATION.get(entity);

		final Body body = physicsComponent.body;
		final Vector2 input = new Vector2();
		final Vector2 velocityBefore = body.getLinearVelocity();
		if (!playerComponent.attacking) {
			if (Gdx.input.isKeyPressed(Keys.W)) input.y += 1.0f;
			if (Gdx.input.isKeyPressed(Keys.S)) input.y -= 1.0f;
			if (Gdx.input.isKeyPressed(Keys.D)) input.x += 1.0f;
			if (Gdx.input.isKeyPressed(Keys.A)) input.x -= 1.0f;
		}
		input.nor();
		body.setLinearVelocity(input.scl(2.0f));
		Animation<TextureRegion> attackAnimation = animationComponent.animations.get("attack");
		if (playerComponent.attacking) {
			if (attackAnimation.isAnimationFinished(animationComponent.stateTime)) {
				playerComponent.attacking = false;
			}
		} else {
			animationComponent.animations.get("idle").setFrameDuration(1.0f / 3);
			if (abs(velocityBefore.x) < 0.001f && abs(velocityBefore.y) < 0.0001f) {
				animationComponent.state = "idle";
			} else {
				animationComponent.state = "walk";
			}
			if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				animationComponent.stateTime = 0.0f;
				animationComponent.state = "attack";
				playerComponent.attacking = true;
			}
		}
		renderableComponent.position.set(body.getPosition());

		if (input.x > 0) renderableComponent.flipped = false;
		if (input.x < 0) renderableComponent.flipped = true;
	}

}
