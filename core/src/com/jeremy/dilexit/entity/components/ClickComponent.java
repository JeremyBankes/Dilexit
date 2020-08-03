package com.jeremy.dilexit.entity.components;

import com.badlogic.ashley.core.Component;

public class ClickComponent implements Component {

	public Runnable press;
	public Runnable release;

	public ClickComponent(Runnable press, Runnable release) {
		this.press = press;
		this.release = release;
	}

}
