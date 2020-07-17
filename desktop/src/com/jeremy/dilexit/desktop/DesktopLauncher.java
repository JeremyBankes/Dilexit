package com.jeremy.dilexit.desktop;

import java.awt.GraphicsEnvironment;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jeremy.dilexit.Dilexit;

public class DesktopLauncher {

	public static void main(String[] arg) {
		GraphicsEnvironment.getLocalGraphicsEnvironment();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Esse Dilexit";
		new LwjglApplication(new Dilexit(), config);
	}

}
