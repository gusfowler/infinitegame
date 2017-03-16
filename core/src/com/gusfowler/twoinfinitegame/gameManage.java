package com.gusfowler.twoinfinitegame;

import com.badlogic.gdx.utils.Array;

public class gameManage {
	final GameScreen gScreen;
	
	private int boardMultiplier;
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
	
	
}
