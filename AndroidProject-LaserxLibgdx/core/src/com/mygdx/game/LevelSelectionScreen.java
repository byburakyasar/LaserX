package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import javax.xml.soap.Text;

/**
 * This class represents the laser.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */

public class LevelSelectionScreen extends InputAdapter implements Screen {

    private Stage stage;

    private Image image;

    private SpriteBatch batch;

    private Button backButton;

    private Button levelOne;
    private Button levelTwo;
    private Button levelThree;
    private Button levelFour;
    private Button levelFive;
    private Button levelSix;
    private Button levelSeven;
    private Button levelEight;
    private Button levelNine;

    private Button levelBox; // sample button to get width and height of level buttons when expected

    private Texture pageLabel;


    private Texture oneStar;
    private Texture twoStar;
    private Texture threeStar;
    private Texture lockedStar;

    private Texture levelsPanel;
    private Texture background;

    private Texture dialogBox;

    private boolean isClicked = false;

    private int i = 1; // i = levelOne.getScore()
    private int m = 2; // m = levelTwo.getScore()
    private int x = 3; // z = levelThree.getScore()


    private Texture oneGraph;
    private Texture twoGraph;

    Levelx01 game;

    public LevelSelectionScreen() {

    }

    @Override
    public void show() {

        batch = new SpriteBatch();

        oneStar = new Texture("onestar.png");
        twoStar = new Texture("twostar.png");
        threeStar = new Texture("fullstar.png");
        lockedStar = new Texture("base_locked.png");

        pageLabel = new Texture("levels.png");
        levelsPanel = new Texture("levels_panel.png");
        background = new Texture("background.png");
        dialogBox = new Texture("dialog_box.png");

        oneGraph = new Texture("number1.png");
        twoGraph = new Texture("number2.png");
        //dialogStage = new Stage(new FitViewport(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4));

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        // setting Buttons size

        //image = new Image(new SpriteDrawable(new Sprite(new Texture("background.png"))));
        //image.setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);


        levelOne = new Button(new SpriteDrawable(new Sprite(new Texture("base_open.png"))));

        levelTwo = new Button(new SpriteDrawable(new Sprite(new Texture("base_open.png"))));

        levelThree = new Button(new SpriteDrawable(new Sprite(new Texture("base_open.png"))));
        levelThree.setColor(Color.GRAY);

        levelFour = new Button(new SpriteDrawable(new Sprite(new Texture("base_locked.png"))));
        levelFour.setColor(Color.GRAY);

        levelFive = new Button(new SpriteDrawable(new Sprite(new Texture("base_locked.png"))));
        levelFive.setColor(Color.GRAY);

        levelSix = new Button(new SpriteDrawable(new Sprite(new Texture("base_locked.png"))));
        levelSix.setColor(Color.GRAY);

        levelSeven = new Button(new SpriteDrawable(new Sprite(new Texture("base_locked.png"))));
        levelSeven.setColor(Color.GRAY);

        levelEight = new Button(new SpriteDrawable(new Sprite(new Texture("base_locked.png"))));
        levelEight.setColor(Color.GRAY);

        levelNine = new Button(new SpriteDrawable(new Sprite(new Texture("base_locked.png"))));
        levelNine.setColor(Color.GRAY);


        //sample button
        levelBox = new Button(new SpriteDrawable(new Sprite(new Texture("base_open.png"))));

        backButton = new Button(new SpriteDrawable(new Sprite(new Texture("logo.png"))));


        levelOne.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);
        levelTwo.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);
        levelThree.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);
        levelFour.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);
        levelFive.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);
        levelSix.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);
        levelSeven.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);
        levelEight.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);
        levelNine.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);

        //Sample Button
        levelBox.setSize(Gdx.graphics.getWidth() / 6, Gdx.graphics.getWidth() / 6);

        backButton.setSize(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 7);


        // positions of level boxes
        levelOne.setPosition(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight());
        levelTwo.setPosition(Gdx.graphics.getWidth() / 5 + levelOne.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight());
        levelThree.setPosition(Gdx.graphics.getWidth() / 5 + levelTwo.getWidth() / 2 * 3 + levelTwo.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight());
        levelFour.setPosition(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight() - levelOne.getHeight() / 2 * 3);
        levelFive.setPosition(Gdx.graphics.getWidth() / 5 + levelOne.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight() - levelOne.getHeight() / 2 * 3);
        levelSix.setPosition(Gdx.graphics.getWidth() / 5 + levelTwo.getWidth() / 2 * 3 + levelTwo.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight() - levelOne.getHeight() / 2 * 3);
        levelSeven.setPosition(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight() - levelOne.getHeight() / 2 * 3 - levelOne.getHeight() / 2 * 3);
        levelEight.setPosition(Gdx.graphics.getWidth() / 5 + levelOne.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight() - levelOne.getHeight() / 2 * 3 - levelOne.getHeight() / 2 * 3);
        levelNine.setPosition(Gdx.graphics.getWidth() / 5 + levelTwo.getWidth() / 2 * 3 + levelTwo.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight() - levelOne.getHeight() / 2 * 3 - levelOne.getHeight() / 2 * 3);

        //position of the logo
        backButton.setPosition(Gdx.graphics.getWidth() / 2 - backButton.getWidth() / 2, 0);


        //image.setPosition(0f, 0f);
        stage.addActor(levelOne);
        stage.addActor(levelTwo);
        stage.addActor(levelThree);

        stage.addActor(levelFour);
        stage.addActor(levelFive);
        stage.addActor(levelSix);

        stage.addActor(levelSeven);
        stage.addActor(levelEight);
        stage.addActor(levelNine);

        //stage.addActor(image); removed


        stage.addActor(backButton);


        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor( stage );
        multiplexer.addProcessor( this ); // Your screen
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor( multiplexer );

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //((Game) Gdx.app.getApplicationListener()).setScreen(new LevelSelectionScreen());


                //((Game)Gdx.app.getApplicationListener()).getClass(new Levelx01);
                isClicked = false;

            }
        });

        levelOne.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Levelx01());
                isClicked = true;
            }
        });

        levelTwo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Levelx02());
                isClicked = true;
            }
        });
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(pageLabel, Gdx.graphics.getWidth() / 2 - pageLabel.getWidth(), Gdx.graphics.getHeight() / 6 * 5, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 5); // (x,y,width,height)
        batch.draw(levelsPanel, Gdx.graphics.getWidth() / 5 - levelBox.getWidth(), Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelBox.getHeight() - levelBox.getHeight() / 2 * 3 - levelBox.getHeight() / 2 * 3 - levelBox.getHeight(), levelBox.getWidth() * 11 / 2, levelOne.getHeight() * 11 / 2);
        batch.end();


        stage.act();
        stage.draw();

        batch.begin();



        //draws the stars for levels, if it is zero teh level box is locked otherwise stars are drawn depending on the score

        //Level1
        if (/* GameOverScreen.prefs.getInteger(highOneScore  == 0*/ i == 0) {
            batch.draw(lockedStar, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelBox.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }
        if ( /*GameOverScreen.prefs.getInteger(highOneScore  <= 30*/ i == 1) {
            batch.draw(oneStar, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelBox.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }
        if (/*GameOverScreen.prefs.getInteger(highOneScore  <= 60*/ i == 2) {
            batch.draw(twoStar, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelBox.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }
        if (/*levelOne.getScore == 3*/ i == 3) {
            batch.draw(threeStar, Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelBox.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }


        // level 2
        if ( /*levelTwo.getScore == 0*/ m == 0) {
            batch.draw(lockedStar, Gdx.graphics.getWidth() / 5 + levelOne.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }
        if ( /*levelTwo.getScore == 1*/ m == 1) {
            batch.draw(oneStar, Gdx.graphics.getWidth() / 5 + levelOne.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }
        if (/*levelTwo.getScore == 2*/ m == 2) {
            batch.draw(twoStar, Gdx.graphics.getWidth() / 5 + levelOne.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }
        if (/*levelTwo.getScore == 3*/ m == 3) {
            batch.draw(threeStar, Gdx.graphics.getWidth() / 5 + levelOne.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }


        // level 3

        if ( /*levelThree.getScore == 0*/ x == 0) {
            batch.draw(lockedStar, Gdx.graphics.getWidth() / 5 + levelTwo.getWidth() / 2 * 3 + levelTwo.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }
        if ( /*levelThree.getScore == 1*/ x == 1) {
            batch.draw(oneStar, Gdx.graphics.getWidth() / 5 + levelTwo.getWidth() / 2 * 3 + levelTwo.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }
        if (/*levelThree.getScore == 2*/ x == 2) {
            batch.draw(twoStar, Gdx.graphics.getWidth() / 5 + levelTwo.getWidth() / 2 * 3 + levelTwo.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }
        if (/*levelThree.getScore == 3*/ x == 3) {
            batch.draw(threeStar, Gdx.graphics.getWidth() / 5 + levelTwo.getWidth() / 2 * 3 + levelTwo.getWidth() / 2 * 3, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight(), levelBox.getWidth(), levelBox.getHeight());
        }

        batch.draw(oneGraph, Gdx.graphics.getWidth() / 5 + levelBox.getWidth() / 2 - oneGraph.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelBox.getHeight() + levelBox.getHeight() / 3, levelBox.getWidth() / 3, levelBox.getHeight() / 2);
        batch.draw(twoGraph, Gdx.graphics.getWidth() / 5 + levelBox.getWidth() / 2 * 3 + levelBox.getWidth() / 2 - oneGraph.getWidth() / 2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelOne.getHeight() + levelBox.getHeight() / 3, levelBox.getWidth() / 3, levelBox.getHeight() / 2);

        if (isClicked) {
            batch.draw(dialogBox, Gdx.graphics.getWidth() / 5 - levelBox.getWidth(), Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 4 - levelBox.getHeight() - levelBox.getHeight() / 2 * 3 - levelBox.getHeight() / 2 * 3, Gdx.graphics.getWidth(), levelOne.getHeight() * 9 / 2);
        }


        batch.end();

    }


    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void pause() {
        ((Game) Gdx.app.getApplicationListener()).setScreen(new SecondMenuScreen());

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyUp (int keycode) {
        // Back to Menu
        if (keycode == Input.Keys.ESCAPE || (keycode == Input.Keys.BACK )) {
            ((Game)Gdx.app.getApplicationListener()).setScreen(new SecondMenuScreen());
        }
        else
        {
            Gdx.app.exit();
        }
        return false;
    }
}