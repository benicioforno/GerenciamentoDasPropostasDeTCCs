/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.controller;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.ProfessorDAO;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Professor;
import java.util.List;

/**
 * Classe controladora de Professor, responsável pela regra de negócio e verificações antes da operações CRUD serem efetivadas.
 * @author Benicio Reinehr
 */
public class ProfessorController {
    private final ProfessorDAO professorDAO;
    
    /**
     * Construtor da classe ProfessorController.
     * Inicia o atributo para fazer a operações CRUD.
     * @param professorDAO Objeto recebido para garantir a unicidade da persistência de dados no sistema.
     */
    public ProfessorController(ProfessorDAO professorDAO){
        this.professorDAO = professorDAO;
    }
    
    /**
     * Método para fazer o cadastro de objetos Professores com as devidas regras de negócio e verificações.
     * @param nome              Nome do objeto Professor que será cadastrado.
     * @param email             E-mail do objeto Professor que será cadastrado.
     * @param areasDeInteresse  Áreas de interesse do objeto Professor que será cadastrado.
     */
    public void cadastrarProfessor(String nome, String email, String areasDeInteresse){
        if(professorDAO.buscarPorEmail(email) != null){
            throw new IllegalArgumentException("Erro: Ja existe um professor cadastrado com este e-mail");
        }
        Professor novoProfessor = new Professor(nome, email, areasDeInteresse);
        professorDAO.salvar(novoProfessor);
    }
    
    /**
     * Método para fazer a listagem de todos os objetos Professor presentes no sistema.
     * @return Uma lista de objetos Professores se houver, caso contrário null.
     */
    public List<Professor> listarProfessores(){
        return professorDAO.listarTodos();
    }
    
    /**
     * Método para fazer a busca de um objeto Professor pelo seu e-mail.
     * @param email E-mail utilizado para fazer a busca do objeto Professor.
     * @return O objeto Professor se encontrado, caso contrário null.
     */
    public Professor buscarPorEmail(String email){
        return professorDAO.buscarPorEmail(email);
    }
    
    /**
     * Método responsável por fazer a remoção de um objeto Professor pelo e-mail.
     * @param email E-mail utilizado para fazer a busca e remoção do objeto Professor.
     */
    public void removerPorEmail(String email){
        if(!professorDAO.removerPorEmail(email)){
            throw new IllegalArgumentException("Erro: Nao foi possivel apagar, Professor com email informado nao encontrado.");
        }
    }
}
