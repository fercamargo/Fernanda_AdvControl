/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "tipo_cliente")
@NamedQueries({
    @NamedQuery(name = "TipoCliente.findAll", query = "SELECT t FROM TipoCliente t")})
public class TipoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cliente_pessoa_id_pessoa")
    private Integer clientePessoaIdPessoa;
    @Basic(optional = false)
    @Column(name = "nome_tipo_cliente")
    private String nomeTipoCliente;
    @JoinColumn(name = "cliente_pessoa_id_pessoa", referencedColumnName = "pessoa_id_pessoa", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Cliente cliente;

    public TipoCliente() {
    }

    public TipoCliente(Integer clientePessoaIdPessoa) {
        this.clientePessoaIdPessoa = clientePessoaIdPessoa;
    }

    public TipoCliente(Integer clientePessoaIdPessoa, String nomeTipoCliente) {
        this.clientePessoaIdPessoa = clientePessoaIdPessoa;
        this.nomeTipoCliente = nomeTipoCliente;
    }

    public Integer getClientePessoaIdPessoa() {
        return clientePessoaIdPessoa;
    }

    public void setClientePessoaIdPessoa(Integer clientePessoaIdPessoa) {
        this.clientePessoaIdPessoa = clientePessoaIdPessoa;
    }

    public String getNomeTipoCliente() {
        return nomeTipoCliente;
    }

    public void setNomeTipoCliente(String nomeTipoCliente) {
        this.nomeTipoCliente = nomeTipoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clientePessoaIdPessoa != null ? clientePessoaIdPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCliente)) {
            return false;
        }
        TipoCliente other = (TipoCliente) object;
        if ((this.clientePessoaIdPessoa == null && other.clientePessoaIdPessoa != null) || (this.clientePessoaIdPessoa != null && !this.clientePessoaIdPessoa.equals(other.clientePessoaIdPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoCliente[ clientePessoaIdPessoa=" + clientePessoaIdPessoa + " ]";
    }
    
}
