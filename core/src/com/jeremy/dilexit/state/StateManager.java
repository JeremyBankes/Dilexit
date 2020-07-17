package com.jeremy.dilexit.state;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;

public class StateManager {

	private HashMap<Class<? extends State>, State> states = new HashMap<>();

	private State currentState;

	public void update(float deltaTime) {
		if (currentState == null) return;
		currentState.update(deltaTime);
	}

	public void register(State state) {
		states.put(state.getClass(), state);
	}

	public <T extends State> T getState(Class<T> stateClass) {
		if (!states.containsKey(stateClass)) throw new IllegalArgumentException("state not registered");
		return stateClass.cast(states.get(stateClass));
	}

	public State enterState(Class<? extends State> stateClass) {
		if (currentState != null) currentState.exit();
		State state = getState(stateClass);
		state.enter();
		state.onResize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		return state;
	}

	public void dispose() {
		if (currentState != null) currentState.exit();
		states.values().forEach(State::dispose);
	}

	public void onResize(int width, int height) {
		if (currentState != null) currentState.onResize(width, height);
	}

}
