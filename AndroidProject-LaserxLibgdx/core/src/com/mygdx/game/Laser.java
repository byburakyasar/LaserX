package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.Mirror.mirrorType;

/**
 * This class represents the laser.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */

public class Laser {
    // CONSTANT
    private final float SPEED = 2f;

    // instance variables
    private float x;
    private float y;
    private float speedX;
    private float speedY;
    private Levelx level;
	private float width;
	private float height;
    Square square;
    Mirror myMirror;



    // constructor
    public Laser(float x, float y, Levelx level,float width,float height) {
        this.x = x;
        this.y = y;
        this.level = level;
        speedX = SPEED;
        speedY = 0;
        this.width = width;
        this.height = height;
    }

    /**
     * Draws the batch of the laser
     * @param renderer is the instance of ShapeRenderer which will be drawn
     */
        public void draw(ShapeRenderer renderer) {
            renderer.rect(x, y, width, height);
            x = x + speedX;
            y = y + speedY;
            intersect();
    }

    /**
     * Takes action depending on which game object that the laser encounter
     */
    public void intersect() {
        for (Square s : level.getSquares()) {
            if (s.check(x, y) != null) {
                square = s;
            }
        }

        if (square != null && square.getObject() != null) {
            if (square.look() instanceof Obstacle) {
                level.init();
            }

            if (square.look() instanceof Mirror) {
                if(myMirror == null)
                {
                    myMirror = (Mirror) square.look();
                    reflect();
                    level.getSound().play(1.0f);
                }
                if( myMirror != null && ((Mirror) square.look()).mirrorType != myMirror.mirrorType) {
                    myMirror = (Mirror) square.look();
                    reflect();
                    level.getSound().play(1.0f);
                }
            }
        }
    }

    /**
     * Reflects the laser depending on which type of mirror that the laser encounter
     */
	public void reflect(){

        float length = Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f));

        if( speedX > 0 ) {
			if (myMirror.mirrorType == mirrorType.NORTH_EAST) {
				speedY = -speedX;
				speedX = 0;
                float initValue = width;
                width = height;
                height = initValue;
                setX(square.getX()+ length/2);
                setY(square.getY() + length/2);

			} else if (myMirror.mirrorType == mirrorType.NORTH_WEST) {
				speedX = -speedX;

			} else if (myMirror.mirrorType == mirrorType.SOUTH_EAST) {
				speedY = speedX;
				speedX = 0;
                float initValue = width;
                width = height;
                height = initValue;
                setX(square.getX()+ length/2);
                setY(square.getY() + length/2);

			} else if (myMirror.mirrorType == mirrorType.SOUTH_WEST) {
				speedX = -speedX;
			}
		}

		else if( speedX < 0 ){
			if (myMirror.mirrorType == mirrorType.NORTH_EAST) {
				speedX = -speedX;

			} else if (myMirror.mirrorType == mirrorType.NORTH_WEST) {
				speedY = speedX;
				speedX = 0;
                float initValue = width;
                width = height;
                height = initValue;
                setX(square.getX()+ length/2);
                setY(square.getY() + length/2);

			} else if (myMirror.mirrorType == mirrorType.SOUTH_EAST) {
				speedX = -speedX;

			} else if (myMirror.mirrorType == mirrorType.SOUTH_WEST) {
				speedY = -speedX;
				speedX = 0;
                float initValue = width;
                width = height;
                height = initValue;
                setX(square.getX()+ length/2);
                setY(square.getY() + length/2);
			}
		}
		else {
			if(speedY > 0) {
				if (myMirror.mirrorType == mirrorType.NORTH_EAST) {
					speedX = -speedY;
					speedY = 0;
                    float initValue = width;
                    width = height;
                    height = initValue;
                    setX(square.getX()+ length/2);
                    setY(square.getY() + length/2);

				} else if (myMirror.mirrorType == mirrorType.NORTH_WEST) {
					speedX = speedY;
					speedY = 0;
                    float initValue = width;
                    width = height;
                    height = initValue;
                    setX(square.getX()+ length/2);
                    setY(square.getY() + length/2);

				} else if (myMirror.mirrorType == mirrorType.SOUTH_EAST) {
					speedY = -speedY;

				} else if (myMirror.mirrorType == mirrorType.SOUTH_WEST) {
					speedY = -speedY;
				}
			} else {
				if (myMirror.mirrorType == mirrorType.NORTH_EAST) {
					speedY = -speedY;

				} else if (myMirror.mirrorType == mirrorType.NORTH_WEST) {
					speedY = -speedY;

				} else if (myMirror.mirrorType == mirrorType.SOUTH_EAST) {
					speedX = speedY;
					speedY = 0;
                    float initValue = width;
                    width = height;
                    height = initValue;
                    setX(square.getX()+ length/2);
                    setY(square.getY() + length/2);

				} else if (myMirror.mirrorType == mirrorType.SOUTH_WEST) {
					speedX = -speedY;
					speedY = 0;
                    float initValue = width;
                    width = height;
                    height = initValue;
                    setX(square.getX()+ length/2);
                    setY(square.getY() + length/2);
				}
			}
		}
	}

    /**
     * setter method for speedX
     * @param speedX is the speed of the laser in x axis
     */
    public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}

    /**
     * setter method for speedY
     * @param speedY is the speed of the laser in y axis
     */
	public void setSpeedY(float speedY) {
		this.speedY = speedY;
	}

    /**
     * getter method of the variable speedX
     * @return returns speedX
     */
	public float getSpeedX() {
		return speedX;
	}

    /**
     * getter method of the variable speedY
     * @return returns speedY
     */
	public float getSpeedY() {
		return speedY;
	}

    /**
     * setter method for the x coordinate of the current point
     * @param x is the x coordinate of the current position of the laser
     */
	public void setX(float x) {
		this.x = x;
	}

    /**
     * setter method for the y coordinate of the current point
     * @param y is the y coordinate of the current position of the laser
     */
	public void setY(float y) {
		this.y = y;
	}

    /**
     * getter method of the x coordinate of the current position of the laser
     * @return returns x
     */
	public float getX() {
		return x;
	}

    /**
     * getter method of the y coordinate of the current position of the laser
     * @return returns y
     */
	public float getY() {
		return y;
	}

    /**
     * getter method of the width of the laser Texture
     * @return returns width
     */
    public float getWidth() { return width; }

    /**
     * setter method for width of the laser Texture
     * @param width is the width of the laser Texture
     */
    public void setWidth(float width) { this.width = width; }

    /**
     * getter method of the height of the laser Texture
     * @return returns height
     */
    public float getHeight() { return height; }

    /**
     * setter method for height of the laser Texture
     * @param height is the height of the laser Texture
     */
    public void setHeight(float height) { this.height = height; }
}
