package DAOs;

import Entidades.Advogado;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOAdvogado extends DAOGenerico<Advogado> {

    public DAOAdvogado() {
        super(Advogado.class);
    }

    public int autoIdAdvogado() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.pessoaIdPessoa) FROM Advogado e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Advogado> listByPessoaIdPessoa(int pessoaIdPessoa) {
        return em.createQuery("SELECT e FROM Advogado e WHERE e.pessoaIdPessoa = :pessoaIdPessoa").setParameter("pessoaIdPessoa", pessoaIdPessoa).getResultList();
    }

    public List<Advogado> listByNumeroOab(String NumeroOab) {
        return em.createQuery("SELECT e FROM Advogado e WHERE e.numeroOab LIKE :numeroOab").setParameter("numeroOab", "%" + NumeroOab + "%").getResultList();
    }

    public List<Advogado> listInOrderPessoaIdPessoa() {
        return em.createQuery("SELECT e FROM Advogado e ORDER BY e.pessoaIdPessoa").getResultList();
    }

    public List<Advogado> listInOrderNumeroOab() {
        return em.createQuery("SELECT e FROM Advogado e ORDER BY e.numeroOab").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Advogado> lf;
        if (qualOrdem.equals("pessoaIdPessoa")) {
            lf = listInOrderPessoaIdPessoa();
        } else {
            lf = listInOrderNumeroOab();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPessoaIdPessoa() + ";" + lf.get(i).getNumeroOab() + ";" + lf.get(i).getEmail() + ";" + lf.get(i).getSenha() + ";");
        }
        return ls;
    }
}

