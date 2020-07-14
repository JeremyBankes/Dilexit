package com.jeremy.dilexit.entity.components;

import com.badlogic.ashley.core.Component;
import com.jeremy.dilexit.entity.EntityType;

public class TypeComponent implements Component {

	public final EntityType type;

	public TypeComponent(EntityType type) {
		this.type = type;
	}

}
