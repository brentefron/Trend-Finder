import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
/*
 *
 *Purpose: Generates important documents in 
 *
 */


public class Carsarray {
	public static void main (String[] args) throws FileNotFoundException
	{
		LinkedHashSet<String> Makes = Makes();
		ArrayList<ArrayList<String>> Models = Models();


		PrintWriter pw = new PrintWriter(new FileOutputStream("Makes.txt"));
		for (String str : Makes)
			pw.println(str);
		pw.close();
		System.out.println("Not Done!");

		PrintWriter PW = new PrintWriter(new FileOutputStream("Models.txt"));
		for (ArrayList<String> stri : Models)
			PW.println(stri.toString().substring(1,stri.toString().length()-1));
		PW.close();
		System.out.println("Done!");




	}
	public static ArrayList<String[]> Cardata() throws FileNotFoundException
	{


		Scanner s;

		s = new Scanner(new File("/Users/Brent/Desktop/workspace/Trend Finder/ImportantDocs/Make and Model.txt"));

		ArrayList<String[]> cars= new ArrayList<String[]>();
		while (s.hasNext()){
			{
				cars.add(s.nextLine().split("#"));
			}
		}
		s.close();

		return cars;	
	}
	public static LinkedHashSet<String> Makes() throws FileNotFoundException

	{

		LinkedHashSet<String> data = new LinkedHashSet<String>();

		for (int i=0; i<Cardata().size(); i++)
		{
			data.add(Cardata().get(i)[0]);
		}

		return data;

	}
	
	public static ArrayList<ArrayList<String>> Models() throws FileNotFoundException{
		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
		int j = 0;
		data.add(new ArrayList<String>());
		for (int i=0; i<Cardata().size(); i++)
		{
			LinkedHashSet<String> Makes = Makes();
			String[] makes = Makes.toArray(new String[Makes.size()]);

			System.out.println(Cardata().get(i)[0]);
			System.out.println(makes[j]);
			if (Cardata().get(i)[0].equals(makes[j]))
			{
				data.get(j).add(Cardata().get(i)[1]);

			}
			else{
				data.add(new ArrayList<String>());
				j++;
				data.get(j).add(Cardata().get(i)[1]);
			}
		}
		return data;
	}
}

