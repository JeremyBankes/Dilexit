package com.jeremy.dilexit.state;

public abstract class State {

	public void create() {};

	protected abstract void enter();

	public abstract void update(float deltaTime);

	protected abstract void exit();

	public void dispose() {};

	public void onResize(int width, int height) {}

}
