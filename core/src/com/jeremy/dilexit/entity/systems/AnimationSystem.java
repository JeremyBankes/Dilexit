package com.jeremy.dilexit.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jeremy.dilexit.entity.Mappers;
import com.jeremy.dilexit.entity.components.AnimationComponent;

public class AnimationSystem extends IteratingSystem {

	public AnimationSystem() {
		super(Family.all(AnimationComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		final AnimationComponent animationComponent = Mappers.ANIMATION.get(entity);
		Animation<TextureRegion> animation = animationComponent.animations.get(animationComponent.state);
		animationComponent.frame = animation.getKeyFrame(animationComponent.stateTime, animationComponent.looping);
		animationComponent.stateTime += deltaTime;
	}

	public static AnimationComponent addAnimations(Entity entity, String name, TextureRegion region, int frames, int fps, int spriteWidth,
			int spriteHeight) {
		AnimationComponent animationComponent = entity.getComponent(AnimationComponent.class);
		TextureRegion[][] array2d = region.split(spriteWidth, spriteHeight);
		TextureRegion[] array = new TextureRegion[frames];
		int index;
		out: for (int i = 0; i < array2d.length; i++) {
			for (int j = 0; j < array2d[i].length; j++) {
				index = i * array2d[i].length + j;
				if (index < frames) array[index] = array2d[i][j];
				else break out;
			}
		}
		animationComponent.animations.put(name, new Animation<>(1.0f / fps, array));
		if (animationComponent.state == null) animationComponent.state = name;
		return animationComponent;
	}

}
