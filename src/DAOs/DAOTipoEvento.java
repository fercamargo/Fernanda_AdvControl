package DAOs;

import Entidades.TipoEvento;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOTipoEvento extends DAOGenerico<TipoEvento> {

    public DAOTipoEvento() {
        super(TipoEvento.class);
    }

    public int autoIdTipoEvento() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTipoEvento) FROM TipoEvento e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoEvento> listByIdTipoEvento(int idTipoEvento) {
        return em.createQuery("SELECT e FROM TipoEvento e WHERE e.idTipoEvento = :idTipoEvento").setParameter("idTipoEvento", idTipoEvento).getResultList();
    }

    public List<TipoEvento> listByNomeTipoEvento(String nomeTipoEvento) {
        return em.createQuery("SELECT e FROM TipoEvento e WHERE e.nomeTipoEvento LIKE :nomeTipoEvento").setParameter("nomeTipoEvento", "%" + nomeTipoEvento + "%").getResultList();
    }

    public List<TipoEvento> listInOrderIdTipoEvento() {
        return em.createQuery("SELECT e FROM TipoEvento e ORDER BY e.idTipoEvento").getResultList();
    }

    public List<TipoEvento> listInOrderNomeTipoEvento() {
        return em.createQuery("SELECT e FROM TipoEvento e ORDER BY e.nomeTipoEvento").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoEvento> lf;
        if (qualOrdem.equals("idTipoEvento")) {
            lf = listInOrderIdTipoEvento();
        } else {
            lf = listInOrderNomeTipoEvento();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTipoEvento() + ";" + lf.get(i).getNomeTipoEvento() + ";");
        }
        return ls;
    }
}

