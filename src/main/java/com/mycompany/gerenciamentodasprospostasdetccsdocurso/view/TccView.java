/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.view;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.controller.TccController;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.StatusProposta;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Tcc;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável por se comunicar com o usuário via console.
 * @author Benicio Reinehr
 */
public class TccView {
    private final Scanner scanner;
    private final TccController controller;

    /**
     * Construtor da classe TccView.
     * Inicia o atributo controlador para as operações CRUD dos objetos Tcc.
     * @param controller Objeto TccController passado para ajuda a garantir a unicidade da persistência de dados.
     */
    public TccView(TccController controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }

    /**
     * Método principal que reúne os outros métodos. Responsável por tratar erros e se comunicar com o usuário captando as informações.
     */
    public void menuTcc() {
        int opcao;
        do {
            System.out.println("\n--- Menu de propostas de TCC ---");
            System.out.println("1. Criar nova proposta");
            System.out.println("2. Mudar status de proposta");
            System.out.println("3. Listar todas as propostas");
            System.out.println("4. Listar propostas em analise ordenadas pelo prazo");
            System.out.println("5. Excluir proposta");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada invalida.");
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> criarProposta();
                case 2 -> alterarStatusProposta();
                case 3 -> listarTodasPropostas();
                case 4 -> listarPropostasOrdenadas();
                case 5 -> removerProposta();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    /**
     * Método responsável por tratar erroo e captar as informações do usuário para o cadastro do novo objeto Tcc no sistema
     */
    private void criarProposta() {
        try {
            System.out.println("\n--- Nova proposta de TCC ---");
            System.out.print("E-mail do professor proponente: ");
            String email = scanner.nextLine();
            System.out.print("Título da proposta: ");
            String titulo = scanner.nextLine();
            System.out.print("Descrição detalhada: ");
            String desc = scanner.nextLine();
            System.out.print("Conhecimentos necessários: ");
            String conh = scanner.nextLine();

            controller.criarPropostaTCC(email, titulo, desc, conh);
            System.out.println("\nProposta criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro ao criar proposta: " + e.getMessage());
        }
    }

    /**
     * Método responsável por tratar os erros, captar as informações do usuário para a alteração do status do objeto Tcc.
     */
    private void alterarStatusProposta() {
        try {
            System.out.println("\n--- Alterar status da proposta ---");
            System.out.print("Digite o id da proposta: ");
            int idTcc = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Digite a matrícula do aluno interessado: ");
            String matAluno = scanner.nextLine();
            
            System.out.print("Digite o novo status, EM_ANALISE ou ACEITA: ");
            StatusProposta novoStatus = StatusProposta.valueOf(scanner.nextLine().toUpperCase());
            
            if(novoStatus != StatusProposta.ACEITA && novoStatus != StatusProposta.EM_ANALISE) {
                System.out.println("\nErro: Status inválido. Apenas ACEITA ou EM_ANALISE são permitidos aqui.");
                return;
            }

            controller.alterarStatusDaProposta(idTcc, matAluno, novoStatus);
            System.out.println("\nStatus da proposta alterado com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("\nErro: O id da proposta deve ser um número.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }
    
    /**
     * Método responsável por listar os objetos Tcc se haver, e mostrar uma mensagem caso contrário.
     */
    private void listarTodasPropostas() {
        System.out.println("\n--- Lista de todas as propostas ---");
        List<Tcc> propostas = controller.listarPropostas();
        if (propostas.isEmpty()) {
            System.out.println("Nenhuma proposta cadastrada no sistema.");
        } else {
            for (Tcc tcc : propostas) {
                System.out.println(tcc);
            }
        }
    }
    
    private void listarPropostasOrdenadas() {
        System.out.println("\n--- Propostas em analise. Mais antigas primeiro ---");
        List<Tcc> propostas = controller.listarPropostasOrdenadasPorDataAnalise();
        if (propostas.isEmpty()) {
            System.out.println("Nenhuma proposta esta em analise no momento.");
        } else {
            for (Tcc tcc : propostas) {
                System.out.println(tcc);
            }
        }
    }
    
    /**
     * Método responsável captar as informações do usuário sobre o objeto Tcc a ser removido.
     */
    private void removerProposta() {
        try {
            System.out.println("\n--- Remover proposta ---");
            System.out.print("Digite o id da proposta a ser removida: ");
            int idTcc = Integer.parseInt(scanner.nextLine());
            System.out.println("Digite o e-mail do professor proponente: ");
            String emailProfessor = scanner.nextLine();
            controller.removerProposta(idTcc, emailProfessor);
            System.out.println("\nProposta removida com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("\nErro: O id da proposta deve ser um número.");
        } catch (IllegalArgumentException e) {
            System.out.println("\nErro: " + e.getMessage());
        }
    }
}
