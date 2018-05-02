package com.mygdx.game;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import static com.badlogic.gdx.Gdx.input;

/**
 * This class is the super class of level classes.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */
public abstract class Levelx implements Screen,InputProcessor {

    //properties
    //SpriteBatch and ShapeRenderer for drawing
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    //touchCoordinates for interaction with the user
    private Vector2 touchCoordinates;
    //arrays for mirrors obstacles and the squares on the screen
    private Mirror[] mirrors;
    private Obstacle[] obstacles;
    private Square[] squares;
    //entering and leaving indices the square numbers start from zero on the left bottom and increases to right
    //when a row is full the squares in the upper row are numbered from left
    private int enteringIndex;
    private int leavingIndex;
    //booleans for controlling if the game over and lost situations started controls if the laser enters the screen
    private boolean over;
    private boolean started;
    private boolean lost;
    //textures
    private Texture laserGun;
    private Texture satellite;
    private Texture background;
    //startTime is called when the level starts
    private long startTime;
    //mirror object the one being moved by the user
    private Mirror draggingMirror;
    //laser object
    private Laser laser;
    //score and highestScore
    private int score;
    private int highestScore;
    //sound is used when the laser interacts with a mirror
    private Sound sound;
    //gameTime is the time between starting and the time laser enters the screen
    private long gameTime;


    /**
     * This method gets the time duration between start of the level and the laser's entering time.
     * @return the game time
     */
    public long getGameTime() {
        return gameTime;
    }

    /**
     * This method sets the time duration between start of the level and the laser's entering moment.
     * @param gameTime  the time duration between start of the level and the laser's entering moment
     */
    public void setGameTime(long gameTime) {
        this.gameTime = gameTime;
    }

    /**
     * This method gets the sound called whenever the laser is reflected.
     * @return  the sound called whenever the laser is reflected
     */
    public Sound getSound() {
        return sound;
    }

    /**
     * This method sets the sound called whenever the laser is reflected.
     * @param sound the sound called whenever the laser is reflected
     */
    public void setSound(Sound sound) {
        this.sound = sound;
    }

    /**
     * This method gets the started variable which is called when the laser enters the screen if the laser entered it returns true and false
     * otherwise.
     * @return true if the laser has entered to screen and false otherwise.
     */
    public boolean isStarted() {
        return started;
    }

    /**
     * This method sets the started variable which checks if the laser has entered to screen.
     * @param started must be true if the laser has entered and false otherwise.
     */
    public void setStarted(boolean started) {
        this.started = started;
    }

    /**
     * This method gets the index number of the square which the laser enters.
     * @return  the index number of the square which the laser enters
     */
    public int getEnteringIndex() {
        return enteringIndex;
    }

    /**
     * This method sets the index number of the square which the laser enters.
     * @param enteringIndex the index number of the square which the laser enters
     */
    public void setEnteringIndex(int enteringIndex) {
        this.enteringIndex = enteringIndex;
    }

    /**
     * This method gets the index number of the square which the laser leaves the screen.
     * @return  the index number of the square which the laser leaves the screen
     */
    public int getLeavingIndex() {
        return leavingIndex;
    }

    /**
     * This method sets the index number of the square which the laser leaves the screen.
     * @param leavingIndex the index number of the square which the laser leaves the screen
     */
    public void setLeavingIndex(int leavingIndex) {
        this.leavingIndex = leavingIndex;
    }

    /**
     * This method checks if the game is lost.
     * @return lost variable that returns true when the game is lost and false otherwise.
     */
    public boolean isLost() {
        return lost;
    }

    /**
     * This method sets if the game is lost.
     * @param lost variable that must be true when the game is lost and false otherwise.
     */
    public void setLost(boolean lost) {
        this.lost = lost;
    }

    /**
     * This method gets the SpriteBatch variable used for drawing in the level.
     * @return the SpriteBatch variable used for drawing in the level
     */
    public SpriteBatch getBatch() {
        return batch;
    }

    /**
     * This method sets the SpriteBatch variable used for drawing in the level.
     * @param batch  the SpriteBatch variable used for drawing in the level
     */
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    /**
     * This method gets the ShapeRenderer variable used for drawing in the level.
     * @return the ShapeRenderer variable used for drawing in the level
     */
    public ShapeRenderer getRenderer() {
        return renderer;
    }

