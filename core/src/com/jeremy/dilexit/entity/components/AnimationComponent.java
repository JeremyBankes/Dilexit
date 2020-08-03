package com.jeremy.dilexit.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationComponent implements Component {

	public final Animation<TextureRegion> animation;
	public boolean flipped;

	public AnimationComponent(Animation<TextureRegion> animation) {
		this.animation = animation;
	}

}
