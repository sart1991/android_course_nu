package com.exercises.sart1991.evaluacionfinal7.ui.activity.main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.exercises.sart1991.evaluacionfinal7.R;
import com.exercises.sart1991.evaluacionfinal7.data.db.model.Donor;
import com.exercises.sart1991.evaluacionfinal7.ui.activity.login.LoginActivity;
import com.exercises.sart1991.evaluacionfinal7.ui.subview.donor.CardDonor;
import com.exercises.sart1991.evaluacionfinal7.ui.base.BaseActivity;

import java.util.List;

public class MainActivity extends BaseActivity implements MainMvpView {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final MainMvpPresenter<MainMvpView> PRESENTER = new MainPresenter<>();

    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Dialog dialogNewDonor;
    private TextInputLayout tilSearchDonor;
    private EditText editSearchDonor;
    private CheckBox checkUserFilter;
    private RecyclerView recyclerDonors;
    private CardDonor cardDonor = new CardDonor();
    //Dialog donor
    private TextInputLayout tilDialogNewDonorId;
    private EditText editDialogDonorId, editDialogDonorName, editDialogDonorLastName,
                        editDialogDonorAge, editDialogDonorWeight, editDialogDonorHeight;
    private Spinner spinnerDialogDonorBloodType, spinnerDialogDonorRh;
    //Dialog new password
    private TextInputLayout tilDialogCurrentPasword, tilDialogConfNewPassword;
    private EditText editDialogCurrentPassword, editDialogNewPassword, editDialogConfNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PRESENTER.onAttach(this);
        PRESENTER.validateSession();
        initializeComponents();
        PRESENTER.onWelcome(fab);
        PRESENTER.onFirstLoadDonorData();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    protected void initializeComponents() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        dialogNewDonor = makeDialogForDonor(
                R.string.dialogNewDonor_title,
                R.string.dialogNewDonor_positiveButton,
                listenerForCancelNewDonor, null);
        tilSearchDonor = (TextInputLayout) findViewById(R.id.til_main_searchDonor);
        editSearchDonor = tilSearchDonor.getEditText();
        checkUserFilter = (CheckBox) findViewById(R.id.checkBox_main_searchAllUsers);
        recyclerDonors = (RecyclerView) findViewById(R.id.recycler_main_donorInfoContainer);

