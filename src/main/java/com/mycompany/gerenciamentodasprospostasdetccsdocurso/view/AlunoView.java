/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.view;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.controller.AlunoController;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Aluno;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pela entrada e saída de dados, comandos e menus para interação dos objetos alunos
 * @author Benicio Reinehr
 */
public class AlunoView {
    private final Scanner scanner;
    private final AlunoController controller;
    
    /**
     * Construtor da classe AlunoView. 
     * @param controller Recebe o objeto AlunoController que permeia o sistema, garantindo a mesma e única persistência de dados. 
     */
    public AlunoView(AlunoController controller){
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }
    
    /**
     * Método responsável por mostrar ao usuário as opções, coletar os comandos do Usuário e tratar o erro em caso haver.
     */
    public void menuAluno() {
        int opcao;
        do {            
            System.out.println("\n--- Menu de alunos ---");
            System.out.println("1. Cadastrar aluno");
            System.out.println("2. Listar alunos");
            System.out.println("3. Buscar aluno pela matricula");
            System.out.println("4. Remover aluno pela matricula");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro, por favor digite um numero valido.");
                opcao = -1;
            }
            
            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> listarAlunos();
                case 3 -> buscarAlunoPorMatricula();
                case 4 -> removerAluno();
                case 0 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opcao invalida. Tente novamente.");
            }
        } while (opcao != 0);
        
    }
    
    /**
     * Método responsável para fazer a captaçãos dos dados do usuário para mandar para o objeto AlunoController fazer o cadastro do Aluno e o tratamento de erro.
     */
    private void cadastrarAluno() {
        try {
            System.out.println("\n--- Cadastro de aluno ---");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("Matricula: ");
            String matricula = scanner.nextLine();
            System.out.print("E-mail: ");
            String email = scanner.nextLine();
            System.out.println("Telefone: ");
            String telefone = scanner.nextLine();
            
            controller.cadastrarAluno(matricula, nome, email, telefone);
            System.out.println("\nAluno cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao cadastrar: " + e.getMessage());
        }
    }
    
    /**
     * Método responsável por listar os objetos Alunosno console para o usuário.
     */
    private void listarAlunos() {
        System.out.println("\n--- Lista de aluno ---");
        List<Aluno> alunos = controller.listarAluno();

        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (Aluno aluno : alunos) {
                System.out.println(aluno);
            }
        }
    }
    
    /**
     * Método responsável por fazer a busca de um objeto Aluno pela sua matrícula.
     */
    private void buscarAlunoPorMatricula() {
        System.out.println("\n--- Buscar aluno por matricula ---");
        System.out.print("Digite a matricula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = controller.buscarPorMatricula(matricula);
        
        if (aluno != null) {
            System.out.println("Aluno encontrado:");
            System.out.println(aluno);
        } else {
            System.out.println("\nNenhum aluno encontrado com esta matricula.");
        }
    }
    
    /**
     * Método responsável por fazer a remoção de um objeto Aluno pela sua matrícula e tratar erro em caso de haver.
     */
    private void removerAluno() {
        System.out.println("\n--- Remover aluno ---");
        System.out.print("Digite a matricula do aluno a ser removido: ");
        String matricula = scanner.nextLine();
    
        try {
            controller.removePorMatricula(matricula);
        
            System.out.println("\nAluno removido com sucesso!");
        
        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
        }
    }
}
