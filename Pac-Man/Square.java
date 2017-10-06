
public class Square {
	private int x;
	private int y;
	private double g=0;
	private int h;
	private double f;
	private int parent[] = new int[100];
	
	public Square(){}
	
	public Square(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void calculateh(int G1x, int G1y){
		this.h=	Math.abs(G1x-x)+Math.abs(G1y-y);
	}
	public void calculatef(){
		this.f=this.g+this.h;
	}
	public void setg(double x){
		this.g=x;
	}
	public double getg(){
		return g;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	public double getf(){
		return f;
	}
	public int geth(){
		return h;
	}
	public int[] getParent(){
		return this.parent;
	}
	public void setParent(int x,int y){
		this.parent[0]=x;
		this.parent[1]=y;
	}
}
