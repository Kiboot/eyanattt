package eyana.uic.edu.ph.aljunstictactoeai;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private boolean HumanTurn = true;
    private int buttonCount;
    private int HumanPoints = 0;
    private int AIPoints = 0;
    private TextView textViewHuman;
    private TextView textViewAI;
    public int PlayerChoice = 1;
    public int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHuman = findViewById(R.id.text_view_p1);
        textViewAI = findViewById(R.id.text_view_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }}

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                resetGame();
                buttonEnabled();
            }
        });
        Button buttonReplay = findViewById(R.id.button_reset);
        buttonReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonEnabled();
                resetBoard();
                PlayerChoice = 1;
            }
        });}


    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (HumanTurn) {
            ((Button) v).setText("X");
            ((Button) v).setBackgroundResource(R.drawable.emote_btn_y);
            buttonDisabled();
        } else {
            ((Button) v).setText("O");
            ((Button) v).setBackgroundResource(R.drawable.emote_btn_g);
            buttonEnabled();
        }

        buttonCount++;

        if (checkForWin()) {
            if (HumanTurn) {
                HumanPlayerWins();
            } else {
                AIPlayerWins();
            }
        } else if (buttonCount == 9) {
            draw();
        } else {
            HumanTurn = !HumanTurn;

            if (!HumanTurn) {

                PlayerChoice += 6;
                if (PlayerChoice > 5) {

                    AIMove();
                }
                PlayerChoice += 5;
            }
        }
    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }


        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;

        }

        return field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("");

    }

    private void HumanPlayerWins() {
        counter = 1;
        HumanPoints++;
        // RoundCount++;
        Toast.makeText(this, "X wins!", Toast.LENGTH_SHORT).show();
        //PlayerChoice = 10;
        updatePointsText();
        buttonDisabled();
    }

    private void AIPlayerWins() {
        counter = 1;
        AIPoints++;
        Toast.makeText(this, "O wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        buttonDisabled();
    }

    private void draw() {
        counter = 1;
        Toast.makeText(this, "Its a tie!", Toast.LENGTH_SHORT).show();
        buttonDisabled();
    }

    private void updatePointsText() {
        textViewHuman.setText("Player Wins:" + HumanPoints);
        textViewAI.setText("AI Wins:" + AIPoints);

    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackgroundResource(R.drawable.emote_btn_d);
            }
        }

        buttonCount = 0;
        //RoundCount = 0;
        HumanTurn = true;
    }

    private void resetGame() {
        HumanPoints = 0;
        AIPoints = 0;
        // RoundCount = 0;
        PlayerChoice = 1;
        counter = 0;
        updatePointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("buttonCount", buttonCount);
        outState.putInt("HumanPoints", HumanPoints);
        outState.putInt("AIPoints", AIPoints);
        outState.putBoolean("HumanTurn", HumanTurn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        buttonCount = savedInstanceState.getInt("buttonCount");
        HumanPoints = savedInstanceState.getInt("HumanPoints");
        AIPoints = savedInstanceState.getInt("AIPoints");
        HumanTurn = savedInstanceState.getBoolean("HumanTurn");

    }

    private void buttonDisabled() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    private void buttonEnabled() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(true);
            }
        }
    }

    private void AIMove() {

        Random r = new Random();

        int cell_1;
        int cell_2;
        do {
            switch (cell_1 = r.nextInt(3)) {
            }
            switch (cell_2 = r.nextInt(3)) {
            }

        } while (!buttons[cell_1][cell_2].getText().toString().equals(""));
        final int finalCell_ = cell_1;
        final int finalCell_1 = cell_2;
        new CountDownTimer(1000, 100) {
            public void onTick(long millisUntilFinished) {
                counter++;
                if (counter > 5) {
                    buttons[finalCell_][finalCell_1].performClick();

                }
            }
            @Override
            public void onFinish() {
                counter = 0;
            }

        }.start();
    }

}
