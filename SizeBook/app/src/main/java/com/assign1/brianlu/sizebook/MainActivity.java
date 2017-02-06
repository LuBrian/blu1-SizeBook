package com.assign1.brianlu.sizebook;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.R.id.list;

public class MainActivity extends ListActivity {
    private static final String FILENAME = "file.sav";

    private EditText nameText;
    private EditText dateText;

    private EditText neckText;
    private EditText bustText;

    private EditText chestText;
    private EditText waistText;

    private EditText hipText;
    private EditText inseamText;

    private EditText commentText;

    private TextView recordsCount;
    private ListView oldRecords;

    private ArrayList<Record> recordList;
    private ArrayAdapter<Record> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameText = (EditText) findViewById(R.id.RName);
        dateText = (EditText) findViewById(R.id.RDate);

        neckText = (EditText) findViewById(R.id.RNeck);
        bustText = (EditText) findViewById(R.id.RBust);

        chestText = (EditText) findViewById(R.id.RChest);
        waistText = (EditText) findViewById(R.id.RWaist);

        hipText = (EditText) findViewById(R.id.RHip);
        inseamText = (EditText) findViewById(R.id.RInseam);

        commentText = (EditText) findViewById(R.id.RComment);
        Button updateButton = (Button) findViewById(R.id.update);
        Button createButton = (Button) findViewById(R.id.create);
        Button deleteButton = (Button) findViewById(R.id.delete);
        recordsCount = (TextView) findViewById(R.id.recordsCount);
        oldRecords = (ListView) findViewById(android.R.id.list);



        createButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setResult(RESULT_OK);
                String name = nameText.getText().toString();
                String date = dateText.getText().toString();
                //reference: http://stackoverflow.com/questions/6866633/converting-string-to-double-in-android
                // 2017-02-04
                Double neck = checkInputDouble(neckText.getText().toString());
                Double bust = checkInputDouble(bustText.getText().toString());
                Double chest = checkInputDouble(chestText.getText().toString());
                Double waist = checkInputDouble(waistText.getText().toString());
                Double hip = checkInputDouble(hipText.getText().toString());
                Double inseam = checkInputDouble(inseamText.getText().toString());
                String comment = commentText.getText().toString();
                if (name.equals("")){
                    //reference http://stackoverflow.com/questions/3500197/how-to-display-toast-in-android
                    // 2017-02-04
                    Toast.makeText( getBaseContext() ,"Please enter a name! ",Toast.LENGTH_SHORT).show();
                } else {
                    if(!date.equals("")) {
                       if(!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                           Toast.makeText(getBaseContext(), "Please enter a valid date! ", Toast.LENGTH_SHORT).show();
                       }else{
                           Record newRecord = new Record(name, date, neck, bust, chest, waist, hip, inseam, comment);
                           recordList.add(newRecord);
                           adapter.notifyDataSetChanged();
                           saveInFile();
                           recordsCount.setText(String.valueOf(oldRecords.getAdapter().getCount()));
                           emptyInput();
                       }
                    } else{
                        Record newRecord = new Record(name, date, neck, bust, chest, waist, hip, inseam, comment);
                        recordList.add(newRecord);
                        adapter.notifyDataSetChanged();
                        saveInFile();
                        recordsCount.setText(String.valueOf(oldRecords.getAdapter().getCount()));
                        emptyInput();
                    }
                }

            }
        });
        /**
         * @
         */
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setResult(RESULT_OK);
                //reference: http://wptrafficanalyzer.in/blog/deleting-selected-items-from-listview-in-android/
                // 2017-02-04
                /** Getting the checked items from the listview */
                SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                        adapter.remove(recordList.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();
                saveInFile();
                recordsCount.setText(String.valueOf(oldRecords.getAdapter().getCount()));
                emptyInput();
            }
        });
        //reference: http://stackoverflow.com/questions/9097723/adding-an-onclicklistener-to-listview-android
        // 2017-02-04
        oldRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                setResult(RESULT_OK);
                SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();

                for(int i=itemCount-1; i >= 0; i--) {
                    if (checkedItemPositions.get(i)) {
                        nameText.setText(recordList.get(i).getName());
                        dateText.setText(recordList.get(i).getDate());
                        neckText.setText(recordList.get(i).getNeck());
                        bustText.setText(recordList.get(i).getBust());
                        chestText.setText(recordList.get(i).getChest());
                        waistText.setText(recordList.get(i).getWaist());
                        hipText.setText(recordList.get(i).getHip());
                        inseamText.setText(recordList.get(i).getInseam());
                        commentText.setText(recordList.get(i).getComment());
                    }
                }
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                setResult(RESULT_OK);
                //reference: http://wptrafficanalyzer.in/blog/deleting-selected-items-from-listview-in-android/
                // 2017-02-04
                /** Getting the checked items from the listview */
                SparseBooleanArray checkedItemPositions = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checkedItemPositions.get(i)){
                        if (nameText.getText().toString().equals( "")){
                            Toast.makeText( getBaseContext() ,"Please enter a name! ",Toast.LENGTH_SHORT).show();
                        } else {
                            if(!dateText.getText().toString().equals("")){
                                    if(!dateText.getText().toString().matches("\\d{4}-\\d{2}-\\d{2}")) {
                                        Toast.makeText(getBaseContext(), "Please enter a valid date! ", Toast.LENGTH_SHORT).show();
                                    }else{
                                        try {
                                            recordList.get(i).setName( nameText.getText().toString());

                                            recordList.get(i).setComment( commentText.getText().toString());
                                            recordList.get(i).setDate( dateText.getText().toString());
                                            recordList.get(i).setNeck( checkInputDouble(neckText.getText().toString()));
                                            recordList.get(i).setBust( checkInputDouble(bustText.getText().toString()));
                                            recordList.get(i).setChest( checkInputDouble(chestText.getText().toString()));
                                            recordList.get(i).setWaist( checkInputDouble(waistText.getText().toString()));
                                            recordList.get(i).setHip( checkInputDouble(hipText.getText().toString()));
                                            recordList.get(i).setInseam( checkInputDouble(inseamText.getText().toString()));
                                            checkedItemPositions.clear();
                                            adapter.notifyDataSetChanged();
                                            saveInFile();
                                            recordsCount.setText(String.valueOf(oldRecords.getAdapter().getCount()));
                                            emptyInput();
                                        } catch (StringTooLongException | NoNegativeValueException e) {
                                            e.printStackTrace();
                                        }
                                    }
                            }else{
                                try {
                                    recordList.get(i).setName( nameText.getText().toString());

                                    recordList.get(i).setComment( commentText.getText().toString());
                                    recordList.get(i).setDate( dateText.getText().toString());
                                    recordList.get(i).setNeck( checkInputDouble(neckText.getText().toString()));
                                    recordList.get(i).setBust( checkInputDouble(bustText.getText().toString()));
                                    recordList.get(i).setChest( checkInputDouble(chestText.getText().toString()));
                                    recordList.get(i).setWaist( checkInputDouble(waistText.getText().toString()));
                                    recordList.get(i).setHip( checkInputDouble(hipText.getText().toString()));
                                    recordList.get(i).setInseam( checkInputDouble(inseamText.getText().toString()));
                                    checkedItemPositions.clear();
                                    adapter.notifyDataSetChanged();
                                    saveInFile();
                                    recordsCount.setText(String.valueOf(oldRecords.getAdapter().getCount()));
                                    emptyInput();
                                } catch (StringTooLongException | NoNegativeValueException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        });
    }



    @Override
    public void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Record>(this, R.layout.record_item,recordList);
        oldRecords.setAdapter(adapter);
        //reference: http://stackoverflow.com/questions/23708271/count-total-number-of-list-items-in-a-listview
        // 2017-02-04
        recordsCount.setText(String.valueOf(oldRecords.getAdapter().getCount()));
    }
    private void emptyInput(){
        nameText.setText("");
        dateText.setText("");
        neckText.setText("");
        bustText.setText("");
        chestText.setText("");
        waistText.setText("");
        hipText.setText("");
        inseamText.setText("");
        commentText.setText("");

    }




    private Double checkInputDouble(String inputString){
        try{
            return Double.parseDouble(inputString);
        }catch(NumberFormatException e){
            return 0.0;
        }
    }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson  = new Gson();
            // Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Record>>(){}.getType();
            recordList = gson.fromJson(in,listType);
        } catch (FileNotFoundException e) {
            recordList = new ArrayList<Record>();
            // TODO Auto-generated catch block
        }

    }



    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(recordList, out);
            out.flush();
            fos.close();
        }  catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
}


