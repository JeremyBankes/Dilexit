package com.jeremy.dilexit.state.states;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
		assetManager.load("textures/atlases/wit.atlas", TextureAtlas.class);
		assetManager.load("textures/atlases/enya.atlas", TextureAtlas.class);
		assetManager.load("backgrounds.png", Texture.class, param);
		assetManager.load("busy.png", Texture.class);
		assetManager.load("busy2.png", Texture.class);

		assetManager.load("sounds/shift.wav", Sound.class);
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
