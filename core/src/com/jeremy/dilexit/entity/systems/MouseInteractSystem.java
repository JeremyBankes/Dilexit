package com.jeremy.dilexit.entity.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.jeremy.dilexit.Dilexit;
import com.jeremy.dilexit.entity.Mappers;
import com.jeremy.dilexit.entity.components.ClickComponent;
import com.jeremy.dilexit.entity.components.HoverComponent;
import com.jeremy.dilexit.entity.components.PositionComponent;
import com.jeremy.dilexit.entity.components.SizeComponent;
import com.jeremy.dilexit.input.InputManager.MouseEventType;

public class MouseInteractSystem extends IteratingSystem {

	private final Vector2 previousMousePosition = new Vector2();
	private final Vector2 latestMousePosition = new Vector2();

	public MouseInteractSystem() {
		super(Family.one(HoverComponent.class, ClickComponent.class, PositionComponent.class, SizeComponent.class).get());
		Dilexit.getInstance().getInputManager().addMouseEventListener(event -> {
			final Camera camera = getEngine().getSystem(RenderSystem.class).getCamera();
			if (event.type == MouseEventType.MOVE) {
				previousMousePosition.set(latestMousePosition);
				Vector3 worldPosition = camera.unproject(new Vector3(event.location.x, event.location.y, 0));
				latestMousePosition.set(new Vector2(worldPosition.x, worldPosition.y));
			}
		});
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		final Vector2 position = Mappers.POSITION.get(entity).position;
		final Vector2 size = Mappers.SIZE.get(entity).size;
		final Rectangle bounds = new Rectangle(position.x, position.y, size.x, size.y);
		if (Mappers.HOVER.has(entity)) {
			final HoverComponent hoverComponent = Mappers.HOVER.get(entity);
			if (bounds.contains(latestMousePosition)) {
				if (!bounds.contains(previousMousePosition)) {
					hoverComponent.enter.run();
				}
			} else if (bounds.contains(previousMousePosition)) {
				hoverComponent.exit.run();
			}
		} else if (Mappers.CLICK.has(entity)) {
			// TODO: Finish click component
			final ClickComponent clickComponent = Mappers.CLICK.get(entity);
			final Camera camera = getEngine().getSystem(RenderSystem.class).getCamera();
			Vector3 worldPosition = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			if (bounds.contains(worldPosition.x, worldPosition.y)) {

			}
		}
	}

}
