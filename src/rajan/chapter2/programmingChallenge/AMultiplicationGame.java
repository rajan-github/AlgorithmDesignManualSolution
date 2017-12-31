package rajan.chapter2.programmingChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AMultiplicationGame {
	public String playGame(int number) {
		int stanScore = 1, ollieScore = 1;
		while (stanScore >= number || ollieScore >= number) {
			stanScore *= ThreadLocalRandom.current().nextInt(2, 9 + 1);
			ollieScore *= ThreadLocalRandom.current().nextInt(2, 9 + 1);
		}
		return (stanScore >= number) ? "Stan wins." : "Ollie wins.";
	}

	public static void main(String[] args) {
		AMultiplicationGame comptetion = new AMultiplicationGame();
		List<String> outputs = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] inputs = line.split("\\s+");
				if (inputs.length == 1 && !inputs[0].equals("")) {
					outputs.add(comptetion.playGame(Integer.parseInt(inputs[0])));
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
