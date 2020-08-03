package com.jeremy.dilexit.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class SizeComponent implements Component {

	public final Vector2 size = new Vector2();
	public final Vector2 offset = new Vector2();

	public SizeComponent(float width, float height) {
		size.set(width, height);
		offset.set(-width / 2, -height / 2);
	}

}
