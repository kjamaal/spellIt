package com.kirkplace.spellit.activities;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kirkplace.spellit.R;
import com.kirkplace.spellit.constants.SpellitException;
import com.kirkplace.spellit.dao.WordDAO;
import com.kirkplace.spellit.dto.GradeDTO;
import com.kirkplace.spellit.utils.Manager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private Manager gameManager = new Manager();
        private GradeDTO grade = new GradeDTO();
        private TextView answer;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button gradeBtn = (Button)rootView.findViewById(R.id.grade);
            answer = (TextView) rootView.findViewById(R.id.answer);
            gameManager.getNextWord();
            gradeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.grade) {
                        gameManager.setAnswer(answer.getText().toString());
                        try {
                            grade = gameManager.checkAnswer();
                            if (grade.isCorrect()) {
                                answer.setText("Correct, hooray");
                            } else {
                                answer.setText(grade.getCharMap().get(0).toString());
                                for (int k : grade.getCharMap().keySet()) {
                                    Log.d("GradeMap", String.valueOf(k));
                                    if (k > 0)
                                        answer.append(String.valueOf(grade.getCharMap().get(k)));
                                }
                            }
                        } catch (SpellitException e) {
                            answer.setText(e.toString());
                        }
                    }
                }
            });
            return rootView;
        }
    }
}
