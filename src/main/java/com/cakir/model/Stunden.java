package com.cakir.model;

public class Stunden {
	
	private long id;
	private Sortieraktion aktion;
	private String datum;
	private int checked_quantity;
	private int ok_quantity;
	private int nok_quantity;
	private int rework_quantity;
	
	public Stunden() {
		super();
		
	}

	
	public Stunden(StundenBuilder builder) {
		this.id = builder.id;
		this.aktion = builder.aktion;
		this.datum = builder.datum;
		this.checked_quantity = builder.checked_quantity;
		this.ok_quantity = builder.ok_quantity;
		this.nok_quantity = builder.nok_quantity;
		this.rework_quantity = builder.rework_quantity;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sortieraktion getAktion() {
		return aktion;
	}

	public void setAktion(Sortieraktion aktion) {
		this.aktion = aktion;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	
	public int getChecked_quantity() {
		return checked_quantity;
	}

	public void setChecked_quantity(int checked_quantity) {
		this.checked_quantity = checked_quantity;
	}

	public int getOk_quantity() {
		return ok_quantity;
	}

	public void setOk_quantity(int ok_quantity) {
		this.ok_quantity = ok_quantity;
	}

	public int getNok_quantity() {
		return nok_quantity;
	}

	public void setNok_quantity(int nok_quantity) {
		this.nok_quantity = nok_quantity;
	}

	public int getRework_quantity() {
		return rework_quantity;
	}

	public void setRework_quantity(int rework_quantity) {
		this.rework_quantity = rework_quantity;
	}
	
	public static class StundenBuilder {
		
		private long id;
		private Sortieraktion aktion;
		private String datum;
		private int checked_quantity;
		private int ok_quantity;
		private int nok_quantity;
		private int rework_quantity;
		
		public StundenBuilder id(long id) {
			this.id = id;
			return this;
		}
		public StundenBuilder aktion(Sortieraktion aktion) {
			this.aktion = aktion;
			return this;
		}
		public StundenBuilder datum(String datum) {
			this.datum = datum;
			return this;
		}
		
		public StundenBuilder checked_quantity(int checked_quantity) {
			this.checked_quantity = checked_quantity;
			return this;
		}
		public StundenBuilder ok_quantity(int ok_quantity) {
			this.ok_quantity = ok_quantity;
			return this;
		}
		public StundenBuilder nok_quantity(int nok_quantity) {
			this.nok_quantity = nok_quantity;
			return this;
		}
		public StundenBuilder rework_quantity(int rework_quantity) {
			this.rework_quantity = rework_quantity;
			return this;
		}
		
		public Stunden build() {
			Stunden stunden = new Stunden(this);
			return stunden;
		}
	}

	@Override
	public String toString() {
		return "Stunden [id=" + id + ", aktion=" + aktion + ", datum=" + datum + ", checked_quantity="
				+ checked_quantity + ", ok_quantity=" + ok_quantity + ", nok_quantity=" + nok_quantity
				+ ", rework_quantity=" + rework_quantity + "]";
	}
	

}
