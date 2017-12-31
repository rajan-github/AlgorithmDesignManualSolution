package rajan.chapter2.programmingChallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LightMoreLight {

	public String getBulbState(int number) {
		boolean isOn = false;
		for (int i = 1; i < (number / 2) + 1; i++) {
			if (number % i == 0) {
				isOn = !isOn;
			}
		}
		return (isOn) ? "no" : "yes";
	}

	public static void main(String[] args) {
		LightMoreLight lightManager = new LightMoreLight();
		List<String> outputs = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] inputs = line.split("\\s+");
				if (inputs.length == 1 && !inputs[0].equals("0")) {
					outputs.add(lightManager.getBulbState(Integer.parseInt(inputs[0])));
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
