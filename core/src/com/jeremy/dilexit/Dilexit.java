package com.jeremy.dilexit;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.jeremy.dilexit.state.StateManager;
import com.jeremy.dilexit.state.states.MenuState;

public class Dilexit extends ApplicationAdapter {

	private StateManager stateManager;
	private Engine engine;

	@Override
	public void create() {
		stateManager = new StateManager();
		engine = new Engine();

		stateManager.register(new MenuState());

		stateManager.enterState(MenuState.class);
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

}
