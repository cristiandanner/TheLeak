package com.thousandeyes.TheLeak.State.Enemy;
import com.thousandeyes.TheLeak.State.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.thousandeyes.TheLeak.Base.*;
import com.badlogic.gdx.*;

public class EnemyConfusedState extends BaseState
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

	public EnemyConfusedState(GameObject _gameObject)
	{
		
		gameObject = _gameObject;
		stateAnimation = AnimationHelper.GetAnimationFromSpritesheet(this.getGameObject().getName() + "-hit-spritesheet.png",3,2,0.1f);
		name = this.getClass().getName();
	}
	@Override
	public void Update()
	{
		super.Update();
		if(getStateTime() >= 1.5f)
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
		if(other.getTag() == "attack")
		{
			this.gameObject.setState(new EnemyHitState(this.gameObject, other.getOwner()));
		}
	}
}
