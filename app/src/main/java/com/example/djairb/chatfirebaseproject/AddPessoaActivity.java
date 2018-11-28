package com.example.djairb.chatfirebaseproject;

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

public class AddPessoaActivity extends AppCompatActivity {


    private EditText nomeNovoUsuario;
    private EditText senhaNovoUsuario;
    private EditText cpfNovoUsuario;
    private Button registrarNovoUsuario;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pessoa);
        setView();
        registrarNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Usuarios");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(cpfNovoUsuario.getText().toString())){
                            Toast.makeText(getApplicationContext(), "Cpf ja registrado", Toast.LENGTH_SHORT).show();


                        }else{
                            addUsuarioBanco();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });


    }

    private void setView() {

        nomeNovoUsuario = findViewById(R.id.nomeInfoId);
        senhaNovoUsuario = findViewById(R.id.senhaInfoId);
        cpfNovoUsuario = findViewById(R.id.cpfInfoId);
        registrarNovoUsuario = findViewById(R.id.botaoRegistrarUsuarioId);
    }

    private void addUsuarioBanco(){
        String nomeUsuario = nomeNovoUsuario.getText().toString();
        String cpfUsuario = cpfNovoUsuario.getText().toString();
        String senhaUsuario = senhaNovoUsuario.getText().toString();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        //String uniqueKey = databaseReference.push().getKey();
        Usuario usuario = new Usuario(nomeUsuario,cpfUsuario,senhaUsuario);
        databaseReference.child(cpfUsuario).setValue(usuario);

        Toast.makeText(this, "Usuário Adicionado com suscesso", Toast.LENGTH_SHORT).show();
        finish();

    }
}
