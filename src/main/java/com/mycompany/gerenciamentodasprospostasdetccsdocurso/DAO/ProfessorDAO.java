/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Professor;
import java.util.List;

/**
 * Interface DAO do Professor, responsável por fazer os contratos para a classe implementada.  
 * @author Benicio Reinehr
 */
public interface ProfessorDAO {
    /**
     * Assinatura do método sem corpo responsável por salvar um objeto Professor.
     * @param professor 
     */
    public void salvar(Professor professor);
    
    /**
     * Assinatura do método sem corpo responsável por buscar um objeto Professor pelo seu e-mail.
     * @param email E-mail para fazer a busca do objeto Professor.
     * @return O objeto Professor encontrado, caso contrário null.
     */
    public Professor buscarPorEmail(String email);
    
    /**
     * Assinatura do método sem corpo responsável por listar todos os objetos Professores existentes na persistência de dados.
     * @return O ou os objetos Professores se haver, caso contrário null;
     */
    public List<Professor> listarTodos();
    
    /**
     * Assinatura do método sem corpo responsável por remover um objeto Professor pelo seu e-mail.
     * @param email E-mail para a busca do objeto Professor a ser removido.
     * @return True se o objeto Professor foi removido com sucesso, caso contrário false.
     */
    boolean removerPorEmail(String email);
}
