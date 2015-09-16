package com.kirkplace.spellit.activities;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kirkplace.spellit.R;
import com.kirkplace.spellit.constants.SpellitException;
import com.kirkplace.spellit.dto.GradeDTO;
import com.kirkplace.spellit.utils.Manager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GradingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GradingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GradingFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_GRADE = "grade";
    private static final String ARG_PARAM_GAME_MANAGER = "gameManager";
    public GradeDTO mGrade;
    public Manager mGameManager;
    private TextView gradedAnswer;
    private EditText retry;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param gradeParam Parameter 1.
     * @return A new instance of fragment GradingFragment.
     */
    public static GradingFragment newInstance(GradeDTO gradeParam, Manager gameManager) {
        GradingFragment fragment = new GradingFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM_GRADE, gradeParam);
        args.putParcelable(ARG_PARAM_GAME_MANAGER, gameManager);
        fragment.setArguments(args);

        return fragment;
    }

    public GradingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGrade = getArguments().getParcelable(ARG_PARAM_GRADE);
            mGameManager = getArguments().getParcelable(ARG_PARAM_GAME_MANAGER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grading, container, false);
        gradedAnswer = (TextView) rootView.findViewById(R.id.gradedAnswer);
        retry = (EditText) rootView.findViewById(R.id.missingLetters);
        final Button retryBtn = (Button) rootView.findViewById(R.id.retryBtn);
        final Button newWordBtn = (Button) rootView.findViewById(R.id.newWordBtn);
        if (mGrade.isCorrect()) {
            gradedAnswer.setText("Correct, hooray");
            retry.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
        } else {
            retry.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);
            for (int k=0;k<mGameManager.getWord().getLength();k++) {
                if (!mGrade.getCharMap().containsKey(k)) {
                    gradedAnswer.append(String.valueOf(mGameManager.getWord().getChars()[k])+" ");
                } else {
                    gradedAnswer.append("_ ");
                }
            }
            if(mGameManager.getWord().getLength() < mGameManager.getAnswer().getLength()){
                gradedAnswer.append(" Too many letters!");
            }
        }
        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.retryBtn){
                    mGameManager.setAnswer(retry.getText().toString());
                    gradedAnswer.setText("");
                    try{
                        mGrade = mGameManager.checkAnswer();
                        if (mGrade.isCorrect()) {
                            gradedAnswer.setText("Correct, hooray");
                            retry.setVisibility(View.GONE);
                            retryBtn.setVisibility(View.GONE);
                        } else {
                            retry.setVisibility(View.VISIBLE);
                            retryBtn.setVisibility(View.VISIBLE);
                            for (int k=0;k<mGameManager.getWord().getLength();k++) {
                                if (!mGrade.getCharMap().containsKey(k)) {
                                    gradedAnswer.append(String.valueOf(mGameManager.getWord().getChars()[k])+" ");
                                } else {
                                    gradedAnswer.append("_ ");
                                }
                            }
                            if(mGameManager.getWord().getLength() < mGameManager.getAnswer().getLength()){
                                gradedAnswer.append(" Too many letters!");
                            }
                        }
                    }catch (SpellitException e){
                        gradedAnswer.setText(e.toString());
                    }
                }
            }
        });
        newWordBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(v.getId() == R.id.newWordBtn){
                    onButtonPressed(v);
                }
            }
        });
        return rootView;
    }

    public void onButtonPressed(View v) {
        if (mListener != null) {
            mListener.onFragmentInteraction(v);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(View v);
    }

}
