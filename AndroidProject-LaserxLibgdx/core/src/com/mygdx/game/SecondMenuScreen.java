package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class is the main menu screen.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */
public class SecondMenuScreen implements Screen{

    //properties
    SpriteBatch batch = new SpriteBatch();

    private Stage stage;

    private Image bg;
    private Button startButton;
    private Button tutorialButton;

    private Texture astronot;
    private float positionX = Gdx.graphics.getWidth() / 5;
    private float positiyonY= Gdx.graphics.getHeight() / 5;
    private float angle = 0;

    private Button soundOn;
    private Button soundOff;

    public static boolean sound = false;

    private TextureRegion astronot2;


    @Override
    public void show() {

        //positions and images of buttons on the screen are arranged
        soundOn = new Button( new SpriteDrawable(new Sprite(new Texture("sound_on_button.png"))));
        soundOff = new Button(new SpriteDrawable(new Sprite(new Texture("sound_off_button.png"))));

        soundOn.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/7);


        soundOff.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/7);
        soundOff.setVisible(false);

        soundOn.setPosition(Gdx.graphics.getWidth() - soundOn.getWidth(), Gdx.graphics.getHeight() - soundOn.getHeight());
        soundOff.setPosition(Gdx.graphics.getWidth() - soundOn.getWidth(), Gdx.graphics.getHeight() - soundOn.getHeight());


        astronot = new Texture("astro_timer.png");
        astronot2 = new TextureRegion(astronot);

        TimerTask tasknew = new TimerTask() {
            @Override
            public void run() {
                angle = angle + 0.6f;


                if(positionX < Gdx.graphics.getWidth())
                    positionX = positionX + 4 ;


                else if (positionX == Gdx.graphics.getWidth())
                    positiyonY = positiyonY + 4;

                if(positiyonY < Gdx.graphics.getHeight())
                    positionX = positionX  - 4;


            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(tasknew, 50,50);




        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        bg = new Image(new SpriteDrawable(new Sprite(new Texture("mainmenu.png"))));
        bg.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        startButton = new Button( new SpriteDrawable(new Sprite(new Texture("play_button.png"))));
        startButton.setSize(Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()/6);

        tutorialButton = new Button( new SpriteDrawable(new Sprite(new Texture("tutorial_button.png"))));
        tutorialButton.setSize(Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()/6);


        startButton.setPosition(Gdx.graphics.getWidth() /2 - startButton.getWidth() / 2, Gdx.graphics.getHeight()/2 - startButton.getHeight() + 20f );
        tutorialButton.setPosition(Gdx.graphics.getWidth() /2 - tutorialButton.getWidth() / 2, Gdx.graphics.getHeight()/2-tutorialButton.getHeight() -  tutorialButton.getHeight() + 20f);

        bg.setPosition(0f, 0f);


        //listener for buttons
        startButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelSelectionScreen());
            }
        });


        tutorialButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new TutorialScreen());
            }
        });


        soundOn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                soundOff.setVisible(true);
                soundOn.setVisible(false);
                MyGame.music.stop();


            }
        });

        soundOff.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                soundOff.setVisible(false);
                soundOn.setVisible(true);
                MyGame.music.play();


            }
        });


        stage.addActor(bg);
        stage.addActor(startButton);
        stage.addActor(tutorialButton);
        stage.addActor(soundOn);
        stage.addActor(soundOff);


        Gdx.input.setInputProcessor(stage);

    }



    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0x64/255.0f,0x95/255.0f,0xed/255.0f,0xff/255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();

        batch.begin();
        batch.draw(astronot2, positionX, positiyonY, Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() / 3, Gdx.graphics.getWidth() / 4,  Gdx.graphics.getHeight() / 6, 0.7f, 0.8f, angle);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

        stage.dispose();

    }


}
