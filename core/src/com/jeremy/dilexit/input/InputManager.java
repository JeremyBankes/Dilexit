package com.jeremy.dilexit.input;

import java.awt.Point;
import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {

	private HashSet<KeyEventListener> keyEventListeners = new HashSet<>();
	private HashSet<MouseEventListener> mouseEventListeners = new HashSet<>();

	public boolean addKeyEventListener(KeyEventListener listener) {
		return keyEventListeners.add(listener);
	}

	public boolean addMouseEventListener(MouseEventListener listener) {
		return mouseEventListeners.add(listener);
	}

	public boolean removeKeyEventListener(KeyEventListener listener) {
		return keyEventListeners.remove(listener);
	}

	public boolean removeMouseEventListener(MouseEventListener listener) {
		return mouseEventListeners.remove(listener);
	}

	@Override
	public boolean keyDown(int keycode) {
		keyEventListeners.forEach(listener -> listener.onKeyEvent(new KeyEvent(KeyEventType.PRESS, keycode)));
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		keyEventListeners.forEach(listener -> listener.onKeyEvent(new KeyEvent(KeyEventType.RELEASE, keycode)));
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		keyEventListeners.forEach(listener -> listener.onKeyEvent(new KeyEvent(KeyEventType.TYPE, character)));
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		mouseEventListeners.forEach(listener -> listener.onMouseEvent(new MouseEvent(MouseEventType.PRESS, screenX, screenY, button)));
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		mouseEventListeners.forEach(listener -> listener.onMouseEvent(new MouseEvent(MouseEventType.RELEASE, screenX, screenY, button)));
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouseEventListeners.forEach(listener -> listener.onMouseEvent(new MouseEvent(MouseEventType.MOVE, screenX, screenY, -1)));
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		int screenX = Gdx.input.getX();
		int screenY = Gdx.input.getY();
		mouseEventListeners.forEach(listener -> listener.onMouseEvent(new MouseEvent(MouseEventType.SCROLL, screenX, screenY, amount)));
		return false;
	}

	public static enum KeyEventType {

		PRESS, RELEASE, TYPE

	}

	public static class KeyEvent {

		public final KeyEventType type;
		public final int key;

		public KeyEvent(KeyEventType type, int keycode) {
			this.type = type;
			this.key = keycode;
		}

	}

	public static interface KeyEventListener {

		public abstract void onKeyEvent(KeyEvent event);

	}

	public static enum MouseEventType {

		MOVE, PRESS, RELEASE, SCROLL

	}

	public static class MouseEvent {

		public final Point location;
		public final MouseEventType type;
		public final int button;

		public MouseEvent(MouseEventType type, int x, int y, int button) {
			this.location = new Point(x, y);
			this.type = type;
			this.button = button;
		}

	}

	public static interface MouseEventListener {

		public abstract void onMouseEvent(MouseEvent event);

	}

}
