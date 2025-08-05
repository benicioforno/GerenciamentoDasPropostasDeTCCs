/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Aluno;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de implementação da interface AlunoDAO.
 * Para efetivar o CRUD e a persistência de dados.
 * @author Benicio Reinehr
 */
public class AlunoDAOImpl implements AlunoDAO{

    private final List<Aluno> alunos;
    
    /**
     * Construtor da class AlunoDAOImpl. Inicia a lista de Alunos.
     */
    public AlunoDAOImpl(){
        this.alunos = new ArrayList<>();
    }
    
    /**
     * Sobrescrição do método de salvamento da classe implementada, AlunoDAO.
     * Salva ou atualiza um objeto Aluno.
     * @param aluno Objeto Aluno que será atualizado ou salvo.
     */
    @Override
    public void salvar(Aluno aluno) {
        alunos.removeIf(p -> p.getMatricula().equals(aluno.getMatricula()));
        alunos.add(aluno);
    }

    /**
     * Sobrescrição do método de busca da classe implementada, AlunoDAO.
     * Busca um objeto Aluno pela matrícula.
     * @param matricula
     * @return Um objeto Aluno se for encontrado, caso contrário null.
     */
    @Override
    public Aluno buscarPorMatricula(String matricula) {
        return alunos.stream().filter(p -> p.getMatricula().equals(matricula)).findFirst().orElse(null);
    }

    /**
     * Sobrescrição do método de listagem da classe AlunoDAO.
     * Lista todos os objeto Alunos presentes na persistência de dados.
     * @return Uma lista dos alunos presentes na persistência de dados, caso não houver alunos, null.
     */
    @Override
    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }

    /**
     * Sobrescrição do método de remoção da classe AlunoDAO.
     * Remove um objeto Aluno da persistência de dados.
     * @param matricula Matrícula ultilizada para buscar aluno na persistência de dados.
     * @return True se o objeto Aluno for removido com sucesso, false caso contrário.
     */
    @Override
    public boolean removerPorMatricula(String matricula) {
        return alunos.removeIf(p -> p.getMatricula().equals(matricula));
    }
    
}
