package com.kirkplace.spellit.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kirkplace.spellit.R;
import com.kirkplace.spellit.constants.SpellitException;
import com.kirkplace.spellit.dto.GradeDTO;
import com.kirkplace.spellit.utils.Manager;


public class MainActivity extends ActionBarActivity implements GradingFragment.OnFragmentInteractionListener{

    @Override
    public void onFragmentInteraction(View v){
        if(v.getId() == R.id.newWordBtn){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlaceholderFragment(this))
                    .commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment(this))
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
    public static class PlaceholderFragment extends Fragment implements GradingFragment.OnFragmentInteractionListener{

        private Manager gameManager;
        private GradeDTO grade = new GradeDTO();
        private TextView answer;


        public PlaceholderFragment(Context ctx) {
            gameManager = new Manager(ctx);
        }

        @Override
        public void onFragmentInteraction(View v){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button gradeBtn = (Button)rootView.findViewById(R.id.grade);
            answer = (EditText) rootView.findViewById(R.id.answer);
            gameManager.getNextWord();
            gradeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.grade) {
                        gameManager.setAnswer(answer.getText().toString());
                        try {
                            grade = gameManager.checkAnswer();
                            getFragmentManager().beginTransaction().replace(R.id.container, GradingFragment.newInstance(grade,gameManager)).commit();
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
