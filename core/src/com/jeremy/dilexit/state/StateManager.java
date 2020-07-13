package com.jeremy.dilexit.state;

import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;

public class StateManager {

	private State currentState;
	private HashMap<Class<? extends State>, State> states = new HashMap<>();

	public State getCurrentState() {
		return currentState;
	}

	void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public void register(State state) {
		states.put(state.getClass(), state);
		state.create();
	}

	public <T extends State> T getState(Class<T> stateClass) {
		return stateClass.cast(states.get(stateClass));
	}

	public void enter(Class<? extends State> stateClass) {
		if (getCurrentState() != null) getCurrentState().exit();
		State state = getState(stateClass);
		setCurrentState(state);
		state.enter();
		state.onResize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public Collection<State> getStates() {
		return states.values();
	}

	public void dispose() {
		getCurrentState().exit();
	}

}
