package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Mirror.mirrorType;

import static com.badlogic.gdx.Gdx.input;

/**
 * This class is an instance of teh level class.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */

public class Levelx01 extends Levelx {

    //cosntants
    private final long TIME_LIMIT = 12000;
    private final long TIMER_DURATION = 10000;



    @Override
    public void show()
    {
        init();
    }

    //this method initializes the variables, starts the start time ans sets the entering and leaving indices for laser
    @Override
    public void init()
    {
        float length = Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f));
        setRenderer(new ShapeRenderer());
        getRenderer().setAutoShapeType(true);
        setBatch( new SpriteBatch());
        input.setInputProcessor(this);
        setTouchCoordinates(new Vector2());
        setObstacles(new Obstacle[3]);
        setMirrors(new Mirror[4]);
        setSquares(new Square[36]);
        setLaserGun(new Texture("laser_gun.png"));
        setSatellite(new Texture("satellite.png"));
        setBackground(new Texture("game_screen_bg.png"));
        drawBackground();
        addObstacles();
        addMirrors();
        setStartTime(TimeUtils.millis());
        setGameTime(0);
        setEnteringIndex(30);
        setLeavingIndex(5);
        setLaser(new Laser(getSquares()[getEnteringIndex()].getX(),getSquares()[getEnteringIndex()].getY() + length/2
                ,this,length/2,length/10));
        setLost(false);
        setOver(false);
        setStarted(false);
        setSound(Gdx.audio.newSound(Gdx.files.internal("laserHit.mp3")));
    }

    //touchDown is called every time the user touches the screen to check if the user is touching a mirror object
    //or the entering square for the laser if the user touches the entering square teh laser enters
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!isStarted()) {
            setTouchCoordinates(new Vector2(screenX,Gdx.graphics.getHeight() - screenY));
            for (Mirror mirror : getMirrors()) {
                if (mirror != null) {
                    if (mirror.contains(getTouchCoordinates().x, getTouchCoordinates().y) != null) {
                        setDraggingMirror(mirror.contains(getTouchCoordinates().x, getTouchCoordinates().y));
                        getDraggingMirror().setSelected(true);
                    }
                }
            }

            if(getSquares()[getEnteringIndex()].check(getTouchCoordinates().x,getTouchCoordinates().y) != null)
            {
                setStarted(true);
                setScore(100 - (int) (TimeUtils.millis() -  getStartTime() - 2000)/100);
            }
        }
        return true;
    }

    //touch up checks if the user stopped touching the screen while dragging an object and sets the final position of mirror according to it
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

            setTouchCoordinates(new Vector2(screenX,Gdx.graphics.getHeight() - screenY));
            for (Mirror mirror : getMirrors()) {
                if (getDraggingMirror() != null) {
                    getDraggingMirror().setX(getTouchCoordinates().x);
                    getDraggingMirror().setY(getTouchCoordinates().y);
                    getDraggingMirror().place();
                    getDraggingMirror().setSelected(false);
                    setDraggingMirror(null);
                }
            }
        return true;
    }

    //this method checks if the user is dragging a mirror object and sets its position according to it simultaneously
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        if (!isStarted()) {
            setTouchCoordinates(new Vector2(screenX,Gdx.graphics.getHeight() - screenY));
            for (Mirror mirror : getMirrors()) {
                if (getDraggingMirror() != null) {
                    getDraggingMirror().setX(getTouchCoordinates().x);
                    getDraggingMirror().setY(getTouchCoordinates().y);
                    getDraggingMirror().setSelected(true);
                }
            }
        }
        return true;
    }

    //this method updates the screen 60 times a second draws the grid,obstacles and mirrors uses graphics coordinates to draw therfore it
    //works fine with different devices
    @Override
    public void render(float delta)
    {
        float length = Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f));
        float x = ((Gdx.graphics.getWidth() - (length*6f))/2f);
        float y = (Gdx.graphics.getHeight()*(0.3f));
        long elapsedTime = TimeUtils.millis() - getStartTime();

        // the time limit is exceeded the laser enters
        if(elapsedTime > 12000)
        {
            setGameTime(10000);
            setStarted(true);
        }

        //screen is cleared
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draws the background
        getBatch().begin();
        getBatch().draw(getBackground(),0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        getBatch().end();

        getRenderer().begin(ShapeRenderer.ShapeType.Line);
        getRenderer().setColor(Color.FIREBRICK);

        //draws the squares
        for(int i = 0 ; i <36 ; i++)
        {
            if(i % 6 == 0 && i != 0)
            {
                x = ((Gdx.graphics.getWidth() - (length*6f))/2f);
                y = y + length;
            }
            getRenderer().rect(x,y,length,length);
            x = x + length;
        }

        //draws the timer
        if(elapsedTime > 2000 && !isStarted())
        {
            long time = elapsedTime - 2000;
            getRenderer().set(ShapeRenderer.ShapeType.Filled);
            getRenderer().setColor(Color.BLUE);
            getRenderer().rect(getSquares()[3].getX(),getSquares()[35].getY()+length*4f/3f,3*length * (time)/10000,length/3f);
        }

        getRenderer().set(ShapeRenderer.ShapeType.Filled);
        getRenderer().setColor(Color.RED);

        //draws teh laser
        if(!isOver()) {
            if (isStarted()) {
                getLaser().draw(getRenderer());
            }
            checkLaser();
        }

        getRenderer().end();
        getBatch().begin();

        //draws obstacles
        for(Obstacle ob : getObstacles())
        {
            ob.draw(getBatch(),ob.getRadius()*2f,ob.getRadius()*2f);
            if(ob instanceof MovingObstacle)
            {
                //teh moving obstacle doesn't move if the laser has entered
                if (!isStarted()) {
                    ((MovingObstacle) ob).update();
                    checkMovingObstacle((MovingObstacle) ob);
                }
            }
        }

        //draws satellite and the laser gun
        getBatch().draw(getLaserGun(),getSquares()[getEnteringIndex() - 6].getX()-length/3f,getSquares()[getEnteringIndex() - 6].getY()+length/1.8f,length/0.8f,length/0.7f);
        getBatch().draw(getSatellite(),getSquares()[getLeavingIndex()].getX(),getSquares()[getLeavingIndex()].getY()-length/1.3f,length/0.7f,length/0.7f);

        //draws mirrors
        for (Mirror mirror : getMirrors())
        {
            mirror.draw(getBatch(),length,length);
        }
        getBatch().end();
    }

    @Override
    public void dispose() {
        //disposes the disposable objects
        getBatch().dispose();
        getRenderer().dispose();
        for(Mirror mirror : getMirrors())
        {
            mirror.getTexture().dispose();
        }
        for (Obstacle obstacle : getObstacles())
        {
            obstacle.getTexture().dispose();
        }
        getLaserGun().dispose();
        getBackground().dispose();
        getSatellite().dispose();
        getSound().dispose();
        Gdx.input.setInputProcessor(null);
    }

    //this method adds Mirrors to the screen and the mirror array
    public void addMirrors() {
        setMirrors(new Mirror[4]);
        float length = Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f));
        float x = ((Gdx.graphics.getWidth() - (length*6f))/2f);
        float y = (Gdx.graphics.getHeight()*(0.2f));
        getMirrors()[0] = new Mirror(x,y,"mirror1.png",mirrorType.SOUTH_EAST,length,this);
        getMirrors()[1] = new Mirror(x+length,y,"mirror2.png",mirrorType.NORTH_WEST,length,this);
        getMirrors()[2] = new Mirror(x+(2f*length),y,"mirror3.png",mirrorType.SOUTH_WEST,length,this);
        getMirrors()[3] = new Mirror(x+(3f*length),y,"mirror4.png",mirrorType.NORTH_EAST,length,this);
    }

    //adds obstacles
    @Override
    public void addObstacles() {
        getObstacles()[0] = new Obstacle(Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f))/2f,getSquares()[16].getX(),
                getSquares()[16].getY(),"asteroid.png");
        getObstacles()[1] = new Obstacle(Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f))/2f,getSquares()[9].getX(),
                getSquares()[9].getY(),"asteroid.png");
        getObstacles()[2] = new MovingObstacle(Math.min(Gdx.graphics.getHeight()*(0.1f),Gdx.graphics.getWidth()*(0.15f))/2f,getSquares()[20].getX(),getSquares()[20].getY(),0,0.2f,
                "metoroid.png",1,20,this);
        getSquares()[16].setObject(getObstacles()[0]);
        getSquares()[9].setObject(getObstacles()[1]);
        getSquares()[20].setObject(getObstacles()[2]);
    }
}
