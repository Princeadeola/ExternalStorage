package com.example.externalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class RetrieveActivity extends AppCompatActivity {
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        output = (TextView) findViewById(R.id.outPutID);
    }


    public void PublicRetrieve(View view) {
        File storageLocation = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS);
        File file = new File(storageLocation, "input.txt");
        String text = ReadDateFromExternalStorageToOutPutTextView(file);
        if (text != null){
            output.setText(text);
        }else {
            output.setText("No Text Found");
        }
    }

    public void PrivateRetrieve(View view) {
        // Folder location
        File storageLocation = getExternalFilesDir("ExternalStorageLesson");
        // File Location
        File file = new File(storageLocation, "input.txt");
        String text = ReadDateFromExternalStorageToOutPutTextView(file);
        if (text != null){
            output.setText(text);
        }else {
            output.setText("No Text Found");
        }
    }

    public void Back(View view) {
        Intent intent = new Intent(RetrieveActivity.this, MainActivity.class);
        startActivity(intent);
    }



    private String ReadDateFromExternalStorageToOutPutTextView(File file) {
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = inputStream.read()) != -1){
                buffer.append((char) i);
            }
            return buffer.toString();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}


//Sales Receipt
//Think about the grocery store and how long your receipt can get! Write a program that allows for a receipt for 100 items.
// Populate the array with random doubles in a range you find typical for grocery purchases. Allow for some ‘small’ negative numbers
// to represent coupons or deals. Calculate the sum, the sum of the positive numbers and the sum of the negative numbers.
// Calculate 3% sales tax on the positive numbers only. Print a receipt that includes each value in the array, the positive total,
// the most expensive item, the negative total (with a you saved type statement), the tax, and the total due to be paid to the store.
// Test it with random data, only positive numbers and with a small receipt of numbers you enter.

// <==== Psedocode =========>
// ==> User is able to generate a receipt for 100 items
// ==> Range of typical for grocery purchases
// ==> User is able to get a coupon code or deal
// ==> Calculate sum
// ==> Calculate sum of the positive numbers
// ==> Calculate the sum of the negative numbers
//
//
//








