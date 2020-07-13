package com.jeremy.dilexit.entity;

import com.badlogic.ashley.core.Component;

public class TypeComponent implements Component {

	public final EntityType type;

	public TypeComponent(EntityType type) {
		this.type = type;
	}

}
