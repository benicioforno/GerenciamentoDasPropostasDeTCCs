/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Aluno;
import java.util.List;

/**
 * Interface para o DAO da classe Aluno. 
 * Define o contrato para as operações CRUD da persistência de dados.
 * @author Benicio Reinehr
 */
public interface AlunoDAO {
    /**
     * Salva ou atualiza um aluno na persintência de dados.
     * @param aluno Aluno para ser salvo ou atualizado.
     */
    public void salvar(Aluno aluno);
    
    /**
     * Busca um aluno na persistência de dados.
     * @param matricula Matricula utilizada para a busca do aluno.
     * @return O objeto Aluno se encontrado, caso contrário null.
     */
    public Aluno buscarPorMatricula(String matricula);
    
    /**
     * Lista todos os alunos presentes na persistência de dados.
     * @return Um List dos Alunos.
     */
    public List<Aluno> listarTodos();
    
    /**
     * Remove um aluno da persistência de dados.
     * @param matricula Matricula ultilizada fazer a busca e remoção do aluno.
     * @return Retorna true se o aluno foi removido com sucesso, caso contrário false.
     */
    public boolean removerPorMatricula(String matricula);
}
