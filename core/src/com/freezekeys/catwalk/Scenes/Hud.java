package com.freezekeys.catwalk.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.freezekeys.catwalk.Catwalk;

/**
 * Created by xrans on 3/6/2016.
 */
public class Hud {

    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countDownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label catLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 0;
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(Catwalk.V_WIDTH, Catwalk.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new Label(String.format("#03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        scoreLabel = new Label(String.format("#06d",score),new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        levelLabel = new Label("1",new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        worldLabel = new Label("World",new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
        catLabel = new Label("Mr. Cat",new Label.LabelStyle(new BitmapFont(), Color.YELLOW));

        table.add(catLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);
    }
}
