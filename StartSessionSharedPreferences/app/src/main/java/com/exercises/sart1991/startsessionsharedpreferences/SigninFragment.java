package com.exercises.sart1991.startsessionsharedpreferences;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SigninFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SigninFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SigninFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TextInputLayout txtEditName;
    private TextInputLayout txtEditPassword;
    private EditText editName;
    private EditText editPassword;

    public SigninFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SigninFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SigninFragment newInstance() {
        return new SigninFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        Button btnSignin = (Button) view.findViewById(R.id.btn_signin);
        btnSignin.setOnClickListener(onBtnSignInClicked);

        txtEditName = (TextInputLayout) view.findViewById(R.id.edit_name);
        editName = txtEditName.getEditText();
        txtEditPassword = (TextInputLayout) view.findViewById(R.id.edit_password);
        editPassword = txtEditPassword.getEditText();

        return view;
    }

    View.OnClickListener onBtnSignInClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if ("".equals(String.valueOf(editName.getText()))) {
                txtEditName.setError("Ingrese un valor");
                return;
            } else if ("".equals(String.valueOf(editPassword.getText()))) {
                txtEditPassword.setError("Ingrese un valor");
                return;
            }
            mListener.onFragmentSignIn(
                    String.valueOf(editName.getText()),
                    String.valueOf(editPassword.getText())
            );
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentSignIn(String name, String password);
    }
}
