package com.gusfowler.twoinfinitegame;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Piece {
	private BitmapFont font;
	
	public GlyphLayout text;
	public Rectangle rec;
	public Sprite sprite;
	public float boardX, boardY;
	public int scoreVal;
	
	public final long ID;
	
	public Piece(BitmapFont f, Sprite bS, float x, float y, float bX, float bY) 
	{
		ID = (long)(MathUtils.random(1000) * x * y * bS.getWidth() * bS.getHeight() * bX * bY);
		
		boardX = bX;
		boardY = bY;
		int sV = MathUtils.random(1,2);
		if (sV == 1) scoreVal = 2;
		if (sV == 2) scoreVal = 4;
		text = new GlyphLayout(font, Integer.toString(scoreVal));
		
		sprite = new Sprite(bS);
		sprite.setX(x);
		sprite.setY(y);
	}
	
	public GlyphLayout getGlyph() 
	{
		text.setText(font, Integer.toString(scoreVal));
		return text;
	}
	
	public boolean equals(Piece p) 
	{
		boolean tf = false;
		if (p.boardX == boardX && p.boardY == boardY || p.ID == ID) tf = true;
		return tf;
	}
}
