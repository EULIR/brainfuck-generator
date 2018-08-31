package org.eulir.test;

import org.eulir.brainfuck.StringConverter;

public class Main {
	public static void main(String[] args) {
		//++++++++++[>++++++>+++++++>++++++++<<<-]>+++++++++.>>+++++.<++++++.---.>---.
		System.out.println(new StringConverter("EULIR").generate().toString());
		//++++++++++[>+++>+++++++>++++++++>++++++++++>+++++++++++<<<<<-]>>++.>>+.+++++++..
		//>+.<<<<++.>>+++++++.>>.+++.<.--------.<<<+.
		System.out.println(new StringConverter("Hello World!").generate().toString());
	}
}
