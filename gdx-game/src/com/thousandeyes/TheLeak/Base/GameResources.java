package com.thousandeyes.TheLeak.Base;
import java.util.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.graphics.g2d.*;

public class GameResources
{
	public static OrthographicCamera Camera;
	public static List<IGameObject> Objects = new ArrayList<IGameObject>();
	public static boolean Debug = true;
	public static ShapeRenderer ShapeRenderer;
	public static SpriteBatch SpriteBatch;
}
