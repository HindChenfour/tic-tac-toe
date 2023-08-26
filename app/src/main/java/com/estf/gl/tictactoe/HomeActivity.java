package com.estf.gl.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.estf.gl.tictactoe.models.Game;
import com.estf.gl.tictactoe.models.Player;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private GridLayout gameBoard;
    private TextView player1TextView;
    private TextView player2TextView;
    private TextView turnTextView;
    private TextView congratText;
    private List<Button> boxes;
    private Player player1;
    private Player player2;
    private String sign;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        player1TextView = findViewById(R.id.player1);
        player2TextView = findViewById(R.id.player2);
        turnTextView = findViewById(R.id.turnText);
        congratText = findViewById(R.id.congratText);

        boxes = new Vector<>();
        sign = "X";

        Intent intent = getIntent();
        Game game = (Game) intent.getSerializableExtra("game");
        player1 = game.getPlayer1();
        player2 = game.getPlayer2();

        Toast.makeText(this, "player 1 : " + player1, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "player 2 : " + player2, Toast.LENGTH_SHORT).show();


        init();
    }

    public void init() {
        player1TextView.setText(player1.getName() + " - Score : 0");
        player2TextView.setText(player2.getName() + " - Score : 0");

        player1TextView.setBackground(getResources().getDrawable(R.drawable.btnbg3));
        player1TextView.setTextColor(getResources().getColor(R.color.white));
        player2TextView.setBackground(getResources().getDrawable(R.drawable.textbg));

        turnTextView.setText("It is your turn\n" + player1.getName());

        gameBoard = findViewById(R.id.gameBoard);
        int size = gameBoard.getChildCount();
        for (int i = 0; i < size; i++) {
            View v = gameBoard.getChildAt(i);
            if(v instanceof Button) {
                Button box = (Button)v;
                boxes.add(box);
                box.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        Button box = (Button)view;
        congratText.setText("");

        if(box.getText().toString().equals("")) {
            box.setText(sign);
            if (checkWin(box)) {
                if (sign.equals("X")) {
                    player1.addScore();
                    congratText.setText("The Winner is " + player1.getName() + " !!!");
                }

                else {
                    player2.addScore();
                    congratText.setText("The Winner is " + player2.getName() + " !!!");
                }

                Toast.makeText(this, " WINNER !!! ", Toast.LENGTH_LONG).show();
                player1TextView.setText(player1.getName() + " - Score : " + player1.getScore());
                player2TextView.setText(player2.getName() + " - Score : " + player2.getScore());
                emptyGrid();

            }

            if(checkFull()) {
                congratText.setText("No one has won :( ");
                emptyGrid();
            }

            changeSign();
            changeStyle();
        }
    }

    public void changeSign() {
        if("X".equals(sign)) sign = "O";
        else sign = "X";
    }

    public void changeStyle() {
        if(sign.equals("X")) {
            player1TextView.setBackground(getResources().getDrawable(R.drawable.btnbg3));
            player1TextView.setTextColor(getResources().getColor(R.color.white));
            player2TextView.setBackground(getResources().getDrawable(R.drawable.textbg));
            player2TextView.setTextColor(getResources().getColor(R.color.teal_700));
            turnTextView.setText("It is your turn\n" + player1.getName());
        }

        else {
            player2TextView.setBackground(getResources().getDrawable(R.drawable.btnbg3));
            player2TextView.setTextColor(getResources().getColor(R.color.white));
            player1TextView.setBackground(getResources().getDrawable(R.drawable.textbg));
            player1TextView.setTextColor(getResources().getColor(R.color.teal_700));
            turnTextView.setText("It is your turn\n" + player2.getName());
        }
    }

    public void emptyGrid() {
        int size = gameBoard.getChildCount();
        for (int i = 0; i < size; i++) {
            View v = gameBoard.getChildAt(i);
            if(v instanceof Button) {
                Button box = (Button)v;
                box.setText("");
            }
        }

    }

    public boolean checkWin(Button box) {
        int posButton = boxes.indexOf(box);
        String val = box.getText().toString();
        List<Integer> valBoxes = new Vector<>();

        for (Button b : boxes) {
            if((boxes.indexOf(b) != posButton) && (b.getText().toString().equals(val))) {
                Integer i = new Integer(boxes.indexOf(b));
                valBoxes.add(i);
            }
        }


        if(valBoxes.size() >= 2) {
            int pos1 = -2;
            int pos2 = -2;

            for (Integer integ : valBoxes) {
                pos2 = integ.intValue();

                int array[] = {posButton, pos1, pos2};
                Arrays.sort(array);

                if(Arrays.equals(array, new int[]{0, 1, 2}) || Arrays.equals(array, new int[]{3, 4, 5}) || Arrays.equals(array, new int[]{6, 7, 8})) {
                    return true;
                }

                if(Arrays.equals(array, new int[]{0, 3, 6}) || Arrays.equals(array, new int[]{2, 5, 8}) || Arrays.equals(array, new int[]{1, 4, 7})) {
                    return true;
                }

                if(Arrays.equals(array, new int[]{0, 4, 8}) || Arrays.equals(array, new int[]{2, 4, 6})) {
                    return true;
                }
                pos1 = integ.intValue();
            }
        }
        return false;
    }

    public boolean checkFull() {
        List<Button> emptyBoxes = new Vector<>();

        for (Button b : boxes) {
            if(b.getText().equals("")) {
                emptyBoxes.add(b);
            }
        }
        if(emptyBoxes.size() == 0) {
            return true;
        }
        return false;
    }
}
