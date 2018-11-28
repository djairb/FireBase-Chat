package com.example.djairb.chatfirebaseproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaPessoasActivity extends AppCompatActivity {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayAdapter adapter;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceConversa;
    private ListView listaUsuario;
    private Auxiliar auxiliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoas);

        listaUsuario = findViewById(R.id.listaUsuariosId);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        //databaseReferenceConversa = databaseReference.child("Mensagens").child(auxiliar.retornarCpfUsuarioLogado());
        auxiliar = new Auxiliar(getApplicationContext());
        
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                
                for(DataSnapshot dados:dataSnapshot.getChildren()){
                    
                    Usuario usuario = dados.getValue(Usuario.class);
                    addUsuarioLista(usuario);
                    
                    
                    
                }

                setAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        listaUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = usuarios.get(position);
                if(usuario.getCpf().equals(auxiliar.retornarCpfUsuarioLogado())){
                    Toast.makeText(getApplicationContext(), "É vc bro", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(ListaPessoasActivity.this, ChatActivity.class);

                    // recupera dados a serem passados


                    // enviando dados para conversa activity
                    intent.putExtra("nome", usuario.getNome() );
                    intent.putExtra("cpf", usuario.getCpf() );

                    startActivity(intent);


                    //abrir as instancias de conversa like udemy -- se exploda
                    /*
                    MANDA PRA TELA DE CONVERSAS
                    CRIA O OBJETO CONVERSA
                    JOGA NO BANCO
                    BEE HAPPY
                     */




                }
            }
        });



    }


    private void addUsuarioLista(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    private void setAdapter() {
        adapter = new PessoasAdapter(getApplicationContext(), this.usuarios);
        listaUsuario.setAdapter(adapter);
    }


}
