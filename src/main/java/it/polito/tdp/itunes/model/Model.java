package it.polito.tdp.itunes.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.itunes.db.ItunesDAO;

public class Model {
	
	private Graph<Track, DefaultWeightedEdge> grafo; 
	private ItunesDAO dao; 
	private List<Track> vertici; 
	private Map<Integer, Track> mappaVertici;
	private List<Arco> archi; 
	
	public Model() {
		
		this.dao = new ItunesDAO();
	} 
	
	public List<Genre> getGeneri(){
		return this.dao.getAllGenres(); 
	}
	
	public void creaGrafo(Genre g) {
		
		this.grafo = new SimpleWeightedGraph<Track, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.vertici = this.dao.getAllTracks(g);
		
		this.mappaVertici = new HashMap<>(); 
		for (Track t: vertici ) {
			mappaVertici.put(t.getTrackId(), t); 
		}
		
		Graphs.addAllVertices(this.grafo, this.vertici); 
		
		this.archi = this.dao.getArchi(g, mappaVertici);
		
		for(Arco a: archi) {
			Graphs.addEdgeWithVertices(this.grafo, a.getT1(), a.getT2(), a.getPeso()); 
		}
		
		System.out.println(this.grafo.vertexSet().size()); 
		System.out.println(this.grafo.edgeSet().size()); 

	}
	
	public List<Arco> getArchiDeltaMax(){
		
		List<Arco> lista = new LinkedList<>(this.archi); 
		int pesoMax =0; 
		List<Arco> result = new LinkedList<>(); 
		for (Arco a: lista) {
			if(a.getPeso() > pesoMax) {
				pesoMax = a.getPeso(); 
			}
		}
		
		for(Arco a: lista) {
			if(a.getPeso() == pesoMax) {
				result.add(a); 
			}
		}		
		
		return result; 
	}
	
	public int getNVertici() {
		return this.grafo.vertexSet().size(); 
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size(); 
	} 
	
	
}
