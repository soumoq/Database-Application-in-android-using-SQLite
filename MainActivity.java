package com.example.app1;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Databash mydb;
    EditText ename,esurname,emask,id;
    Button badd,view,update,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new Databash(this);

        ename=(EditText)findViewById(R.id.name);
        esurname=(EditText)findViewById(R.id.surname);
        emask=(EditText)findViewById(R.id.mask);
        badd=(Button) findViewById(R.id.add);
        view=(Button)findViewById(R.id.view);
        update=(Button)findViewById(R.id.update);
        id=(EditText)findViewById(R.id.id);
        delete=(Button)findViewById(R.id.delete);
        AddData();
        viewAll();
        Updatedata();
        edelete();
    }

    public void edelete()
    {
        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedrow=mydb.deleteData(id.getText().toString());
                        if(deletedrow>0)
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void Updatedata()
    {
        update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate= mydb.updateData(id.getText().toString(),ename.getText().toString(),esurname.getText().toString(),emask.getText().toString());
                    if(isUpdate==true)
                        Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(MainActivity.this,"Data Not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData()
    {
        badd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInseretd = mydb.insertdata(ename.getText().toString(),esurname.getText().toString(),emask.getText().toString());
                        if(isInseretd==true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewAll()
    {
        view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor ras= mydb.getAlldata();
                        if(ras.getCount()==0)
                        {
                            //show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        else
                        {
                            StringBuffer buffer= new StringBuffer();
                            while(ras.moveToNext())
                            {
                                buffer.append("Id :"+ ras.getString(0)+"\n");
                                buffer.append("NAME :"+ ras.getString(1)+"\n");
                                buffer.append("SUR_NAME :"+ ras.getString(2)+"\n");
                                buffer.append("MARKS :"+ ras.getString(3)+"\n\n");

                            }

                            //show all data
                            showMessage("Data",buffer.toString());
                        }
                    }
                }
        );
    }

    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
