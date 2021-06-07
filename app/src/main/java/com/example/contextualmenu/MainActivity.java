package com.example.contextualmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String [] name={"Shital","Siddharth","Abhishek","Supriya","Kanishaka","Komal"};
    String[] phone={"34234","456734","8756748","564784","6753943","7563985"};

    int nameIndex=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.listview);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,name);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                nameIndex=position;
            }
        });

        registerForContextMenu(listView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=getMenuInflater();
        menu.setHeaderTitle("Select Action");
        menuInflater.inflate(R.menu.my_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.edit:
                Toast.makeText(this,"click on edit",Toast.LENGTH_LONG).show();
                break;
            case R.id.remove:
                Toast.makeText(this,"click on remove",Toast.LENGTH_LONG).show();
                break;
            case R.id.view:
                Toast.makeText(this,"click on view",Toast.LENGTH_LONG).show();
                break;
            case R.id.call:
                Log.d("Phone",phone[nameIndex]);
                callToStudent(phone[nameIndex]);
                break;
            default:
                super.onContextItemSelected(item);
        }
        return true;
    }

    public void callToStudent(String number)
    {
        Intent calling=new Intent(Intent.ACTION_CALL);
        calling.setData(Uri.parse("tel:"+number));
        startActivity(calling);
    }
}