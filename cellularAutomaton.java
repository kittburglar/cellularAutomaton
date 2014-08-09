import java.io.*;
import java.net.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.*;
import java.nio.*;
import java.lang.Math.*;

public class cellularAutomaton {

	ArrayList<Integer> firstArray = new ArrayList<Integer>();
	ArrayList<Integer> secondArray = new ArrayList<Integer>();
	int numberofCells;
	int numberofGens;
	//Constructor
	cellularAutomaton(String numberofCells2, String numberofGens2){
		try{
			numberofCells = Integer.parseInt(numberofCells2);
			numberofGens = Integer.parseInt(numberofGens2);

		}catch(Exception e){
			System.exit(1);
		}

	}


	//Send inital packets 
	public void Init() throws Exception {
		try{
			Random rand = new Random();
			for(int i=0; i<numberofCells; i++){
				int random = rand.nextInt((1-0) + 1) + 0;		
				firstArray.add(random);
			}
		}catch(Exception e){
			System.err.println("Init error!");
			System.exit(1);
		}
	}

	public int ruleList(int left, int center, int right) throws Exception{
		if ((left == 0) && (center == 0) && (right == 0)){
			
			return 0;
		}
		else if ((left == 0) && (center == 0) && (right == 1)){
			
			return 1;
		}
		else if ((left == 0) && (center == 1) && (right == 0)){
			
			return 1;
		}
		else if ((left == 0) && (center == 1) && (right == 1)){
			
			return 0;
		}
		else if ((left == 1) && (center == 0) && (right == 0)){
			
			return 1;
		}
		else if ((left == 1) && (center == 0) && (right == 1)){
			
			return 0;
		}
		else if ((left == 1) && (center == 1) && (right == 0)){
			
			return 0;
		}
		else if ((left == 1) && (center == 1) && (right == 1)){
			
			return 0;
		}
		return 0;
	}

	public void nextGen() throws Exception {
		for(int i=0; i<numberofCells ; i++){
				int newCell;
				if (i == 0){
					newCell = ruleList(0, firstArray.get(i), firstArray.get(i+1));
				}
				else if(i == numberofCells-1){
					newCell = ruleList(firstArray.get(i-1), firstArray.get(i), 0);
				}
				else{
					newCell = ruleList(firstArray.get(i-1), firstArray.get(i), firstArray.get(i+1));
				}
				secondArray.add(newCell);
		}
		overWriteold();
	}

	public void overWriteold(){
		firstArray.clear();
		for(int i=0; i<numberofCells ; i++){
				firstArray.add(secondArray.get(i));
		}
		secondArray.clear();
	}

	//Receive circuit database
	public void printfirstArray() throws Exception {
		try{
			for(int i=0; i<numberofCells ; i++){
				System.out.print(firstArray.get(i));
			}
			System.out.print("\n");
		}catch(Exception e){
			System.err.println("failed to print first array");
			System.exit(1);
		}
	}

	public void printsecondArray() throws Exception {
		try{
			for(int i=0; i<numberofCells ; i++){
				System.out.print(secondArray.get(i));
			}
			System.out.print("\n");
		}catch(Exception e){
			System.err.println("failed to print second array");
			System.exit(1);
		}
	}




public static void main(String [ ] args) throws Exception {

	if( args.length != 2) {
		System.err.println("Usage: CA <number of cells> <number of generations>");
		System.exit(1);
	}
	cellularAutomaton r = new cellularAutomaton(args[0],args[1]);
	try{
		//System.out.println("Initial First Array...");
		r.Init();
		r.printfirstArray();
		//System.out.println("Initial First Array...");
		for(int i=0; i<r.numberofGens ; i++){		
			
			r.nextGen();
			r.printfirstArray();
		}	
	}catch(Exception e) {
		System.err.println("Whoops! Something unexepceted happened. Please try again! :)");
	}

}
}


