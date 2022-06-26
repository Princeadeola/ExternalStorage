package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText inputText;
    private int STORAGE_PERMISSION_CODE = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (EditText) findViewById(R.id.inputTextID);
    }

    public void PublicClick(View view){
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE },
                STORAGE_PERMISSION_CODE);

        String input = inputText.getText().toString();
        File storageLocation = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS);
        File file = new File(storageLocation, "input.txt");
        writeDateFromEditTextToInputText(file, input);
        inputText.setText("");

    }

    public void PrivateClick(View view){
        String input = inputText.getText().toString();
        File storageLocation = getExternalFilesDir("ExternalStorageLesson");
        File file = new File(storageLocation, "input.txt");
        writeDateFromEditTextToInputText(file, input);
        inputText.setText("");
    }

    public void Next(View view){
        Intent intent = new Intent(MainActivity.this, RetrieveActivity.class);
        startActivity(intent);
    }

    public void writeDateFromEditTextToInputText(File file, String input){
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(input.getBytes());
            Toast.makeText(this, "Finished writing to " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}