package com.jeremy.dilexit;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;
import com.jeremy.dilexit.input.InputManager;
import com.jeremy.dilexit.state.StateManager;
import com.jeremy.dilexit.state.states.GameState;
import com.jeremy.dilexit.state.states.MenuState;

public class Dilexit extends ApplicationAdapter {

	private AssetManager assetManager;
	private StateManager stateManager;
	private InputManager inputManager;
	private Engine engine;

	@Override
	public void create() {
		assetManager = new AssetManager();
		stateManager = new StateManager();
		inputManager = new InputManager();
		engine = new Engine();

		stateManager.register(new MenuState());
		stateManager.register(new GameState());

		stateManager.enterState(GameState.class);

		Gdx.input.setInputProcessor(inputManager);
	}

	@Override
	public void render() {
		final float deltaTime = Gdx.graphics.getDeltaTime();
		stateManager.update(deltaTime);
		engine.update(deltaTime);
	}

	@Override
	public void dispose() {
		stateManager.dispose();
		for (EntitySystem system : engine.getSystems()) {
			if (system instanceof Disposable) ((Disposable) system).dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		stateManager.onResize(width, height);
	}

	public Engine getEngine() {
		return engine;
	}

	public static Dilexit getInstance() {
		return (Dilexit) Gdx.app.getApplicationListener();
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public InputManager getInputManager() {
		return inputManager;
	}

}
