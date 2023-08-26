package com.estf.gl.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.estf.gl.tictactoe.models.Game;
import com.estf.gl.tictactoe.models.Player;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private EditText player1Input;
    private EditText player2Input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Input = findViewById(R.id.player1Input);
        player2Input = findViewById(R.id.player2Input);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnStart) {

            Player player1 = new Player(player1Input.getText() + "");
            Player player2 = new Player(player2Input.getText() + "");
            Game game = new Game(player1, player2);

            Toast.makeText(this, "Let's start playing", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("game", game);

            this.startActivity(intent);
        }
    }
}