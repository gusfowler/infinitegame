package com.gusfowler.twoinfinitegame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InfiniteGame extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public float vsHeight = 480, vsWidth = 800;
	public String assetPath = "C:/Users/gusfo/Desktop/infinitegame/android/assets/";
	public int boardMultiplier = 4;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//Use default arial font
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
