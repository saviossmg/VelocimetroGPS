package com.svmdev.velocimetrogps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Desenvolvido por Sávio Martins Valentim em 22/07/2019.
 */
public class MainActivity extends AppCompatActivity implements MainInterface {

    private TextView txtSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.txtSpeed = findViewById(R.id.speed_text);
        MainController vController = new MainController(this);

        vController.initGps();

    }

    @Override
    public TextView txtVelocidade() {
        return this.txtSpeed;
    }

}