    /**
     * This method sets the ShapeRenderer variable used for drawing in the level.
     * @param renderer the ShapeRenderer variable used for drawing in the level
     */
    public void setRenderer(ShapeRenderer renderer) {
        this.renderer = renderer;
    }

    /**
     * This method gets the point where the user touches the screen.
     * @return  the touch coordinates on the screen as a Vector2
     */
    public Vector2 getTouchCoordinates() {
        return touchCoordinates;
    }

    /**
     * This method sets the point where the user touches the screen.
     * @param touchCoordinates the point where the user touches the screen
     */
    public void setTouchCoordinates(Vector2 touchCoordinates) {
        this.touchCoordinates = touchCoordinates;
    }


    /**
     * This method gets the mirrors in the level.
     * @return the mirrors array
     */
    public Mirror[] getMirrors() {
        return mirrors;
    }

    /**
     * This method sets the mirrors in the level.
     * @param mirrors the mirrors array
     */
    public void setMirrors(Mirror[] mirrors) {
        this.mirrors = mirrors;
    }

    /**
     * This method gets the obstacles in the level.
     * @return the obstacle array
     */
    public Obstacle[] getObstacles() {
        return obstacles;
    }

    /**
     * This method sets the obstacles in the level.
     * @param obstacles the obstacle array
     */
    public void setObstacles(Obstacle[] obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * This method sets the squares in the level.
     * @param squares the square array
     */
    public void setSquares(Square[] squares) {
        this.squares = squares;
    }

    /**
     * This method checks if the game is over.
     * @return true if the user has finished the level and false otherwise
     */
    public boolean isOver() {
        return over;
    }

    /**
     * This method gets the user's score.
     * @return score for this level
     */
    public int getScore() {
        return score;
    }

    /**
     * This method sets the user's score.
     * @param score score for this level
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * This method gets the user's highestScore.
     * @return highestScore for this level
     */
    public int getHighestScore() {
        return highestScore;
    }

    /**
     * This method sets the user's highestScore.
     * @param highestScore  highestScore for this level
     */
    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    /**
     * This method gets the squares in the level.
     * @return  squares the square array
     */
    public Square[] getSquares() {
        return squares;
    }

    /**
     * This method sets the over boolean in the level.
     * @param over must be true when the level is finished and false otherwise.
     */
    public void setOver(boolean over) {
        this.over = over;
    }

    /**
     * This method gets the laser gun image.
     * @return the laser gun image.
     */
    public Texture getLaserGun() {
        return laserGun;
    }

    /**
     * This method sets the laser gun image.
     * @param laserGun the laser gun image.
     */
    public void setLaserGun(Texture laserGun) {
        this.laserGun = laserGun;
    }

    /**
     * This method gets the satellite image.
     * @return the satellite image.
     */
    public Texture getSatellite() {
        return satellite;
    }

    /**
     * This method sets the satellite image.
     * @param satellite  the satellite image.
     */
    public void setSatellite(Texture satellite) {
        this.satellite = satellite;
    }

    /**
     * This method gets the background image.
     * @return the background image.
     */
    public Texture getBackground() {
        return background;
    }

    /**
     * This method sets the background image.
     * @param background the background image.
     */
    public void setBackground(Texture background) {
        this.background = background;
    }

    /**
     * This method gets the start time variable.
     * @return  the start time.
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * This method sets the start time variable.
     * @param startTime the start time.
     */
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * This method gets the mirror which is interacting withe the user.
     * @return  the mirror the user interacts with
     */
    public Mirror getDraggingMirror() {
        return draggingMirror;
    }


    /**
     * This method sets the mirror which is interacting withe the user.
     * @param draggingMirror the mirror the user interacts with
     */
    public void setDraggingMirror(Mirror draggingMirror) {
        this.draggingMirror = draggingMirror;
    }

    /**
     * This method gets the laser object of the class.
     * @return the laser object of the class
     */
    public Laser getLaser() {
        return laser;
    }

    /**
     * This method sets the laser object of the class.
     * @param laser the laser object of the class
     */
    public void setLaser(Laser laser) {
        this.laser = laser;
    }

    @Override
    public abstract void show();

    @Override
    public abstract void render(float delta);

    @Override
    public void hide() {
    }

    //this method initializes variables of levelx class
    public abstract void init();


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }


    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public abstract boolean touchDown(int screenX, int screenY, int pointer, int button);

