package br.edu.ifbaiano.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUser;
    Button btnLogin;

    TextView tvCadastrar;


    /*
    * Primeiro método a ser executado quando uma Activity (tela) é chamada.
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Método responsável pela ligação da activity com o layout produzido.
        setContentView(R.layout.activity_login);

        //Método responsável por pegar em tempo de execução o objeto ActionBar e posteriormente ocultá-lo.
        getSupportActionBar().hide();

        //Ação necessária para obter em tempo de execução a view (componente) correspondente.
        tvCadastrar = findViewById(R.id.txt_cadastrar);
        edtUser = findViewById(R.id.edtUsuario);
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
                }else{
                    Toast.makeText(MainActivity.this, "Usuário não cadastrado",
                            Toast.LENGTH_SHORT).show();

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
















        /*txtNovoCadastro = findViewById(R.id.txtLinkCadastro);
        edtUser = findViewById(R.id.edtUsuario);
        btnLogin = findViewById(R.id.btnEntrar);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtUser.getText().toString().isEmpty()){
                    edtUser.setError("Campo obrigatório!");
                }
            }
        });

        progressBar = new ProgressDialog(MainActivity.this);

                txtNovoCadastro.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {


               progressBar.setCancelable(true);//you can cancel it by pressing back button
               progressBar.setMessage("Redirecionando ...");

               progressBar.show();//displays the progress bar


               Intent it = new Intent(MainActivity.this, CadastroActivity.class);


               it.putExtra("user", edtUser.getText().toString() == null ? "" : edtUser.getText().toString());


               startActivity(it);

               }
               });





        Log.d("TAG", "onCreate: ");
        if(getSupportActionBar() != null){

            getSupportActionBar().hide();
        }*/
        /*Toast.makeText(MainActivity.this, "passou create", Toast.LENGTH_SHORT).show();*/
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