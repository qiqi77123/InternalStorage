package com.example.ssl.internalstorage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText internal;
    private Button save,read;
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        internal = (EditText)findViewById(R.id.Internal);
        save = (Button)findViewById(R.id.Save);
        read = (Button)findViewById(R.id.Read);
        show = (TextView) findViewById(R.id.Show);

        save.setOnClickListener(this);
        read.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Save:
                savedata();
                break;
            case R.id.Read:
                readdata();
                break;
        }

    }

    private void savedata() {
        String data=internal.getText().toString();
        try {
            FileOutputStream fos = openFileOutput("data.txt",MODE_PRIVATE);
            try {
                fos.write(data.getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }


    private void readdata() {
        try {
            FileInputStream fis=openFileInput("data.txt");
            StringBuffer str=new StringBuffer();
            byte[] buffer = new byte[512];
            int hasRead=-1;
            try {
                while((hasRead=fis.read(buffer))!=-1){
                    str.append(new String(buffer,0,hasRead));
                }
                show.setText(str.toString());
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
