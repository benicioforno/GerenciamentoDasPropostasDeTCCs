/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.view;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.controller.ProfessorController;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Professor;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por fazer as interações de professor com o usuário via console.
 * @author Benicio Reinehr
 */
public class ProfessorView {
    private final Scanner scanner;
    private final ProfessorController controller;
    
    /**
     * Construtor da classe ProfessorView.
     * @param controller Recebe o objeto ProfessorController único no sistema e o ultiliza para as operações CRUD da lista de professores.
     */
    public ProfessorView(ProfessorController controller){
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }
    
    /**
     * Método responsável por mostrar as opções ao usuário e captar o comando.
     */
    public void menuProfessor() {
        int opcao;
        do {
            System.out.println("\n--- Menu de professores ---");
            System.out.println("1. Cadastrar professor");
            System.out.println("2. Listar professores");
            System.out.println("3. Buscar professor por e-mail");
            System.out.println("4. Remover professor por e-mail");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro, por favor digite um numero valido.");
                opcao = -1;
            }
            
            switch (opcao) {
                case 1 -> cadastrarProfessor();
                case 2 -> listarProfessores();
                case 3 -> buscarProfessorPorEmail();
                case 4 -> removerProfessor();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);
        
    }
    
    /**
     * Método responsável por tratar os erros e receber os dados do usuário para mandar ao objeto ProfessorController para efetivar o cadastro.
     */
    private void cadastrarProfessor() {
        try {
            System.out.println("\n--- Cadastro de professor ---");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            System.out.print("Areas de interesse: ");
            String areas = scanner.nextLine();
            
            controller.cadastrarProfessor(nome, email, areas);
            System.out.println("\nProfessor cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao cadastrar: " + e.getMessage());
        }
    }
    
    /**
     * Método responsável por receber a lista de professores e tratar caso vazia.
     */
    private void listarProfessores() {
        System.out.println("\n--- Lista de professores ---");
        List<Professor> professores = controller.listarProfessores();

        if (professores.isEmpty()) {
            System.out.println("Nenhum professor cadastrado.");
        } else {
            for (Professor professor : professores) {
                System.out.println(professor);
            }
        }
    }
    
    /**
     * Método responsável por captar o e-mail digitado pelo usuário e enviar ao objeto ProfessorController para fazer a busca.
     */
    private void buscarProfessorPorEmail() {
        System.out.println("\n--- Buscar professor por e-mail ---");
        System.out.print("Digite o e-mail do professor: ");
        String email = scanner.nextLine();
        
        Professor professor = controller.buscarPorEmail(email);
        
        if (professor != null) {
            System.out.println("Professor encontrado:");
            System.out.println(professor);
        } else {
            System.out.println("\nNenhum professor encontrado com este e-mail.");
        }
    }
    
    /**
     * Método responsável por fazer o tratamento de exceções e a captação do e=mail digitado pelo usuário, envia-lo ao objeto ProfessorController para efetivar a remoção.
     */
    private void removerProfessor() {
        System.out.println("\n--- Remover professor ---");
        System.out.print("Digite o e-mail do professor a ser removido: ");
        String email = scanner.nextLine();
    
        try {
            controller.removerPorEmail(email);
        
            System.out.println("\nProfessor removido com sucesso!");
        
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
        }
    }
}

