package com.jeremy.dilexit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.jeremy.dilexit.state.StateManager;
import com.jeremy.dilexit.state.states.GameState;
import com.jeremy.dilexit.state.states.LoadState;
import com.jeremy.dilexit.state.states.MenuState;
import com.jeremy.dilexit.state.states.TransitionState;

public class Dilexit extends ApplicationAdapter {

	public static final int WIDTH = 512;
	public static final int HEIGHT = WIDTH * 9 / 16;

	private SpriteBatch batch;
	private StateManager stateManager;
	private AssetManager assetManager;

	private long startTime;

	@Override
	public void create() {
		startTime = TimeUtils.millis();
		batch = new SpriteBatch();

		stateManager = new StateManager();
		assetManager = new AssetManager();

		stateManager.register(new LoadState());
		stateManager.register(new MenuState());
		stateManager.register(new GameState());
		stateManager.register(new TransitionState());

		stateManager.enter(LoadState.class);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		getBatch().begin();
		stateManager.getCurrentState().update(Gdx.graphics.getDeltaTime());
		getBatch().end();
	}

	@Override
	public void dispose() {
		stateManager.dispose();
		assetManager.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stateManager.getCurrentState().onResize(width, height);
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public StateManager getStateManager() {
		return stateManager;
	}

	public float getAge() {
		return (float) TimeUtils.timeSinceMillis(startTime) / 1000;
	}

	public static Dilexit getInstance() {
		return (Dilexit) Gdx.app.getApplicationListener();
	}
}
