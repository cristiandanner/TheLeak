package com.thousandeyes.TheLeak.State;
import com.badlogic.gdx.graphics.g2d.*;
import com.thousandeyes.TheLeak.Base.*;
import com.badlogic.gdx.*;
import android.hardware.input.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;

public class PlayerWalkingState implements IState
{
	private Animation stateAnimation;
	private IGameObject gameObject;
	private String name;
	
	@Override
	public Animation getStateAnimation()
	{
		return stateAnimation;
	}

	@Override
	public Transform getCollider()
	{
		return new Transform();
	}

	
	@Override
	public IGameObject getGameObject()
	{
		return gameObject;
	}
	@Override
	public String getName ()
	{
		return name;
	}

	public PlayerWalkingState(IGameObject _gameObject){
		gameObject = _gameObject;
		stateAnimation = AnimationHelper.GetAnimationFromSpritesheet("hero-walking-spritesheet.png",5,2,0.1f);
		name = this.getClass().getName();
	}
	@Override
	public void Update( Float time)
	{
		this.gameObject.getTransform().AddTransform(InputHandler.InputVector(),this.gameObject.getSpeed());
		boolean flipFrame = false;
		if(!this.gameObject.getFlipped() && InputHandler.InputVector().x < 0)
			this.gameObject.setFlipped(true);
		if(this.gameObject.getFlipped() && InputHandler.InputVector().x > 0)
			this.gameObject.setFlipped(false);
		
		if
		(
			this.gameObject.getFlipped() && !this.getStateAnimation().getKeyFrame(time,true).isFlipX()
			||
			!this.gameObject.getFlipped() && this.getStateAnimation().getKeyFrame(time,true).isFlipX()
		)
			flipFrame = true;
			
		if(InputHandler.InputVector()==null || InputHandler.InputVector().equals(Vector2.Zero))
			gameObject.setState(new PlayerIdleState(gameObject));
		
		
		
		this.getStateAnimation().getKeyFrame(time, true).flip(flipFrame,false);
			
		GameResources.SpriteBatch.draw(this.getStateAnimation().getKeyFrame(time, true), getGameObject().getTransform().x,getGameObject().getTransform().y, getGameObject().getTransform().width, getGameObject().getTransform().height);
		

	}

	@Override
	public void onTriggerEnter()
	{
		// TODO: Implement this method
	}

	}
