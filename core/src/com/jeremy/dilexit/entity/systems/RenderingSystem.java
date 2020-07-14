package com.jeremy.dilexit.entity.systems;

import static com.badlogic.gdx.math.MathUtils.*;

import java.util.Comparator;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.jeremy.dilexit.Dilexit;
import com.jeremy.dilexit.entity.Mappers;
import com.jeremy.dilexit.entity.components.AnimationComponent;
import com.jeremy.dilexit.entity.components.TransformComponent;
import com.jeremy.dilexit.state.StateManager;
import com.jeremy.dilexit.state.states.GameState;

public class RenderingSystem extends SortedIteratingSystem {

	public RenderingSystem() {
		super(Family.all(TransformComponent.class).one(AnimationComponent.class).get(), new Sorter());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		StateManager stateManager = Dilexit.getInstance().getStateManager();
		GameState gameState = stateManager.getState(GameState.class);
		Batch batch = gameState.getBatch();

		final TransformComponent transformComponent = Mappers.TRANFORM.get(entity);
		final Vector2 position = transformComponent.position;
		final Vector2 size = transformComponent.size;
		final float rotation = transformComponent.rotation;
		if (Mappers.ANIMATION.has(entity)) {
			final AnimationComponent animationComponent = Mappers.ANIMATION.get(entity);

			float width = size.x;
			float height = size.y;
			float x = position.x - width / 2;
			float y = position.y - height / 2;

			if (transformComponent.flipped) {
				x += width;
				width *= -1;
			}

			batch.setProjectionMatrix(gameState.getCamera().combined);
			batch.begin();
			batch.draw(animationComponent.frame, x, y, 0.0f, 0.0f, width, height, 1.0f, 1.0f, radiansToDegrees * rotation);
			batch.end();
		}
		forceSort();
	}

	private static class Sorter implements Comparator<Entity> {

		@Override
		public int compare(Entity entityA, Entity entityB) {
			final TransformComponent transformComponentA = Mappers.TRANFORM.get(entityA);
			final TransformComponent transformComponentB = Mappers.TRANFORM.get(entityB);
			return round((transformComponentB.position.y - transformComponentA.position.y) * 100);
		}

	}

}
