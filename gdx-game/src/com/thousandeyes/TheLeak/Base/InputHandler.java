package com.thousandeyes.TheLeak.Base;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.*;
import com.thousandeyes.TheLeak.State.GameState.*;
public class InputHandler

{
	private static  boolean swipe = false;
	public static String Touches="";
	public static String LastTouches="";
	
	public static Vector2 OriginalTouch;
	public static float  TouchDeltaTime;
	public static Vector2 InputVector(){
		if(Gdx.input.isTouched())
		{
			
			for (int i=0; i<5; i++)
			{ 
				if(Gdx.input.getX(i)<= Gdx.graphics.getWidth() / 2)
				{
					if(InputHandler.OriginalTouch == null)
					{
						InputHandler.OriginalTouch = new Vector2(Gdx.input.getX(i), Gdx.input.getY(i));
						InputHandler.TouchDeltaTime =0f;
						return Vector2.Zero;
					}
					else
					{  
						
						InputHandler.TouchDeltaTime += Gdx.graphics.getDeltaTime();
						if(Math.abs(Gdx.input.getDeltaX(i))/InputHandler.TouchDeltaTime>350)
						{
							String action="Swipe" + (Gdx.input.getDeltaX(i)> 0? "Right":"Left");
							if(!InputHandler.Touches.contains(action))
							{
								InputHandler.Touches += "|" + action;
								swipe = true;
							}
						}
					
						Vector2 vec =new Vector2(Gdx.input.getX(i), Gdx.input.getY(i)).sub(InputHandler.OriginalTouch);
						vec.y *= -1;
						if(Math.abs(vec.x) > 25f || Math.abs(vec.y) > 25f)
							return vec.nor();
						else
							return Vector2.Zero;
					}
				}
				else
				{

					InputHandler.OriginalTouch = null;
					return Vector2.Zero;
				}
			}
			
		}
		
		InputHandler.OriginalTouch = null;
		return Vector2.Zero;
	};
	public static boolean getTouched(String action)
	{
		boolean result = InputHandler.Touches.contains(action);
			if(result)
			{ 
			
				if(!InputHandler.LastTouches.contains(action))
					InputHandler.LastTouches += (InputHandler.LastTouches.equals("")?"":"|") + action;
				InputHandler.Touches = InputHandler.Touches.replace("|" + action,"").replace(action,"");
					
			}
		
		return result;
	}
	
}
