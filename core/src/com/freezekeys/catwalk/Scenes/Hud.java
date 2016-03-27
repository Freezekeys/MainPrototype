package com.freezekeys.catwalk.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freezekeys.catwalk.Catwalk;

/**
 * Created by xrans on 3/6/2016.
 */
public class Hud implements Disposable{

    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private static Integer score;

    private Label countDownLabel;
    private Label timeLabel;
    private Label speedLabel;
    private static Label speedCount;
    public static float playerSpeed = 1f;

    public Hud(SpriteBatch sb){
        worldTimer = 0;
        timeCount = 0;

        viewport = new FitViewport(Catwalk.V_WIDTH, Catwalk.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("#03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        speedLabel = new Label("SPEED",new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        speedCount = new Label(String.format("%03d",(int)(playerSpeed*100)),new Label.LabelStyle
                (new
                BitmapFont(),
                Color.YELLOW));

        table.add(speedLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        table.row();
        table.add(speedCount).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);
    }

    public void update(float dt){
        timeCount+=dt;
        if(timeCount >= 1) {
            worldTimer++;
            countDownLabel.setText((String.format("%03d", worldTimer)));
            timeCount = 0;
        }
    }

    public static void changeSpeed(float value){
        if((playerSpeed - value) < 0)
            playerSpeed = 0;
        else playerSpeed += value ;
        speedCount.setText(String.format("%03d", (int)(playerSpeed*100)));
    }

    public Integer getWorldTimer()
    {
        return worldTimer;
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
