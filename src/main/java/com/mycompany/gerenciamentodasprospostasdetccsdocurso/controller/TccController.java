/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gerenciamentodasprospostasdetccsdocurso.controller;

import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.AlunoDAO;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.ProfessorDAO;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.DAO.TccDAO;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Aluno;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Professor;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.StatusProposta;
import static com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.StatusProposta.EM_ANALISE;
import com.mycompany.gerenciamentodasprospostasdetccsdocurso.model.Tcc;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe controladora responsável pelo tratamento de exceções e regra de negócio com verificações.
 * @author Benicio Reinehr
 */
public class TccController {
    private final TccDAO tccDAO;
    private final ProfessorDAO professorDAO;
    private final AlunoDAO alunoDAO;
    
    /**
     * Construtor da classe TccController.
     * Ajuda a garantir a unicidade da persistência de dados no sistema.
     * @param tccDAO        Objeto unico no sistema, que representa a persistência de dados de Tcc.
     * @param professorDAO  Objeto único no sistema, que representa a persistência de dados de Professor.
     * @param alunoDAO      Objeto único no sistema, que representa a persistência de dados de Aluno.
     */
    public TccController(TccDAO tccDAO, ProfessorDAO professorDAO, AlunoDAO alunoDAO){
        this.tccDAO = tccDAO;
        this.professorDAO = professorDAO;
        this.alunoDAO = alunoDAO;
    }
    
    /**
     * Método responsável por tratar os erros, fazer as verificações e cadastrar o objeto Tcc na persistência de dados do sistema.
     * @param emailProfessor            E-mail recebido do usuário para ser salvo no novo objeto Tcc.
     * @param titulo                    Título recebido do usuário para ser salvo no novo objeto Tcc.
     * @param descricao                 Descrição recebida do usuario para ser salvo no novo objeto Tcc.
     * @param conhecimentosNecessarios  Conhecimentos Necessário recebido do usuário para ser salvo no novo objeto Tcc.
     */
    public void criarPropostaTCC(String emailProfessor, String titulo, String descricao, String conhecimentosNecessarios){
        Professor professor = professorDAO.buscarPorEmail(emailProfessor);
        if(professor == null){
            throw new IllegalArgumentException("Erro: Nao existe um professor cadastrado com este e-mail");
        }
        Tcc novaProposta = new Tcc(professor, titulo, descricao, conhecimentosNecessarios);
        tccDAO.salvar(novaProposta);
    }
    
    /**
     * Método responsável pelo tratamento de erros e a alteração do status da proposta.
     * @param idTcc             O id para a busca do objeto Tcc que será alterado.
     * @param matriculaAluno    A matrícula do aluno que está interessado no Tcc.
     * @param novoStatus        O novo status do objeto Tcc.
     */
    public void alterarStatusDaProposta(int idTcc, String matriculaAluno, StatusProposta novoStatus){
        Tcc proposta = tccDAO.buscarPorId(idTcc);
        if(proposta == null){
            throw new IllegalArgumentException("Erro: Nao existe um tcc cadastrado com este ID");
        }
        
        Aluno aluno = alunoDAO.buscarPorMatricula(matriculaAluno);
        if(aluno == null){
            throw new IllegalArgumentException("Erro: Nao existe um Aluno cadastrado com esta matricula");
        }
        
        proposta.setAlunoInteressado(aluno);
        proposta.setStatus(novoStatus);
        proposta.setDataAlteracaoStatus(LocalDate.now());
        tccDAO.salvar(proposta);
    }
    
    /**
     * Método responsável por listar todas os objetos Tcc presentes no sistema.
     * @return Uma lista de objetos Tcc se haver, caso contrário null.
     */
    public List<Tcc> listarPropostas(){
        verificarPrazosDasPropostas();
        return tccDAO.listarTodos();
    }
    
    /**
     * Método responsável pela listagem dos objetos Tcc que estão em análise pelo aluno, onde os mais antigos vem primeiro.
     * @return Uma lista de objetos Tcc ordenados pelas datas de alteração em ordem crescente, null caso contrário.
     */
    public List<Tcc> listarPropostasOrdenadasPorDataAnalise(){
        verificarPrazosDasPropostas();
        return tccDAO.listarTodos().stream()
                .filter(t -> t.getStatus() == EM_ANALISE && t.getDataAlteracaoStatus() != null)
                .sorted(Comparator.comparing(Tcc::getDataAlteracaoStatus))
                .collect(Collectors.toList());
    }
    
    /**
     * Método para verificar os prazos dos objetos Tcc que estão em análise.
     * O prazo não pode ser maior que sete dias.
     */
    private void verificarPrazosDasPropostas(){
        for(Tcc proposta : tccDAO.listarTodos()){
            if(proposta.getStatus() == StatusProposta.EM_ANALISE){
                long diasPassados = ChronoUnit.DAYS.between(proposta.getDataAlteracaoStatus(), LocalDate.now());
                if(diasPassados > 7){
                    System.out.println("\nAVISO: Prazo da proposta ID " + proposta.getId() + " expirou. Voltando para DISPONIVEL.");
                    proposta.setStatus(StatusProposta.DISPONIVEL);
                    proposta.setAlunoInteressado(null);
                    proposta.setDataAlteracaoStatus(null);
                    tccDAO.salvar(proposta);
                }
            }
        }
    }
    
    /**
     * Método responsável por tratar erros e remover um objeto Tcc da persisntência de dados.
     * @param idTcc             Id usado para encontrar e remover o objeto Tcc.
     * @param emailProfessor    E-mail do professor responsável pelo cadastro do objeto Tcc.
     */
    public void removerProposta(int idTcc, String emailProfessor){
        Tcc proposta = tccDAO.buscarPorId(idTcc);
        if(proposta == null){
            throw new IllegalArgumentException("Erro: Proposta com ID informado nao encontrada.");
        }
        
        if(proposta.getProfessor().getEmail().equalsIgnoreCase(emailProfessor)){
            throw new IllegalArgumentException("Erro: Acesso negado, apenas o professor que criou a proposta pode exclui-la.");
        }
        
        if(tccDAO.remover(idTcc)){
            throw new IllegalArgumentException("Erro: Nao foi possivel remover a proposta de TCC.");
        }
    }
    
    
}
