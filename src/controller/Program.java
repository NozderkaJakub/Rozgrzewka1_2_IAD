package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.Centra;

public class Program {

	public double[][] pointsOne;
	public double[][] pointsTwo;
	public List<Centra> centra;

	public Program(int radius, int centerX, int centerY, int noOfCenters, int noOfPoints) throws IOException {
		pointsOne = new double[100][2];
		pointsTwo = new double[100][2];
		centra = new ArrayList<Centra>();
		generateRandomPointsInCircle(radius, centerX, centerY, noOfPoints);
		savePoints();
		initCenters(noOfCenters);
//		makeCenterCollections();
		do {
			makeCenterCollections();
		} while (checkCenters());
	}

	public void savePoints() throws IOException {
		File file = new File("points.txt");
		PrintWriter out = null;
		try {
			file.createNewFile();
			out = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			System.out.println();
		}

		for (int i = 0; i < pointsOne.length; i++) {
			out.println(pointsOne[i][0] + ";" + pointsOne[i][1]);
		}
		for (int i = 0; i < pointsTwo.length; i++) {
			out.println(pointsTwo[i][0] + ";" + pointsTwo[i][1]);
		}

		out.close();
	}

	public void initCenters(int n) {
		for (int i = 0; i < n; i++) {
			centra.add(i, new Centra(setCenterCoords()));
		}
	}

	public boolean checkCenters() {
		boolean check = false;
		for (int i = 0; i < centra.size(); i++) {
			if (centra.get(i).collection.size() == 0) {
				centra.get(i).setXY(setCenterCoords());
				check = true;
			}
		}
		return check;
	}

	public Vector<Double> setCenterCoords() {
		double x = 0, y = 0;
		Vector<Double> V = new Vector<Double>();

		x = Math.random() * 10;
		if (Math.random() > 0.5)
			x *= -1;
		y = Math.random() * 10;
		if (Math.random() > 0.5)
			y *= -1;

		V.add(x);
		V.add(y);
		return V;
	}

	public void generateRandomPointsInCircle(int R, int OX, int OY, int noOfPoints) {
		double a, r;

		for (int i = 0; i < noOfPoints; i++) {
			a = Math.random() * 2 * Math.PI;
			r = R * Math.sqrt(Math.random());
			pointsOne[i][0] = r * Math.cos(a) + OX;
			pointsOne[i][1] = r * Math.sin(a) + OY;

			a = Math.random() * 2 * Math.PI;
			r = R * Math.sqrt(Math.random());
			pointsTwo[i][0] = r * Math.cos(a) - OX;
			pointsTwo[i][1] = r * Math.sin(a) + OY;
		}
	}

	public void makeCenterCollections() {
		for (int i = 0; i < centra.size(); i++) {
			centra.get(i).collection.clear();
		}

		for (int i = 0; i < pointsOne.length; i++) {

			int centro = 0;
			Vector<Double> V = new Vector<Double>();
			V.add(pointsOne[i][0]);
			V.add(pointsOne[i][1]);

			for (int j = 0; j < centra.size() - 1; j++) {
				if (centra.get(j).checkDistance(pointsOne[i]) < centra.get(j + 1).checkDistance(pointsOne[i])) {
					if (centra.get(j).checkDistance(pointsOne[i]) < centra.get(centro).checkDistance(pointsOne[i]))
						centro = j;
				} else {
					if (centra.get(j + 1).checkDistance(pointsOne[i]) < centra.get(centro).checkDistance(pointsOne[i]))
						centro = j + 1;
				}
			}
			centra.get(centro).collection.add(V);
		}

		for (int i = 0; i < pointsTwo.length; i++) {

			int centro = 0;
			Vector<Double> V = new Vector<Double>();
			V.add(pointsTwo[i][0]);
			V.add(pointsTwo[i][1]);

			for (int j = 0; j < centra.size() - 1; j++) {
				if (centra.get(j).checkDistance(pointsTwo[i]) < centra.get(j + 1).checkDistance(pointsTwo[i])) {
					if (centra.get(j).checkDistance(pointsTwo[i]) < centra.get(centro).checkDistance(pointsTwo[i]))
						centro = j;
				} else {
					if (centra.get(j + 1).checkDistance(pointsTwo[i]) < centra.get(centro).checkDistance(pointsTwo[i]))
						centro = j;
				}
			}
			centra.get(centro).collection.add(V);
		}
	}

}
