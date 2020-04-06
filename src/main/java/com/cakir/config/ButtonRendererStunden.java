package com.cakir.config;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

public class ButtonRendererStunden extends JButton implements TableCellRenderer {
	  
	  public ButtonRendererStunden() {
	    setOpaque(true);
	  }
	   
	  public Component getTableCellRendererComponent(JTable table, Object value,
	                   boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(SystemColor.inactiveCaption);
	    } else{
	      setForeground(table.getForeground());
	      setBackground(SystemColor.inactiveCaption);
	    }
	    setText( (value ==null) ? "" : value.toString() );
	    return this;
	  }
	}
