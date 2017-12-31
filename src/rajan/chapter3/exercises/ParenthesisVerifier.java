package rajan.chapter3.exercises;

public class ParenthesisVerifier {
	public int verifyParenthesis(String expression) {
		Character temp, popedBracket;
		int position = -1;
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < expression.length(); i++) {
			position = i;
			temp = expression.charAt(i);
			if (temp.equals('(') || temp.equals('{') || temp.equals('[')) {
				stack.push(temp);
			} else if (temp.equals(')') || temp.equals('}') || temp.equals(']')) {
				popedBracket = stack.pop();
				if (popedBracket == null || popedBracket != getComplementary(temp)) {
					position = i;
					break;
				}
			}
		}
		if (stack.isEmpty()) {
			position = -1;
		}
		return position;
	}

	private char getComplementary(Character c) {
		char complementary = '0';
		switch (c) {
		case ')':
			complementary = '(';
			break;
		case '}':
			complementary = '{';
			break;
		case ']':
			complementary = '[';
		}
		return complementary;
	}

	public static void main(String[] args) {
		ParenthesisVerifier verifier = new ParenthesisVerifier();
		System.out.println(verifier.verifyParenthesis("()"));
		System.out.println(verifier.verifyParenthesis("((())())()"));
		System.out.println(verifier.verifyParenthesis("()(()"));
		System.out.println(verifier.verifyParenthesis("{((())())()()()()}[({})]"));
	}
}
