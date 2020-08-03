package com.jeremy.dilexit.utility;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationUtility {

	public static final Animation<TextureRegion> createAnimation(float fps, TextureRegion textureRegion, int frameWidth, int frameHeight,
			int frameCount) {
		Array<TextureRegion> frames = new Array<>(frameCount);
		TextureRegion[][] frameArray = textureRegion.split(frameWidth, frameHeight);
		for (int i = 0; i < frameArray.length; i++) {
			for (int j = 0; j < frameArray[i].length; j++) {
				int index = i * frameArray.length + j;
				if (index < frameCount) {
					frames.add(frameArray[i][j]);
				}
			}
		}
		return new Animation<>(1.0f / fps, frames, PlayMode.NORMAL);
	}

}
