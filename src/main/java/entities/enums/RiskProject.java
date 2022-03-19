package entities.enums;

public enum RiskProject {
	BAIXO_RISCO(0, "Baixo Risco"),
	MEDIO_RISCO(1, "Médio Risco"),
	ALTO_RISCO(2, "Alto Risco");

	private Integer cod;
	private String description;
	
	RiskProject(int cod, String description) {
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
	
	public static RiskProject toEnum(Integer cod, String description) {
		
		if (cod != null) {
			for (RiskProject rp : RiskProject.values()) {
				if (cod.equals(rp.getCod())) {
					return rp;
				}
			}
			
			throw new IllegalArgumentException("Invalid ID: " + cod);
		} else if (description != null) {
			for (RiskProject rp : RiskProject.values()) {
				if (description.equals(rp.getDescription())) {
					return rp;
				}
			}
			
			throw new IllegalArgumentException("Invalid Description: " + description);
		}
		
		return null;
	}
}