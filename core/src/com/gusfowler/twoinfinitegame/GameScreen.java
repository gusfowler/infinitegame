package com.gusfowler.twoinfinitegame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {
	final InfiniteGame infGame;
	
	Texture blackBox, whiteBox;
	Rectangle gameBoard;
	OrthographicCamera camera;
	Array<Piece> livePieces;
	gameManage gameMan;
	
	private int spawnPieceTimeout = 64, spawnPieceCounter = 0;
	
	public GameScreen(InfiniteGame g) {
		infGame = g;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, infGame.vsWidth, infGame.vsHeight);
		
		blackBox = new Texture(Gdx.files.internal(infGame.assetPath + "data/blackbox.png"));
		gameBoard = new Rectangle(((infGame.vsWidth / 2) - (blackBox.getWidth() / 2)), ((infGame.vsHeight / 2) - (blackBox.getHeight() / 2)), (float)blackBox.getWidth(), (float)blackBox.getHeight());
		whiteBox = new Texture(Gdx.files.internal(infGame.assetPath + "data/whitebox.png"));
		livePieces = new Array<Piece>();
		gameMan = new gameManage(this, blackBox.getWidth());
		
		spawnPiece();
		spawnPiece();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		infGame.batch.setProjectionMatrix(camera.combined);

		infGame.batch.begin();
		infGame.batch.draw(blackBox, gameBoard.x, gameBoard.y, gameBoard.width, gameBoard.height);
		drawPieces();
		infGame.batch.end();
		
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) spawnPiece();
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

	private void spawnPiece() {
		spawnPieceCounter++;    
		boolean addPiece = true;
		int xMult = MathUtils.random(0, 3);
		int yMult = MathUtils.random(0, 3);
		Piece piece = new Piece(infGame.font, (gameBoard.x + (16 * (xMult + 1)) + (whiteBox.getWidth() * xMult)), (gameBoard.y + (16 * (yMult + 1)) + (whiteBox.getHeight() * yMult)), (float)whiteBox.getWidth(), (float)whiteBox.getHeight());
		
		for (Piece x : gameMan.gamePieces) {
			if (piece.rec.x == x.rec.x && piece.rec.y == x.rec.y) {
				addPiece = false;
			}
		}
		
		if (addPiece) {
			gameMan.gamePieces.add(piece);
			spawnPieceCounter = 0;
		}
		if (addPiece == false && spawnPieceCounter < spawnPieceTimeout) {
			spawnPiece();
		}
		if (addPiece == false && spawnPieceCounter > spawnPieceTimeout) {
			System.out.println("do something for loss game here");
			System.exit(0);
		}
	}
	
	private void drawPiece(Piece p) {
		infGame.batch.draw(whiteBox, p.rec.x, p.rec.y, p.rec.width, p.rec.height);
		infGame.font.draw(infGame.batch, p.getGlyph(), (float)(p.rec.x + (p.rec.width / 2) - (p.getGlyph().width / 2)), (float)(p.rec.y + (p.rec.height / 2) + (p.getGlyph().height / 2)));
	}
	
	private void drawPieces() {
		for (Piece pieces : gameMan.gamePieces) {
			drawPiece(pieces);
		}
	}
}
