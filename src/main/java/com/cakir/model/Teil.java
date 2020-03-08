package com.cakir.model;

public class Teil {
	
	private long id;
	private String teilename;
	private String teilenummer;
	private Kunde kunde;
	
	
	public Teil() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Teil(TeilBuilder builder) {
		this.id = builder.id;
		this.teilename = builder.teilename;
		this.teilenummer = builder.teilenummer;
		this.kunde = builder.kunde;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTeilename() {
		return teilename;
	}
	public void setTeilename(String teilename) {
		this.teilename = teilename;
	}
	public String getTeilenummer() {
		return teilenummer;
	}
	public void setTeilenummer(String teilenummer) {
		this.teilenummer = teilenummer;
	}
	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	
	
	public static class TeilBuilder {
		
		private long id;
		private String teilename;
		private String teilenummer;
		private Kunde kunde;
		
		public TeilBuilder id(long id) {
			this.id = id;
			return this;
		}
		public TeilBuilder teilename(String teilename) {
			this.teilename = teilename;
			return this;
		}
		public TeilBuilder teilenummer(String teilenummer) {
			this.teilenummer = teilenummer;
			return this;
		}
		public TeilBuilder kunde(Kunde kunde) {
			this.kunde = kunde;
			return this;
		}
		
		public Teil build() {
			Teil teil = new Teil(this);
			return teil;
		}
	}
	

}
