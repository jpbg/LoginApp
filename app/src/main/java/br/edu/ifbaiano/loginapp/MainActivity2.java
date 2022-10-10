package br.edu.ifbaiano.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import br.edu.ifbaiano.loginapp.dao.User;
import br.edu.ifbaiano.loginapp.helper.DBHelper;

public class MainActivity2 extends AppCompatActivity {

    DBHelper mydb;
    User userDao;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv = findViewById(R.id.txtLinkCadastro2);

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        String user = bundle.getString("user");

        mydb = new DBHelper(MainActivity2.this);

        userDao = mydb.obterUsuarioPorUser(user);

        tv.setText(userDao.getUsername());



    }
}