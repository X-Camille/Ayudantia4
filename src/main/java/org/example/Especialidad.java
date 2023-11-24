package org.example;

public enum Especialidad {
	SUSPENSO,
	CIENCIAFICCION,
	TERROR,
	AUTOAYUDA;

	private String categoriaLibro;

	public String getCategoriaLibro() {
		return this.categoriaLibro;
	}

	/**
	 * 
	 * @param categoriaLibro
	 */
	public void setCategoriaLibro(String categoriaLibro) {
		this.categoriaLibro = categoriaLibro;
	}

}