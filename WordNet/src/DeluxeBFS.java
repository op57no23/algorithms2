import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Digraph;

public class DeluxeBFS {

		private int ancestor;
		private int distance;
		private ArrayList<Integer> marked1;
		private ArrayList<Integer> marked2;

		public DeluxeBFS(Digraph G, int v, int w, boolean[] markedV, boolean[] markedW) {
			boolean foundancestor = false;
				if (v == w) {
					ancestor = v;
					distance = 0;
					foundancestor = true;
				}
				marked1 = new ArrayList<Integer>();	
				marked2 = new ArrayList<Integer>();
				Queue<Integer> qV = new Queue<Integer>();
				Queue<Integer> qW = new Queue<Integer>();
				int vCount = 0;
				int wCount = 0;
				marked1.add(v);
				marked1.add(w);
				marked2.add(w);
				marked2.add(v);
				markedV[v] = true;
				markedW[v] = true;
				markedV[w] = true;
				markedW[w] = true;
				qV.enqueue(v);
				vCount = 1;
				qW.enqueue(w);
				wCount = 1;
				int currentDistanceV = 0;
				int currentDistanceW = 0;
				while ((!qV.isEmpty() || !qW.isEmpty()) & !foundancestor) {
						int i = vCount;
						while (i > 0) {
								int s = qV.dequeue();
								vCount--;
								i--;
								for (int x : G.adj(s)) {
										if (markedW[x]) {
												foundancestor = true; 
												ancestor = x;
										}
										if (!markedV[x]) {
												qV.enqueue(x);
												vCount++;
												markedV[x] = true;
												marked1.add(x);
										}
								}
						}
						if (!qV.isEmpty()) currentDistanceV += 1;
						int j = wCount;
						while (j > 0 & !foundancestor) {
								int s = qW.dequeue();
								wCount--;
								j--;
								for (int x : G.adj(s)) {
										if (markedV[x]) {
												foundancestor = true; 
												ancestor = x;
										}
										if (!markedW[x]) {
												qW.enqueue(x);
												wCount++;
												markedW[x] = true;
												marked2.add(x);
										}
								}
						}
				}
		}

		public int getAncestor() {
				return ancestor;
		}

		public int getDistance() {
				return distance;
		}

		public ArrayList<ArrayList<Integer>> getMarked() {
				ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
				a.add(marked1);
				a.add(marked2);
				return a;
		}
}
