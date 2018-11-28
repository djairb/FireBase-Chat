package com.example.djairb.chatfirebaseproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button botaoAdicionar;
    private EditText cpfLogin;
    private EditText senhaLogin;
    private Button botaoLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(cpfLogin.getText().toString())){
                            logar();


                        }else{
                            Toast.makeText(getApplicationContext(), "Usuario nao registrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddPessoaActivity.class);
                startActivity(intent);

            }
        });



    }

    private void setView() {
        botaoAdicionar = findViewById(R.id.botaoAddUsuarioId);
        cpfLogin = findViewById(R.id.cpfLoginId);
        senhaLogin = findViewById(R.id.senhaLoginId);
        botaoLogar = findViewById(R.id.botaoLogar);


    }

    private void logar(){
        Auxiliar auxiliar = new Auxiliar(getApplicationContext());
        auxiliar.saveLoginCurrentUser(cpfLogin.getText().toString());
        Toast.makeText(getApplicationContext(),auxiliar.retornarCpfUsuarioLogado(),Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this,MenuActivity.class);
        startActivity(intent);

    }
}
