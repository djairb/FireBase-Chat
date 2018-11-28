package com.example.djairb.chatfirebaseproject;

public class Mensagem {
    private String cpfUsuario;
    private String mensagem;

    public Mensagem() {
    }

    public Mensagem(String idUsuario, String mensagem) {
        this.cpfUsuario = idUsuario;
        this.mensagem = mensagem;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
