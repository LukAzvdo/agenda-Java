package br.com.esucri.agenda.paciente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pacientes", schema = "public")
public class Paciente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(name = "NOME",length = 100,nullable = false)
    private String nome;
    
    @Column(name = "CPF",length = 15,nullable = false,unique = true)
    private String cpf;
    
    //@Temporal(TemporalType.DATE)
    @Column(name = "NASCIMENTO",nullable = false)
    private LocalDate nascimento;
    
    @Column(name = "TELEFONE",length = 15,nullable = false)
    private String telefone;
    
    @Column(name = "EMAIL",length = 50,nullable = false)
    private String email;

    public Paciente() {
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome.trim().isEmpty() ? "PACIENTE":nome.toUpperCase();
    }
    
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf.trim().isEmpty() ? "000.000.000-00" : cpf;
    }
    
    public LocalDate getNascimento() {
        return nascimento;
    }
    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone.trim().isEmpty() ? "(00)00000-0000" : telefone;
    }
    
    public String getmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email.trim().isEmpty() ? "EMAIL":email.toUpperCase();
    }
    
}