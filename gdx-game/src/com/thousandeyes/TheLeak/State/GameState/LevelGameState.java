package com.thousandeyes.TheLeak.State.GameState;
import com.thousandeyes.TheLeak.Base.*;
import java.util.*;

public class LevelGameState extends GameState
{

	@Override
	public void Update()
	{
		if(InputHandler.getActionPressed())
		{
			GameResources.CurrentGameState = new PauseGameState();
		}
		GameResources.SpriteBatch.draw(GameResources.Level.getBackground(), 0, 0,GameResources.Level.getWidth(), GameResources.Level.getHeight());
		Collections.sort(GameResources.Objects); 
		GameResources.Objects.addAll(GameResources.CreateObjects);
		GameResources.CreateObjects.clear();
		for(GameObject object : GameResources.Objects)
		{
			object.Update();
		}
		GameResources.Objects.removeAll(GameResources.DeleteObjects);
		GameResources.LockingObjects.removeAll(GameResources.DeleteObjects);
		GameResources.DeleteObjects.clear();
	}
	
}