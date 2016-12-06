package com.supero.interview.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Thiago Puluceno
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusEnum {
	OPEN("A fazer"), DONE("Concluída"), REMOVED("Removida");

	private String name;

	StatusEnum(String name) {
		this.name = name;
	}

	@JsonValue
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
