package com.mygdx.game;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Super class for Obstacles and mirrors.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */

public abstract class GameObject extends Sprite {

    public abstract float getX();
    public abstract float getY();
    public abstract void setX(float x);
    public abstract void setY(float y);
    public abstract void draw(SpriteBatch batch,float width,float height);

}
