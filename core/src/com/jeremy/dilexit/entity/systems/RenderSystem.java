package com.jeremy.dilexit.entity.systems;

import java.util.Comparator;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jeremy.dilexit.entity.Mappers;
import com.jeremy.dilexit.entity.components.PositionComponent;
import com.jeremy.dilexit.entity.components.SizeComponent;
import com.jeremy.dilexit.entity.components.TextureComponent;
import com.jeremy.dilexit.entity.components.TextureRegionComponent;

public class RenderSystem extends SortedIteratingSystem implements Disposable {

	private static final int INITIAL_BATCH_SIZE = 250;

	private SpriteBatch batch;
	public Viewport viewport;

	public RenderSystem(Viewport viewport) {
		super(Family.all(PositionComponent.class, SizeComponent.class).one(TextureComponent.class, TextureRegionComponent.class).get(),
				new RenderSortingComparator());
		batch = new SpriteBatch(INITIAL_BATCH_SIZE);
		this.viewport = viewport;
	}

	@Override
	public void update(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.5f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.update(deltaTime);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		final Vector2 position = Mappers.POSITION.get(entity).position;
		final Vector2 size = Mappers.SIZE.get(entity).size;
		batch.begin();
		batch.setProjectionMatrix(viewport.getCamera().combined);
		if (Mappers.TEXTURE.has(entity)) {
			final Texture texture = Mappers.TEXTURE.get(entity).texture;
			batch.draw(texture, position.x, position.y, size.x, size.y);
		} else if (Mappers.TEXTURE_REGION.has(entity)) {
			final TextureRegion textureRegion = Mappers.TEXTURE_REGION.get(entity).textureRegion;
			batch.draw(textureRegion, position.x, position.y, size.x, size.y);
		} else throw new IllegalStateException("no renderable entity component");
		batch.end();
	}

	private static class RenderSortingComparator implements Comparator<Entity> {

		@Override
		public int compare(Entity a, Entity b) {
			return 0;
		}

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
