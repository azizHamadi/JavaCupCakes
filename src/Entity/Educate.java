/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author escobar
 */
public class Educate {

    private Date dateIscri;
    private String etatEduc;
    private Session session;
    private Utilisateur utilisateur;

    public Educate() {
    }


    public Date getDateIscri() {
        return dateIscri;
    }

    public void setDateIscri(Date dateIscri) {
        this.dateIscri = dateIscri;
    }

    public String getEtatEduc() {
        return etatEduc;
    }

    public void setEtatEduc(String etatEduc) {
        this.etatEduc = etatEduc;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.dateIscri);
        hash = 97 * hash + Objects.hashCode(this.session);
        hash = 97 * hash + Objects.hashCode(this.utilisateur);
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
        final Educate other = (Educate) obj;
        if (!Objects.equals(this.dateIscri, other.dateIscri)) {
            return false;
        }
        if (!Objects.equals(this.session, other.session)) {
            return false;
        }
        if (!Objects.equals(this.utilisateur, other.utilisateur)) {
            return false;
        }
        return true;
    }

    
}
