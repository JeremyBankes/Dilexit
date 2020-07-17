package com.jeremy.dilexit.state;

import com.jeremy.dilexit.Dilexit;

public class State {

	protected void enter() {}

	protected void update(float deltaTime) {}

	protected void onResize(int width, int height) {}

	protected void dispose() {}

	protected void exit() {}

	public Dilexit getGame() {
		return Dilexit.getInstance();
	}

}
