import java.util.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SimpleMusicMachine6 {
	ArrayList nodes = new ArrayList();

    public SimpleMusicMachine6() {
    	SimpleGUI gui = new SimpleGUI();
    	Player.init();
		samplePlay();
    	parse("2 3 5 7 1 1 1 3 1 7 1 9 2 3 2 9 3 1 3 7 4 1 4 3 4 7 5 3 5 9 6 1 6 7 7 1 7 3 7 9 8 3 8 9 9 7 1 0 1 1 0 3 1 0 7 1 0 9 1 1 3 1 2 7 1 3 1 1 3 7 1 3 9 1 4 9 1 5 1");
    	parse("2 7 1 8 2 8 1 8 2 8 4 5 9 0 4 5 2 3 5 3 6");
    	parse("3 1 4 1 5 9 2 6 5 3 5 8 9 7 9 3 2 3 8 4 6 2 6 4 3 3 8 3 2 7 9 5 0 2 8 8 4 1 9 7 1 6 9 3 9 9 3 7 5 1 0 5 8 2 0 9 7 4 9 4 4 5 9 2 3 0 7 8 1 6 4 0 6 2 8 6 2 0 8 9 9 8 6 2 8 0 3 4 8 2 5 3 4 2 1 1 7 0 6 7 9");
    	parse("2 1 2 3 3 2 3 4 3 4 3 3 3 2 2 2");
	   	parse("4 4 5 6 5 6 5 5 6 5 4 4 3 4 3 3");
	   	parse("2 2 3 4 3 4 3 3 4 3 2 2 1 2 1 1");
	   	parse("0 1 1 2 3 5 8 1 3 2 1 3 4 5 5 8 9 1 4 4 2 3 3 3 7 7 6 1 0 9 8 7 1 5 9 7 2 5 8 4 4 1 8 1 6 7 6 5");
 //		play("3 2 3 2 3 2 4 5 6 5 4 3 1 2 1 2 1 2 4 5 6 5 4 ");
/*
Silent Night: 
G A G E
G A G E
D D B
C C G
A A C B A G A G E
D D F D B C E
C G E G F D C
*/
		//new PlayList("6165616544233611321616544642353656643");
    	
    	System.out.println("Infinite Play");
    	
    	//parse("0 1");
		infinitePlay();
    }
    
    public void samplePlay(){
    	System.out.println("Sound Test");
    	play("0123456789876543210");
    	System.out.println("Song 1");
    	play("2123323434333222");
    	System.out.println("Song 2");
    	play("4456565565443433");
    	System.out.println("Beta Features: Song 3");
    	playFile("Breath.wav");

    	
    	System.out.println("E");
    	play("271828182845904523536");
    	    	
    	System.out.println("Pi");
    	play("31415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679");    	

    	
    	System.out.println("Perfect");
    	play("6284968128");
    	
    	
    	System.out.println("Primes");
    	play("2357111317192329313741434753596167717379838997101103107109113127131137139149151");
    	
    	System.out.println("Fibonacci");
    	play("011235813213455891442333776109871597258441816765");	
	}
    
    public void play(String str){
    	for(int k = 0; k < str.length(); k++){
    		if(str.charAt(k) != ' '){
	    		Player plyr = new Player(""+str.charAt(k));
	    		try{		Thread.sleep(500);	    		}
	    		catch(Exception e){			System.out.println(e); 		}
    		}
    	}
    }
    
    public static void playFile(String filename){
    	try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			SourceDataLine auline = auline = (SourceDataLine) AudioSystem.getLine(info);
	 		auline.open(format);
	 
			auline.start();
			int nBytesRead = 0;
			byte[] abData = new byte[524288];
	 
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
			auline.drain();
			auline.close();
    	}
    	catch(Exception e){		System.out.println(e);    	}
 	
    }
    
    public void infinitePlay(){
    	Node n = randomNode();
    	while(true){
    		System.out.print(n);
    		n.play();
    		n = n.getNext();    		
    		while(n == null){	n = randomNode();		}
    //		try{	Thread.sleep(1);		}
    //		catch(Exception e){	System.out.println(e);	}
    	}
    }
    
    public Node randomNode(){
    	Random rand = new Random();
		return (Node) nodes.get(rand.nextInt(nodes.size()));
    }
    
    public void parse(String str){
    	StringTokenizer st = new StringTokenizer(str);
    	ArrayList tempContainer = new ArrayList();
    	System.out.println("Parsing "+str);
    	while(st.hasMoreTokens()){
    		tempContainer.add(st.nextToken());
	   	}
	   	
	   	System.out.println("Entering second level parsing for "+tempContainer);
	   	
	   	String s = "";
	   	int level = 1;
	   	
	   	Node previous = null;
	   	while(level < 12){
		   	for(int k = 0; k < tempContainer.size(); k++){
		   		s += (String) tempContainer.get(k);
		   		if( (k % level) == 0){
		   			Node n = newNode(s);
		   			System.out.println("Adding "+s);
		   			s = "";
			    	if(previous != null){
	  					previous.addNext(n);
    				}
    				
    				previous = n;
		   		}
		   	}
		   	level++;
	   	}
    }
    
    public void spawn(Node n, int turns){
    	System.out.println("Spawning from "+n);
    	
    	for(int k = 0; k < turns; k++){
    		System.out.print(n);
    		n = n.getNext();
    	}
    	
    }
    
    public Node newNode(String str){
    	Node n = new Node(str);
    	for(int k = 0; k < nodes.size(); k++){
    		if(((Node)nodes.get(k)).compareTo(n) == 0){
    			n = (Node) nodes.get(k);
    			return n;
    		}
    	}
    	nodes.add(n);
    	return n;
    }
    
    public static void main(String[] args) {
        System.out.println("Simple Music Machine: Prototype Test 4");
        new SimpleMusicMachine6();
    }
}