        //bind listeners
        editSearchDonor.addTextChangedListener(textWatcherForSearchListener);
        editSearchDonor.setOnTouchListener(touchListenerForClearSearch);
    }

    @Override
    public void setToolbarSubtitle(String subtitle) {
        toolbar.setSubtitle(subtitle);
    }

    private TextWatcher textWatcherForSearchListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            PRESENTER.onDonorIdFilterTyping(s.toString());
        }
    };

    private View.OnTouchListener touchListenerForClearSearch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                int xSpace = editSearchDonor.getRight() - editSearchDonor.getTotalPaddingEnd();
                if (event.getRawX() >= xSpace) {
                    PRESENTER.onTouchClearSearch();
                    return true;
                }
            }
            return false;
        }
    };

    @Override
    public void clearSearch() {
        editSearchDonor.setText("");
    }

    @Override
    public boolean checkFilterUserState() {
        return checkUserFilter.isChecked();
    }

    public void onClickCheckFilterForUser(View view) {
        PRESENTER.onDonorIdFilterTyping(editSearchDonor.getText().toString());
    }

    public void gotoLogin() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PRESENTER.onOptionItemClick(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void onClickFab(View view) {
        PRESENTER.onClickAddDonor();
    }

    private Dialog makeDialogForDonor(
            int resTitle, int resMessage,
            DialogInterface.OnCancelListener cancelListener, final String idExcept) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(resTitle);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_newdonor, null);
        tilDialogNewDonorId = (TextInputLayout) view.findViewById(R.id.til_dialogDonor_idDonor);
        editDialogDonorId = (EditText) view.findViewById(R.id.editText_dialogDonor_id);
        editDialogDonorName = (EditText) view.findViewById(R.id.editText_dialogDonor_name);
        editDialogDonorLastName = (EditText) view.findViewById(R.id.editText_dialogDonor_lastName);
        editDialogDonorAge = (EditText) view.findViewById(R.id.editText_dialogDonor_age);
        spinnerDialogDonorBloodType = (Spinner) view.findViewById(R.id.spinner_dialogDonor_bloodType);
        spinnerDialogDonorRh = (Spinner) view.findViewById(R.id.spinner_dialogDonor_rh);
        editDialogDonorWeight = (EditText) view.findViewById(R.id.editText_dialogDonor_weight);
        editDialogDonorHeight = (EditText) view.findViewById(R.id.editText_dialogDonor_height);

        TextWatcher textWatcherDonorId = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilDialogNewDonorId.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                PRESENTER.onDialogDonorIdTyping(s.toString(), idExcept);
            }
        };
        editDialogDonorId.addTextChangedListener(textWatcherDonorId);
        builder.setView(view);
        builder.setOnCancelListener(cancelListener);
        builder.setNegativeButton(R.string.dialogDonor_negativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(resMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PRESENTER.onDialogDonorPositive(
                        editDialogDonorId.getText().toString(),
                        editDialogDonorName.getText().toString(),
                        editDialogDonorLastName.getText().toString(),
                        editDialogDonorAge.getText().toString(),
                        spinnerDialogDonorBloodType.getSelectedItem().toString(),
                        spinnerDialogDonorRh.getSelectedItem().toString(),
                        editDialogDonorWeight.getText().toString(),
                        editDialogDonorHeight.getText().toString(),
                        idExcept
                );
            }
        });
        return builder.create();
    }

    @Override
    public void onDialogDonorIdError(int errorResId) {
        tilDialogNewDonorId.setError(getString(errorResId));
    }

    @Override
    public void showDialogNewDonor() {
        dialogNewDonor.show();
    }

    @Override
    public void cleanDialogNewDonorData() {
        dialogNewDonor = makeDialogForDonor(
                R.string.dialogNewDonor_title,
                R.string.dialogNewDonor_positiveButton,
                listenerForCancelNewDonor, null);

        editDialogDonorId.setText("");
        editDialogDonorName.setText("");
        editDialogDonorLastName.setText("");
        editDialogDonorAge.setText("");
        spinnerDialogDonorBloodType.setSelection(0);
        spinnerDialogDonorRh.setSelection(0);
        editDialogDonorWeight.setText("");
        editDialogDonorHeight.setText("");
    }

    private DialogInterface.OnCancelListener listenerForCancelNewDonor = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            PRESENTER.onCancelNewDonor();
        }
    };

    @Override
    public void initializeCards() {
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerDonors.setLayoutManager(layoutManager);
        cardDonor.onAttach(this, PRESENTER);
        recyclerDonors.setAdapter(cardDonor.getDonorAdapter());
    }

    @Override
    public void showDialogEditDonor(Donor donor) {
        Dialog holder = makeDialogForDonor(
                R.string.dialogUpdateDonor_title,
                R.string.dialogUpdateDonor_positiveButton,
                listenerForCancelEditDonor, donor.getId()
        );
        editDialogDonorId.setText(donor.getId());
        editDialogDonorName.setText(donor.getName());
        editDialogDonorLastName.setText(donor.getLastName());
        editDialogDonorAge.setText(String.valueOf(donor.getAge()));
        spinnerDialogDonorBloodType.setSelection(0);
        spinnerDialogDonorRh.setSelection(0);
        editDialogDonorWeight.setText(String.valueOf(donor.getWeight()));
        editDialogDonorHeight.setText(String.valueOf(donor.getHeight()));
        holder.show();
    }

    DialogInterface.OnCancelListener listenerForCancelEditDonor = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            PRESENTER.onCancelEditDonor();
        }
    };

    @Override
    public void setDonorList(List<Donor> donorList) {
        cardDonor.setDonorList(donorList);
    }

    @Override
    public void onDonorListChanged() {
        cardDonor.onDonorListChanged();
    }

    private Dialog makeDialogForConfirmation(
            int resTitle, String message,
            int positive, DialogInterface.OnCancelListener listenerCancel,
            DialogInterface.OnClickListener listenerPositive) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(resTitle);
        builder.setMessage(message);
        builder.setOnCancelListener(listenerCancel);
        builder.setNegativeButton(R.string._cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNegativeButton(R.string._cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(positive, listenerPositive);
        return builder.create();
    }

    private Dialog makeDialogForDeleteDonor(final Donor donor) {
        DialogInterface.OnCancelListener listenerCancel = new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                PRESENTER.onCancelDeleteDonor();
            }
        };
        DialogInterface.OnClickListener listenerPositive = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PRESENTER.onConfirmDeleteDonor(donor.getId());
            }
        };
        return makeDialogForConfirmation(
                R.string.dialogDeleteDonor_title,
                getString(R.string.dialogDeleteDonor_startMessage) + " " +
                        donor.getName() + " " + donor.getLastName() + " " +
                        getString(R.string.dialogDeleteDonor_endMessage) + " " +
                        donor.getId(),
                R.string.dialogDeleteDonor_positiveButton,
                listenerCancel,
                listenerPositive
        );
    }

    @Override
    public void showDialogDeleteDonor(Donor donor) {
        makeDialogForDeleteDonor(donor).show();
    }

    private Dialog makeDialogForNewPassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialogNewPassword_title);
        View view = getLayoutInflater().inflate(R.layout.layout_dialog_newpassword, null);
        tilDialogCurrentPasword = (TextInputLayout) view.findViewById(R.id.til_dialogNewPassword_current);
        tilDialogConfNewPassword = (TextInputLayout) view.findViewById(R.id.til_dialogNewPassword_conf);
        editDialogCurrentPassword = (EditText) view.findViewById(R.id.editText_dialogNewPassword_current);
        editDialogNewPassword = (EditText) view.findViewById(R.id.editText_dialogNewPassword_new);
        editDialogConfNewPassword = (EditText) view.findViewById(R.id.ediText_dialogNewPassword_conf);
        TextWatcher textWatcherCurrentPassword = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilDialogCurrentPasword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                PRESENTER.onTypingCurrentPassword(s.toString());
            }
        };
        TextWatcher textWatcherConfPassword = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tilDialogConfNewPassword.setErrorEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {
                PRESENTER.onTypingConfPassword(
                        editDialogNewPassword.getText().toString(),
                        s.toString()
                );
            }
        };
        editDialogCurrentPassword.addTextChangedListener(textWatcherCurrentPassword);
        editDialogConfNewPassword.addTextChangedListener(textWatcherConfPassword);
        builder.setView(view);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                PRESENTER.onCancelNewPassword();
            }
        });
        builder.setNegativeButton(R.string.dialogNewPassword_negativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.dialogNewPassword_positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PRESENTER.onChangeNewPassword(
                        editDialogCurrentPassword.getText().toString(),
                        editDialogNewPassword.getText().toString(),
                        editDialogConfNewPassword.getText().toString()
                );
            }
        });
        return builder.create();
    }

    @Override
    public void showDialogNewPassword() {
        makeDialogForNewPassword().show();
    }

    @Override
    public void onCurrentPasswordTypingError(int resError) {
        tilDialogCurrentPasword.setError(getString(resError));
    }

    @Override
    public void onConfPasswordTypingError(int resError) {
        tilDialogConfNewPassword.setError(getString(resError));
    }

    @Override
    public void showDialogDeleteAccount() {
        makeDialogForDeleteAccount().show();
    }

    private Dialog makeDialogForDeleteAccount() {
        DialogInterface.OnCancelListener listenerCancel = new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                PRESENTER.onCancelDeleteAccount();
            }
        };
        DialogInterface.OnClickListener listenerPositive = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PRESENTER.onDeleteAccount();
            }
        };
        return makeDialogForConfirmation(
                R.string.dialogDeleteAccount_title,
                getString(R.string.dialogDeleteAccount_message),
                R.string.dialogDeleteAccount_positiveButton,
                listenerCancel,
                listenerPositive
        );
    }

    @Override
    public void onBackPressed() {
        Intent intentHome = new Intent(Intent.ACTION_MAIN);
        intentHome.addCategory(Intent.CATEGORY_HOME);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intentHome);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PRESENTER.onDetach();
        cardDonor.onDetach();
    }
}
