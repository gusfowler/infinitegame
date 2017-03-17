package com.gusfowler.twoinfinitegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {
	final InfiniteGame infGame;
	
	Texture blackBox, whiteBox;
	Rectangle gameBoard;
	OrthographicCamera camera;
	Sprite baseSprite;
	Array<Piece> livePieces;
	gameManage gameMan;
	
	boolean boardChanged = false;
	float stateTime;
	
	public GameScreen(InfiniteGame g) {
		infGame = g;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, infGame.vsWidth, infGame.vsHeight);
		
		blackBox = new Texture(Gdx.files.internal(infGame.assetPath + "data/blackbox.png"));
		whiteBox = new Texture(Gdx.files.internal(infGame.assetPath + "data/whitebox.png"));
		gameBoard = new Rectangle(((infGame.vsWidth / 2) - (blackBox.getWidth() / 2)), ((infGame.vsHeight / 2) - (blackBox.getHeight() / 2)), (float)blackBox.getWidth(), (float)blackBox.getHeight());
		baseSprite = new Sprite(whiteBox);
		livePieces = new Array<Piece>();
		gameMan = new gameManage(this, blackBox.getWidth());
		
		
		gameMan.newPiece();
		gameMan.newPiece();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();

		camera.update();
		infGame.batch.setProjectionMatrix(camera.combined);

		infGame.batch.begin();
		infGame.batch.draw(blackBox, gameBoard.x, gameBoard.y, gameBoard.width, gameBoard.height);
		drawPieces();
		
		updateLive();
		drawPieces();
		infGame.batch.end();
		
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) gameMan.newPiece();
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
		blackBox.dispose();
		whiteBox.dispose();
		infGame.dispose();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	private void drawPiece(Piece p) {
		p.sprite.draw(infGame.batch);
		infGame.font.draw(infGame.batch, p.getGlyph(), (float)(p.sprite.getX() + (p.sprite.getWidth() / 2) - (p.getGlyph().width / 2)), (float)(p.sprite.getY() + (p.sprite.getHeight() / 2) + (p.getGlyph().height / 2)));
	}
	
	private void drawPieces() {
		for (Piece pieces : livePieces) {
			drawPiece(pieces);
		}
	}
	
	private void updateLive() {
		livePieces.clear();
		livePieces.addAll(gameMan.gamePieces);
	}
	
	private void createChanges() {
		Array<Piece> workingLivePieces = new Array<Piece>();
		workingLivePieces.addAll(livePieces);
		
		boolean newPiece = false;
		boolean removePiece = false;
		boolean left = false;
		boolean right = false;
		boolean up = false;
		boolean down = false;
		
		//add new pieces and make changes
		for (Piece x : gameMan.gamePieces) {
			for (Piece y : workingLivePieces) {
				if (x.ID != y.ID) newPiece = true;
				if (x.ID == y.ID && x.boardY == y.boardY && x.boardX < y.boardX) left = true;
				if (x.ID == y.ID && x.boardY == y.boardY && x.boardX > y.boardX) right = true;
				if (x.ID == y.ID && x.boardX == y.boardX && x.boardY < y.boardY) down = true;
				if (x.ID == y.ID && x.boardX == y.boardX && x.boardY > y.boardY) up = true;
				
				if (newPiece) {
					for (float z = 0; z < y.sprite.getHeight(); z++) {
						y.sprite.setOriginCenter();
						y.sprite.setScale(z);
						//frames.add(s);
					}
					newPiece = false;
				}
				if (left) {
					for (float z = y.sprite.getX(); z > x.sprite.getX(); z--) {
						y.sprite.setX(z);
						//frames.add(s);
					}
					left = false;
				}
				if (right) {
					for (float z = y.sprite.getX(); z < x.sprite.getX(); z++) {
						Sprite s = new Sprite(y.sprite);
						y.sprite.setX(z);
						//frames.add(s);
					}
					right = false;
				}
				if (up) {
					for (float z = y.sprite.getY(); z > x.sprite.getY(); z--) {
						y.sprite.setY(z);
						//frames.add(s);
					}
					up = false;
				}
				if (down) {
					for (float z = y.sprite.getY(); z < x.sprite.getY(); z++) {
						y.sprite.setY(z);
						//frames.add(s);
					}
					down = false;
				}
			}
		}
		
		//remove old pieces
		for (Piece y : workingLivePieces) {
			for (Piece x : gameMan.gamePieces) {
				if (y.ID != x.ID) removePiece = true;
				
				if (removePiece) {
					for (float z = y.sprite.getHeight(); z > 0; z--) {
						y.sprite.setOriginCenter();
						y.sprite.setScale(z);
						//frames.add(s);
					}
					removePiece = false;
				}
			}
		}
		
		//create pixmap
	}
	
	private Texture renderPixmap(Array<Piece> lP) {
		Pixmap draw = blackBox.getTextureData().consumePixmap();
		Pixmap tempText;
		BitmapFontData bF = infGame.font.getData();
		
		for (Piece x : lP) {
			draw.drawPixmap(x.sprite.getTexture().getTextureData().consumePixmap(), (int)x.sprite.getX(), (int)x.sprite.getY());
			draw.drawPixmap(, x, y);
		}
		
	}
}
