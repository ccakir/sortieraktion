package com.cakir.model;

public class Details {
	
	private long id;
	private String beginn;
	private String anzahlStueck;
	private String anzahlStunde;
	private String bisDatum;
	private String bisLieferung;
	private String bisWiderruf;
	
	
	public Details() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Details(DetailsBuilder builder) {
		this.id = builder.id;
		this.beginn = builder.beginn;
		this.anzahlStueck = builder.anzahlStueck;
		this.anzahlStunde = builder.anzahlStunde;
		this.bisDatum = builder.bisDatum;
		this.bisLieferung = builder.bisLieferung;
		this.bisWiderruf = builder.bisWiderruf;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBeginn() {
		return beginn;
	}
	public void setBeginn(String beginn) {
		this.beginn = beginn;
	}
	public String getAnzahlStueck() {
		return anzahlStueck;
	}
	public void setAnzahlStueck(String anzahlStueck) {
		this.anzahlStueck = anzahlStueck;
	}
	public String getAnzahlStunde() {
		return anzahlStunde;
	}
	public void setAnzahlStunde(String anzahlStunde) {
		this.anzahlStunde = anzahlStunde;
	}
	public String getBisDatum() {
		return bisDatum;
	}
	public void setBisDatum(String bisDatum) {
		this.bisDatum = bisDatum;
	}
	public String getBisLieferung() {
		return bisLieferung;
	}
	public void setBisLieferung(String bisLieferung) {
		this.bisLieferung = bisLieferung;
	}
	public String getBisWiderruf() {
		return bisWiderruf;
	}
	public void setBisWiderruf(String bisWiderruf) {
		this.bisWiderruf = bisWiderruf;
	}
	
	public static class DetailsBuilder {
		
		private long id;
		private String beginn;
		private String anzahlStueck;
		private String anzahlStunde;
		private String bisDatum;
		private String bisLieferung;
		private String bisWiderruf;
		
		public DetailsBuilder id(long id) {
			this.id = id;
			return this;
		}
		public DetailsBuilder beginn(String beginn) {
			this.beginn = beginn;
			return this;
		}
		public DetailsBuilder anzahlStueck(String anzahlStueck) {
			this.anzahlStueck = anzahlStueck;
			return this;
		}
		public DetailsBuilder anzahlStunde(String anzahlStunde) {
			this.anzahlStunde = anzahlStunde;
			return this;
		}
		public DetailsBuilder bisDatum(String bisDatum) {
			this.bisDatum = bisDatum;
			return this;
		}
		public DetailsBuilder bisLieferung(String bisLieferung) {
			this.bisLieferung = bisLieferung;
			return this;
		}
		public DetailsBuilder bisWiderruf(String bisWiderruf) {
			this.bisWiderruf = bisWiderruf;
			return this;
		}
		
		public Details build() {
			Details details = new Details(this);
			return details;
		}
		
	}

}
