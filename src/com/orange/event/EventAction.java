package com.orange.event;

import java.util.ArrayList;
import java.util.List;

public class EventAction {

	private List<String> mActions = new ArrayList<String>();

	public EventAction() {

	}

	public EventAction(String pAction) {
		this.mActions.add(pAction);
	}

	public EventAction(String... pActions) {
		for (String action : pActions) {
			this.mActions.add(action);
		}
	}

	public EventAction(List<String> pActions) {
		this.mActions.addAll(pActions);
	}

	public void addAction(String pAction) {
		this.mActions.add(pAction);
	}

	public void addAction(String... pActions) {
		for (String action : pActions) {
			this.mActions.add(action);
		}
	}

	public void addAction(List<String> pActions) {
		this.mActions.addAll(pActions);
	}
}
