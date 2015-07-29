package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Gestor
 *
 */
@Entity
@Table(name = "gestor")
public class Gestor extends ResponsavelPosicao implements Serializable {

	private static final long serialVersionUID = 1L;

	public Gestor() {
		super();
	}

}
