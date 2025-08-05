/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Tcc;
import java.util.List;

/**
 * Interface DAO que determina os contratos para a classe implementadora.
 * @author Benicio Reinehr
 */
public interface TccDAO {
    /**
     * Assinatura do método sem corpo responsável por salvar um objeto Tcc.
     * @param tcc Objeto Tcc passado para ser salvo.
     */
    public void salvar(Tcc tcc);
    
    /**
     * Assinatura do método sem corpo responsável por buscar um objeto Tcc pelo seu Id.
     * @param id Id referente ao objeto Tcc que será buscado.
     * @return O objeto Tcc se encontrado, caso contrário null.
     */
    public Tcc buscarPorId(int id);
    
    /**
     * Assinatura do método sem corpo responsável por listar todos objeto Tcc no sistema.
     * @return Uma lista dos objetos cadastrados no sistema se haver, caso contrário null.
     */
    public List<Tcc> listarTodos();
    
    /**
     * Assinatura do método sem corpo responsável por remover um objeto Tcc do sistema.
     * @param id Id usado para encontrar o objeto Tcc.
     * @return True se o objeto for removido com sucesso, false caso contrário.
     */
    public boolean remover(int id);
}
