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
 * This class represents the laser.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */

public class GameOverScreen implements Screen {

    //properties
    private Stage stage;
    private Image pageBackground;

    private Button pausePanel ;
    private Button backToLevelsMenu;
    private Button repeatLevel;
    private Button congrats ;

    @Override
    public void show() {

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        congrats = new Button( new SpriteDrawable(new Sprite(new Texture("game_over.png"))));
        congrats.setSize(Gdx.graphics.getWidth() / 4 * 3  ,Gdx.graphics.getHeight()/ 5);

        pageBackground = new Image(new SpriteDrawable(new Sprite(new Texture("background.png"))));
        pageBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        backToLevelsMenu = new Button( new SpriteDrawable(new Sprite(new Texture("menu_button.png"))));
        backToLevelsMenu.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/6);

        repeatLevel = new Button( new SpriteDrawable(new Sprite(new Texture("reload_button.png"))));
        repeatLevel.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/6);

        pageBackground.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        pageBackground.setPosition(0f, 0f);

        repeatLevel.setPosition(Gdx.graphics.getWidth() / 2 - 2 * repeatLevel.getWidth() ,Gdx.graphics.getHeight() / 8 );
        backToLevelsMenu.setPosition(Gdx.graphics.getWidth() / 2 + repeatLevel.getWidth() , Gdx.graphics.getHeight()/8);
        congrats.setPosition( Gdx.graphics.getWidth() / 2 - congrats.getWidth() / 2 , Gdx.graphics.getHeight()  / 2 - repeatLevel.getHeight()/2 );


        repeatLevel.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){

                ((Game)Gdx.app.getApplicationListener()).setScreen(new Levelx01());
            }
        });


        backToLevelsMenu.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelSelectionScreen());
            }
        });

        stage.addActor(pageBackground);
        stage.addActor(repeatLevel);
        stage.addActor(backToLevelsMenu);
        stage.addActor(congrats);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0x64/255.0f,0x95/255.0f,0xed/255.0f,0xff/255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        ((Game)Gdx.app.getApplicationListener()).setScreen(new SecondMenuScreen());

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