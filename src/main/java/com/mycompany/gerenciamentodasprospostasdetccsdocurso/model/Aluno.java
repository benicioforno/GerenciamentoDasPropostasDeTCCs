/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.model;

import java.util.Objects;

/**
 * Representa um aluno no sistema. Esta classe armazena informações de identificação de um aluno.
 * @author Benicio Reinehr
 */
public class Aluno {
    private String matricula;
    private String nome;
    private String email;
    private String telefone;

    /**
     * Construtor da classe Aluno
     * 
     * @param matricula A Matricula única do aluno. Usado com chave identificadora;
     * @param nome      O Nome completo do aluno;
     * @param email     O e-mail do aluno.
     * @param telefone  O telefone do aluno.
     */
    public Aluno(String matricula, String nome, String email, String telefone){
        setMatricula(matricula);
        setNome(nome);
        setEmail(email);
        setTelefone(telefone);
    }
    
    // Getters e Setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Override hashCode, equals e toString
    /**
     * Gera um código hash para o objeto baseado nos seus atributos.
     * @return O código hash do objeto.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.matricula);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.telefone);
        return hash;
    }

    /**
     * Compara este Aluno com outro objeto para verificar a igualdade.
     * A igualdade é baseada nos atributos.
     * 
     * @param obj O objeto a ser comparado.
     * @return True se os objetos forem iguais, caso contrário false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Aluno other = (Aluno) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.telefone, other.telefone);
    }

    /**
     * Gera uma representação em String do objeto Aluno.
     * 
     * @return Uma String formatada com os dados de Aluno.
     */
    @Override
    public String toString() {
        return "Aluno{" + "matricula=" + matricula + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + '}';
    }
    
    
}
