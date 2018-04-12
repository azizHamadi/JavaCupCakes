/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Produit;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author escobar
 */
public class PanierService {
     public static PanierService Panier;

        private Map<Produit, Integer> panier;
 
         public static PanierService getInstance()
    {
        if(Panier == null)
        {
            Panier=new PanierService();
        }
        return Panier;
        
    }
        
	public PanierService() {
		this.panier = new HashMap<Produit, Integer>();
	}

	public void ajouterArticle(Produit art, int qte) {
		Integer quantity = panier.get(art);
		if (quantity == null){
			quantity = 0;
 
		}
		quantity = Integer.valueOf(quantity.intValue()+qte);
		panier.put(art, quantity);
	}
 
	public void supprimerArticle(Produit article){
		 panier.remove(article);
	}	
 
	public int calculerPanier() throws SQLException {
             LinePromoService line = new LinePromoService();
                     List<Integer> l =line.afficherProduit();
		int total = 0;
		Iterator<Map.Entry<Produit, Integer>> il = this.panier.entrySet().iterator();
		while (il.hasNext()) {
                        Map.Entry<Produit, Integer> entry = il.next();
                        if(l.contains(entry.getKey().getIdProd())){
                            total += entry.getKey().getNvPrix() * entry.getValue();                        
                        }
                        else{
                            total += entry.getKey().getPrixProd() * entry.getValue();
                        }
		}
		return total;
	}
        
        public Map<Produit, Integer> Afficher(){
            return panier;
            
        }
        public void closePanier(){
        panier.clear();
        
    }
}
