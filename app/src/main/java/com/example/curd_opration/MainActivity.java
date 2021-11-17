package com.example.curd_opration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements DataAdapter.OnEditListener {
    RecyclerView EventList;
    DataAdapter dataAdapterList;
    ArrayList<DataModelClass> contactModelClassArrayList;
    private EditText EventName, EventDec;
    AlertDialog alertDialog;
    private MaterialButton addBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn=findViewById(R.id.btnAdd);

        contactModelClassArrayList = new ArrayList<>();
        EventName = findViewById(R.id.EventName);
        EventDec = findViewById(R.id.EventDec);

        EventList = findViewById(R.id.rv);
        EventList.setHasFixedSize(true);

        EventList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEventName = "", strEventDec = "";
                if (EventName.getText() != null) {
                    strEventName = EventName.getText().toString();
                }

                if (strEventName.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter Event name", Toast.LENGTH_LONG).show();
                    return;
                }

                if (EventDec.getText() != null) {
                    strEventDec = EventDec.getText().toString();
                }
                if (strEventDec.equals("")) {
                    Toast.makeText(MainActivity.this, "Please enter Event description", Toast.LENGTH_LONG).show();
                    return;
                }

                addEvent(strEventName, strEventDec);
            }

        });

    }

    private void addEvent(String strEventName,String strEventDec) {
        DataModelClass obj = new DataModelClass();

        obj.setName(strEventName);
        obj.setDec(strEventDec);


        contactModelClassArrayList.add(obj);


        dataAdapterList = new DataAdapter(this, contactModelClassArrayList, this::onEditClick);
        EventList.setAdapter(dataAdapterList);

    }


    @Override
    public void onEditClick(DataModelClass listCurrentData,int CurrentPosition) {

        AlertDialog.Builder builderObj=new AlertDialog.Builder(this);
        View view=LayoutInflater.from(this).inflate(R.layout.layout_edit_cantact,null);

        EditText userNameEtn=view.findViewById(R.id.userName);
        EditText userContactEtn=view.findViewById(R.id.userMobile);
        MaterialButton btnEdit=view.findViewById(R.id.btnEdit);

        userContactEtn.setText(listCurrentData.getDec());
        userNameEtn.setText(listCurrentData.getName());

        ImageView closeAlert=view.findViewById(R.id.closeAlert);
        builderObj.setCancelable(false);
        builderObj.setView(view);

        closeAlert.setOnClickListener(v->{
            alertDialog.cancel();
        });

        btnEdit.setOnClickListener(v->{
            String strUserName = "", strUserContact = "";

            if (userNameEtn.getText() != null) {
                strUserName = userNameEtn.getText().toString();
            }

            if (strUserName.equals("")) {
                Toast.makeText(this, "Please enter user name", Toast.LENGTH_LONG).show();
                return;
            }

            if (userContactEtn.getText() != null) {
                strUserContact = userContactEtn.getText().toString();
            }
            if (strUserContact.equals("")) {
                Toast.makeText(this, "Please enter your contact number", Toast.LENGTH_LONG).show();
                return;
            }

            editEvent(strUserName, strUserContact,CurrentPosition);

        });

        alertDialog=builderObj.create();
        alertDialog.show();

    }

    public void editEvent(String strUserName, String strUserContact,int currentPosition){


        DataModelClass obj = new DataModelClass();

        obj.setName(strUserName);
        obj.setDec(strUserContact);


        dataAdapterList.editData(obj,currentPosition);
        alertDialog.cancel();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}