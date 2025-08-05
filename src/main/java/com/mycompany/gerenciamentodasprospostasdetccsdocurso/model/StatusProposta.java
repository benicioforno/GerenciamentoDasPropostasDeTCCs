/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.model;

/**
 * Enumeração para possíveis status de uma proposta de TCC.
 * Usar um enum garante que apenas valores válidos possam ser atribuídos ao status.
 * 
 * @author Benicio Reinehr
 */
public enum StatusProposta {
    DISPONIVEL("Disponivel"),
    EM_ANALISE("Em analise"),
    ACEITA("Aceita");
    
    private final String descricao;
    
    /**
     * Construtor do enum.
     * @param descricao A representação textual amigável do status.
     */
    private StatusProposta(String descricao){
        this.descricao = descricao;
    }
    
    /**
     * Retorna a descrição amigável do status.
     * @return A String formatada para a exibição
     */
    public String getDescricao(){
        return this.descricao;
    }
}
