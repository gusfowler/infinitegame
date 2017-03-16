package com.gusfowler.twoinfinitegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MainMenuScreen implements Screen {
	final InfiniteGame game;
	OrthographicCamera camera;
	GlyphLayout header, prompt;
	
	public MainMenuScreen(final InfiniteGame g) {
		this.game = g;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, game.vsWidth, game.vsHeight);
		
		game.font.setColor(Color.BLACK);
		header = new GlyphLayout(game.font, "Welcome to 2-infinite!!!");
		prompt = new GlyphLayout(game.font, "Tap anywhere to begin");
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		
		game.batch.begin();
		game.font.draw(game.batch, header, ((game.vsWidth / 2) - (header.width / 2)), (float)(game.vsHeight * (0.75)));
		game.font.draw(game.batch, prompt, ((game.vsWidth / 2) - (prompt.width / 2)), (game.vsHeight / 2));
		game.batch.end();
		
		if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		
	}
}
