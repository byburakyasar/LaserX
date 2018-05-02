package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Tutorials screen.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */

public class TutorialScreen extends InputAdapter implements Screen {

    //properties
    private Button one;
    private Button two;
    private Button three;
    private Button threeSecond;
    private Button twoSecond;

    private Button four;

    private Stage stageOne;
    private Stage stageTwo;
    private Stage stageThree;

    private Image storyOne;
    private Image storyTwo;
    private Image storyThree;

    private boolean stageOneCheck = true;
    private boolean stageTwoCheck = false;
    private boolean stageThreeCheck = false;
    private boolean stageFourCheck = false;




    @Override
    public void show() {

        stageOne = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stageTwo = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        stageThree = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        storyOne = new Image(new SpriteDrawable(new Sprite(new Texture("background.png"))));
        storyTwo = new Image(new SpriteDrawable(new Sprite(new Texture("mainmenu.png"))));
        storyThree = new Image(new SpriteDrawable(new Sprite(new Texture("game_bg.png"))));


        storyOne.setPosition(0f, 0f);
        storyTwo.setPosition(0f, 0f);
        storyThree.setPosition(0f, 0f);

        storyOne.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        storyTwo.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        storyThree.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        one = new Button( new SpriteDrawable(new Sprite(new Texture("one_button.png"))));
        two = new Button( new SpriteDrawable(new Sprite(new Texture("two_button.png"))));
        twoSecond = new Button( new SpriteDrawable(new Sprite(new Texture("two_button.png"))));
        three = new Button( new SpriteDrawable(new Sprite(new Texture("three_button.png"))));
        threeSecond = new Button( new SpriteDrawable(new Sprite(new Texture("three_button.png"))));
        four = new Button( new SpriteDrawable(new Sprite(new Texture("four_button.png"))));

        two.setPosition(Gdx.graphics.getWidth() - two.getWidth() * 4, Gdx.graphics.getHeight() / 10);
        one.setPosition(one.getWidth(),Gdx.graphics.getHeight() / 10);
        three.setPosition(Gdx.graphics.getWidth() - three.getWidth() * 4, Gdx.graphics.getHeight() / 10);
        four.setPosition(Gdx.graphics.getWidth() - four.getWidth() * 4, Gdx.graphics.getHeight() / 10);
        twoSecond.setPosition(twoSecond.getWidth(),Gdx.graphics.getHeight() / 10);
        threeSecond.setPosition(threeSecond.getWidth(),Gdx.graphics.getHeight() / 10);

        two.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/7);
        one.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/7);
        three.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/7);
        four.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/7);
        twoSecond.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/7);
        threeSecond.setSize(Gdx.graphics.getWidth()/6 ,Gdx.graphics.getHeight()/7);


        stageOne.addActor(storyOne);
        stageOne.addActor(two);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor( stageOne );
        multiplexer.addProcessor( this ); // Your screen
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor( multiplexer );


        stageTwo.addActor(storyTwo);
        stageTwo.addActor(three);
        stageTwo.addActor(one);

        stageThree.addActor(storyThree);
        stageThree.addActor(twoSecond);
        stageThree.addActor(four);


        one.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.input.setInputProcessor(stageOne);
                stageTwoCheck = false;
                stageOneCheck = true;
                //((Game)Gdx.app.getApplicationListener()).setScreen(stageTwo);
            }
        });



        two.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.input.setInputProcessor(stageTwo);
                stageTwoCheck = true;
                stageOneCheck = false;
                //((Game)Gdx.app.getApplicationListener()).setScreen(screen);
            }
        });

        three.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.input.setInputProcessor(stageThree);
                stageThreeCheck = true;
                stageTwoCheck = false;
                //((Game)Gdx.app.getApplicationListener()).setScreen(screen);
            }
        });

        twoSecond.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                Gdx.input.setInputProcessor(stageTwo);
                stageTwoCheck = true;
                stageThreeCheck = false;
                //((Game)Gdx.app.getApplicationListener()).setScreen(screen);
            }
        });



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0x64/255.0f,0x95/255.0f,0xed/255.0f,0xff/255.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(stageOneCheck)
        {
            stageOne.act();
            stageOne.draw();
        }

        if(stageTwoCheck)
        {
            stageTwo.act();
            stageTwo.draw();
        }
        if(stageThreeCheck)
        {
            stageThree.act();
            stageThree.draw();
        }



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

        stageOne.dispose();
        stageTwo.dispose();
        stageThree.dispose();

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
