package wags.ProxyFramework;

import wags.WEStatus;
import wags.magnet.view.ProblemPageModel;
import wags.presenters.concrete.ProblemType;

import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;

/**
 * @author Dakota Murray
 * 
 * Server File: GetMagnetExercises.php
 * Arguments: None
 * Method: GET
 * 
 * Updates the ProblemPageModel with data for all assigned and attempted problems.
 * Once finished will also make the model notify its observers.
 */
public class LoadAssignedProblemsCommand extends AbstractServerCall {

	private ProblemPageModel model;
	
	@Override
	protected void handleResponse(Response response) {
		WEStatus stat = new WEStatus(response);
		
		String[] problems = stat.getMessageArray();
		
		int magnetCount = Integer.parseInt(problems[0]);
		int count = 0;
		//Window.alert("Before the for loop");
		// Iterate over the problems list in steps of three, using the three
		// data points to add a new problem to the model. 
		for(int i = 1; i < problems.length - 2; i += 4){
			int id = Integer.parseInt(problems[i]);
			String title = problems[i+1];
			int status = Integer.parseInt(problems[i+2]);
			String group = problems[i+3];
			
			if ( count < magnetCount) {
				model.addProblem(id, title, status, group, ProblemType.MAGNET_PROBLEM);
			} else {
				//Window.alert("ID: " + id + " title: "  + title + " status :" + status + " group: " + group);
				model.addProblem(id, title, status, group, ProblemType.LOGICAL_PROBLEM);
			}
			count++;
		}
		model.notifyObservers();
	}
	
	public LoadAssignedProblemsCommand(ProblemPageModel model)
	{
		this.model = model;
		command = "GetAssignedExercises";
	}
}
