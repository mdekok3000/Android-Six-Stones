package com.syrus.kane.sixstones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Puller {

	private static final String[] colors 	= { "white", "blue", "black", "red", "green", "clear" };
	
	public static void main(String[] args) 
	{
		if(args.length > 0 && args[0].matches("[1-6]"))
		{
			int numStones = Integer.parseInt(args[0]);
			List<String> pulls = getPulls(numStones);
			System.out.println(pulls);
		}
		else
			pull();
	}
	
	public static List<String> getPulls(int numStones) 
	{
		List<String> pullList = new ArrayList<String>(numStones);
		
		if(numStones > 0)
		{
			String[] picked = pickNRandom(colors, numStones);
			pullList = Arrays.asList(picked);
		}
		
		return pullList;
	}
	
	public static void pull(Boolean repeat) 
	{
		String[] picked = { };
		
		int numStones = getNumStones();
		
		if(numStones > 0)
		{
			System.out.println("\nPulling " + numStones + " stone" + (numStones == 1 ? "" : "s") + "...");
			
			picked = pickNRandom(colors, numStones);
			for (int i = 0; i < picked.length; i++) {
				String color = picked[i];
				
				System.out.println(color);
			}
			
			if(repeat == true)
			{
				System.out.println("");
				pull(repeat);
			}
			else
				return;
		}
		
		return;
	}
	
	public static void pull() 
	{
		pull(true);
	}
	
	private static int getNumStones()
	{
		String input = getInput("How many stones are you pulling?\n");
		
		if(input.matches("[1-6]"))
			return Integer.parseInt(input);
		else
			return 0;
	}

	private static String getInput(String prompt) {
		BufferedReader stdin = new BufferedReader(
				new InputStreamReader(System.in));

		System.out.print(prompt);
		System.out.flush();
		
		try {
			return stdin.readLine();
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}
	
	public static String[] pickNRandom(String[] array, int n) {
	    List<String> list = new ArrayList<String>(array.length);
	    for (String s : array)
	    {
	        list.add(s);
	    }
	    
	    Collections.shuffle(list);

	    String[] answer = new String[n];
	    for (int i = 0; i < n; i++)
	        answer[i] = list.get(i);

	    return answer;
	}
}
