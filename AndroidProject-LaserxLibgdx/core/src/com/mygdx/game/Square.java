package com.mygdx.game;

/**
 * Objects of this class represents the the squares on the level screen they are used for checking laser's interaction with game objects.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */

//this class helps to find the places of mirrors and obstacles
public class Square {

    //properties
    private float x;
    private float y;
    private boolean empty;
    private float lengthx;
    private float lengthy;
    //this variable represents the objects in the square on the game screen
    private GameObject object;

    //constructor
    public Square(float x, float y,float lengthx,float lengthy) {
        this.x = x;
        this.y = y;
        this.lengthx = lengthx;
        this.lengthy = lengthy;
        object = null;
    }

    /**
     * setter method for the x coordinate
     * @param x is the x coordinate of the current position of the obstacle
     */
    public void setX( float x) {
        this.x = x;
    }

    /**
     * setter method for the y coordinate
     * @param y is the y coordinate of the current position of the obstacle
     */
    public void setY( float y) {
        this.y = y;
    }


    /**
     * getter method of the y coordinate of the square's left side
     * @return returns x
     */
    public float getX() {
        return x;
    }

    /**
     * getter method of the y coordinate of the square's bottom
     * @return returns y
     */
    public float getY() {
        return y;
    }

    /**
     * this method checks if the square is empty returns true if it is empty and false otherwise
     * @return returns empty
     */
    public boolean isEmpty() {
        return empty;
    }


    /**
     * this method sets the empty variable
     * @param empty must be true if the square is empty and false otherwise
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }


    /**
     * this method gets the objects inside the square if the square is empty it returns null
     * @return the object in the square
     */
    public GameObject getObject() {
        return object;
    }

    /**
     * this method sets the objects inside the square if the square is empty it must be null
     * @param object the object inside the square
     */
    public void setObject(GameObject object) {
        this.object = object;
        setEmpty(false);
    }

    /**
     * this method checks if the coordinates are inside of the square object and returns the square object
     * @param checkX the x coordinate of the point that will be checked
     * @param checkY teh y coordinate of the point that will be checked
     * @return the square object if the coordinates are inside of it and null otherwise
     */
    public Square check(float checkX, float checkY)
    {
        if( x <= checkX && x + lengthx >= checkX && y <= checkY && y + lengthy >= checkY )
        {
            return this;
        }

        else
        {
            return null;
        }
    }

    /**
     * this method returns the object in teh square
     * @param x the x coordinate of the square that will be checked
     * @param y the y coordinate of the square that will be checked
     * @return the object in the square and null if the square is empty
     */
    public GameObject look(float x,float y)
    {
        if(empty)
        {
            return null;
        }

        else
        {
            return this.object;
        }
    }


    /**
     * this method returns the object in the square
     * @return the object in the square and null if the square is empty
     */
    public GameObject look()
    {
        if(empty)
        {
            return null;
        }

        else
        {
            return this.object;
        }
    }
}
