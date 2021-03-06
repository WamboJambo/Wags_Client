package wags.ProxyFramework;

import wags.WEStatus;
import wags.magnet.view.ProblemPageModel;
import wags.presenters.concrete.ProblemType;

import com.google.gwt.http.client.Response;

/**
 * 
 * @author Dakota Murray
 * 
 * Server File : GetLogicalExercises.php
 * Arguments : None
 * Method: GET
 * Modifies: Main WAGS object, changes center content panel
 *
 */
public class LoadLogicalCommand extends AbstractServerCall {

	private ProblemPageModel model;
	
	@Override
	protected void handleResponse(Response response) {
		WEStatus status = new WEStatus(response);

		/*
		 * The problems array should have even indices corresponding to 
		 * the problem title, and odd indices with either 0 or 1 for 
		 * not completed and completed.
		 */
		String[] problems = status.getMessageArray();

		for (int i = 0; i < problems.length - 2; i += 4) {
			//Message string is the format of "title","status","id"
			String title = problems[i];
			int stat = Integer.parseInt(problems[i+1]);
			int id = Integer.parseInt(problems[i+2]);
			String group = problems[i+3];
			model.addProblem(id, title, stat, group, ProblemType.LOGICAL_PROBLEM);
		}
		model.notifyObservers();
	}
	
	public LoadLogicalCommand(ProblemPageModel model)
	{
		this.model = model;
		command = ProxyCommands.GetLogicalExercises;
	}

}
