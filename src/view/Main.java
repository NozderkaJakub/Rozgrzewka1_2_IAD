package view;

import java.io.IOException;

import controller.Program;

public class Main {
	public static void main(String[] args) throws IOException {

		Program program = new Program(2, 3, 0, 10, 100);
		double error = 0, oldError = 0;
		int iteration = 1;

		do {
			System.out.println("Nowy zbiór danych " + iteration);
			oldError = error;
			error = 0;
			program.makeCenterCollections();
			for (int i = 0; i < program.centra.size(); i++) {
				error += program.centra.get(i).quantisationError();
				System.out.println(program.centra.get(i).x + ";" + program.centra.get(i).y);
				program.centra.get(i).changeCoordinates();
			}
			System.out.println("b³¹d");
			System.out.println(iteration); // do pliku OutK.png - wykres zmian b³êdu kwantyzacji
			System.out.println(error + "\n\n\n");
			iteration++;
		} while (error != oldError);

		System.out.println("Koniec :)");

	}

}
