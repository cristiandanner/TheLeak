package com.thousandeyes.TheLeak.State.Enemy;
import com.badlogic.gdx.graphics.g2d.*;
import com.thousandeyes.TheLeak.Base.*;
import com.badlogic.gdx.*;
import android.hardware.input.*;
import com.badlogic.gdx.math.*;
import java.util.*;
import com.thousandeyes.TheLeak.State.*;

public class EnemyAttackState implements IState
{
	private Animation stateAnimation;
	private GameObject gameObject;
	private float stateTime;
	private String name;
	private List<Transform> colliders;
	private List<GameObject> collisions;
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

	@Override
	public Transform getCollider()
	{
		double i = Math.floor(stateAnimation.getKeyFrameIndex(stateTime)/(stateAnimation.getKeyFrames().length/colliders.size()));
		Transform collider = colliders.get((int)i);
		if(this.gameObject.getFlipped())
		{
			return new Transform(collider.x - collider.width - this.gameObject.getTransform().width, collider.y,collider.getWidthPercentage(), collider.getHeightPercentage());
		}
		return collider;
	}


	public EnemyAttackState(GameObject _gameObject){
		stateTime = 0f;
		gameObject = _gameObject;
		stateAnimation = AnimationHelper.GetAnimationFromSpritesheet(this.getGameObject().getName()+"-attack-spritesheet.png",5,2,0.1f);
		stateAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		name = this.getClass().getName();
		colliders = new ArrayList<Transform>();
		colliders.add(new Transform(gameObject.getTransform().x +gameObject.getTransform().width, gameObject.getTransform().y, 10f,10f));
		colliders.add(new Transform(gameObject.getTransform().x +gameObject.getTransform().width, gameObject.getTransform().y, 20f,10f));
		collisions = new ArrayList<GameObject>();

	}
	@Override
	public void Update()
	{
		stateTime  += Gdx.graphics.getDeltaTime();
		
		if(this.getStateAnimation().isAnimationFinished(stateTime))
		{
			gameObject.setState(new EnemyIdleState(gameObject));
		}
	
		boolean flipFrame = false;
		if
		(
			this.gameObject.getFlipped() && !this.getStateAnimation().getKeyFrame(stateTime,true).isFlipX()
			||
			!this.gameObject.getFlipped() && this.getStateAnimation().getKeyFrame(stateTime,true).isFlipX()
		)
			flipFrame = true;
		
		for(GameObject objy : GameResources.Objects)
		{ 
			if(this.gameObject != objy && !collisions.contains(objy))
			{
				if(this.gameObject.getCollider().overlaps(objy.getTransform()))
				{
					objy.getState().onTriggerEnter(this.gameObject);
					collisions.add(objy);
				}
			}
		}

		this.getStateAnimation().getKeyFrame(stateTime, true).flip(flipFrame,false);
		
		GameResources.SpriteBatch.draw(getStateAnimation().getKeyFrame(stateTime, true), getGameObject().getTransform().getCanvas().x,getGameObject().getTransform().getCanvas().y, getGameObject().getTransform().getCanvas().width, getGameObject().getTransform().getCanvas().height);
		
	}

	@Override
	public void onTriggerEnter(GameObject other)
	{
		this.gameObject.setState(new EnemyHitState(this.gameObject, other));
	}
}