    @Override
    public abstract boolean touchUp(int screenX, int screenY, int pointer, int button);

    @Override
    public abstract boolean touchDragged(int screenX, int screenY, int pointer);

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    /**
     * This method controls the move of the moving obstacle objects in the level changes their direction if they reach their limit square.
     * @param obstacle moving obstacle object which will be checked for limits
     */
    public void checkMovingObstacle(MovingObstacle obstacle)
    {
        float length = Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f));
        if(obstacle.getSpeedX() != 0)
        {
            if(obstacle.getX()+2*obstacle.getRadius()>getSquares()[obstacle.getStartingIndex()+obstacle.getLimit()].getX())
            {
                obstacle.setSpeedX(-obstacle.getSpeedX());
            }

            if(obstacle.getX() < getSquares()[obstacle.getStartingIndex()-obstacle.getLimit()].getX() + length)
            {
                obstacle.setSpeedX(-obstacle.getSpeedX());
            }
        }

        if(obstacle.getSpeedY() != 0)
        {
            if(obstacle.getY()+2*obstacle.getRadius() > getSquares()[obstacle.getStartingIndex()+obstacle.getLimit()*6].getY() + length)
            {
                obstacle.setSpeedY(-obstacle.getSpeedY());
            }

            if(obstacle.getY() < getSquares()[obstacle.getStartingIndex()-obstacle.getLimit()*6].getY())
            {
                obstacle.setSpeedY(-obstacle.getSpeedY());
            }
        }
    }

    /**
     * This method checks the move of the laser object in the level for sides of the grid and ends the level if the laser hits to sides.
     */
    public void checkLaser()
    {
        float length = Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f));
        if(getSquares()[getLeavingIndex()] == getSquares()[getLeavingIndex()].check(laser.getX(),laser.getY()))
        {
            setOver(true);
            setLost(false);
            gameOver();
            init();
        }

        if((getLaser().getX() + getLaser().getWidth() > getSquares()[5].getX() + length && getLaser().getSpeedX() >0) || (getLaser().getX() - getLaser().getWidth() < getSquares()[0].getX() && getLaser().getSpeedX() <0)
                || (getLaser().getY() + getLaser().getHeight()  > getSquares()[35].getY() + length && getLaser().getSpeedY() > 0) || (getLaser().getY() - getLaser().getHeight() < getSquares()[0].getY() && getLaser().getSpeedY() < 0))
        {
            setLost(true);
            setOver(true);
            gameOver();
            init();
        }

    }

    @Override
    public abstract void dispose();
    public abstract void addObstacles();
    public abstract void addMirrors();

    /**
     * This method creates the square objects representing the squares in the grid on the screen.
     */
    public void drawBackground() {

        float length = Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f));
        float x = ((Gdx.graphics.getWidth() - (length*6f))/2f);
        float y = (Gdx.graphics.getHeight()*0.3f);

        for(int i = 0 ; i <36 ; i++)
        {
            if(i % 6 == 0 && i != 0)
            {
                x = ((Gdx.graphics.getWidth() - (length*6f))/2f);
                y = y + length;
            }
            getSquares()[i] = new Square(x,y,length,length);
            x = x + length;
        }
    }

    @Override
    public boolean keyUp (int keycode) {
        // Back to Menu
        if (keycode == Input.Keys.ESCAPE || (keycode == Input.Keys.BACK )) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelSelectionScreen());
        }
        else
        {
            Gdx.app.exit();
        }
        return false;
    }

    /**
     * This method is called when the level is over and it sets the score.
     */
    public void gameOver()
    {
        if(isLost())
        {
            setScore(0);
            this.dispose();
            ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelSelectionScreen());
        }

        else
        {
            if (getScore() > 100)
            {
                setScore(100);
            }

            else
            {
                setScore(getScore());
            }
            ((Game)Gdx.app.getApplicationListener()).setScreen(new NextLevelScreen());
        }
        System.out.println(score);

    }
}
