package com.jeremy.dilexit.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class TextureComponent implements Component {

	public Texture texture;

	public TextureComponent(Texture texture) {
		this.texture = texture;
	}

}
