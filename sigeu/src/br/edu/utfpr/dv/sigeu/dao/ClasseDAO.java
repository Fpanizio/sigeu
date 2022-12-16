package br.edu.utfpr.dv.sigeu.dao;

import org.hibernate.Query;

import br.edu.utfpr.dv.sigeu.entities.Campus;
import br.edu.utfpr.dv.sigeu.entities.Classe;
import br.edu.utfpr.dv.sigeu.persistence.HibernateDAO;
import br.edu.utfpr.dv.sigeu.persistence.Transaction;

public class ClasseDAO extends HibernateDAO<Classe> {

	public ClasseDAO(Transaction transaction) {
		super(transaction);
	}

	@Override
	public Classe encontrePorId(Integer id) {
		String hql = "from Classe o where o.idClasse = :id";
		Query q = extracted(hql);
		q.setInteger("id", id);
		return (Classe) q.uniqueResult();
	}

	@Override
	public String getNomeSequencia() {
		return "classe";
	}

	@Override
	public void preCriacao(Classe o) {
		Integer val = this.gerarNovoId().intValue();
		o.setIdClasse(val);
	}

	public Classe encontrePorCodigo(Campus campus, String codigo) {
		String hql = "from Classe o where o.codigo = :codigo and o.idCampus.idCampus = :idCampus";
		Query q = extracted(hql);
		q.setString("codigo", codigo);
		q.setInteger("idCampus", campus.getIdCampus());
		return (Classe) q.uniqueResult();
	}

	private Query extracted(String hql) {
		Query q = session.createQuery(hql);
		return q;
	}

	@Override
	public void preAlteracao(Classe o) {
		
	}

}
