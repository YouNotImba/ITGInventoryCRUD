package main.util;

import java.util.ArrayList;
import java.util.List;

import main.beans.Printer;

public class SearchUtil {

	public static List<Printer> searchPrinter(String searchString, List<Printer> source) {

		// if(searchString.contains("+"))
		String[] searchParts = searchString.split(",");
		// else searchParts[0] = searchString;

		List<Printer> tempList = new ArrayList<>();
		tempList = source;
		for (String string : searchParts) {

			List<Printer> result = new ArrayList<>();
			for (Printer printer : tempList) {
				if (printer.getTitle().toLowerCase().contains(string.toLowerCase())
						|| printer.getIp().toLowerCase().contains(string.toLowerCase())
						|| printer.getMac().toLowerCase().contains(string.toLowerCase())
						|| printer.getLocation().toLowerCase().contains(string.toLowerCase())
						|| printer.getModel().getName().toLowerCase().contains(string.toLowerCase())
						|| printer.getCompany().getName().toLowerCase().contains(string.toLowerCase())
						|| printer.getModel().getCartridge().getName().toLowerCase().contains(string.toLowerCase())) {

					result.add(printer);

				}
			}
			if (result.isEmpty())
				return result;
			tempList = result;

		}
		return tempList;
	}

	/*
	 * public static void main(String[] args) { List<Printer> allPrinters = new
	 * PrinterController().getAllPrinters(); List<Printer> result =
	 * searchPrinter("p170", allPrinters); System.out.println(result); }
	 */
}
