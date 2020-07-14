package com.jeremy.dilexit.dialog;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.StringBuilder;
import com.jeremy.dilexit.Dilexit;

public class Dialog {

	private SpriteBatch batch;

	private String name;
	private Texture portrait;

	private BitmapFont normalFont;
	private BitmapFont fancyFont;
	// private Texture background;

	private float characterDelay = 0.1f;
	private float timeSinceUpdate = 0.0f;
	private String toDisplay;
	private StringBuilder displayBuffer = new StringBuilder();

	public Dialog(String name, Texture portrait) {
		this.name = name;
		this.portrait = portrait;

		AssetManager assetManager = Dilexit.getInstance().getAssetManager();
		normalFont = assetManager.get("normal.fnt");
		fancyFont = assetManager.get("fancy.fnt");

		batch = new SpriteBatch();
	}

	public void udpate(float deltaTime) {
		if (toDisplay != null && toDisplay.length() != displayBuffer.length) {
			if (timeSinceUpdate > characterDelay) {
				displayBuffer.append(toDisplay.charAt(displayBuffer.length));
				timeSinceUpdate -= characterDelay;
			} else {
				timeSinceUpdate += deltaTime;
			}
		}
	}

	public void say(String message) {
		if (displayBuffer.length() != 0) clear();
		toDisplay = message;
	}

	public void clear() {
		toDisplay = null;
		displayBuffer.setLength(0);
	}

	public void dispose() {
		batch.dispose();
	}

}
