/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.main;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.AlunoDAO;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.AlunoDAOImpl;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.ProfessorDAO;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.ProfessorDAOImpl;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.TccDAO;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.TccDAOImpl;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.controller.AlunoController;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.controller.ProfessorController;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.controller.TccController;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Aluno;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Professor;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.StatusProposta;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Tcc;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.view.AlunoView;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.view.MainView;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.view.ProfessorView;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.view.TccView;
import java.time.LocalDate;

/**
 * Classe central que reúne os objetos principais e os inicializa para o funcionamento.
 * @author Benicio Reinehr
 */
public class Main {
    public static void main(String[] args) {
        /**
         * Camada de dados (DAO).
         * Inicialização úica e central do ProfessorDAO, AlunoDAO e TccDAO para garantir o acesso sempre à mesma persistência de dados.
         * 
         */
        ProfessorDAO professorDAO = new ProfessorDAOImpl();
        AlunoDAO alunoDAO = new AlunoDAOImpl();
        TccDAO tccDAO = new TccDAOImpl();
        
        /**
         * Camada de controle (Controller).
         * Inicialização única do ProfessorController, AlunoController e TccController -- passando os respectivos objetos DAO para manter a unicidade da persistência de dados.
         */
        ProfessorController professorController = new ProfessorController(professorDAO);
        AlunoController alunoController = new AlunoController(alunoDAO);
        TccController tccController = new TccController(tccDAO, professorDAO, alunoDAO);
        
        /**
         * Camada de visão (View).
         * Inicialização única de ProfessorView, AlunoView e TccView -- passando os respectivos objetos Controller para manter a unicidade da persistência de dados.
         */
        ProfessorView professorView = new ProfessorView(professorController);
        AlunoView alunoView = new AlunoView(alunoController);
        TccView tccView = new TccView(tccController);
        
        System.out.println("Iniciando o sistema de gerenciamento de TCCs...");
        
        /**
         * População de dados no sistema para fins de teste.
         */
        // Começo da população de dados.
        Professor novoProfessor = new Professor("Marcela", "marcela@ifrs.com", "Biologia");
        professorDAO.salvar(novoProfessor);
        
        Aluno novoAluno = new Aluno("2024019988", "Benicio", "benicio@ifrs.com", "51998454958");
        alunoDAO.salvar(novoAluno);
        
        Tcc tccTeste1 = new Tcc(novoProfessor, "Gerenciamento de TCCs", "Fazer um programa capaz de gerenciar propostas de TCCs...", "Java, OOPI, Banco de Dados");
        tccDAO.salvar(tccTeste1);
        
        tccTeste1.setStatus(StatusProposta.EM_ANALISE);
        tccTeste1.setDataAlteracaoStatus(LocalDate.now().minusDays(3));
        tccTeste1.setAlunoInteressado(novoAluno);
        tccDAO.salvar(tccTeste1);
        
        
        Tcc tccTeste2 = new Tcc(novoProfessor, "PetShop", "Fazer um programa capaz de gerenciar uma PetShop...", "Java, OOPI, Banco de Dados");
        tccDAO.salvar(tccTeste2);
        
        tccTeste2.setStatus(StatusProposta.EM_ANALISE);
        tccTeste2.setDataAlteracaoStatus(LocalDate.now().plusDays(3));
        tccTeste2.setAlunoInteressado(novoAluno);
        tccDAO.salvar(tccTeste2);
        
        Tcc tccTeste3 = new Tcc(novoProfessor, "Gerenciamento de nada", "Fazer um programa capaz de fazer nada...", "Java, OOPI, Banco de Dados");
        tccDAO.salvar(tccTeste3);
        
        tccTeste3.setStatus(StatusProposta.EM_ANALISE);
        tccTeste3.setDataAlteracaoStatus(LocalDate.now().minusDays(8));
        tccTeste3.setAlunoInteressado(novoAluno);
        tccDAO.salvar(tccTeste3);
        // Fim da população de dados.
        
        /**
         * Inicialização da visão principal, MainView, que recebe todas as outras visões já inicializadas e faz as chamadas quando preciso.
         */
        MainView view = new MainView(professorView, alunoView, tccView);
        /**
         * Método pertencente ao objeto MainView para mostrar o menu central para a comunicação com o usuário.
         */
        view.iniciar();
    }
}
