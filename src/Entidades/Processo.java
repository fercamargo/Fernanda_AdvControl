/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fernanda
 */
@Entity
@Table(name = "processo")
@NamedQueries({
    @NamedQuery(name = "Processo.findAll", query = "SELECT p FROM Processo p")})
public class Processo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_processo")
    private Integer idProcesso;
    @Basic(optional = false)
    @Column(name = "numero_acao")
    private String numeroAcao;
    @Column(name = "descricao_processo")
    private String descricaoProcesso;
    @Basic(optional = false)
    @Column(name = "data_cadastro_processo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastroProcesso;
    @Column(name = "situacao_pagamento")
    private String situacaoPagamento;
    @OneToMany(mappedBy = "processoIdProcesso")
    private List<Evento> eventoList;

    public Processo() {
    }

    public Processo(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public Processo(Integer idProcesso, String numeroAcao, Date dataCadastroProcesso) {
        this.idProcesso = idProcesso;
        this.numeroAcao = numeroAcao;
        this.dataCadastroProcesso = dataCadastroProcesso;
    }

    public Integer getIdProcesso() {
        return idProcesso;
    }

    public void setIdProcesso(Integer idProcesso) {
        this.idProcesso = idProcesso;
    }

    public String getNumeroAcao() {
        return numeroAcao;
    }

    public void setNumeroAcao(String numeroAcao) {
        this.numeroAcao = numeroAcao;
    }

    public String getDescricaoProcesso() {
        return descricaoProcesso;
    }

    public void setDescricaoProcesso(String descricaoProcesso) {
        this.descricaoProcesso = descricaoProcesso;
    }

    public Date getDataCadastroProcesso() {
        return dataCadastroProcesso;
    }

    public void setDataCadastroProcesso(Date dataCadastroProcesso) {
        this.dataCadastroProcesso = dataCadastroProcesso;
    }

    public String getSituacaoPagamento() {
        return situacaoPagamento;
    }

    public void setSituacaoPagamento(String situacaoPagamento) {
        this.situacaoPagamento = situacaoPagamento;
    }

    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcesso != null ? idProcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processo)) {
            return false;
        }
        Processo other = (Processo) object;
        if ((this.idProcesso == null && other.idProcesso != null) || (this.idProcesso != null && !this.idProcesso.equals(other.idProcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Processo[ idProcesso=" + idProcesso + " ]";
    }
    
}
