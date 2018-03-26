/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Collection;


/**
 *
 * @author escobar
 */
public class Produit {

    private Integer idProd;
    private String nomProd;
    private Double qteStockProd;
    private String typeProd;
    private Integer prixProd;
    private Integer nvPrix;
    private String etatProd;
    private String imageprod;
    private Integer qteAcheter;
    private int valeur;
    private Collection<LinePromo> linePromoCollection;
    private Categorie idCat;
    private Utilisateur idUser;
    private Collection<LineCmd> lineCmdCollection;

    public Produit() {
    }

    public Produit(Integer idProd) {
        this.idProd = idProd;
    }

    public Produit(Integer idProd, int valeur) {
        this.idProd = idProd;
        this.valeur = valeur;
    }

    public Integer getIdProd() {
        return idProd;
    }

    public void setIdProd(Integer idProd) {
        this.idProd = idProd;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public Double getQteStockProd() {
        return qteStockProd;
    }

    public void setQteStockProd(Double qteStockProd) {
        this.qteStockProd = qteStockProd;
    }

    public String getTypeProd() {
        return typeProd;
    }

    public void setTypeProd(String typeProd) {
        this.typeProd = typeProd;
    }

    public Integer getPrixProd() {
        return prixProd;
    }

    public void setPrixProd(Integer prixProd) {
        this.prixProd = prixProd;
    }

    public Integer getNvPrix() {
        return nvPrix;
    }

    public void setNvPrix(Integer nvPrix) {
        this.nvPrix = nvPrix;
    }

    public String getEtatProd() {
        return etatProd;
    }

    public void setEtatProd(String etatProd) {
        this.etatProd = etatProd;
    }

    public String getImageprod() {
        return imageprod;
    }

    public void setImageprod(String imageprod) {
        this.imageprod = imageprod;
    }

    public Integer getQteAcheter() {
        return qteAcheter;
    }

    public void setQteAcheter(Integer qteAcheter) {
        this.qteAcheter = qteAcheter;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Collection<LinePromo> getLinePromoCollection() {
        return linePromoCollection;
    }

    public void setLinePromoCollection(Collection<LinePromo> linePromoCollection) {
        this.linePromoCollection = linePromoCollection;
    }

    public Categorie getIdCat() {
        return idCat;
    }

    public void setIdCat(Categorie idCat) {
        this.idCat = idCat;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    public Collection<LineCmd> getLineCmdCollection() {
        return lineCmdCollection;
    }

    public void setLineCmdCollection(Collection<LineCmd> lineCmdCollection) {
        this.lineCmdCollection = lineCmdCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProd != null ? idProd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Produit)) {
            return false;
        }
        Produit other = (Produit) object;
        if ((this.idProd == null && other.idProd != null) || (this.idProd != null && !this.idProd.equals(other.idProd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Produit[ idProd=" + idProd + " ]";
    }
    
}
