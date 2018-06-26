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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Fernanda
 */
@Entity
@Table(name = "tipo_evento")
@NamedQueries({
    @NamedQuery(name = "TipoEvento.findAll", query = "SELECT t FROM TipoEvento t")})
public class TipoEvento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_tipo_evento")
    private Integer idTipoEvento;
    @Basic(optional = false)
    @Column(name = "nome_tipo_evento")
    private String nomeTipoEvento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoEventoIdTipoEvento")
    private List<Evento> eventoList;

    public TipoEvento() {
    }

    public TipoEvento(Integer idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    public TipoEvento(Integer idTipoEvento, String nomeTipoEvento) {
        this.idTipoEvento = idTipoEvento;
        this.nomeTipoEvento = nomeTipoEvento;
    }

    public Integer getIdTipoEvento() {
        return idTipoEvento;
    }

    public void setIdTipoEvento(Integer idTipoEvento) {
        this.idTipoEvento = idTipoEvento;
    }

    public String getNomeTipoEvento() {
        return nomeTipoEvento;
    }

    public void setNomeTipoEvento(String nomeTipoEvento) {
        this.nomeTipoEvento = nomeTipoEvento;
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
        hash += (idTipoEvento != null ? idTipoEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEvento)) {
            return false;
        }
        TipoEvento other = (TipoEvento) object;
        if ((this.idTipoEvento == null && other.idTipoEvento != null) || (this.idTipoEvento != null && !this.idTipoEvento.equals(other.idTipoEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TipoEvento[ idTipoEvento=" + idTipoEvento + " ]";
    }
    
}
