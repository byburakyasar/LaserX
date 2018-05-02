package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * This is the class called when the game starts.
 * @author Vedat M. Asan
 * @author Berat Aydın
 * @author Burak Yaşar
 * @author M. Safa Aşkın
 * @author S.Yagiz Yetim
 * @version 15,05,2017
 */

public class MyGame extends Game {

    //properties
    //background music
    protected static Music music;

    //this method is called whenever the game starts
    @Override
    public void create() {

        //sets the screen and starts the music
        setScreen(new SplashScreen());
        music = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.ogg"));

        //looping music
        music.setLooping(true);
        music.setVolume(0.8f);
        music.play();

    }

    //disposes the music variable
    @Override
    public void dispose() {
        music.dispose();
    }
}