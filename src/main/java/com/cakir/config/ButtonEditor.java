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

public class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;
	  private String    label;
	  private boolean   isPushed;
	  
	  public ButtonEditor(JCheckBox checkBox) {
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
	      
	     
	      SortieraktionAnsichtJFrame aFrame = new SortieraktionAnsichtJFrame(button,label);
	      aFrame.setLocationRelativeTo(null);
	     
	      aFrame.setVisible(true);
	    		  
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