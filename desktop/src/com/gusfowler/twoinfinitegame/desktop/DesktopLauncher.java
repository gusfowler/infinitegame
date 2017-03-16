package com.gusfowler.twoinfinitegame.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gusfowler.twoinfinitegame.InfiniteGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "2-infinite";
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new InfiniteGame(), config);
	}
}
