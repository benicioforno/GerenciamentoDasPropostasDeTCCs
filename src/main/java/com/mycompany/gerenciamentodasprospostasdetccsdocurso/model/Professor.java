/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.model;

import java.util.Objects;

/**
 * Representa um professor no sistema. Esta classe armazena informações de um Professor.
 * @author Benicio Reinehr
 */
public class Professor {
    private String nome;
    private String email;
    private String areasDeInteresse;
    
    /**
     * Construtor da classe Professor.
     * 
     * @param nome              O nome completo do professor.
     * @param email             O e-mail único do professor. Usado com identificador. 
     * @param areasDeInteresse  A ou as áreas de interesse de um professor.
     */
    public Professor(String nome, String email, String areasDeInteresse){
        setNome(nome);
        setEmail(email);
        setAreasDeInteresse(areasDeInteresse);
    }
    
    // Getters e Setters
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setAreasDeInteresse(String areasDeInteresse){
        this.areasDeInteresse = areasDeInteresse;
    }
    
    public String getAreasDeInteresse(){
        return this.areasDeInteresse;
    }

    // Override hashCode, equals e toString
    
    /**
     * Gera um código hash para o objeto baseado nos seus atributos.
     * @return O código hash do objeto.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.areasDeInteresse);
        return hash;
    }

    /**
     * Compara este Professor com outro objeto para verificar a igualdade.
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
        final Professor other = (Professor) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.areasDeInteresse, other.areasDeInteresse);
    }

    /**
     * Gera uma representação em String do objeto Professor.
     * 
     * @return Uma String formatada com os dados de Professor.
     */
    @Override
    public String toString() {
        return "Professor{" + "nome=" + nome + ", email=" + email + ", areasDeInteresse=" + areasDeInteresse + '}';
    }
}
