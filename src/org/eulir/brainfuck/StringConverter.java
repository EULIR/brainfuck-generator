package org.eulir.brainfuck;

import java.util.stream.IntStream;

/**
 * This class intends to convert Strings into brainfuck codes that can print out those Strings.
 */
public class StringConverter {
	private String origin;

	private int[] array = new int[100];
	private int pointer = 0;

	public StringConverter(String str) {
		origin = str;
	}

	public StringBuilder generate() {
		StringBuilder sb = new StringBuilder();
		boolean[] blanket = new boolean[13];
		int[] direct = new int[13];
		char[] chars = origin.toCharArray();
		IntStream.range(0, chars.length).forEach(i -> {
			char aChar = chars[i];
			blanket[aChar / 10] = true;
		});
		sb.append("++++++++++");
		array[0] = 10;
		sb.append("[");
		IntStream.range(0, blanket.length).filter(i -> blanket[i]).forEach(i -> {
			sb.append(">");
			pointer++;
			direct[i] = pointer;
			IntStream.range(0, i).mapToObj(j -> "+").forEach(sb::append);
			array[pointer] = i;
		});
		IntStream.rangeClosed(1, pointer).forEach(i -> array[i] *= 10);
		IntStream.range(0, pointer).mapToObj(i -> "<").forEach(sb::append);
		sb.append("-]");
		pointer = 0;
		array[0] = 0;
		IntStream.range(0, chars.length).forEach(i -> {
			char aChar = chars[i];
			int targetIndex = direct[aChar / 10];
			if (targetIndex > pointer) {
				IntStream.range(0, targetIndex - pointer).mapToObj(j -> ">").forEach(sb::append);
				pointer = targetIndex;
			} else if (targetIndex < pointer) {
				IntStream.range(0, pointer - targetIndex).mapToObj(j -> "<").forEach(sb::append);
				pointer = targetIndex;
			}
			int currentNumber = array[pointer];
			if ((int) aChar > currentNumber) {
				IntStream.range(0, (int) aChar - currentNumber).forEach(j -> {
					sb.append("+");
					array[pointer] = (int) aChar;
				});
			} else if ((int) aChar < currentNumber) {
				IntStream.range(0, currentNumber - (int) aChar).forEach(j -> {
					sb.append("-");
					array[pointer] = (int) aChar;
				});
			}
			sb.append(".");
		});
		return sb;
	}
}
