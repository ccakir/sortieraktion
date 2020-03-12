package com.cakir.service;

import java.util.List;

import com.cakir.model.Breakpoint;

public interface BreakpointService {
	
	List<Breakpoint> alleBreakpoints();
	
	boolean speichern(Breakpoint breakpoint);
	
	boolean update(Breakpoint breakpoint);
	
	boolean delete(String id);
	
	Breakpoint findById(String id);
	

}
