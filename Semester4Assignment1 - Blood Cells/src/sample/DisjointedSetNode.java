package sample;

class DisjointedSetNode {
     public static int find(int[] a, int id) {
         if(a[id]==-1) return -1;
        while(a[id]!=id) {
            a[id]=a[a[id]]; //Compression path
            id=a[id];
        }
        return id;
    }
  public static void union(int[] a, int p, int q) {
      a[find(a,q)] = find(a,p);
    }
}