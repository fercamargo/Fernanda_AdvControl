package DAOs;

import Entidades.Processo;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOProcesso extends DAOGenerico<Processo> {

    public DAOProcesso() {
        super(Processo.class);
    }

    public int autoIdProcesso() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idProcesso) FROM Processo e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Processo> listByIdProcesso(int idProcesso) {
        return em.createQuery("SELECT e FROM Processo e WHERE e.idProcesso = :idProcesso").setParameter("idProcesso", idProcesso).getResultList();
    }

    public List<Processo> listByNumeroAcao(String numeroAcao) {
        return em.createQuery("SELECT e FROM Processo e WHERE e.numeroAcao LIKE :numeroAcao").setParameter("numeroAcao", "%" + numeroAcao + "%").getResultList();
    }

    public List<Processo> listInOrderIdProcesso() {
        return em.createQuery("SELECT e FROM Processo e ORDER BY e.idProcesso").getResultList();
    }

    public List<Processo> listInOrderNumeroAcao() {
        return em.createQuery("SELECT e FROM Processo e ORDER BY e.numeroAcao").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Processo> lf;
        if (qualOrdem.equals("idProcesso")) {
            lf = listInOrderIdProcesso();
        } else {
            lf = listInOrderNumeroAcao();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdProcesso() + ";" + lf.get(i).getNumeroAcao() + ";" + sdf.format(lf.get(i).getDataCadastroProcesso()) + ";" + lf.get(i).getSituacaoPagamento() + ";");
        }
        return ls;
    }
}

