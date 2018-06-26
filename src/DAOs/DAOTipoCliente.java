package DAOs;

import Entidades.TipoCliente;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOTipoCliente extends DAOGenerico<TipoCliente> {

    public DAOTipoCliente() {
        super(TipoCliente.class);
    }

    public int autoIdTipoCliente() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.clientePessoaIdPessoa) FROM TipoCliente e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<TipoCliente> listByClientePessoaIdPessoa(int clientePessoaIdPessoa) {
        return em.createQuery("SELECT e FROM TipoCliente e WHERE e.clientePessoaIdPessoa = :clientePessoaIdPessoa").setParameter("clientePessoaIdPessoa", clientePessoaIdPessoa).getResultList();
    }

    public List<TipoCliente> listByNomeTipoCliente(String nomeTipoCliente) {
        return em.createQuery("SELECT e FROM TipoCliente e WHERE e.nomeTipoCliente LIKE :nomeTipoCliente").setParameter("nomeTipoCliente", "%" + nomeTipoCliente + "%").getResultList();
    }

    public List<TipoCliente> listInOrderClientePessoaIdPessoa() {
        return em.createQuery("SELECT e FROM TipoCliente e ORDER BY e.clientePessoaIdPessoa").getResultList();
    }

    public List<TipoCliente> listInOrderNomeTipoCliente() {
        return em.createQuery("SELECT e FROM TipoCliente e ORDER BY e.nomeTipoCliente").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<TipoCliente> lf;
        if (qualOrdem.equals("clientePessoaIdPessoa")) {
            lf = listInOrderClientePessoaIdPessoa();
        } else {
            lf = listInOrderNomeTipoCliente();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getClientePessoaIdPessoa() + ";" + lf.get(i).getNomeTipoCliente() + ";");
        }
        return ls;
    }
}

