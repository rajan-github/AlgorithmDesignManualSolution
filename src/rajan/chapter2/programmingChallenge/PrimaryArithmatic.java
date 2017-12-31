package rajan.chapter2.programmingChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrimaryArithmatic {
	public String countCarryOperations(int number1, int number2) {
		int carryOperations = 0, lastCarry = 0;
		while (number1 > 0 && number2 > 0) {
			if (number1 % 10 + number2 % 10 + lastCarry > 9) {
				carryOperations++;
				lastCarry++;
			} else {
				lastCarry = 0;
			}
			number1 = number1 / 10;
			number2 = number2 / 10;
		}
		return ((carryOperations > 0) ? carryOperations : "No") + " carry operation.";
	}

	public static void main(String[] args) {
		PrimaryArithmatic pa = new PrimaryArithmatic();
		List<String> outputs = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] inputs = line.split("\\s+");
				if (inputs.length == 2 && !inputs[0].equals("0") && !inputs[1].equals("0")) {
					outputs.add(pa.countCarryOperations(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])));
				} else {
					break;
				}
			}
			for (String output : outputs) {
				System.out.println(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
