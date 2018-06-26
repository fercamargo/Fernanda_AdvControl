/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fernanda
 */
@Entity
@Table(name = "evento")
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_agenda")
    private Integer idAgenda;
    @Basic(optional = false)
    @Column(name = "data_evento")
    @Temporal(TemporalType.DATE)
    private Date dataEvento;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Column(name = "hora_termino")
    @Temporal(TemporalType.TIME)
    private Date horaTermino;
    @JoinColumn(name = "advogado_pessoa_id_pessoa", referencedColumnName = "pessoa_id_pessoa")
    @ManyToOne(optional = false)
    private Advogado advogadoPessoaIdPessoa;
    @JoinColumn(name = "processo_id_processo", referencedColumnName = "id_processo")
    @ManyToOne
    private Processo processoIdProcesso;
    @JoinColumn(name = "tipo_evento_id_tipo_evento", referencedColumnName = "id_tipo_evento")
    @ManyToOne(optional = false)
    private TipoEvento tipoEventoIdTipoEvento;

    public Evento() {
    }

    public Evento(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public Evento(Integer idAgenda, Date dataEvento) {
        this.idAgenda = idAgenda;
        this.dataEvento = dataEvento;
    }

    public Integer getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Integer idAgenda) {
        this.idAgenda = idAgenda;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Date horaTermino) {
        this.horaTermino = horaTermino;
    }

    public Advogado getAdvogadoPessoaIdPessoa() {
        return advogadoPessoaIdPessoa;
    }

    public void setAdvogadoPessoaIdPessoa(Advogado advogadoPessoaIdPessoa) {
        this.advogadoPessoaIdPessoa = advogadoPessoaIdPessoa;
    }

    public Processo getProcessoIdProcesso() {
        return processoIdProcesso;
    }

    public void setProcessoIdProcesso(Processo processoIdProcesso) {
        this.processoIdProcesso = processoIdProcesso;
    }

    public TipoEvento getTipoEventoIdTipoEvento() {
        return tipoEventoIdTipoEvento;
    }

    public void setTipoEventoIdTipoEvento(TipoEvento tipoEventoIdTipoEvento) {
        this.tipoEventoIdTipoEvento = tipoEventoIdTipoEvento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAgenda != null ? idAgenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idAgenda == null && other.idAgenda != null) || (this.idAgenda != null && !this.idAgenda.equals(other.idAgenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Evento[ idAgenda=" + idAgenda + " ]";
    }

    public void setProcessoIdProcesso(Integer idProcesso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAdvogadoPessoaIdPessoa(Integer idAdvogado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setTipoEventoIdTipoEvento(Integer idTipoEvento) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
