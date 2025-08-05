/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.view;

import java.util.Scanner;

/**
 * Classe central de visões.
 * @author Benicio Reinehr
 */
public class MainView {
    private final Scanner scanner;
    
    private final ProfessorView professorView;
    private final AlunoView alunoView;
    private final TccView tccView;
    
    /**
     * Construtor da classe MainView.
     * Inicializa os objetos da classe, recebendo por parâmetro os objetos já inicializados.
     * Para garantir a unicidade da persistência de dados.
     * @param professorView Objeto de visão de ProfessorView recebido para a construção da classe.
     * @param alunoView     Objeto de visão de AlunoView recebido para a construção da classe.
     * @param tccView       Objeto de visão de TccView recebido para a construção da classe.
     */
    public MainView(ProfessorView professorView, AlunoView alunoView, TccView tccView){
        scanner = new Scanner(System.in);
        this.professorView = professorView;
        this.alunoView = alunoView;
        this.tccView = tccView;
    }
    
    /**
     * Método responsável por fazer o tratamento de erros e a comunicação com o usuário para a captação de comandos.
     */
    public void iniciar(){
        int opcao;
        do {            
            System.out.println("\n--- Gerenciamento de proposta de Tcc ---");
            System.out.println("1. Gerenciar professores");
            System.out.println("2. Gerenciar alunos");
            System.out.println("3. Gerenciar propostas de Tcc");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opcao: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro, por favor digite um numero valido.");
                opcao = -1;
            }
            
            switch (opcao) {
                case 1 -> professorView.menuProfessor();
                case 2 -> alunoView.menuAluno();
                case 3 -> tccView.menuTcc();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);   
    }
}
