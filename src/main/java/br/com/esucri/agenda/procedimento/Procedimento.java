/*
 */
package br.com.esucri.agenda.procedimento;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "procedimentos", schema = "public")
@SequenceGenerator(name = "PROCEDIMENTO_SEQ", sequenceName = "PROCEDIMENTO_SEQ")
public class Procedimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROCEDIMENTO_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CODIGO",length = 8,nullable = false, unique = true)
    private int codigo;
    
    @Column(name = "DESCRICAO",length = 100,nullable = false, unique = true)
    private String descricao;

    public Procedimento() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao.trim().isEmpty() ? "PROCEDIMENTO" :descricao.toUpperCase();
    }
}
