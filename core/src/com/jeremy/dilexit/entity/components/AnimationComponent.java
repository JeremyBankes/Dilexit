package com.jeremy.dilexit.entity.components;

import java.util.HashMap;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationComponent implements Component {

	public String state = null;
	public float stateTime = 0.0f;
	public boolean looping = true;
	public final HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();
	public TextureRegion frame;

}
