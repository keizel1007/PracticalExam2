package com.example.kc.practicalexam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class MainActivity extends AppCompatActivity {
    EditText fName, lName, exam1, exam2, avgDisplay;
    Button avgBtn;
    public String[] away;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        exam1= findViewById(R.id.exam1);
        exam2= findViewById(R.id.exam2);
        avgDisplay= findViewById(R.id.avgDisplay);
        avgBtn = (Button) findViewById(R.id.avgBtn);
        avgBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try {
                    saveInternal();
                    loadInternal();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        loadInternal();

    }


    public void saveInternal() throws IOException {
        int ex1= Integer.parseInt(exam1.getText().toString());
        int ex2= Integer.parseInt(exam2.getText().toString());

        int avg = (ex1+ex2)/2;
        String fn = fName.getText().toString();
        String ln = "%"+ lName.getText().toString();
        String ave = "%"+ avg;
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("Data2.txt",MODE_PRIVATE);
            fos.write(fn.getBytes());
            fos.write(ln.getBytes());
            fos.write(ave.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Saved in "+getFilesDir(),Toast.LENGTH_LONG).show();
    }


    public void loadInternal(){
        try {
            FileInputStream fis = openFileInput("Data2.txt");
            StringBuffer buffer = new StringBuffer();
            int read=0;

            while ((read = fis.read())!=-1){
                buffer.append((char)read);

            }
            String s  = buffer.toString();
            away = s.split("%");
            int count= away.length;
        if(buffer != null){
            fName.setText(away[0]);
            lName.setText(away[1]);
           avgDisplay.setText(away[2]);

        }


        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


    }
}