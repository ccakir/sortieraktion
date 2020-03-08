package com.cakir.model;

public class Kontakt {
	
	private long id;
	private String vorname;
	private String nachname;
	private String tel;
	private String fax;
	private String email;
	
	
	public Kontakt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Kontakt(KontaktBuilder builder) {
		this.id = builder.id;
		this.vorname = builder.vorname;
		this.nachname = builder.nachname;
		this.tel = builder.tel;
		this.fax = builder.fax;
		this.email = builder.email;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNachname() {
		return nachname;
	}


	public void setNachname(String nachname) {
		this.nachname = nachname;
	}


	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public static class KontaktBuilder {
		
		private long id;
		private String vorname;
		private String nachname;
		private String tel;
		private String fax;
		private String email;
		
		public KontaktBuilder id(long id) {
			this.id = id;
			return this;
		}
		
		public KontaktBuilder vorname(String vorname) {
			this.vorname = vorname;
			return this;
		}
		public KontaktBuilder nachname(String nachname) {
			this.nachname = nachname;
			return this;
		}
		public KontaktBuilder email(String email) {
			this.email = email;
			return this;
		}
		public KontaktBuilder tel(String tel) {
			this.tel = tel;
			return this;
		}
		public KontaktBuilder fax(String fax) {
			this.fax = fax;
			return this;
		}
		
		public Kontakt build() {
			Kontakt kontakt = new Kontakt(this);
			return kontakt;
		}
	}
	

}
