import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import java.lang.String;
import java.lang.Integer;
import java.util.ArrayList;
import java.lang.System;


public class WordNet {

	private Digraph wordNet;
	private SeparateChainingHashST<String, Integer> wordTable;

	// constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms) {
		   //create graph from hypernms
		   ArrayList<String[]> listHyps = new ArrayList<String[]>();
		   In hypernmStream = new In(hypernyms);

		   String hypline = hypernmStream.readLine();
		   while (hypline != null) {
				   String[] lineSplit = hypline.split(",");
				   listHyps.add(lineSplit);
				   hypline = hypernmStream.readLine();
		   }

		   wordNet = new Digraph(listHyps.size());
		   for (String[] line: listHyps) {
				   int i = 1;
				   int v = Integer.parseInt(line[0]);
				   while (i < line.length) {
						   wordNet.addEdge(v, Integer.parseInt(line[i]));
						   i++;
									   }
		   }

			//create symbol table of words
		   In synsetStream = new In(synsets);
		   wordTable = new SeparateChainingHashST<String, Integer>(listHyps.size());
		   String line = synsetStream.readLine();
		   while (line != null) {
				   String[] lineSplit = line.split(",");
				   String[] syns = lineSplit[1].split(" ");
				   for (String word : syns) {
						   wordTable.put(word, Integer.parseInt(lineSplit[0]));
				   }
				   line = synsetStream.readLine();
		   }
   }

   // returns all WordNet nouns
   public Iterable<String> nouns() {
		   return wordTable.keys();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word) {
		if (wordTable.contains(word)) {
				return true;
		};
		return false;
   }
   
/**
   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB) {

   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB) {
   }

*/
   
   // do unit testing of this class
   public static void main(String[] args) {
		String synsets = args[0];
		String hypernyms = args[1];
		WordNet net = new WordNet(synsets, hypernyms);
		for (String noun : net.nouns()) {
				System.out.println(noun);
		}
		System.out.println(net.isNoun("fdhhdf"));
		System.out.println(net.isNoun("thing"));
		
   }

}
