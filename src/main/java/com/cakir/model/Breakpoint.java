package com.cakir.model;

public class Breakpoint {
	
	private String id;
	private boolean erste;
	private boolean zweite;
	private boolean dritte;
	
	
	public Breakpoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Breakpoint(BreakpointBuilder builder) {
		this.id = builder.id;
		this.erste = builder.erste;
		this.zweite = builder.zweite;
		this.dritte = builder.dritte;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isErste() {
		return erste;
	}
	public void setErste(boolean erste) {
		this.erste = erste;
	}
	public boolean isZweite() {
		return zweite;
	}
	public void setZweite(boolean zweite) {
		this.zweite = zweite;
	}
	public boolean isDritte() {
		return dritte;
	}
	public void setDritte(boolean dritte) {
		this.dritte = dritte;
	}
	
	public static class BreakpointBuilder {
		
		private String id;
		private boolean erste;
		private boolean zweite;
		private boolean dritte;
		
		public BreakpointBuilder id(String id) {
			this.id = id;
			return this;
		}
		public BreakpointBuilder erste(boolean erste) {
			this.erste = erste;
			return this;
		}
		public BreakpointBuilder zweite(boolean zweite) {
			this.zweite = zweite;
			return this;
		}
		public BreakpointBuilder dritte(boolean dritte) {
			this.dritte = dritte;
			return this;
		}
		
		public Breakpoint build() {
			Breakpoint breakpoint = new Breakpoint(this);
			return breakpoint;
		}
	}

}
