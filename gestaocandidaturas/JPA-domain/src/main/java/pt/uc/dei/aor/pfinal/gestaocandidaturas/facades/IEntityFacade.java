package pt.uc.dei.aor.pfinal.gestaocandidaturas.facades;

import java.util.Collection;

public interface IEntityFacade<T> {

	T create(T entity);

	T update(T entity);

	boolean delete(T entity);

	T find(long pk);

	Collection<T> findAll();
}
