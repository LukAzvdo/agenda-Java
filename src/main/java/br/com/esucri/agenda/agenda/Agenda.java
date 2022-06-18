/*
 */
package br.com.esucri.agenda.agenda;

import br.com.esucri.agenda.medico.Medico;
import br.com.esucri.agenda.paciente.Paciente;
import br.com.esucri.agenda.procedimento.Procedimento;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "agendas", schema = "public")
@SequenceGenerator(name = "AGENDA_SEQ", sequenceName = "AGENDA_SEQ")
public class Agenda implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AGENDA_SEQ")
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String descricao;
    
    @Column(name = "DATA",nullable = false)
    private LocalDate data;
    
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
        name="agendas_pacientes",
        joinColumns = @JoinColumn(name = "id_agenda"),
        inverseJoinColumns = @JoinColumn(name = "id_paciente"),
        foreignKey = @ForeignKey(name = "fk_agenda"),
        inverseForeignKey = @ForeignKey(name = "fk_paciente")
    )
    private List<Paciente> pacientes;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
        name="agendas_medicos",
        joinColumns = @JoinColumn(name = "id_agenda"),
        inverseJoinColumns = @JoinColumn(name = "id_medico"),
        foreignKey = @ForeignKey(name = "fk_agenda"),
        inverseForeignKey = @ForeignKey(name = "fk_medico")
    )
    private List<Medico> medicos;
    
    @ManyToMany(fetch = FetchType.EAGER)  
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
        name="agendas_procedimentos",
        joinColumns = @JoinColumn(name = "id_agenda"),
        inverseJoinColumns = @JoinColumn(name = "id_procedimento"),
        foreignKey = @ForeignKey(name = "fk_agenda"),
        inverseForeignKey = @ForeignKey(name = "fk_procedimento")
    )
    private List<Procedimento> procedimentos;
    
    public Agenda() {
    } 
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao.trim().isEmpty() ? "AGENDA":descricao.toUpperCase();
    }
    
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public List<Paciente> getPacientes() {
        return pacientes;
    }
    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }
    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }
    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }
}
