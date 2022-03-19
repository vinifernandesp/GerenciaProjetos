package entities.enums;

public enum StatusProject {
	
	EM_ANALISE(0, "Em Análise"),
	ANALISE_REALIZADA(1, "Análise Realizada"),
	ANALISE_APROVADA(2, "Análise Aprovada"),
	INICIADO(3, "Iniciado"),
	PLANEJADO(4, "Planejado"),
	EM_ANDAMENTO(5, "Em Andamento"),
	ENCERRADO(6, "Encerrado"),
	CANCELADO(7, "Cancelado");
	
	private Integer cod;
	private String description;
	
	StatusProject(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}
	
	public Integer getCod() {
		return cod;
	}
	
	public void setCod(Integer cod) {
		this.cod = cod;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public static StatusProject toEnum(Integer cod, String description) {
		
		if (cod != null) {
			for (StatusProject sp : StatusProject.values()) {
				if (cod.equals(sp.getCod())) {
					return sp;
				}
			}
			
			throw new IllegalArgumentException("Invalid ID: " + cod);
		} else if (description != null) {
			for (StatusProject sp : StatusProject.values()) {
				if (description.equals(sp.getDescription())) {
					return sp;
				}
			}
			
			throw new IllegalArgumentException("Invalid Description: " + description);
		}
		
		return null;
	}
}
