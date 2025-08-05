/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.controller;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.AlunoDAO;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Aluno;
import java.util.List;

/**
 * Classe controladora dos alunos.
 * Responsável pela regra de negócio do sistema e pelas operações de CRUD com as filtragens necessárias.
 * @author Benicio Reinehr
 */
public class AlunoController {
    private final AlunoDAO alunoDAO;
    
    /**
     * Construtor da class controladora de alunos.
     * Inicia o atributo de tipo responsável pela persistência de dados. 
     * @param alunoDAO Objeto AlunoDAO único no sistema que percorre entre classes, 
     * garantindo que as operações se deem sempre na mesma persistência de dados.
     */
    public AlunoController(AlunoDAO alunoDAO){
        this.alunoDAO = alunoDAO;
    }
    
    /**
     * Método responsável pelo cadastro de alunos no sistema.
     * @param matricula A matrícula do aluno que será cadastrado ou atualizado.
     * @param nome      O nome do aluno que será cadastrado ou atualizado.
     * @param email     O e-mail do aluno que será cadastrado ou atualizado.
     * @param telefone  O telefone do aluno que será cadastrado ou atualizado.
     */
    public void cadastrarAluno(String matricula, String nome, String email, String telefone){
        if(alunoDAO.buscarPorMatricula(matricula) != null){
            throw new IllegalArgumentException("Error: Ja existe aluno cadastrado com essa matricula.");
        }
        Aluno novoAluno = new Aluno(matricula, nome, email, telefone);
        alunoDAO.salvar(novoAluno);
    }
    
    /**
     * Método responsável pela listagem de todos os aluno no sistema.
     * @return Retorna uma lista de todos os alunos, se não houver nenhum, null. 
     */
    public List<Aluno> listarAluno(){
        return alunoDAO.listarTodos();
    }
    
    /**
     * Método de busca um aluno pela sua matrícula.
     * @param matricula A matrícula para a busca do objeto Aluno.
     * @return Retorna um objeto Aluno se encontrado, caso contrário null;
     */
    public Aluno buscarPorMatricula(String matricula){
        return alunoDAO.buscarPorMatricula(matricula);
    }
    
    /**
     * Método de remoção de um aluno pela sua matrícula.
     * @param matricula Matrícula para que seja feita a busca para a remoção do objeto Aluno. 
     * Dispara um erro caso não seja encontrada a matrícula
     */
    public void removePorMatricula(String matricula){
        if(!alunoDAO.removerPorMatricula(matricula)){
            throw new IllegalArgumentException("Erro: Nao foi possivel remover aluno, matricula nao encontrada.");
        }
    }
}
