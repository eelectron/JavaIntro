package bst;

public class BST23Tree {
	private Node root;
	
	//Node-contains link atmost to 3 other node
	private class Node{
		private Key k1,k2;
		private Value v1,v2;
		private Node left,middle,right;
		public Node(Key k, Value v){
			k1=k; v1=v;
		}
		
		//Looks of this object
		public String toString(){
			String objectState = null;
			if(k1 != null)
				objectState += k1.toString()+":"+v1.toString();
			if(k2 != null)
				objectState += k2.toString()+":"+v2.toString();
			return objectState;
		}
	}
	
	//Inserts a key
	public void put(Key k, Value v){
		if (root == null) {
			root=new Node(k,v);
		}else if(root.k2 == null  ){
			if((k.compareTo(root.k1) > 0)){
				root.k2=k;
				root.v2 = v;
			}else{
				//Copy k1,v1 in second k2,v2 and set new k,v in k1,v1
				root.k2 = root.k1;
				root.v2=root.v1;
				
				root.k1=k;
				root.v1=v;
			}
		}else{
			int cmp1 = k.compareTo(root.k1);
			int cmp2 = k.compareTo(root.k2);
			if(cmp1 > 0 && cmp2<0){
				Node x=new Node(k,v);
				x.left = new Node(root.k1, root.v1);
				x.right=new Node(root.k2, root.v2);
				
				//attach x to node 
				root=x;
			}
		}
	}
	
	//SIMPLE Recursive code for get
	public Node get(Key key){
		return get(key, root);
	}
	
	private Node get(Key key, Node x){
		int cmp;
		if(x == null)
			return null;
		if(x.k1 != null){
			//compare with k1
			cmp=key.compareTo(x.k1);
			if(cmp == 0)return x;
			if(cmp < 0) return get(key, x.left);
		}
		if(x.k2 != null){
			cmp=key.compareTo(x.k2);
			if(cmp == 0)return x;
			if(cmp > 0) return get(key, x.right);
		}
		return get(key, x.middle);
	}
}