package com.jeremy.dilexit.state.states;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.jeremy.dilexit.entity.components.AnimationComponent;
import com.jeremy.dilexit.entity.components.PositionComponent;
import com.jeremy.dilexit.entity.components.SizeComponent;
import com.jeremy.dilexit.entity.systems.RenderSystem;
import com.jeremy.dilexit.state.State;
import com.jeremy.dilexit.utility.AnimationUtility;

public class GameState extends State {

	@Override
	protected void enter() {
		final Engine engine = getGame().getEngine();
		engine.addSystem(new RenderSystem(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight())));

		getGame().getAssetManager().load("icons.png", Texture.class);
		getGame().getAssetManager().load("normal.png", Texture.class);
		getGame().getAssetManager().load("textures/atlases/wit.atlas", TextureAtlas.class);
		getGame().getAssetManager().finishLoading();
		createPlayer();
	}

	private void createPlayer() {
		TextureAtlas playerTextureAtlas = getGame().getAssetManager().get("textures/atlases/wit.atlas", TextureAtlas.class);

		Entity player = new Entity();

		SizeComponent sizeComponent = new SizeComponent(1.0f, 1.0f);
		sizeComponent.offset.y = 0.0f;
		player.add(new PositionComponent(0.0f, 0.0f));
		player.add(sizeComponent);
		player.add(new AnimationComponent(AnimationUtility.createAnimation(10, playerTextureAtlas.findRegion("wit-idle"), 32, 32, 4)));

		getGame().getEngine().addEntity(player);
	}

	@Override
	protected void update(float deltaTime) {
		super.update(deltaTime);
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

}
