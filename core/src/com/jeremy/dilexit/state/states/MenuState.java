package com.jeremy.dilexit.state.states;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jeremy.dilexit.entity.components.PositionComponent;
import com.jeremy.dilexit.entity.components.SizeComponent;
import com.jeremy.dilexit.entity.components.TextureComponent;
import com.jeremy.dilexit.entity.systems.RenderSystem;
import com.jeremy.dilexit.state.State;

public class MenuState extends State {

	private AssetManager assetManager;

	public MenuState() {
		assetManager = new AssetManager();
	}

	@Override
	protected void enter() {
		assetManager.load("icons.png", Texture.class);
		assetManager.finishLoading();

		final Engine engine = getGame().getEngine();
		engine.addSystem(new RenderSystem(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));

		Entity entity = new Entity();
		entity.add(new PositionComponent(10.0f, 10.0f));
		entity.add(new SizeComponent(100.0f, 100.0f));
		entity.add(new TextureComponent(assetManager.get("icons.png")));

		engine.addEntity(entity);
	}

	@Override
	protected void update(float deltaTime) {

	}

	@Override
	protected void onResize(int width, int height) {
		RenderSystem renderSystem = getGame().getEngine().getSystem(RenderSystem.class);
		renderSystem.viewport.update(width, height);
		renderSystem.viewport.apply();
	}

	@Override
	protected void exit() {
		assetManager.dispose();
	}

	@Override
	protected void dispose() {

	}

}
