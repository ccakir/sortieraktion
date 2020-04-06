package com.cakir.model;

import com.cakir.model.Stunden.StundenBuilder;

public class StundenDetails {

	private long id;
	private Stunden stunden;
	private Mitarbeiter mitarbeiter;
	private String von_1;
	private String bis_1;
	private String von_2;
	private String bis_2;
	private String von_3;
	private String bis_3;
	
	
	public StundenDetails() {
		super();
		
	}
	
	
	public StundenDetails(StundenDetailsBuilder builder) {
		this.id = builder.id;
		this.mitarbeiter = builder.mitarbeiter;
		this.stunden = builder.stunden;
		this.von_1 = builder.von_1;
		this.bis_1 = builder.bis_1;
		this.von_2 = builder.von_2;
		this.bis_2 = builder.bis_2;
		this.von_3 = builder.von_3;
		this.bis_3 = builder.bis_3;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Stunden getStunden() {
		return stunden;
	}
	public void setStunden(Stunden stunden) {
		this.stunden = stunden;
	}
	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}
	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	public String getVon_1() {
		return von_1;
	}
	public void setVon_1(String von_1) {
		this.von_1 = von_1;
	}
	public String getBis_1() {
		return bis_1;
	}
	public void setBis_1(String bis_1) {
		this.bis_1 = bis_1;
	}
	public String getVon_2() {
		return von_2;
	}
	public void setVon_2(String von_2) {
		this.von_2 = von_2;
	}
	public String getBis_2() {
		return bis_2;
	}
	public void setBis_2(String bis_2) {
		this.bis_2 = bis_2;
	}
	public String getVon_3() {
		return von_3;
	}
	public void setVon_3(String von_3) {
		this.von_3 = von_3;
	}
	public String getBis_3() {
		return bis_3;
	}
	public void setBis_3(String bis_3) {
		this.bis_3 = bis_3;
	}
	
	public static class StundenDetailsBuilder {
		
		private long id;
		private Stunden stunden;
		private Mitarbeiter mitarbeiter;
		private String von_1;
		private String bis_1;
		private String von_2;
		private String bis_2;
		private String von_3;
		private String bis_3;
		
		
		public StundenDetailsBuilder id(long id) {
			this.id = id;
			return this;
		}
		
		public StundenDetailsBuilder mitarbeiter(Mitarbeiter mitarbeiter) {
			this.mitarbeiter = mitarbeiter;
			return this;
		}
		public StundenDetailsBuilder stunden(Stunden stunden) {
			this.stunden = stunden;
			return this;
		}
		public StundenDetailsBuilder von_1(String von_1) {
			this.von_1 = von_1;
			return this;
		}
		public StundenDetailsBuilder bis_1(String bis_1) {
			this.bis_1 = bis_1;
			return this;
		}
		public StundenDetailsBuilder von_2(String von_2) {
			this.von_2 = von_2;
			return this;
		}
		public StundenDetailsBuilder bis_2(String bis_2) {
			this.bis_2 = bis_2;
			return this;
		}
		public StundenDetailsBuilder von_3(String von_3) {
			this.von_3 = von_3;
			return this;
		}
		public StundenDetailsBuilder bis_3(String bis_3) {
			this.bis_3 = bis_3;
			return this;
		}
		
		
		public StundenDetails build() {
			StundenDetails stundenDetails = new StundenDetails(this);
			return stundenDetails;
		}
	}
}
