package it.polito.tdp.itunes.model;

public class Arco {
	
	private Track t1; 
	private Track t2; 
	private int mediaType; 
	private int peso;
	
	public Arco(Track t1, Track t2, int mediaType, int peso) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.mediaType = mediaType;
		this.peso = peso;
	}

	public Track getT1() {
		return t1;
	}

	public Track getT2() {
		return t2;
	}

	public int getMediaType() {
		return mediaType;
	}

	public int getPeso() {
		return peso;
	}

	@Override
	public String toString() {
		return  t1.getName() + " *** " + t2.getName() + " --> " + peso ;
	} 
	
	
	
	

}
