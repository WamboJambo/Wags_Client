package wags.logical.TreeProblems.HeapProblems;

import org.vaadin.gwtgraphics.client.DrawingArea;

import wags.ProxyFramework.AbstractServerCall;
import wags.ProxyFramework.UploadLogicalMicrolabCommand;
import wags.admin.builders.InsertMethod;
import wags.logical.AddEdgeRules;
import wags.logical.DisplayManager;
import wags.logical.EdgeCollection;
import wags.logical.Evaluation;
import wags.logical.NodeCollection;
import wags.logical.NodeDragController;
import wags.logical.NodeDropController;
import wags.logical.Problem;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class SortProblem extends Problem implements IsSerializable {
	private String name;
	private String problemText;
	private String nodes;
	private int[] xPositions; // must be same size as nodes
	private int[] yPositions; // must be same size edges
	private String insertMethod;
	private String[] edges; // each array element contains two chars (i.e AB),
							// 1st is parent, 2nd is child
	private Evaluation eval;
	private AddEdgeRules rules;
	private String[] arguments;
	private boolean edgesRemovable;
	private boolean nodesDraggable;
	private String nodeType;
	private DisplayManager dm;

	public SortProblem(String name, String problemText, String nodes,
			String insertMethod, int[] xPositions, int[] yPositions,
			String[] edges, String[] arguments, Evaluation eval,
			AddEdgeRules rules, boolean edgesRemovable, boolean nodesDraggable,
			String nodeType) {
		this.name = name;
		this.problemText = problemText;
		this.nodes = nodes;
		this.insertMethod = insertMethod;
		this.xPositions = xPositions;
		this.yPositions = yPositions;
		this.edges = edges;
		this.arguments = arguments;
		this.eval = eval;
		this.rules = rules;
		this.edgesRemovable = edgesRemovable;
		this.nodesDraggable = nodesDraggable;
		this.nodeType = nodeType;
	}

	@Override
	public DisplayManager createDisplayManager(AbsolutePanel panel,
			DrawingArea canvas) {
		EdgeCollection ec = new EdgeCollection(getRules(), new String[] {
				"Select first node of edge", "Select second node of edge" },
				getEdgesRemovable());
		NodeDragController.setFields(panel, true, ec);
		NodeDropController.setFields(panel, ec);
		NodeDragController.getInstance().registerDropController(
				NodeDropController.getInstance());
		NodeCollection nc = new NodeCollection();

		dm = new SortDisplayManager(canvas, panel, nc, this);

		return dm;
	}

	@Override
	public String evaluate() {
		return getEval().evaluate(getName(), getArguments(), dm.getNodes(),
				dm.getEdges());
	}

	/**
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getProblemText() {
		return problemText;
	}

	@Override
	public void setProblemText(String problemText) {
		this.problemText = problemText;
	}

	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	@Override
	public Evaluation getEval() {
		return eval;
	}

	public void setEval(Evaluation eval) {
		this.eval = eval;
	}

	public AddEdgeRules getRules() {
		return rules;
	}

	public void setRules(AddEdgeRules rules) {
		this.rules = rules;
	}

	@Override
	public String[] getArguments() {
		return arguments;
	}

	public void setArguments(String[] arguments) {
		this.arguments = arguments;
	}

	public int[] getXPositions() {
		return xPositions;
	}

	public int[] getYPositions() {
		return yPositions;
	}

	public String getInsertMethod() {
		return insertMethod;
	}

	public String[] getEdges() {
		return edges;
	}

	public boolean getEdgesRemovable() {
		return edgesRemovable;
	}

	public boolean getNodesDraggable() {
		return nodesDraggable;
	}

	@Override
	public String getNodeType() {
		return nodeType;
	}
	
	public String printDetails(){
		String str = "";
		str = "&title=" + this.name + "&problemText=" + this.problemText + "&nodes=" + this.nodes;
		String xPos = "";
		String yPos = "";
		
		str += "&xPositions=" + xPos + "&yPositions=" + yPos + "&insertMethod=" + InsertMethod.BY_VALUE;
		
		String edgStr = "";
		String args = "";
		for(int i = 0; i < arguments.length; i++){
			args += arguments[i] + ",";
		}
		args = args.substring(0, args.length() - 1);
		
		int edgeRem = 0, nodesDrag = 0;
		if(this.edgesRemovable) edgeRem = 1;
		if(this.nodesDraggable) nodesDrag = 1;
		str += "&edges=" + edgStr + "&evaluation=" + this.eval.returnKeyValue() + "&edgeRules=" + this.rules.returnKeyValue() 
				+ "&arguments=" + args + "&edgesRemovable=" + edgeRem
				+ "&nodesDraggable=" + nodesDrag + "&nodeType=" + this.nodeType + "&genre=heapsort" + 
				"&group=8";
		
		AbstractServerCall cmd = new UploadLogicalMicrolabCommand(str);
		cmd.sendRequest();
		return str;
		
	}
}