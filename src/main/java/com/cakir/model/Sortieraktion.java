package com.cakir.model;

import java.math.BigDecimal;

public class Sortieraktion {
	
	private String id; //Sortieraktion Nummer 
	private String datum;
	private Kunde kunde;
	private String dunsnummer;
	private Kontakt kontaktPerson;
	private Teil teil;
	private Details details;
	private String grund;
	private String anweisung;
	private BigDecimal stundensatzNormal;
	private BigDecimal stundesatzRE;
	private String zusatzkosten;
	private boolean teileReturn;
	private Mitarbeiter mitarbeiter;
	private boolean offen;
	private boolean freigabe;
	private Breakpoint breakpoint;
	
	
	public Sortieraktion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Sortieraktion(SortieraktionBuilder builder) {
		this.id = builder.id;
		this.datum = builder.datum;
		this.kunde = builder.kunde;
		this.dunsnummer = builder.dunsnummer;
		this.kontaktPerson = builder.kontaktPerson;
		this.teil = builder.teil;
		this.details = builder.details;
		this.grund = builder.grund;
		this.anweisung = builder.anweisung;
		this.stundensatzNormal = builder.stundensatzNormal;
		this.stundesatzRE = builder.stundesatzRE;
		this.zusatzkosten = builder.zusatzkosten;
		this.teileReturn = builder.teileReturn;
		this.mitarbeiter = builder.mitarbeiter;
		this.offen = builder.offen;
		this.freigabe = builder.freigabe;
		this.breakpoint = builder.breakpoint;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	public String getDunsnummer() {
		return dunsnummer;
	}
	public void setDunsnummer(String dunsnummer) {
		this.dunsnummer = dunsnummer;
	}
	public Kontakt getKontaktPerson() {
		return kontaktPerson;
	}
	public void setKontaktPerson(Kontakt kontaktPerson) {
		this.kontaktPerson = kontaktPerson;
	}
	public Teil getTeil() {
		return teil;
	}
	public void setTeil(Teil teil) {
		this.teil = teil;
	}
	public Details getDetails() {
		return details;
	}
	public void setDetails(Details details) {
		this.details = details;
	}
	public String getGrund() {
		return grund;
	}
	public void setGrund(String grund) {
		this.grund = grund;
	}
	public String getAnweisung() {
		return anweisung;
	}
	public void setAnweisung(String anweisung) {
		this.anweisung = anweisung;
	}
	public BigDecimal getStundensatzNormal() {
		return stundensatzNormal;
	}
	public void setStundensatzNormal(BigDecimal stundensatzNormal) {
		this.stundensatzNormal = stundensatzNormal;
	}
	public BigDecimal getStundesatzRE() {
		return stundesatzRE;
	}
	public void setStundesatzRE(BigDecimal stundesatzRE) {
		this.stundesatzRE = stundesatzRE;
	}
	public String getZusatzkosten() {
		return zusatzkosten;
	}
	public void setZusatzkosten(String zusatzkosten) {
		this.zusatzkosten = zusatzkosten;
	}
	public boolean isTeileReturn() {
		return teileReturn;
	}
	public void setTeileReturn(boolean teileReturn) {
		this.teileReturn = teileReturn;
	}
	public Mitarbeiter getMitarbeiter() {
		return mitarbeiter;
	}
	public void setMitarbeiter(Mitarbeiter mitarbeiter) {
		this.mitarbeiter = mitarbeiter;
	}
	public boolean isOffen() {
		return offen;
	}
	public void setOffen(boolean offen) {
		this.offen = offen;
	}
	public boolean isFreigabe() {
		return freigabe;
	}
	public void setFreigabe(boolean freigabe) {
		this.freigabe = freigabe;
	}
	public Breakpoint getBreakpoint() {
		return breakpoint;
	}
	public void setBreakpoint(Breakpoint breakpoint) {
		this.breakpoint = breakpoint;
	}
	
	public static class SortieraktionBuilder {
		
		private String id; 
		private String datum;
		private Kunde kunde;
		private String dunsnummer;
		private Kontakt kontaktPerson;
		private Teil teil;
		private Details details;
		private String grund;
		private String anweisung;
		private BigDecimal stundensatzNormal;
		private BigDecimal stundesatzRE;
		private String zusatzkosten;
		private boolean teileReturn;
		private Mitarbeiter mitarbeiter;
		private boolean offen;
		private boolean freigabe;
		private Breakpoint breakpoint;
		
		public SortieraktionBuilder id(String id) {
			this.id = id;
			return this;
		}
		public SortieraktionBuilder datum(String datum) {
			this.datum = datum;
			return this;
		}
		public SortieraktionBuilder kunde(Kunde kunde) {
			this.kunde = kunde;
			return this;
		}
		public SortieraktionBuilder dunsnummer(String dunsnummer) {
			this.dunsnummer = dunsnummer;
			return this;
		}
		public SortieraktionBuilder kontaktPerson(Kontakt kontaktPerson) {
			this.kontaktPerson = kontaktPerson;
			return this;
		}
		public SortieraktionBuilder teil(Teil teil ) {
			this.teil = teil;
			return this;
		}
		public SortieraktionBuilder details(Details details) {
			this.details = details;
			return this;
		}
		public SortieraktionBuilder grund(String grund) {
			this.grund = grund;
			return this;
		}
		public SortieraktionBuilder anweisung(String anweisung) {
			this.anweisung = anweisung;
			return this;
		}
		public SortieraktionBuilder stundensatzNormal(BigDecimal stundensatzNormal) {
			this.stundensatzNormal = stundensatzNormal;
			return this;
		}
		public SortieraktionBuilder stundesatzRE(BigDecimal stundesatzRE) {
			this.stundesatzRE = stundesatzRE;
			return this;
		}
		public SortieraktionBuilder zusatzkosten(String zusatzkosten) {
			this.zusatzkosten = zusatzkosten;
			return this;
		}
		public SortieraktionBuilder teileReturn(boolean teileReturn) {
			this.teileReturn = teileReturn;
			return this;
		}
		public SortieraktionBuilder mitarbeiter(Mitarbeiter mitarbeiter) {
			this.mitarbeiter = mitarbeiter;
			return this;
		}
		public SortieraktionBuilder offen(boolean offen) {
			this.offen = offen;
			return this;
		}
		public SortieraktionBuilder freigabe(boolean freigabe) {
			this.freigabe = freigabe;
			return this;
		}
		public SortieraktionBuilder breakpoint(Breakpoint breakpoint) {
			this.breakpoint = breakpoint;
			return this;
		}
		
		public Sortieraktion build() {
			Sortieraktion sortieraktion = new Sortieraktion(this);
			return sortieraktion;
		}
	}

}
