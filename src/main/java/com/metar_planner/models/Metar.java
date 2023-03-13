package com.metar_planner.models;

import java.util.Objects;

public class Metar {
	private String icaoCode;
	private String rawMetar;

	public Metar() {}
	public Metar(String icaoCode, String rawMetar) {
		this.icaoCode = icaoCode;
		this.rawMetar = rawMetar;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Metar metar = (Metar) o;
		return Objects.equals(icaoCode, metar.icaoCode) && Objects.equals(rawMetar, metar.rawMetar);
	}

	@Override
	public int hashCode() {
		return Objects.hash(icaoCode, rawMetar);
	}

	public String getIcaoCode() {
		return this.icaoCode;
	}

	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}

	public String getRawMetar() {
		return this.rawMetar;
	}

	public void setRawMetar(String rawMetar) {
		this.rawMetar = rawMetar;
	}

	@Override
	public String toString() {
		return "Metar{" +
			"icaoCode=" + icaoCode +
			", rawMetar='" + rawMetar + '\'' +
			'}';
	}
}
