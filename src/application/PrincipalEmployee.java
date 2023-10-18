package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entities.Employee;

public class PrincipalEmployee {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter File path: ");
		String path = sc.next();
		// to read a .csv file Ex.: C:\Users\User\Desktop\in.csv -- File model.: Marco,marco@gmail.com,1400.00

		System.out.print("Enter Salary: ");
		double targetSalary = sc.nextDouble();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			List<Employee> employees = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {

				String[] fields = line.split(",");
				String name = fields[0];
				String email = fields[1];
				double salary = Double.parseDouble(fields[2]);

				Employee objEmployee = new Employee(name, email, salary);
				employees.add(objEmployee);

				line = br.readLine();

			}
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

			List<String> names = employees.stream().filter(e -> e.getSalary() > targetSalary).map(e -> e.getEmail()).sorted(comp).collect(Collectors.toList());

			System.out.println(
					"==========================================================================================");
			System.out.println("Email of people whose salary is biger than " + targetSalary + ":");
			names.forEach(System.out::println);

			System.out.println(
					"==========================================================================================");
			System.out.print("Enter a Letter: ");
			String nameChar = sc.next().toUpperCase();

			System.out.println("Sum of salary of people whose name start wiyh " + nameChar.charAt(0));
			Stream<Double> peopleCharList = employees.stream().filter(x -> x.getName().charAt(0) == nameChar.charAt(0))
					.map(x -> x.getSalary());
			System.out.println("Sum = " + peopleCharList.reduce(0.0, (x, y) -> x + y));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}

}