class Node implements Comparable{
	ArrayList next = new ArrayList();
	String str = "";

	public Node(String s){
		str = s;
	}
	
	public void addNext(Node n){
		next.add(n);
	}
    private String filename;	
	
	public void play(){
		for(int k = 0; k < str.length(); k++){
			char c = str.charAt(k);
			String filename = ""+c;
			Player p = new Player(filename);
			try{
				Thread.sleep(500);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
	
	public Node getNext(){
		if(next.size() > 0){
			Random rand = new Random();
			return (Node) next.get(rand.nextInt(next.size()));
		}
		return null;
	}
	
	public int compareTo(Object obj){
		Node n = (Node) obj;
		return str.compareTo(n.str);
	}
	
	public String toString(){
		return " "+str+" ";
	}
}

class PlayList implements Runnable{
	String str = "";
	public PlayList(String s){
		str = s;
		new Thread(this).start();
	}
	public void run(){
		for(int k = 0; k < str.length(); k++){
			Player p = new Player(""+str.charAt(k));
			try{
				Thread.sleep(500);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
	}
}

class Player implements Runnable{
	String filename = "";
	
	static TreeMap<String, String> map = new TreeMap<String, String>();
	
	public static void init(){
		map.put("0","none.wav");
		map.put("1","piano_fifth_A_E.wav");
		map.put("2","piano_fourth_A_D.wav");
		map.put("3","piano_Major_Sixth_A_F_sharp.wav");
		map.put("4","piano_Major_Third_A_C_sharp.wav");
		map.put("5","piano_octave_A_A.wav");
		map.put("6","piano_twelfth_A_E.wav");
		map.put("7","piano_octave_A_A.wav");
		map.put("8","piano_Major_Third_A_C_sharp.wav");
		map.put("9","piano_Major_Sixth_A_F_sharp.wav");
	}
	
	public Player(String f){
		filename = (String) map.get(f);
		new Thread(this).start();
	}
	public void run(){
		try{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
	 		
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			SourceDataLine auline = auline = (SourceDataLine) AudioSystem.getLine(info);
	 		auline.open(format);
	 
			auline.start();
			int nBytesRead = 0;
			byte[] abData = new byte[524288];
	 
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
			auline.drain();
			auline.close();
	
		}
		catch(Exception e){
			System.out.println(e);
		}		
	}
}