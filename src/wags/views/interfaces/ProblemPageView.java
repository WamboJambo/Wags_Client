package wags.views.interfaces;

import wags.Common.View;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Legend;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.google.gwt.user.client.ui.ComplexPanel;

public interface ProblemPageView extends View {

	public ComplexPanel getMagnetPanel();
	public ComplexPanel getLogicalPanel();
    public ComplexPanel getDatabasePanel(); //Disabled until database problems are made
	
	public Button getMagnetCategory();
	public Button getLogicalCategory();
	//public Button getDatabaseCategory(); Disabled until database problems are made
	ListBox getListBox();
	ListBox getlogicalListBox();
	Legend getMagnetLegend();
	Legend getLogicalLegend();
	public ComplexPanel getMagnetReviewPanel();
	public ComplexPanel getMagnetCompletedPanel();
	public ComplexPanel getMagnetDuePanel();
	public ComplexPanel getLogicalDuePanel();
	public ComplexPanel getLogicalCompletedPanel();
	public ComplexPanel getLogicalReviewPanel();
}