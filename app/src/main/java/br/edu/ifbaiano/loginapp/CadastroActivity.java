package br.edu.ifbaiano.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifbaiano.loginapp.helper.DBHelper;

public class CadastroActivity extends AppCompatActivity {

    DBHelper mydb;
    EditText edt_user, edt_mail, edt_password;
    Button btn_cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cadastro);

        mydb = new DBHelper(CadastroActivity.this);
        
        getSupportActionBar().hide();
        edt_user = findViewById(R.id.edt_username);
        edt_mail = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_senha);
        btn_cadastrar = findViewById(R.id.btn_cadastrar);        


        //O método getIntent retorna o objeto do tipo Intent, sendo possível assim,
        // obter as informações extras que foram passadas da activity anterior.

        Intent it = getIntent();

        edt_user.setText(it.getStringExtra("user").toString());

        
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_mail.getText().toString().equals("")){
                    edt_mail.setError("Campos obrigatório");
                }
                if(edt_user.getText().toString().equals("")){
                    edt_user.setError("Campos obrigatório");
                }
                if(edt_password.getText().toString().equals("")){
                    edt_password.setError("Campos obrigatório");
                }
                
                boolean isCadastrado = mydb.usuarioExistente(edt_mail.getText().toString());
                if(isCadastrado){
                    Toast.makeText(CadastroActivity.this, "Já existe usuário cadastrado com este email", Toast.LENGTH_SHORT).show();
                }else{
                    boolean insert = mydb.cadastrarUsuario(edt_user.getText().toString(), edt_mail.getText().toString(),
                            edt_password.getText().toString());
                    
                    
                    if(insert){
                        Intent it = new Intent(CadastroActivity.this, MainActivity2.class);
                        it.putExtra("user", edt_user.getText().toString());
                        startActivity(it);
                    }else{
                        Toast.makeText(CadastroActivity.this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                    }
                }
                
            }
        });




















    }
}
