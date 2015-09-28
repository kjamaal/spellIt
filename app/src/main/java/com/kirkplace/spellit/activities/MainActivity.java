package com.kirkplace.spellit.activities;

import android.app.Fragment;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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

import java.nio.CharBuffer;
import java.util.Locale;


public class MainActivity extends ActionBarActivity implements GradingFragment.OnFragmentInteractionListener{

    @Override
    public void onFragmentInteraction(View v){
        if(v.getId() == R.id.newWordBtn){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, PlaceholderFragment.newInstance(this))
                    .commit();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, PlaceholderFragment.newInstance(this))
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
        private Context ctx;
        private TextToSpeech tts;
        private CharSequence wordCharSeq;
        private CharSequence usageCharSeq;

        /*
        TODO: automatically dload and install samsung tts high quality voice file
         */

        public static PlaceholderFragment newInstance(Context ctx) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.gameManager = new Manager(ctx);
            fragment.ctx = ctx;
            fragment.setRetainInstance(true);
            return fragment;
        }

        public PlaceholderFragment(){
            //Required empty constructor
        }

        @Override
        public void onFragmentInteraction(View v){

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            Button gradeBtn = (Button)rootView.findViewById(R.id.grade);
            Button playWord = (Button) rootView.findViewById(R.id.playWord);
            answer = (EditText) rootView.findViewById(R.id.answer);
            answer.setHint(R.string.answer);
            if(savedInstanceState == null) {
                gameManager.getNextWord();
                wordCharSeq = CharBuffer.wrap(gameManager.getWord().getWordChars());
                usageCharSeq = CharBuffer.wrap(gameManager.getWord().getUsageChars());
            }
            tts = new TextToSpeech(ctx,new TextToSpeech.OnInitListener(){
                @Override
                public void onInit(int status) {
                    tts.setLanguage(Locale.US);
                    tts.setSpeechRate((float).90);
                }
            });
            gradeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.grade) {
                        gameManager.setAnswer(answer.getText().toString());
                        try {
                            grade = gameManager.checkAnswer();
                            getFragmentManager().beginTransaction().replace(R.id.container, GradingFragment.newInstance(grade, gameManager)).commit();
                        } catch (SpellitException e) {
                            answer.setText(e.toString());
                        }
                    }
                }
            });
            playWord.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(v.getId() == R.id.playWord){
                        tts.speak(wordCharSeq, TextToSpeech.QUEUE_FLUSH,null,null);
                        tts.speak(usageCharSeq, TextToSpeech.QUEUE_ADD,null,null);
                    }
                }
            });
            return rootView;
        }
    }
}
