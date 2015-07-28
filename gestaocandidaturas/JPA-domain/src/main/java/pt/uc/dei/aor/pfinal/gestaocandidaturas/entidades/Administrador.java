package pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity
@Table(name = "administrador")
public class Administrador extends ResponsavelPosicao implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}

}
