package com.thousandeyes.TheLeak.Entities;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.thousandeyes.TheLeak.Base.*;
import com.thousandeyes.TheLeak.State.*;

public class Player extends GameObject
{
	public Player(Transform _transform){
		this.setTransform ( _transform);
		this.getTransform().setOwner(this);
		this.setState( new PlayerIdleState(this));
		
	}
	
	
}
