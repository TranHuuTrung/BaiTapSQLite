package com.example.admin.baitapsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtName, edtPhone, edtAddress;
    private RadioGroup rbGroup;
    private RadioButton rbGender;
    //private RadioButton isMale, isFemale;
    private Button btnAdd, btnCancel;
    private MyDatabase db  = new MyDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        getSupportActionBar().setTitle("Add");
        init();
    }


    private void init() {
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtAddress = (EditText) findViewById(R.id.edtAddress);

        rbGroup = (RadioGroup) findViewById(R.id.rbGroup);
//        isMale = (RadioButton) findViewById(R.id.isMale);
//        isFemale = (RadioButton) findViewById(R.id.isFemale);
        rbGender = (RadioButton) findViewById(R.id.rbGender);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        
    }

    private void add() {
        Contact contact = new Contact();
        contact.setAddress(edtAddress.getText().toString());
        contact.setName(edtName.getText().toString());
        contact.setNumber(edtPhone.getText().toString());
        long day = System.currentTimeMillis();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date(day));
        contact.setDate(date);
        String h = new SimpleDateFormat("hh:mm:ss").format(new Date(day));
        contact.setTime(h);
        int idrad = rbGroup.getCheckedRadioButtonId();
        rbGender = (RadioButton) findViewById(idrad);
        contact.setGender(rbGender.getText().toString());

        db.open();
        int id = db.addContact(contact);
        contact.setId(id);
        db.close();

        Intent i = new Intent();
        Bundle b = new Bundle();
        b.putSerializable("RETURN",contact);
        i.putExtras(b);
        setResult(RESULT_OK,i);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnAdd:
                add();
                break;
            case R.id.btnCancel:
                finish();
                break;
        }

    }


}
