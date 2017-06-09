/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

/**
 * Classe que monta os objetos de Alunos
 *
 * @author pfsel
 */
public class Alunos {

    private String nome;
    private String matricula;
    private String turma;

    /**
     * Construtor para as situações que necessitam dos tres parametros
     *
     * @param matricula = matricula do aluno
     * @param nome = nome do aluno
     * @param turma = turma do aluno
     */
    public Alunos(String matricula, String nome, String turma) {
        this.nome = nome;
        this.matricula = matricula;
        this.turma = turma;
    }

    /**
     * Contrutor para as situação que necessitam apenas do nome do aluno
     *
     * @param nome = nome do aluno
     */
    public Alunos(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

}
