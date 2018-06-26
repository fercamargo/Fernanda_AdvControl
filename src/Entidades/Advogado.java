/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Fernanda
 */
@Entity
@Table(name = "advogado")
@NamedQueries({
    @NamedQuery(name = "Advogado.findAll", query = "SELECT a FROM Advogado a")})
public class Advogado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "pessoa_id_pessoa")
    private Integer pessoaIdPessoa;
    @Basic(optional = false)
    @Column(name = "numero_oab")
    private int numeroOab;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "advogadoPessoaIdPessoa")
    private List<Evento> eventoList;
    @JoinColumn(name = "pessoa_id_pessoa", referencedColumnName = "id_pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Pessoa pessoa;

    public Advogado() {
    }

    public Advogado(Integer pessoaIdPessoa) {
        this.pessoaIdPessoa = pessoaIdPessoa;
    }

    public Advogado(Integer pessoaIdPessoa, int numeroOab, String email, String senha) {
        this.pessoaIdPessoa = pessoaIdPessoa;
        this.numeroOab = numeroOab;
        this.email = email;
        this.senha = senha;
    }

    public Integer getPessoaIdPessoa() {
        return pessoaIdPessoa;
    }

    public void setPessoaIdPessoa(Integer pessoaIdPessoa) {
        this.pessoaIdPessoa = pessoaIdPessoa;
    }

    public int getNumeroOab() {
        return numeroOab;
    }

    public void setNumeroOab(int numeroOab) {
        this.numeroOab = numeroOab;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        if (!(object instanceof Advogado)) {
            return false;
        }
        Advogado other = (Advogado) object;
        if ((this.pessoaIdPessoa == null && other.pessoaIdPessoa != null) || (this.pessoaIdPessoa != null && !this.pessoaIdPessoa.equals(other.pessoaIdPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Advogado[ pessoaIdPessoa=" + pessoaIdPessoa + " ]";
    }
    
}
