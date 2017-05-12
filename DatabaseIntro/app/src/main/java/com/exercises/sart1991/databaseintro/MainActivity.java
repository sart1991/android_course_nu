package com.exercises.sart1991.databaseintro;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private DatabaseExample dbExample;
    private TextView textViewTableContent;
    private Button buttonCreate, buttonDelete, buttonInsertTable, buttonInsertColumn;
    private AlertDialog dialogForInsertName;
    private FloatingActionButton fab;
    private String tableName;
    private AlertDialog dialogForInsertColumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textViewTableContent = (TextView) findViewById(R.id.textView_main_tableContent);
        buttonCreate = (Button) findViewById(R.id.button_main_dbCreation);
        buttonInsertTable = (Button) findViewById(R.id.button_main_insertTable);
        buttonInsertColumn = (Button) findViewById(R.id.button_main_insertColumn);
        buttonDelete = (Button) findViewById(R.id.button_main_delete);
        fab = (FloatingActionButton) findViewById(R.id.fab_main_insertName);

        dialogForInsertName = makeDialogForInsertName();
        dialogForInsertColumn = makeDialogForInsertColumn();

        buttonInsertTable.setEnabled(false);
        buttonInsertColumn.setEnabled(false);
        buttonDelete.setEnabled(false);
        fab.setEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.all_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (dbExample == null) return false;
        if (itemId == R.id.item_options_unplug) {
            closeDatabase();
            return true;
        }

        return false;
    }

    public void onClickButtonCreate(View view) {
        dbExample = new DatabaseExample(this);
        tableName = getTableName();
        buttonCreate.setEnabled(false);
        if (tableName.equals("android_metadata")) {
            buttonInsertTable.setEnabled(true);
        } else {
            buttonInsertColumn.setEnabled(true);
            buttonDelete.setEnabled(true);
            fab.setEnabled(true);
        }
        showSnackBar(
                "Base de datos " + dbExample.getDatabaseName() +
                " creada, tablas: " + tableName
        );
    }

    public void onClickButtonInsert(View view) {
        dbExample.createTable();
        buttonInsertTable.setEnabled(false);
        buttonInsertColumn.setEnabled(true);
        buttonDelete.setEnabled(true);
        fab.setEnabled(true);
        showSnackBar("La tabla " + getTableName() + " ha sido creada.");
    }

    public void onClickButtonDelete(View view) {
        SQLiteDatabase database = dbExample.getReadableDatabase();
        database.execSQL("DROP TABLE IF EXISTS " + getTableName());

        buttonInsertTable.setEnabled(true);
        buttonInsertColumn.setEnabled(false);
        buttonDelete.setEnabled(false);
        fab.setEnabled(false);

        showSnackBar("La tabla " + tableName + " fue eliminada");
    }

    public void onClickFab(View view) {
        dialogForInsertName.show();
    }

    public void onClickButtonInsertColumn(View view) {
        dialogForInsertColumn.show();
    }

    private void closeDatabase() {
        SQLiteDatabase database = dbExample.getReadableDatabase();

        if (database.isOpen()) {
            database.close();
            dbExample = null;
            buttonCreate.setEnabled(true);
            buttonInsertTable.setEnabled(false);
            buttonInsertColumn.setEnabled(false);
            buttonDelete.setEnabled(false);
            fab.setEnabled(false);
        }
    }

    private AlertDialog makeDialogForInsertName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view= getLayoutInflater().inflate(R.layout.dialog_insert, null);
        builder.setView(view);
        builder.setTitle("Insertar nuevo nombre");
        final TextInputLayout tilInsertName =
                (TextInputLayout) view.findViewById(R.id.til_dialogInsert_insertName);
        final EditText editTextInsertName = tilInsertName.getEditText();

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showSnackBar("Operación cancelada");
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.setPositiveButton("Insertar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("".equals(editTextInsertName.getText().toString())) {
                    tilInsertName.setErrorEnabled(true);
                    dialog.cancel();
                } else {
                    tilInsertName.setErrorEnabled(false);
                    insertNewName(editTextInsertName.getText().toString());
                }
            }
        });

        return builder.create();
    }

    private void showSnackBar(String message) {
        Snackbar.make(buttonCreate, message, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    private void insertNewName(String newName) {
        String name = getTableName();
        SQLiteDatabase database = dbExample.getWritableDatabase();
        if (!name.equals(newName)) {
            database.execSQL("ALTER TABLE " + name + " RENAME TO " + newName);
            textViewTableContent.setText("First Name: " + name + "\nCurrent name: " + getTableName() + "\nNew name: " + newName);
        }
        else {
            showSnackBar("Esta insertando el mismo nombre");
        }
    }



    private String getTableName() {
        tableName = getColumnName("sqlite_master", "type", "table");
        textViewTableContent.setText("fuck: " + tableName);
        Log.i(TAG, "tableName: " + tableName);
        return tableName;
    }

    private String getColumnName(String tableName, String findBy, String type) {
        SQLiteDatabase database = dbExample.getReadableDatabase();
        String columnName = "";

        Cursor cursor =
                database.rawQuery(
                        "SELECT name FROM " + tableName +
                        " WHERE " + findBy + "='" + type + "'", null
                );

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                columnName = cursor.getString(cursor.getColumnIndex("name"));
                cursor.moveToNext();
            }
        }

        Log.i(TAG, "columnName: " + columnName);
        return columnName;
    }

    private void insertColumn(String name, String type) {
        String lTableName = getTableName();
        SQLiteDatabase database = dbExample.getReadableDatabase();
        database.execSQL("ALTER TABLE " + lTableName + " ADD COLUMN " + name + " " + type);
        textViewTableContent.setText(getColumnName(lTableName, "name", name));
        showSnackBar("Columna " + name + " creada");
    }

    private AlertDialog makeDialogForInsertColumn() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insertar columna");
        View view =  getLayoutInflater().inflate(R.layout.dialog_insertcolumn, null);
        builder.setView(view);
        final EditText editTextColumnName =
                ((TextInputLayout)view.findViewById(R.id.til_dialogInsertColumn_columnName))
                        .getEditText();
        final Spinner spinnerType = (Spinner) view.findViewById(R.id.spinner_dialogInsertColumn_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.dialogInsertColumn_types, android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);
        builder.setPositiveButton("Insertar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String columnName = editTextColumnName.getText().toString();
                String columnType = spinnerType.getSelectedItem().toString();
                insertColumn(columnName, columnType);
            }
        });
        return builder.create();
    }
}
