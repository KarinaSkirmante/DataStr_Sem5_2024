package service;

import java.util.ArrayList;

import datastr.MyGraph;

public class MainService {

	public static void main(String[] args) {
		MyGraph<String> map = new MyGraph<String>();
		try {
			map.addVertice("Ventspils");
			map.addVertice("Rīga");
			map.addVertice("Kuldīga");
			map.addVertice("Talsi");
			map.addVertice("Tukums");
			
			map.addEdge("Ventspils", "Rīga", 189);
			map.addEdge("Rīga","Ventspils", 189);
			map.addEdge("Ventspils", "Kuldīga", 56.49f);
			map.addEdge("Ventspils", "Talsi", 64);
			map.addEdge("Tukums", "Rīga", 58);
			map.addEdge("Rīga", "Kuldīga", 148.88f);
			map.print();
			
			System.out.println("--------------DEPTH----------------");
			ArrayList<String> path1 = map.searchPathByDepth("Ventspils", "Kuldīga");
			for(String tempS: path1)
			{
				System.out.print(tempS + " -> ");
			}
			System.out.println();
			
			ArrayList<String> path2 = map.searchPathByDepth("Talsi", "Tukums");
			for(String tempS: path2)
			{
				System.out.print(tempS + " -> ");
			}
			System.out.println();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		//TODO uztaisīt piemēru no moodle sgrafu sadaļā (Minimālās pārklāšanas koks (Minimum Spanning Tree))
		try {
			System.out.println("------------MINIMUM SPANNING TREE-----------");
			map.minimumSpanningTree("Ventspils");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
