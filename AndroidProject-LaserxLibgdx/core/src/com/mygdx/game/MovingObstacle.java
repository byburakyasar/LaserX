package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This class respresents moving obstacles.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */


public class MovingObstacle extends Obstacle {

    // properties
    private float radius;
    private float speedX;
    private float speedY;
    //limit for move if it is 1 obstacle can go to a lower and higher index
    private int limit;
    private int startingIndex;
    private Texture texture;
    private Levelx levelx;
    private float x;
    private float y;

    // constructor
    public MovingObstacle(float radius, float x, float y, float speedX, float speedY, String path,int limit,int startingIndex,Levelx levelx) {
        super(radius,x,y,path);
        this.speedX = speedX;
        this.speedY = speedY;
        this.limit = limit;
        this.startingIndex = startingIndex;
        this.levelx = levelx;
    }

    /**
     * draw method that draws the batch and sets the objects in the squares it touches as obstacles
     * @param batch is the batch of moving obstacle which will be drawn
     * @param width width of the batch of the moving obstacles
     * @param height height of the batch of the moving obstacles
     */
    @Override
    public void draw( SpriteBatch batch, float width, float height) {

        float length = Math.min(Gdx.graphics.getHeight()*(0.1f), Gdx.graphics.getWidth()*(0.15f));
        super.draw(batch, width, height);
        if(speedY != 0){

            if(levelx.getSquares()[startingIndex].check(x,y) != null || levelx.getSquares()[startingIndex].check(x,y + length) != null)
            {
                levelx.getSquares()[startingIndex].setObject(this);
            }

            else
            {
                levelx.getSquares()[startingIndex].setObject(null);
            }

            if(levelx.getSquares()[startingIndex + limit * 6].check(x,y) != null || levelx.getSquares()[startingIndex + limit * 6].check(x,y + length) != null)
            {
                levelx.getSquares()[startingIndex + limit * 6].setObject(this);
            }

            else
            {
                levelx.getSquares()[startingIndex + limit *6].setObject(null);
            }

            if(levelx.getSquares()[startingIndex - limit * 6].check(x,y) != null || levelx.getSquares()[startingIndex - limit * 6].check(x,y + length) != null)
            {
                levelx.getSquares()[startingIndex - limit * 6].setObject(this);
            }

            else
            {
                levelx.getSquares()[startingIndex - limit *6].setObject(null);
            }
        }

        if(speedX != 0){

            if(levelx.getSquares()[startingIndex].check(x,y) != null || levelx.getSquares()[startingIndex].check(x + length,y) != null)
            {
                levelx.getSquares()[startingIndex].setObject(this);
            }

            else
            {
                levelx.getSquares()[startingIndex].setObject(null);
            }

            if(levelx.getSquares()[startingIndex + limit].check(x,y) != null || levelx.getSquares()[startingIndex + limit].check(x + length,y) != null)
            {
                levelx.getSquares()[startingIndex + limit].setObject(this);
            }

            else
            {
                levelx.getSquares()[startingIndex + limit].setObject(null);
            }

            if(levelx.getSquares()[startingIndex - limit].check(x,y) != null || levelx.getSquares()[startingIndex - limit].check(x+ length,y) != null)
            {
                levelx.getSquares()[startingIndex - limit ].setObject(this);
            }

            else
            {
                levelx.getSquares()[startingIndex - limit ].setObject(null);
            }
        }
    }

    /**
     * updates the x and y variables depending on the speed
     */
    public void update()
    {
        setY(getY() + speedY);
        setX(getX() + speedX);
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

    /**
     * getter method of limit
     * @return
     */
    public int getLimit() { return limit; }

    /**
     * setter method of limit
     * @param limit
     */
    public void setLimit( int limit) { this.limit = limit; }

    /**
     * getter method of starting index
     * @return
     */
    public int getStartingIndex() { return startingIndex; }

    /**
     * setter method of starting index
     * @param startingIndex
     */
    public void setStartingIndex( int startingIndex) {this.startingIndex = startingIndex; }

    /**
     * getter method of speedX
     * @return
     */
    public float getSpeedX() { return speedX;}

    /**
     * setter method of speedX
     * @param speedX
     */
    public void setSpeedX( float speedX) {this.speedX = speedX; }

    /**
     * getter method of sppedY
     * @return speedY
     */
    public float getSpeedY() { return speedY;}

    /**
     * setter method of speedY
     * @param speedY
     */
    public void setSpeedY( float speedY) { this.speedY = speedY; }
}
