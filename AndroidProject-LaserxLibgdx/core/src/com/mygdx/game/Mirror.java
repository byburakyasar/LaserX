package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Mirror.mirrorType;

/**
 * Mirror class for mirror objects.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */
//mirror class
public class Mirror extends GameObject {

    //properties
    private float x;
    private float y;
    //initial x and y coordinates
    public  float initX ;
    public  float initY ;
    //booleans for selection and placing
    private boolean selected;
    private boolean placed;
    private Texture texture;
    public mirrorType mirrorType ;
    private Levelx myLevel = null;
    private Square squareLinked;
    private float width; //width= height is assumed isosceles triangle



    //mirror types
    public enum mirrorType
    {SOUTH_WEST, NORTH_EAST ,NORTH_WEST,SOUTH_EAST}//The direction which the right angle points

    //constructor
    public Mirror(float x, float y,String path ,mirrorType mirrorType,float width,Levelx levelx) {
        this.x = x;
        this.y = y;
        initX = x;
        initY = y;
        this.mirrorType = mirrorType;
        texture = new Texture(path) ;
        this.width = width;
        myLevel = levelx;
        selected = false;
        placed = false;
    }

    /**
     * This method checks if the mirror is placed.
     * @return  true if the mirror is placed in a square and false otherwise
     */
    public boolean isPlaced() {
        return placed;
    }

    /**
     * This method sets if the mirror is placed.
     * @param placed must be true if the mirror is placed in a square and false otherwise
     */
    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    /**
     * This method checks if the mirror is selected by the user.
     * @return true if the user touches the mirror and false otherwise
     */
    public boolean isSelected()
    {
        return selected;
    }

    /**
     * This method controls if the mirror is selected by the user.
     * @param selected must be true if the user touches the mirror and false otherwise
     */
    public void setSelected(boolean selected) {

        this.selected = selected;
    }

    //draws the mirror
    @Override
    public void draw(SpriteBatch batch,float width,float height) {
        batch.draw(texture,this.x,this.y,width,height);
    }

    @Override
    public float getX() {

        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setX(float x) {

        this.x = x;
    }

    @Override
    public void setY(float y) {

        this.y = y;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }


    /**
     * This method places the mirror to the closest square when it is dragged and dropped by the user.
     * and if the square has another objects this method throws the mirror to its initial position under the grid.
     */
    public void place() {
        if(isPlaced())
        {
            squareLinked.setObject(null);
            squareLinked = null ;
        }
        for (int i = 0; i < myLevel.getSquares().length; i++) {
            if (myLevel.getSquares()[i].check(x + (width / 2), y + (width / 2)) != null) {
                if (myLevel.getSquares()[i].look() == null) {
                    myLevel.getSquares()[i].setObject(this);
                    x = myLevel.getSquares()[i].getX();
                    y = myLevel.getSquares()[i].getY();
                    squareLinked = myLevel.getSquares()[i] ;
                    placed = true ;
                    return;
                } else if (myLevel.getSquares()[i].look() instanceof Mirror) {
                    myLevel.getSquares()[i].getObject().setX(((Mirror) myLevel.getSquares()[i].getObject()).initX);
                    myLevel.getSquares()[i].getObject().setY(((Mirror) myLevel.getSquares()[i].getObject()).initY);
                    myLevel.getSquares()[i].setObject(this);
                    x = myLevel.getSquares()[i].getX();
                    y = myLevel.getSquares()[i].getY();
                    squareLinked = myLevel.getSquares()[i] ;
                    placed = true ;
                    return;
                }
            }
        }
        x = initX;
        y = initY;
        return ;
    }

    /**
     * This method checks if the given coordinates are inside of the bounding box of the mirror.
     * @param checkX the x coordinate of the point which will be checked
     * @param checkY the y coordinate of the point which will be checked
     * @return the mirror object if the point is inside of it and null otherwise
     */
    public Mirror contains(float checkX,float checkY)
    {
        if( x <= checkX && x + width >= checkX && y <= checkY && y + width >= checkY ) {
            return this;
        }
        return null ;
    }
}
