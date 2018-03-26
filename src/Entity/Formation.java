/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author escobar
 */
public class Formation  {

    private Integer idFor;
    private String nomFor;
    private String place;
    private String etatFor;
    private String descriptionFor;
    private Date dateFor;
    private String imageform;
    private Collection<Session> sessionCollection;
    private TypeFormation idTypeFor;
    private Utilisateur idUser;

    public Formation() {
    }

    public Formation(Integer idFor) {
        this.idFor = idFor;
    }

    public Integer getIdFor() {
        return idFor;
    }

    public void setIdFor(Integer idFor) {
        this.idFor = idFor;
    }

    public String getNomFor() {
        return nomFor;
    }

    public void setNomFor(String nomFor) {
        this.nomFor = nomFor;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEtatFor() {
        return etatFor;
    }

    public void setEtatFor(String etatFor) {
        this.etatFor = etatFor;
    }

    public String getDescriptionFor() {
        return descriptionFor;
    }

    public void setDescriptionFor(String descriptionFor) {
        this.descriptionFor = descriptionFor;
    }

    public Date getDateFor() {
        return dateFor;
    }

    public void setDateFor(Date dateFor) {
        this.dateFor = dateFor;
    }

    public String getImageform() {
        return imageform;
    }

    public void setImageform(String imageform) {
        this.imageform = imageform;
    }

    public Collection<Session> getSessionCollection() {
        return sessionCollection;
    }

    public void setSessionCollection(Collection<Session> sessionCollection) {
        this.sessionCollection = sessionCollection;
    }

    public TypeFormation getIdTypeFor() {
        return idTypeFor;
    }

    public void setIdTypeFor(TypeFormation idTypeFor) {
        this.idTypeFor = idTypeFor;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFor != null ? idFor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formation)) {
            return false;
        }
        Formation other = (Formation) object;
        if ((this.idFor == null && other.idFor != null) || (this.idFor != null && !this.idFor.equals(other.idFor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Formation[ idFor=" + idFor + " ]";
    }
    
}
