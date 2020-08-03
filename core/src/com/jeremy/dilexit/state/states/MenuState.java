package com.jeremy.dilexit.state.states;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jeremy.dilexit.entity.Mappers;
import com.jeremy.dilexit.entity.components.HoverComponent;
import com.jeremy.dilexit.entity.components.PositionComponent;
import com.jeremy.dilexit.entity.components.SizeComponent;
import com.jeremy.dilexit.entity.components.TextureComponent;
import com.jeremy.dilexit.entity.systems.MouseInteractSystem;
import com.jeremy.dilexit.entity.systems.RenderSystem;
import com.jeremy.dilexit.state.State;

public class MenuState extends State {

	@Override
	protected void enter() {
		getGame().getAssetManager().load("icons.png", Texture.class);
		getGame().getAssetManager().load("normal.png", Texture.class);
		getGame().getAssetManager().finishLoading();

		final Engine engine = getGame().getEngine();
		engine.addSystem(new RenderSystem(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));
		engine.addSystem(new MouseInteractSystem());

		Entity entity = new Entity();
		entity.add(new PositionComponent(10.0f, 10.0f));
		entity.add(new SizeComponent(32.0f * 4, 32.0f * 4));
		entity.add(new TextureComponent(getGame().getAssetManager().get("icons.png")));
		entity.add(new HoverComponent(() -> {
			Mappers.TEXTURE.get(entity).texture = getGame().getAssetManager().get("normal.png");
		}, () -> {
			Mappers.TEXTURE.get(entity).texture = getGame().getAssetManager().get("icons.png");
		}));

		engine.addEntity(entity);
	}

	@Override
	protected void update(float deltaTime) {

	}

	@Override
	protected void onResize(int width, int height) {
		RenderSystem renderSystem = getGame().getEngine().getSystem(RenderSystem.class);
		renderSystem.onResize(width, height);
	}

	@Override
	protected void exit() {
		getGame().getAssetManager().dispose();
	}

	@Override
	protected void dispose() {

	}

}
