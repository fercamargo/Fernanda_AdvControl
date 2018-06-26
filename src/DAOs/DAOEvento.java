package DAOs;

import Entidades.Evento;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOEvento extends DAOGenerico<Evento> {

    public DAOEvento() {
        super(Evento.class);
    }

    public int autoIdEvento() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.﻿idAgenda) FROM Evento e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }
public List<Evento> listByIdPessoa(int idAgenda) {
        return em.createQuery("SELECT e FROM Evento e WHERE e.idAgenda = :idAgenda").setParameter("idAgenda", idAgenda).getResultList();
    }

    public List<Evento> listByDataEvento(String dataEvento) {
        return em.createQuery("SELECT e FROM Evento e WHERE e.dataEvento LIKE :dataEvento").setParameter("dataEvento", "%" + dataEvento + "%").getResultList();
    }

    public List<Evento> listInOrder﻿idAgenda() {
        return em.createQuery("SELECT e FROM Evento e ORDER BY e.﻿idAgenda").getResultList();
    }

    public List<Evento> listInOrderDataEvento() {
        return em.createQuery("SELECT e FROM Evento e ORDER BY e.dataEvento").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Evento> lf;
        if (qualOrdem.equals("﻿idAgenda")) {
            lf = listInOrder﻿idAgenda();
        } else {
            lf = listInOrderDataEvento();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).get﻿IdAgenda() + ";" + sdf.format(lf.get(i).getDataEvento()) + ";" + lf.get(i).getHoraInicio() + ";" + lf.get(i).getHoraTermino() + ";" + lf.get(i).getProcessoIdProcesso() + ";" + lf.get(i).getAdvogadoPessoaIdPessoa() + ";" + lf.get(i).getTipoEventoIdTipoEvento() + ";");
        }
        return ls;
    }
}

