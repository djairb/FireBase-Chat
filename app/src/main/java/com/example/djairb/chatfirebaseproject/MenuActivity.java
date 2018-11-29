package com.example.djairb.chatfirebaseproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button botaoChat;
    private Button botaoPessoas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setView();

        botaoPessoas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this,ListaPessoasActivity.class);
                startActivity(intent);

            }
        });

        botaoChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this,ListaChatActivity.class);
                startActivity(intent);

            }
        });
    }


    private void setView() {
        botaoChat = findViewById(R.id.botaoChatId);
        botaoPessoas = findViewById(R.id.botaoListaPessoasId);
    }
}
