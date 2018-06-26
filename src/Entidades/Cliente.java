/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernanda
 */
@Entity
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pessoa_id_pessoa")
    private Integer pessoaIdPessoa;
    @Column(name = "estado_civil_cliente")
    private String estadoCivilCliente;
    @JoinColumn(name = "pessoa_id_pessoa", referencedColumnName = "id_pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cliente")
    private TipoCliente tipoCliente;

    public Cliente() {
    }

    public Cliente(Integer pessoaIdPessoa) {
        this.pessoaIdPessoa = pessoaIdPessoa;
    }

    public Integer getPessoaIdPessoa() {
        return pessoaIdPessoa;
    }

    public void setPessoaIdPessoa(Integer pessoaIdPessoa) {
        this.pessoaIdPessoa = pessoaIdPessoa;
    }

    public String getEstadoCivilCliente() {
        return estadoCivilCliente;
    }

    public void setEstadoCivilCliente(String estadoCivilCliente) {
        this.estadoCivilCliente = estadoCivilCliente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pessoaIdPessoa != null ? pessoaIdPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.pessoaIdPessoa == null && other.pessoaIdPessoa != null) || (this.pessoaIdPessoa != null && !this.pessoaIdPessoa.equals(other.pessoaIdPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cliente[ pessoaIdPessoa=" + pessoaIdPessoa + " ]";
    }
    
}
