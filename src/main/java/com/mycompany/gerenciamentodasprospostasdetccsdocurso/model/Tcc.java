/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa um TCC no sistema. Esta classe armazena informações de um Tcc.
 * @author Benicio Reinehr
 */
public class Tcc {
    private static int proximoId = 1;
    private int id;
    private Professor professor;
    private String titulo;
    private String descricao;
    private String conhecimentosNecessarios;
    private StatusProposta status;
    private Aluno alunoInteressado; 
    private LocalDate dataAlteracaoStatus;
    
    /**
     * Construtor da classe Tcc.
     * 
     * @param professor                 O objeto do Professor proponente da proposta do Tcc.
     * @param titulo                    O título da proposta do Tcc.
     * @param descricao                 A descrição completa para a proposta do Tcc.
     * @param conhecimentosNecessarios  O ou os conhecimentos necessários para realizar um Tcc.
     */
    public Tcc(Professor professor, String titulo, String descricao, String conhecimentosNecessarios){
        this.id = proximoId++;
        setProfessor(professor); 
        setTitulo(titulo);
        setDescricao(descricao);
        setConhecimentosNecessarios(conhecimentosNecessarios);
        this.status = StatusProposta.DISPONIVEL;
        this.alunoInteressado = null;
        this.dataAlteracaoStatus = null;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getConhecimentosNecessarios() {
        return conhecimentosNecessarios;
    }

    public void setConhecimentosNecessarios(String conhecimentosNecessarios) {
        this.conhecimentosNecessarios = conhecimentosNecessarios;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public void setStatus(StatusProposta status) {
        this.status = status;
    }

    public Aluno getAlunoInteressado() {
        return alunoInteressado;
    }

    public void setAlunoInteressado(Aluno alunoInteressado) {
        this.alunoInteressado = alunoInteressado;
    }

    public LocalDate getDataAlteracaoStatus() {
        return dataAlteracaoStatus;
    }

    public void setDataAlteracaoStatus(LocalDate dataAlteracaoStatus) {
        this.dataAlteracaoStatus = dataAlteracaoStatus;
    }
    
    
    // Override hashCode, equals, toString
    /**
     * Gera um código hash para o objeto baseado nos seus atributos.
     * @return O código hash do objeto.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.professor);
        hash = 47 * hash + Objects.hashCode(this.titulo);
        hash = 47 * hash + Objects.hashCode(this.descricao);
        hash = 47 * hash + Objects.hashCode(this.conhecimentosNecessarios);
        hash = 47 * hash + Objects.hashCode(this.status);
        hash = 47 * hash + Objects.hashCode(this.alunoInteressado);
        hash = 47 * hash + Objects.hashCode(this.dataAlteracaoStatus);
        return hash;
    }

    /**
     * Compara este Tcc com outro objeto para verificar a igualdade.
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
        final Tcc other = (Tcc) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.conhecimentosNecessarios, other.conhecimentosNecessarios)) {
            return false;
        }
        if (!Objects.equals(this.professor, other.professor)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.alunoInteressado, other.alunoInteressado)) {
            return false;
        }
        return Objects.equals(this.dataAlteracaoStatus, other.dataAlteracaoStatus);
    }
    
    /**
     * Gera uma representação em String do objeto Tcc.
     * 
     * @return Uma String formatada com os dados de Tcc e também possívelmente do objeto Aluno.
     */
    @Override
    public String toString() {
        String infoAluno = (alunoInteressado != null) ? ", Aluno=" + alunoInteressado.getNome() : "";
        String infoData = (dataAlteracaoStatus != null) ? ", Data=" + dataAlteracaoStatus : ""; 
        
        return "TCC [ID=" + id + ", Título=" + titulo + ", Professor=" + professor.getNome() + 
               ", Status=" + status + infoAluno + infoData + "]";
    }
    
}
