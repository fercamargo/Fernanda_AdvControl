package DAOs;

import Entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOCliente extends DAOGenerico<Cliente> {

    public DAOCliente() {
        super(Cliente.class);
    }

    public int autoIdCliente() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.pessoaIdPessoa) FROM Cliente e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Cliente> listByPessoaIdPessoa(int pessoaIdPessoa) {
        return em.createQuery("SELECT e FROM Cliente e WHERE e.pessoaIdPessoa = :pessoaIdPessoa").setParameter("pessoaIdPessoa", pessoaIdPessoa).getResultList();
    }

    public List<Cliente> listByEstadoCivilCliente(String estadoCivilCliente) {
        return em.createQuery("SELECT e FROM Cliente e WHERE e.estadoCivilCliente LIKE :estadoCivilCliente").setParameter("estadoCivilCliente", "%" + estadoCivilCliente + "%").getResultList();
    }

    public List<Cliente> listInOrderPessoaIdPessoa() {
        return em.createQuery("SELECT e FROM Cliente e ORDER BY e.pessoaIdPessoa").getResultList();
    }

    public List<Cliente> listInOrderEstadoCivilCliente() {
        return em.createQuery("SELECT e FROM Cliente e ORDER BY e.estadoCivilCliente").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Cliente> lf;
        if (qualOrdem.equals("pessoaIdPessoa")) {
            lf = listInOrderPessoaIdPessoa();
        } else {
            lf = listInOrderEstadoCivilCliente();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPessoaIdPessoa() + ";" + lf.get(i).getEstadoCivilCliente() + ";");
        }
        return ls;
    }
}

