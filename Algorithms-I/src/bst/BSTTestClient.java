package bst;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class BSTTestClient {

	public static void main(String[] args) throws FileNotFoundException {
		BST23Tree bst=new BST23Tree();
		Scanner sc=new Scanner(System.in);
		Key key;
		Value v;
		
		System.out.println("Enter a integer and a username:");
		while(true){
			System.out.println(	"1.Put\n"+
							   	"2.DeleteMin\n"+
								"3.DeleteMax\n"+
							   	"4.Min\n"+
								"5.Max\n"+
							   	"6.Get\n"+
								"7.Exit\n");
			int choice=sc.nextInt();
			if(choice==7)break;
			
			switch(choice){
			case 1:System.out.print("Enter a number and name:");
				   bst.put(new Key(sc.nextLong()),new  Value(sc.next()));
				   break;
			case 2:System.out.println("Deleting min..");break;
//			case 4:System.out.println("Min:"+bst.min());break;
			case 6:System.out.println(bst.get(new Key(sc.nextLong())));break;
			default :System.out.println(".........");
			}
		}
	}
}
