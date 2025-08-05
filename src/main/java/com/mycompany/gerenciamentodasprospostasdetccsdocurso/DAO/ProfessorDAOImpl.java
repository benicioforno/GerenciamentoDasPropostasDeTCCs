/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Professor;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface DAO de Professor.
 * @author Benicio Reinehr
 */
public class ProfessorDAOImpl implements ProfessorDAO{

    private final List<Professor> professores;
    
    /**
     * Construtor da classe ProfessorDAOImpl responsável por inicializar a lista única de Professores que percorrerá todo o sistema.
     */
    public ProfessorDAOImpl() {
        this.professores = new ArrayList<>();
    }
    
    /**
     * Método sobrescrito responsável por salvar objeto Professor na lista de professores.
     * @param professor Objeto Professor para ser salvo na lista.
     */
    @Override
    public void salvar(Professor professor) { 
        professores.removeIf(p -> p.getEmail().equalsIgnoreCase(professor.getEmail()));
        professores.add(professor);
    }

    /**
     * Método sobrescrito responsável por buscar um objeto Professor pelo seu e-mail.
     * @param email O e-mail ultilizado para a busca do objeto Professor.
     * @return O objeto Professor se encontrado, null caso o contrário.
     */
    @Override
    public Professor buscarPorEmail(String email) {
        return professores.stream().filter(p -> p.getEmail().equalsIgnoreCase(email)).findFirst().orElse(null);
    }

    /**
     * Método sobrescrito responsável por listar todos os objeto Professores presentes na persistência de dados.
     * @return Um ou mais objetos Professores caso houver, null caso o contrário.
     */
    @Override
    public List<Professor> listarTodos() {
        return new ArrayList<>(professores);
    }

    /**
     * Método sobrescrito responsável por remover um objeto Professor da persistência de dados.
     * @param email O e-mail para a busca do objeto Professor a ser removido.
     * @return True se o objeto Professor foi removido com sucesso, false caso o contrário.
     */
    @Override
    public boolean removerPorEmail(String email) {
        return professores.removeIf(p -> p.getEmail().equalsIgnoreCase(email));
    }
    
}
