package com.example.ohlaph.first;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {



    private List tester;
    private ListView lv;
    private EditText gg;
    private Button button;
    private Button clearButton;
    private ArrayList list;
    private ArrayAdapter<String> arrayAdapter;
    private String pathToAppFolder;
    private String filePath;
    String item;


    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        arrayAdapter.clear();

        startUp();
        arrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        System.out.println("MOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOODE IS IN CREATE");
        setContentView(R.layout.activity_main);

        //create the list
        pathToAppFolder = getExternalFilesDir(null).getAbsolutePath();
        filePath = pathToAppFolder + File.separator + "list.txt";

        startUp();

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);

        lv.setAdapter(arrayAdapter);

        final TextView firstTextView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        clearButton = (Button) findViewById(R.id.clear);
        gg = (EditText) findViewById(R.id.item);
        arrayAdapter.notifyDataSetChanged();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String no = gg.getText().toString();
                if (!no.trim().isEmpty()) {
                    tester.addItem(no);
                    tester.exportList(filePath);
                    arrayAdapter.insert(no, 0);
                    arrayAdapter.notifyDataSetChanged();

                }
                gg.setText("");

            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tester.clearList(filePath);
                arrayAdapter.clear();
                arrayAdapter.notifyDataSetChanged();
            }
        });


        lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arrayAdapter.notifyDataSetChanged();
                item = lv.getItemAtPosition(position).toString();
                tester.removeItem(arrayAdapter.getPosition(item));
                arrayAdapter.remove(item.toString());
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Item removed from list.",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }//END onCreate()


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void plow() {
        try {
            List temp = new List();
            tester = temp;

            tester.exportList(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//END plow

    public void startUp() {

        try {
            tester = new List();
        } catch (Exception e) {
        }

        try {
            tester.importList(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        lv = (ListView) findViewById(R.id.listDisplay);
        list = new ArrayList<String>();

        for (Item item : tester.populate()) {
            list.add(item);
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);

        lv.setAdapter(arrayAdapter);

        final TextView firstTextView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        clearButton = (Button) findViewById(R.id.clear);
        gg = (EditText) findViewById(R.id.item);

    }//END startUp()

}//END MAIN CLASS
