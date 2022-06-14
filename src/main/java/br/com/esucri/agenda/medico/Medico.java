/*

 */
package br.com.esucri.agenda.medico;

import Enums.Especialidades;
import Enums.UFs;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "medicos", schema = "public")
@SequenceGenerator(name = "MEDICO_SEQ", sequenceName = "MEDICO_SEQ")
public class Medico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEDICO_SEQ")
    private Long id;
    
    @Column(name = "NOME",length = 100,nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "ESPECIALIDADE",length = 20,nullable = false)
    private Especialidades especialidade;
    
    @Column(name = "CRM",length = 10,nullable = false,unique = true)
    private int crm;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "UF",length = 2,nullable = false)
    private UFs uf;
    
    @Column(name = "TELEFONE",length = 15,nullable = false)
    private String telefone;
    
    public Medico() {
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
        this.nome = nome.trim().isEmpty() ? "MEDICO":nome.toUpperCase();
    }

    public Especialidades getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(Especialidades especialidade) {
        this.especialidade = especialidade;
    }

    public int getCrm() {
        return crm;
    }
    public void setCrm(int crm) {
        this.crm=(crm < 0) ? 0 : crm;
    }
    
    public UFs getUf() {
        return uf;
    }
    public void setUf(UFs uf) {
        this.uf = uf;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone.trim().isEmpty() ? "(00)00000-0000" : telefone;
    }
}