package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;


/**
 * This class is the screen called whenever the user completes a level successfully.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */
public class NextLevelScreen implements Screen {

    //properties
    private SecondMenuScreen screen;
    private Stage stage;
    private Image pageBackground;

    private Button pausePanel;
    private Button backToLevelsMenu;
    private Button nextLevel;
    private float score = 56;
    private Button emptystar1;
    private Button emptystar2;
    private Button emptystar3;
    private Button star1;
    private Button star2;
    private Button star3;
    private Button reloadButton;



    @Override
    public void show() {

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        //these variables represents the stars shown on the screen
        star1 = new Button(new SpriteDrawable(new Sprite(new Texture("star_open.png"))));
        star2 = new Button(new SpriteDrawable(new Sprite(new Texture("star_open.png"))));
        star3 = new Button(new SpriteDrawable(new Sprite(new Texture("star_open.png"))));

        emptystar1 = new Button(new SpriteDrawable(new Sprite(new Texture("star_close.png"))));
        emptystar2 = new Button(new SpriteDrawable(new Sprite(new Texture("star_close.png"))));
        emptystar3 = new Button(new SpriteDrawable(new Sprite(new Texture("star_close.png"))));

        star1.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getWidth() / 12);
        star3.setSize(Gdx.graphics.getWidth() / 9, Gdx.graphics.getWidth() / 9);
        star2.setSize(Gdx.graphics.getWidth() / 12, Gdx.graphics.getWidth() / 12);

        emptystar1.setSize(Gdx.graphics.getWidth() / 14, Gdx.graphics.getWidth() / 14);
        emptystar3.setSize(Gdx.graphics.getWidth() / 11, Gdx.graphics.getWidth() / 11);
        emptystar2.setSize(Gdx.graphics.getWidth() / 14, Gdx.graphics.getWidth() / 14);

        star1.setPosition(Gdx.graphics.getWidth() / 2 - 2*star1.getWidth(), Gdx.graphics.getHeight() / 2+ star1.getWidth() );
        star3.setPosition(Gdx.graphics.getWidth() / 2 -star1.getWidth(),  Gdx.graphics.getHeight() / 2 + 2* star1.getWidth());
        star2.setPosition(Gdx.graphics.getWidth() / 2 -star1.getWidth()+star2.getWidth() , Gdx.graphics.getHeight() / 2+ star1.getWidth());

        emptystar1.setPosition(Gdx.graphics.getWidth() / 2 - 2*star1.getWidth(), Gdx.graphics.getHeight() / 2+ star1.getWidth() );
        emptystar3.setPosition(Gdx.graphics.getWidth() / 2 -star1.getWidth(),  Gdx.graphics.getHeight() / 2 + 2* star1.getWidth());
        emptystar2.setPosition(Gdx.graphics.getWidth() / 2 -star1.getWidth()+star2.getWidth() , Gdx.graphics.getHeight() / 2+ star1.getWidth());


        backToLevelsMenu = new Button(new SpriteDrawable(new Sprite(new Texture("menu_button.png"))));
        reloadButton = new Button(new SpriteDrawable(new Sprite(new Texture("reload_button.png"))));
        nextLevel = new Button(new SpriteDrawable(new Sprite(new Texture("little_play_button.png"))));


        backToLevelsMenu.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getWidth() / 4);
        reloadButton.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getWidth() / 4);
        nextLevel.setSize(Gdx.graphics.getWidth() / 4, Gdx.graphics.getWidth() / 4);

        /*position setters*/
        backToLevelsMenu.setPosition(Gdx.graphics.getWidth() / 8 + reloadButton.getWidth() + nextLevel.getWidth() , Gdx.graphics.getHeight() / 3);
        reloadButton.setPosition(Gdx.graphics.getWidth() / 8 + nextLevel.getWidth() , Gdx.graphics.getHeight() / 3);
        nextLevel.setPosition(Gdx.graphics.getWidth() / 8 , Gdx.graphics.getHeight() / 3);



        pageBackground = new Image(new SpriteDrawable(new Sprite(new Texture("nextlevel.png"))));
        pageBackground.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        pageBackground.setPosition(0f, 0f);


        //listeners for buttons
        nextLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new Levelx02());
            }
        });

        reloadButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelSelectionScreen());
            }
        });

        backToLevelsMenu.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new LevelSelectionScreen());
            }
        });


        stage.addActor(pageBackground);
        stage.addActor(nextLevel);
        stage.addActor(backToLevelsMenu);
        stage.addActor(reloadButton);

        //setting the stars depending on the score of the user
        if (score < 100 / 3) {
            stage.addActor(star1);
            stage.addActor(emptystar2);
            stage.addActor(emptystar3);

        } else if (score >= 100 / 3 && score < 200 / 3) {
            stage.addActor(star1);
            stage.addActor(star2);
            stage.addActor(emptystar3);

        } else {
            stage.addActor(star1);
            stage.addActor(star2);
            stage.addActor(star3);
        }

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
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

        stage.dispose();
        ((Game) Gdx.app.getApplicationListener()).setScreen(screen);

    }

    public void setScore(float score) {
        this.score = score;
    }

}