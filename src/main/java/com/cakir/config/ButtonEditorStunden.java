package com.cakir.config;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.cakir.gui.SortieraktionAnsichtJFrame;
import com.cakir.gui.StundenDetailsJFrame;

public class ButtonEditorStunden extends DefaultCellEditor {
	  protected JButton button;
	  private String    label;
	  private boolean   isPushed;
	  
	  public ButtonEditorStunden(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	   
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        fireEditingStopped();
	      }
	    });
	  }
	  
	  public Component getTableCellEditorComponent(JTable table, Object value,
	                   boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(SystemColor.inactiveCaption);
	    } else{
	      button.setForeground(table.getForeground());
	      button.setBackground(SystemColor.inactiveCaption);
	    }
	    label = (value ==null) ? "" : value.toString();
	    button.setText( label );
	    isPushed = true;
	    return button;
	  }
	  
	  public Object getCellEditorValue() {
	    if (isPushed)  {
	      
	     
	      long id = Long.parseLong(label.substring(14));
	      
	      StundenDetailsJFrame frame = new StundenDetailsJFrame();
	      frame.setLocationRelativeTo(null);
	      frame.getTableList(id);
	      frame.setVisible(true);
	    		  
	    }
	    isPushed = false;
	    return new String( label ) ;
	  }
	    
	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }
	  
	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	}