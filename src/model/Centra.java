package model;

import java.util.Vector;

public class Centra {
	public double x;
	public double y;
	public Vector<Vector<Double>> collection;

	public Centra() {
		this.x = 0;
		this.y = 0;
		this.collection = new Vector<Vector<Double>>();
	}

	public Centra(Vector<Double> V) {
		this.x = V.get(0);
		this.y = V.get(1);
		this.collection = new Vector<Vector<Double>>();
	}

	public void setXY(Vector<Double> V) {
		this.x = V.get(0);
		this.y = V.get(1);
	}

	public double checkDistance(double[] point) {
		return Math.sqrt(Math.pow((this.x - point[0]), 2) + Math.pow((this.y - point[1]), 2));
	}

	public double checkDistance(Vector<Double> point) {
		return Math.sqrt(Math.pow((this.x - point.get(0)), 2) + Math.pow((this.y - point.get(1)), 2));
	}

	public void changeCoordinates() {
		this.x = averageX();
		this.y = averageY();
	}

	public double averageX() {
		double sum = 0;
		for (int i = 0; i < collection.size(); i++) {
			sum += collection.get(i).get(0);
		}
		return sum / collection.size();
	}

	public double averageY() {
		double sum = 0;
		for (int i = 0; i < collection.size(); i++) {
			sum += collection.get(i).get(1);
		}
		return sum / collection.size();
	}

	public double quantisationError() {
		double sum = 0;
		for (int i = 0; i < collection.size(); i++) {
			sum += checkDistance(collection.get(i));
		}
		return sum / collection.size();
	}
}
