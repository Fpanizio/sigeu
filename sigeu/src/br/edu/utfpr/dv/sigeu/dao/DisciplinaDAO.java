package br.edu.utfpr.dv.sigeu.dao;

import org.hibernate.Query;

import br.edu.utfpr.dv.sigeu.entities.Campus;
import br.edu.utfpr.dv.sigeu.entities.Disciplina;
import br.edu.utfpr.dv.sigeu.persistence.HibernateDAO;
import br.edu.utfpr.dv.sigeu.persistence.Transaction;

public class DisciplinaDAO extends HibernateDAO<Disciplina> {

	public DisciplinaDAO(Transaction transaction) {
		super(transaction);
	}

	@Override
	public Disciplina encontrePorId(Integer id) {
		String hql = "from Disciplina o where o.idDisciplina = :id";
		Query q = extracted(hql);
		q.setInteger("id", id);
		return (Disciplina) q.uniqueResult();
	}

	public Disciplina encontrePorCodigo(Campus campus, String codigo) {
		String hql = "from Disciplina o where o.codigo = :codigo and o.idCampus.idCampus = :idCampus";
		Query q = extracted(hql);
		q.setString("codigo", codigo);
		extracted2(campus, q);
		return (Disciplina) q.uniqueResult();
	}

	public Disciplina encontrePorNome(Campus campus, String nome) {
		String hql = "from Disciplina o where o.nome = :nome and o.idCampus.idCampus = :idCampus";
		Query q = extracted(hql);
		q.setString("nome", nome);
		extracted2(campus, q);
		return (Disciplina) q.uniqueResult();
	}

	public Disciplina encontrePorRotulo(Campus campus, String rotulo) {
		String hql = "from Disciplina o where o.rotulo = :rotulo and o.idCampus.idCampus = :idCampus";
		Query q = extracted(hql);
		q.setString("rotulo", rotulo);
		extracted2(campus, q);
		return (Disciplina) q.uniqueResult();
	}

	@Override
	public String getNomeSequencia() {
		return "disciplina";
	}

	@Override
	public void preCriacao(Disciplina o) {
		Integer val = this.gerarNovoId().intValue();
		o.setIdDisciplina(val);
	}

	@Override
	public void preAlteracao(Disciplina o) {

	}

	private Query extracted(String hql) {
		return session.createQuery(hql);
	}

	private void extracted2(Campus campus, Query q) {
		q.setInteger("idCampus", campus.getIdCampus());
	}

}
