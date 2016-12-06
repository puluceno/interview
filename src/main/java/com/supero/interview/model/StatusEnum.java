package com.supero.interview.model;

/**
 * @author Thiago Puluceno
 */
public enum StatusEnum {
	OPEN("A fazer"), DONE("Concluída"), REMOVED("Removida");

	private String string;

	StatusEnum(String name) {
		string = name;
	}

	@Override
	public String toString() {
		return string;
	}
}
