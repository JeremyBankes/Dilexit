package com.jeremy.dilexit.entity.components;

import com.badlogic.ashley.core.Component;

public class HoverComponent implements Component {

	public Runnable enter = () -> {};
	public Runnable exit = () -> {};

}
