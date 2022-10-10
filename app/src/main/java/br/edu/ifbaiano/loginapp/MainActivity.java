package br.edu.ifbaiano.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifbaiano.loginapp.helper.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText edtUser, edtPassword;
    Button btnLogin;

    TextView tvCadastrar;
    
    DBHelper myDB;


    /*
    * Primeiro método a ser executado quando uma Activity (tela) é chamada.
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isLogged();

        myDB = new DBHelper(MainActivity.this);
        
        //Método responsável pela ligação da activity com o layout produzido.
        setContentView(R.layout.activity_login);

        //Método responsável por pegar em tempo de execução o objeto ActionBar e posteriormente ocultá-lo.
        getSupportActionBar().hide();

        //Ação necessária para obter em tempo de execução a view (componente) correspondente.
        tvCadastrar = findViewById(R.id.txt_cadastrar);
        edtUser = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtSenha);
        btnLogin = findViewById(R.id.btnEntrar);


        /*
        * Ao definir um listener a um botão, o mesmo fica monitorando o componente que caso seja clicado,
        * automaticamente executa o método onClick.
        * */
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(edtUser.getText().toString().isEmpty()){
                    edtUser.setError("Campo obrigatório!");
                }else if(edtPassword.getText().toString().isEmpty()) {
                    edtUser.setError("Campo obrigatório!");
                }
                
               Boolean login = myDB.validarLoginSenha(edtUser.getText().toString(),
                                                      edtPassword.getText().toString());

                       
                       if(login){
                           Toast.makeText(MainActivity.this, "Login com sucesso", Toast.LENGTH_LONG).show();

                           SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                           SharedPreferences.Editor myEditor = myPreferences.edit();
                           myEditor.putString("USER", edtUser.getText().toString());
                           myEditor.commit();


                           Intent it = new Intent(MainActivity.this, MainActivity2.class);
                           it.putExtra("user", edtUser.getText().toString());
                           startActivity(it);
                       }else{
                           Toast.makeText(MainActivity.this, "Login incorreto", Toast.LENGTH_LONG).show();
                       }
            }
        });




        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog pg = new ProgressDialog(MainActivity.this);
                pg.setTitle("Redirecionando...");
                pg.show();


                /*
                * Intent é a forma como o Android gerencia a comunicação entre activitys e outras aplicações.
                * É necessário ao chamar uma nova activity, os parâmetros, Activity atual (contexto) e a nova activity a ser acessada.
                * */
                Intent it = new Intent(MainActivity.this, CadastroActivity.class);

                //O método putExtra é responsável por adicionar informações que serão passadas para a nova activity.
                it.putExtra("user", edtUser.getText().toString());

                startActivity(it);


            }
        });


    }

    private void isLogged() {

        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String user = myPreferences.getString("USER", "");

        if(user.equals("")){
            return;
        }else{
            Intent it = new Intent(getApplicationContext(), MainActivity2.class);
            it.putExtra("user", user);
            startActivity(it);
        }

    }

    @Override
    protected void onResume() {

        super.onResume();

              //  progressBar.dismiss();


        Log.d("TAG", "onResume: ");
       /* if(getSupportActionBar() != null){

            getSupportActionBar().show();
        }
        Toast.makeText(MainActivity.this, "passou resume", Toast.LENGTH_SHORT).show();*/
    }


}