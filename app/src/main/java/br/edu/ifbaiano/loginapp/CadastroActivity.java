package br.edu.ifbaiano.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {


    EditText edt_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro);


        getSupportActionBar().hide();
        edt_user = findViewById(R.id.edt_username);


        //O método getIntent retorna o objeto do tipo Intent, sendo possível assim,
        // obter as informações extras que foram passadas da activity anterior.

        Intent it = getIntent();

        edt_user.setText(it.getStringExtra("user").toString());






















       /* getSupportActionBar().hide();


        Intent it = getIntent();
        Bundle data = it.getExtras();


        String user = data.getString("user","");*/




    }
}
