/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Tcc;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementação da interface TccDAO.
 * Métodos sobrescritos e funcionais.
 * @author Benicio Reinehr
 */
public class TccDAOImpl implements TccDAO{

    private final List<Tcc> propostas;
    
    /**
     * Construtor da class TccDAOImpl.
     * Inicia a lista de Tcc garantindo a unicidade no sistema.
     */
    public TccDAOImpl(){
        this.propostas = new ArrayList<>();
    }
    
    /**
     * Sobrescrição do método de salvamento da interface TccDAO, responsável por salvar ou atualizar um objeto Tcc na persistência de dados.
     * @param tcc Objeto Tcc passado para ser salvo.
     */
    @Override
    public void salvar(Tcc tcc) {
        propostas.removeIf(p -> p.getId() == tcc.getId());
        propostas.add(tcc);
    }

    /**
     * Sobrescrição do método de busca da interface TccDAO, responsável por encontrar um objeto Tcc.
     * @param id Id passado para que seja feita a busca do objeto Tcc presente na persistência de dados.
     * @return O Tcc se encontrado, caso contrário null.
     */
    @Override
    public Tcc buscarPorId(int id) {
        return propostas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    /**
     * Sobrescrição do método de listagem da interface TccDAO, responsável por listar todos os objetos Tcc cadastrados no sistema.
     * @return Uma lista dos objetos Tcc se haver, caso contrário null.
     */
    @Override
    public List<Tcc> listarTodos() {
        return new ArrayList<>(propostas);
    }

    /**
     * Sobrescrição do método de remoção da interface TccDAO, responsável por remover um objeto Tcc da persistência de dados.
     * @param id Id usado para encontrar o objeto que será removido.
     * @return True se o objeto for removido com sucesso, false caso contrário.
     */
    @Override
    public boolean remover(int id) {
        return propostas.removeIf(p -> p.getId() == id);
    }
    
}
