package com.exercises.sart1991.sqlitedrawer.ui.insert;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exercises.sart1991.sqlitedrawer.R;
import com.exercises.sart1991.sqlitedrawer.ui.base.BaseFragment;

public class InsertFragment extends BaseFragment implements InsertMvpView{

    private static final String TAG = InsertFragment.class.getSimpleName();
    private static final InsertMvpPresenter<InsertMvpView> PRESENTER = new InsertPresenter<>();

    private TextInputLayout tilBrand, tilQuantity;
    private EditText editBrand, editQuantity;
    private Button btnInsert, btnShowAll;
    private LinearLayout layoutTableContent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PRESENTER.onAttach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insert, container, false);
        initializeComponents(view);
        return view;
    }

    public static InsertFragment newInstance() {
        Bundle args = new Bundle();
        InsertFragment fragment = new InsertFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initializeComponents(View view) {
        tilBrand = (TextInputLayout) view.findViewById(R.id.til_insert_brandField);
        tilQuantity = (TextInputLayout) view.findViewById(R.id.til_insert_quantityField);
        editBrand = tilBrand.getEditText();
        editQuantity = tilQuantity.getEditText();
        btnInsert = (Button) view.findViewById(R.id.button_insert_save);
        btnShowAll = (Button) view.findViewById(R.id.button_insert_showAll);
        layoutTableContent = (LinearLayout) view.findViewById(R.id.linearLayout_insert_tableContent);

        //bundle with listeners
        btnInsert.setOnClickListener(onClickInsert);
        btnShowAll.setOnClickListener(onClickShowAll);
        editBrand.addTextChangedListener(listenTextChanges);
        editQuantity.addTextChangedListener(listenTextChanges);
    }

    @Override
    public Context getViewContext() {
        return getBaseActivity().getBaseContext();
    }

    @Override
    public void addTableRow(String rowContent) {
        TextView textView = new TextView(getBaseActivity());
        textView.setText(rowContent);
        layoutTableContent.addView(textView);
    }

    @Override
    public void clearTableContentData() {
        layoutTableContent.removeAllViews();
    }

    private View.OnClickListener onClickInsert = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PRESENTER.onInsertButtonClick();
        }
    };

    private View.OnClickListener onClickShowAll = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            PRESENTER.onShowAllButtonClick();
        }
    };

    @Override
    public String getBrandText() {
        return editBrand.getText().toString();
    }

    @Override
    public int getQuantityValue() {
        return Integer.parseInt(editQuantity.getText().toString());
    }

    @Override
    public boolean checkBrandFieldIsEmpty() {
        return "".equals(editBrand.getText().toString());
    }

    @Override
    public boolean checkQuantityFieldIsEmpty() {
        return "".equals(editQuantity.getText().toString());
    }

    @Override
    public void showErrorBrandField(String error) {
        tilBrand.setError(error);
    }

    @Override
    public void showErrorQuantityField(String error) {
        tilQuantity.setError(error);
    }

    @Override
    public void disableFieldsError() {
        tilBrand.setErrorEnabled(false);
        tilQuantity.setErrorEnabled(false);
    }

    private TextWatcher listenTextChanges = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            PRESENTER.onFieldsTextChanged();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
