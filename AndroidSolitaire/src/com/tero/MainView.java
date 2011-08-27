
// Tero Paananen 2011
// tepaanan@gmail.com
// FINLAND

package com.tero;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MainView extends View {
	private Paint mCanvasPaint;

	private Bitmap mCacheBitmap;
	private boolean mUseCache = false;

	private Rect mScreenSize = new Rect();

	private Rect mCardSize = new Rect();
	private int cardXCap;
	private int cardYCap;
	private int mCardCap;

	private ArrayList<Deck> mSourceDecks = new ArrayList<Deck>();
	private ArrayList<Deck> mTargetDecks = new ArrayList<Deck>();
	private ArrayList<Card> mCards = new ArrayList<Card>();
	private Deck mWasteDeck;
	private Deck mWasteDeck2;
	
	private Card mActiveCard;

	public MainView(Context context) {
		super(context);

		mCanvasPaint = new Paint();
		mCanvasPaint.setColor(0xFF228B22); // Green background
		mCanvasPaint.setAntiAlias(false);
		mCanvasPaint.setFilterBitmap(false);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		// Store current screen size
		mScreenSize.set(0, 0, w, h);

		// Calculate card and decks sizes and positions
		int cw = w / 11;
		mCardSize.set(0, 0, cw, (int) (cw * 1.5));
		Log.v("card size", mCardSize.toString());

		int freeSize = w - cw * 7;
		mCardCap = freeSize / (6+4*2);
		
		int cy = (int)(mScreenSize.height()*0.35);

		// Create all cards
		mCards.add(new Card(1, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club1));
		mCards.add(new Card(2, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club2));
		mCards.add(new Card(3, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club3));
		mCards.add(new Card(4, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club4));
		mCards.add(new Card(5, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club5));
		mCards.add(new Card(6, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club6));
		mCards.add(new Card(7, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club7));
		mCards.add(new Card(8, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club8));
		mCards.add(new Card(9, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club9));
		mCards.add(new Card(10, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club10));
		mCards.add(new Card(11, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club11));
		mCards.add(new Card(12, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club12));
		mCards.add(new Card(13, Card.CardLand.EClub, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.club13));
			
		mCards.add(new Card(1, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond1));
		mCards.add(new Card(2, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond2));
		mCards.add(new Card(3, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond3));
		mCards.add(new Card(4, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond4));
		mCards.add(new Card(5, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond5));
		mCards.add(new Card(6, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond6));
		mCards.add(new Card(7, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond7));
		mCards.add(new Card(8, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond8));
		mCards.add(new Card(9, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond9));
		mCards.add(new Card(10, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond10));
		mCards.add(new Card(11, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond11));
		mCards.add(new Card(12, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond12));
		mCards.add(new Card(13, Card.CardLand.EDiamond, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.diamond13));

		mCards.add(new Card(1, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart1));
		mCards.add(new Card(2, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart2));
		mCards.add(new Card(3, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart3));
		mCards.add(new Card(4, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart4));
		mCards.add(new Card(5, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart5));
		mCards.add(new Card(6, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart6));
		mCards.add(new Card(7, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart7));
		mCards.add(new Card(8, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart8));
		mCards.add(new Card(9, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart9));
		mCards.add(new Card(10, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart10));
		mCards.add(new Card(11, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart11));
		mCards.add(new Card(12, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart12));
		mCards.add(new Card(13, Card.CardLand.EHeart, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.heart13));
		
		mCards.add(new Card(1, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade1));
		mCards.add(new Card(2, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade2));
		mCards.add(new Card(3, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade3));
		mCards.add(new Card(4, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade4));
		mCards.add(new Card(5, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade5));
		mCards.add(new Card(6, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade6));
		mCards.add(new Card(7, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade7));
		mCards.add(new Card(8, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade8));
		mCards.add(new Card(9, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade9));
		mCards.add(new Card(10, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade10));
		mCards.add(new Card(11, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade11));
		mCards.add(new Card(12, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade12));
		mCards.add(new Card(13, Card.CardLand.ESpade, 0, getResources(), 0, 0, mCardSize.width(), mCardSize.height(), R.raw.spade13));
		
		// TODO: Suffle card list by Random

		
		
		// Create source decks
		// Add cards to the source decks
		if (mSourceDecks.size()==0) {
			Deck deck = new Deck(Deck.DeckType.ESource, mCardCap*4, cy, mCardSize.width(), mCardSize.height());
			Card c = mCards.remove(0);
			c.mTurned = true;
			deck.addCard(c, false);
			mSourceDecks.add(deck);
			
			deck = new Deck(Deck.DeckType.ESource, mCardCap*5+mCardSize.width(), cy, mCardSize.width(), mCardSize.height());
			for(int i=0;i<2;i++) {
				c = mCards.remove(0);
				deck.addCard(c, false);
			}
			c.mTurned = true;
			mSourceDecks.add(deck);
			
			deck = new Deck(Deck.DeckType.ESource, mCardCap*6+mCardSize.width()*2, cy, mCardSize.width(), mCardSize.height());
			for(int i=0;i<3;i++) {
				c = mCards.remove(0);
				deck.addCard(c, false);
			}
			c.mTurned = true;
			mSourceDecks.add(deck);
			
			deck = new Deck(Deck.DeckType.ESource, mCardCap*7+mCardSize.width()*3, cy, mCardSize.width(), mCardSize.height());
			for(int i=0;i<4;i++) {
				c = mCards.remove(0);
				deck.addCard(c, false);
			}
			c.mTurned = true;
			mSourceDecks.add(deck);
			
			deck = new Deck(Deck.DeckType.ESource, mCardCap*8+mCardSize.width()*4, cy, mCardSize.width(), mCardSize.height());
			for(int i=0;i<5;i++) {
				c = mCards.remove(0);
				deck.addCard(c, false);
			}
			c.mTurned = true;
			mSourceDecks.add(deck);
			
			deck = new Deck(Deck.DeckType.ESource, mCardCap*9+mCardSize.width()*5, cy, mCardSize.width(), mCardSize.height());
			for(int i=0;i<6;i++) {
				c = mCards.remove(0);
				deck.addCard(c, false);
			}
			c.mTurned = true;
			mSourceDecks.add(deck);
			
			deck = new Deck(Deck.DeckType.ESource, mCardCap*10+mCardSize.width()*6, cy, mCardSize.width(), mCardSize.height());
			for(int i=0;i<7;i++) {
				c = mCards.remove(0);
				deck.addCard(c, false);
			}
			c.mTurned = true;
			mSourceDecks.add(deck);


			// Create target decks
			deck = new Deck(Deck.DeckType.ETarget, mCardCap*7+mCardSize.width()*3, mCardCap, mCardSize.width(), mCardSize.height());
			for(int i=0;i<4;i++) {
				c = mCards.remove(0);
				deck.addCard(c, true);
			}
			mTargetDecks.add(deck);
			
			deck = new Deck(Deck.DeckType.ETarget, mCardCap*8+mCardSize.width()*4, mCardCap, mCardSize.width(), mCardSize.height());
			for(int i=0;i<4;i++) {
				c = mCards.remove(0);
				deck.addCard(c, true);
			}
			mTargetDecks.add(deck);
			
			deck = new Deck(Deck.DeckType.ETarget, mCardCap*9+mCardSize.width()*5, mCardCap, mCardSize.width(), mCardSize.height());
			for(int i=0;i<4;i++) {
				c = mCards.remove(0);
				deck.addCard(c, true);
			}
			mTargetDecks.add(deck);

			deck = new Deck(Deck.DeckType.ETarget, mCardCap*10+mCardSize.width()*6, mCardCap, mCardSize.width(), mCardSize.height());
			for(int i=0;i<4;i++) {
				c = mCards.remove(0);
				deck.addCard(c, true);
			}
			mTargetDecks.add(deck);
			
			// Waste decks
			mWasteDeck = new Deck(Deck.DeckType.EWaste1, mCardCap*4, mCardCap, mCardSize.width(), mCardSize.height());

			while (!mCards.isEmpty()) {
				c = mCards.remove(0);
				mWasteDeck.addCard(c, true);
			}

			mWasteDeck2 = new Deck(Deck.DeckType.EWaste2, mCardCap*5+mCardSize.width(), mCardCap, mCardSize.width(), mCardSize.height());

		}

		
		
	}

	private void enableCache(boolean enabled) {
		if(enabled && mUseCache!=enabled) {
			mActiveCard.mVisible = false;
			setDrawingCacheEnabled(true);
			//buildDrawingCache();
			mCacheBitmap = Bitmap.createBitmap(getDrawingCache()); 
			mActiveCard.mVisible = true;
		} else if(!enabled && mUseCache!=enabled) {
			setDrawingCacheEnabled(false);
			mCacheBitmap = null;
		}
		mUseCache = enabled;
	}

	@Override
	public void onDraw(Canvas canvas) {

		// Cache?
		if (mUseCache) {
			// Yes
			canvas.drawBitmap(mCacheBitmap, 0, 0, null);
		} else {
			// No
			mCanvasPaint.setStyle(Style.FILL);
			canvas.drawRect(mScreenSize, mCanvasPaint);
			// Draw decks
			for (Deck deck : mSourceDecks) {
				deck.doDraw(canvas);
			}
			for (Deck deck : mTargetDecks) {
				deck.doDraw(canvas);
			}
			mWasteDeck.doDraw(canvas);
			mWasteDeck2.doDraw(canvas);
		}

		// Draw active card last
		if (mActiveCard!=null) {
			mActiveCard.doDraw(canvas);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			mActiveCard = null;
			// Search card under source decks
			for (Deck deck : mSourceDecks) {
				if (deck.isUnderTouch(x, y)) {
					mActiveCard = deck.getCardFromPos(x, y);
					break;
				} 
			}
			// Search card from waste decks
			if (mActiveCard==null) {
				if (mWasteDeck.isUnderTouch(x, y)) {
					mActiveCard = mWasteDeck.getCardFromPos(x, y);
				} 
				if (mWasteDeck2.isUnderTouch(x, y)) {
					mActiveCard = mWasteDeck2.getCardFromPos(x, y);
				} 
			}
			// Card founds?
			if (mActiveCard!=null) {
				cardXCap = x - mActiveCard.mRect.left;
				cardYCap = y - mActiveCard.mRect.top;
				mActiveCard.storePosition(x - cardXCap, y - cardYCap);
				enableCache(true);
				invalidate();
			}
			
			// Log.v("", "down");
			return true;

		} else if (action == MotionEvent.ACTION_MOVE) {
			int x = (int) event.getX();
			int y = (int) event.getY();
			if (mActiveCard!=null) {
				mActiveCard.setPos(x - cardXCap, y - cardYCap);
				invalidate();
			}
			return true;

		} else if (action == MotionEvent.ACTION_UP) {
			enableCache(false);
			if(mActiveCard!=null)
				mActiveCard.cancelMove();
			mActiveCard = null;
			invalidate();
			return true;
		}
		return false;

	}

	
	
}
