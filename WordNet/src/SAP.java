import edu.princeton.cs.algs4.Digraph;
import java.lang.System;
import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
public class SAP {

	private Digraph net;
	private boolean[] markedV;
	private boolean[] markedW;

   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G) {
		net = G;
		int v = net.V();
		markedV = new boolean[v];
		markedW = new boolean[v];
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w) {
			DeluxeBFS d = new DeluxeBFS(net, v, w, markedV, markedW);
			ArrayList<ArrayList<Integer>> marked = d.getMarked();
			for (Integer x: marked.get(0)) {
				markedV[x] = false;
			}
			for (Integer x: marked.get(1)) {
				markedW[x] = false;
			}
			return d.getDistance();
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w) {
			DeluxeBFS d = new DeluxeBFS(net, v, w, markedV, markedW);
			ArrayList<ArrayList<Integer>> marked = d.getMarked();
			for (Integer x: marked.get(0)) {
				markedV[x] = false;
			}
			for (Integer x: marked.get(1)) {
				markedW[x] = false;
			}
			return d.getAncestor();
			
   }
/**
   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w) {
		
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {

   }
*/
   // do unit testing of this class
   public static void main(String[] args) {
		   In digraph = new In(args[0]);
		   Digraph g = new Digraph(digraph);
		   SAP test = new SAP(g);
		   System.out.println(test.ancestor(7,7));
		   System.out.println(test.length(7,0));
		   System.out.println(test.length(12, 0));
		   System.out.println(test.length(16, 11));
		   System.out.println(test.length(11, 7));
   }
}
