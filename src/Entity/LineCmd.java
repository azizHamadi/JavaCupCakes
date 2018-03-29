/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;


/**
 *
 * @author escobar
 */
public class LineCmd {

    
    private Integer qteAcheter;
    private String etatLineCmd;
    private Produit produit;
    private Commande commande;

    public LineCmd() {
    }

    public LineCmd(Integer qteAcheter, String etatLineCmd) {
        this.qteAcheter = qteAcheter;
        this.etatLineCmd = etatLineCmd;
    }

    public LineCmd(Integer qteAcheter, Produit produit) {
        this.qteAcheter = qteAcheter;
        this.produit = produit;
    }

    
    

    public Integer getQteAcheter() {
        return qteAcheter;
    }

    public void setQteAcheter(Integer qteAcheter) {
        this.qteAcheter = qteAcheter;
    }

    public String getEtatLineCmd() {
        return etatLineCmd;
    }

    public void setEtatLineCmd(String etatLineCmd) {
        this.etatLineCmd = etatLineCmd;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.commande);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineCmd other = (LineCmd) obj;
        if (!Objects.equals(this.commande, other.commande)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LineCmd{" + "qteAcheter=" + qteAcheter + ", produit=" + produit + '}';
    }

   
    
}
