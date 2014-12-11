package com.uitl.button;

import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.tower.resource.Images;

public class LoadButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image loadButton=Images.LOADBUTTON;
	public LoadButton(){
		ImageIcon icon=new ImageIcon(loadButton);
		setSize(icon.getImage().getWidth(null),
				icon.getImage().getHeight(null));
		setIcon(icon);
		setMargin(new Insets(0,0,0,0));
		setIconTextGap(0);
		setBorderPainted(false);
		setBorder(null);
		setText(null);
		setFocusPainted(false);
		setContentAreaFilled(false);
	}
}
