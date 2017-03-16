package com.gusfowler.twoinfinitegame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Piece {
	private BitmapFont font;
	private GlyphLayout text;
	
	public Rectangle rec;
	public float boardX, boardY;
	public int scoreVal;
	
	public Piece(BitmapFont f, float x, float y, float width, float height, float bX, float bY) 
	{
		rec = new Rectangle(x, y, width, height);
		boardX = bX;
		boardY = bY;
		int sV = MathUtils.random(1,2);
		if (sV == 1) scoreVal = 2;
		if (sV == 2) scoreVal = 4;
		text = new GlyphLayout(font, Integer.toString(scoreVal));
	}
	
	public GlyphLayout getGlyph() 
	{
		text.setText(font, Integer.toString(scoreVal));
		return text;
	}
	
	public boolean equals(Piece p) 
	{
		boolean tf = false;
		if (p.boardX == boardX && p.boardY == boardY) tf = true;
		return tf;
	}
}
