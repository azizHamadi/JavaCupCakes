/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Produit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author escobar
 */
public class PanierService {
        private Map<Produit, Integer> panier;
 
	public PanierService() {
		this.panier = new HashMap<Produit, Integer>();
	}

	public void ajouterArticle(Produit art, int qte) {
		Integer quantity = panier.get(art);
		if (quantity == null){
			quantity = Integer.valueOf(0);
 
		}
		quantity = Integer.valueOf(quantity.intValue()+qte);
		panier.put(art, quantity);
	}
 
	public void supprimerArticle(String article){
		 panier.remove(article);
	}	
 
	public int calculerPanier() {
		int total = 0;
		Iterator<Map.Entry<Produit, Integer>> il = this.panier.entrySet().iterator();
		while (il.hasNext()) {
                        Map.Entry<Produit, Integer> entry = il.next();
			total += entry.getKey().getPrixProd() * entry.getValue();
		}
		return total;
	}
        
        public Map<Produit, Integer> Afficher(){
            return panier;
            
        }
}
