package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class represents obstacle.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */

public class Obstacle extends GameObject {
    // properties
    private float radius;
    private float x;
    private float y;
    private Texture texture;

    // constructor
    public Obstacle( float radius, float x, float y, String path) {
        setRadius( radius);
        setX( x);
        setY( y);
        texture = new Texture( path);
    }

    @Override
    /**
     * draws the obstacle
     * @param batch is a SpriteBatch
     * @param width is the width of the batch
     * @param height is the height of the batch
     */
    public void draw(SpriteBatch batch, float width, float height) {
        batch.draw( texture, getX(), getY(), width, height);
    }

    @Override
    /**
     * setter method of the texture of image( image file name as string)
     * @param texture is the texture of image( image file name as string)
     */
    public void setTexture( Texture texture) {
        this.texture = texture;
    }

    @Override
    /**
     * getter method of the texture of image( image file name as string)
     * @return returns texture
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * setter method of the radius of the (circular) obstacle
     * @param radius is the radius of the (circular) obstacle
     */
    public void setRadius( float radius) {
        this.radius = radius;
    }

    /**
     * getter method of the radius of the (circular) obstacle
     * @return returns radius
     */
    public float getRadius() {
        return radius;
    }

    @Override
    /**
     * setter method for the x coordinate of the current point
     * @param x is the x coordinate of the current position of the obstacle
     */
    public void setX( float x) {
        this.x = x;
    }

    @Override
    /**
     * setter method for the y coordinate of the current point
     * @param y is the y coordinate of the current position of the obstacle
     */
    public void setY( float y) {
        this.y = y;
    }

    @Override
    /**
     * getter method of the y coordinate of the current position of the obstacle
     * @return returns x
     */
    public float getX() {
        return x;
    }

    @Override
    /**
     * getter method of the y coordinate of the current position of the obstacle
     * @return returns y
     */
    public float getY() {
        return y;
    }
}
