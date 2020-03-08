package com.cakir.model;

public class Kunde {
	
	
	private long id;
	private String name;
	private String adresse;
	private String plz;
	private String land;
	private String stadt;
	private String uidnummer;
	
	
	public Kunde() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Kunde(KundeBuilder builder) {
		super();
		this.id = builder.id;
		this.name = builder.name;
		this.adresse = builder.adresse;
		this.plz = builder.plz;
		this.land = builder.land;
		this.stadt = builder.stadt;
		this.uidnummer = builder.uidnummer;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	public String getStadt() {
		return stadt;
	}
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}
	public String getUidnummer() {
		return uidnummer;
	}
	public void setUidnummer(String uidnummer) {
		this.uidnummer = uidnummer;
	}
	
	
	public static class KundeBuilder {
		private long id;
		private String name;
		private String adresse;
		private String plz;
		private String land;
		private String stadt;
		private String uidnummer;
		
		public KundeBuilder id(long id) {
			this.id = id;
			return this;
		}
		
		public KundeBuilder name(String name) {
			this.name = name;
			return this;
		}
		
		public KundeBuilder adresse(String adresse) {
			this.adresse = adresse;
			return this;
		}
		
		public KundeBuilder plz(String plz) {
			this.plz = plz;
			return this;
		}
		public KundeBuilder land(String land) {
			this.land = land;
			return this;
		}
		
		public KundeBuilder stadt(String stadt) {
			this.stadt = stadt;
			return this;
		}
		
		public KundeBuilder uidnummer(String uidnummer) {
			this.uidnummer = uidnummer;
			return this;
		}
		
		public Kunde build() {
			Kunde kunde = new Kunde(this);
			return kunde;
		}
	}

}
