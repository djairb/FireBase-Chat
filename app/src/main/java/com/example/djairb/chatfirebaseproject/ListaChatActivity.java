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
import java.util.HashMap;
import java.util.Map;

public class ListaChatActivity extends AppCompatActivity {

    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<String> minhasConversas = new ArrayList<>();
    private ArrayAdapter adapter;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceConversa;
    private ListView listaUsuario;
    private Auxiliar auxiliar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_chat);

        auxiliar = new Auxiliar(getApplicationContext());

        listaUsuario = findViewById(R.id.listaUsuariosId);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        databaseReferenceConversa = FirebaseDatabase.getInstance().getReference().child("mensagens").child(auxiliar.retornarCpfUsuarioLogado());


        databaseReferenceConversa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                minhasConversas.clear();

                for(DataSnapshot dados:dataSnapshot.getChildren()){

                    //Map<String, Object> map = (HashMap<String, Object>) dataSnapshot.getValue();

                    //String a = dados.getKey();

                    //int b = 4;

                    String valor = dados.getKey();
                    addUsuarioCpfLista(valor);
                }

                carregarLista();

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
                    Intent intent = new Intent(ListaChatActivity.this, ChatActivity.class);

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

    private void carregarLista() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                usuarios.clear();

                for(DataSnapshot dados:dataSnapshot.getChildren()){

                    Usuario usuario = dados.getValue(Usuario.class);
                    if(minhasConversas.contains(usuario.getCpf())){
                        addUsuarioLista(usuario);
                    }




                }

                setAdapter();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void addUsuarioLista(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    private void addUsuarioCpfLista(String cpf) {
        this.minhasConversas.add(cpf);
    }

    private void setAdapter() {
        adapter = new PessoasAdapter(getApplicationContext(), this.usuarios);
        listaUsuario.setAdapter(adapter);
    }


}
