package com.example.admin.baitapsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private ContactAdapter mAdapter;
    private MyDatabase  db;
    private List<Contact> contacts;
    private int posClick = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDatabase(this);
        contacts = new ArrayList<>();
        getData();
        handle();

    }
    public void getData() {
        db.open();
        contacts.clear();
        contacts = db.getData();
        db.close();
    }
    private void handle() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mAdapter = new ContactAdapter(contacts, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecycleTouchListener(this, mRecyclerView, new RecycleTouchListener(){
            @Override
            public void onClick(View view, int position){

            }
            @Override
            public void onLongClick(View view, int position){

            }

        }));

     }
     @Override
    public boolean onCreateOptionsMenu(Menu menu){
         getMenuInflater().inflate(R.menu.menu_option, menu);
         return true;
     }
     @Override
    public boolean onOptionsItemSelected(MenuItem item){
         int id = item.getItemId();
         switch(id){
             case android.R.id.home:
                 finish();
                 break;
             case R.id.addContact:
                 Intent intent  = new Intent(MainActivity.this, AddActivity.class);
                 startActivityForResult(intent,1);
                 break;
             case R.id.deleteAll:
                 deleteAll();
                 break;

         }
         return super.onOptionsItemSelected(item);

     }

    private void deleteAll() {
        db.open();
        db.deleteContactAll();
        contacts.clear();
        mAdapter.notifyDataSetChanged();
        db.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if(requestCode==1&&resultCode== RESULT_OK){
            Contact contact = (Contact) data.getExtras().getSerializable("RETURN");
            contacts.add(contact);
            mAdapter.notifyDataSetChanged();
        }
    }


}
