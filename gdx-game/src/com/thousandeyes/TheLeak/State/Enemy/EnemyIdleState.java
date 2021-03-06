package com.thousandeyes.TheLeak.State.Enemy;
import com.badlogic.gdx.graphics.g2d.*;
import com.thousandeyes.TheLeak.Base.*;
import com.badlogic.gdx.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.thousandeyes.TheLeak.State.*;

public class EnemyIdleState extends BaseState
{
	private Animation stateAnimation;
	private GameObject gameObject;
	private String name;
	
	@Override
	public Animation getStateAnimation()
	{
		return stateAnimation;
	}
	
	@Override
	public GameObject getGameObject()
	{
		return gameObject;
	}
	@Override
	public String getName ()
	{
		return name;
	}

	public EnemyIdleState(GameObject _gameObject)
	{
		
		gameObject = _gameObject;
		stateAnimation = AnimationHelper.GetAnimationFromSpritesheet(this.getGameObject().getName() + "-idle-spritesheet.png",3,2,0.1f);
		name = this.getClass().getName();
	}
	@Override
	public void Update()
	{
		super.Update();
		if(getStateTime() >= 1f)
			this.gameObject.setState(new EnemyWalkingState(this.gameObject));
		
		boolean flipFrame = false;
		if
		(
			this.gameObject.getFlipped() && !this.getStateAnimation().getKeyFrame(getStateTime(),true).isFlipX()
			||
			!this.gameObject.getFlipped() && this.getStateAnimation().getKeyFrame(getStateTime(),true).isFlipX()
			)
			flipFrame = true;

		this.getStateAnimation().getKeyFrame(getStateTime(), true).flip(flipFrame,false);
		
		GameResources.SpriteBatch.draw(getStateAnimation().getKeyFrame(getStateTime(), true), getGameObject().getTransform().getCanvas().x,getGameObject().getTransform().getCanvas().y, getGameObject().getTransform().getCanvas().width, getGameObject().getTransform().getCanvas().height);
		
	}

	@Override
	public void onTriggerEnter(Transform other)
	{
		if(other.getTag() == "roll")
			this.gameObject.setState(new EnemyConfusedState(this.gameObject));
		
		if(other.getTag() == "attack")
		{
			this.gameObject.setState(new EnemyHitState(this.gameObject, other.getOwner()));
		}
	}
}
