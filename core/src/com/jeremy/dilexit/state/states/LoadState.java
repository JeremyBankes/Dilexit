package com.jeremy.dilexit.state.states;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.jeremy.dilexit.Dilexit;
import com.jeremy.dilexit.state.State;

public class LoadState extends State {

	private AssetManager assetManager;

	public LoadState() {
		assetManager = Dilexit.getInstance().getAssetManager();
	}

	@Override
	protected void enter() {
		assetManager.load("normal.fnt", BitmapFont.class);
		assetManager.load("fancy.fnt", BitmapFont.class);
		TextureParameter param = new TextureParameter();
		param.minFilter = TextureFilter.Linear;
		param.genMipMaps = true;
		assetManager.load("backgrounds.png", Texture.class, param);
		assetManager.load("busy.png", Texture.class);
		assetManager.load("busy2.png", Texture.class);
	}

	@Override
	public void update(float deltaTime) {
		if (assetManager.update()) {
			Dilexit.getInstance().getStateManager().enter(MenuState.class);
		}
	}

	@Override
	protected void exit() {}

}
