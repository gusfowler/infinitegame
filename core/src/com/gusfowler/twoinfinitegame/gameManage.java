package com.gusfowler.twoinfinitegame;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class gameManage {
	final GameScreen gScreen;
	
	private int boardMultiplier, spawnPieceTimeout = 64, spawnPieceCounter = 0;
	private float boardPixels, boarderLength, pieceLength;
	private float[] coords;
	
	public Array<Piece> gamePieces;
	
	public gameManage(GameScreen gs)
	{
		this(gs, 336);
	}
	
	public gameManage(GameScreen gs, float bp)
	{
		this(gs, bp, 4);
	}
	
	public gameManage(GameScreen gs, float bp, int bM)
	{
		gScreen = gs;
		boardMultiplier = bM;
		boardPixels = bp;
		boarderLength = (float)(boardPixels * 0.05);
		pieceLength = (float)((boardPixels - (boarderLength * 5)) / 4);
		if (((boarderLength * 5)+(pieceLength * 4)) != boardPixels) System.out.println("math err gameManage construct");
		findCoords();
	}
	
	private void findCoords()
	{
		coords = new float[boardMultiplier];
		for (int x = 0; x < coords.length; x++)
		{
			coords[x] = boarderLength + ((boarderLength + pieceLength) * x);
		}
	}
	
	public void newPiece() 
	{
		spawnPieceCounter++;    
		boolean addPiece = true;
		int xMult = MathUtils.random(0, boardMultiplier - 1);
		int yMult = MathUtils.random(0, boardMultiplier - 1);
		Piece piece = new Piece(gScreen.infGame.font, gScreen.baseSprite, coords[xMult], coords[yMult], xMult, yMult);
		
		for (Piece x : gamePieces) {
			if ((piece.boardX == x.boardX && piece.boardY == x.boardY) || (piece.sprite.getX() == x.sprite.getX() && piece.sprite.getY() == x.sprite.getY())) {
				addPiece = false;
			}
		}
		
		if (addPiece) {
			gamePieces.add(piece);
			spawnPieceCounter = 0;
		}
		if (addPiece == false && spawnPieceCounter < spawnPieceTimeout) {
			newPiece();
		}
		if (addPiece == false && spawnPieceCounter > spawnPieceTimeout) {
			System.out.println("do something for loss game here");
			System.exit(0);
		}
	}
}
