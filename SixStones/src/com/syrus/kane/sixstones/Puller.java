package com.syrus.kane.sixstones;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Puller {

	public static final String[] colors1 	= { "white", "blue", "black", "red", "green", "clear" };
	public static final String[] colors2 	= { "white", "blue", "black", "red", "green", "canary" };
	
	public static final String[] rbWhite	= { "black", "green", "blue", "red", "clear", "white" };
	public static final String[] rbBlue		= { "red", "clear", "white", "green", "black", "blue" };
	public static final String[] rbBlack	= { "white", "red", "green", "clear", "blue", "black" };
	public static final String[] rbRed		= { "blue", "black", "clear", "white", "green", "red" };
	public static final String[] rbGreen	= { "clear", "white", "black", "blue", "red", "green" };
	public static final String[] rbClear	= { "green", "blue", "red", "black", "white", "clear" };
	
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
			String[] picked = pickNRandom(colors1, numStones);
			pullList = Arrays.asList(picked);
			System.out.println(pullList.toString());
		}
		
		return pullList;
	}
	
	public static int getRandomBonus(List<String> pulls, String neutral)
	{
		int randomBonus = 0;
		
		for(int i = 0; i < pulls.size(); i++)
		{
			String pull = pulls.get(i);
			if(pull.equals("white"))
				randomBonus = Math.max(randomBonus, Arrays.asList(rbWhite).indexOf(neutral));
			else if(pull.equals("blue"))
				randomBonus = Math.max(randomBonus, Arrays.asList(rbBlue).indexOf(neutral));
			else if(pull.equals("black"))
				randomBonus = Math.max(randomBonus, Arrays.asList(rbBlack).indexOf(neutral));
			else if(pull.equals("red"))
				randomBonus = Math.max(randomBonus, Arrays.asList(rbRed).indexOf(neutral));
			else if(pull.equals("green"))
				randomBonus = Math.max(randomBonus, Arrays.asList(rbGreen).indexOf(neutral));
			else if(pull.equals("clear"))
				randomBonus = Math.max(randomBonus, Arrays.asList(rbClear).indexOf(neutral));
		}
		
		return randomBonus;
	}
	
	public static List<String> getPulls(int numStones, String[] colors) 
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
			
			picked = pickNRandom(colors1, numStones);
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
