/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.util.Objects;



/**
 *
 * @author FERIEL FADHLOUN
 */
public class Notification {
    private int idNotif;
    private Date dateNotif;

    public String getEtatNotif() {
        return etatNotif;
    }

    public void setEtatNotif(String etatNotif) {
        this.etatNotif = etatNotif;
    }
    private String etatNotif;
    private Utilisateur idUser;
    private Session idSes;

    public Notification(Date dateNotif, Utilisateur idUser, Session idSes) {
        this.dateNotif = dateNotif;
        this.idUser = idUser;
        this.idSes = idSes;
    }

    public int getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(int idNotif) {
        this.idNotif = idNotif;
    }

    public Date getDateNotif() {
        return dateNotif;
    }

    public void setDateNotif(Date dateNotif) {
        this.dateNotif = dateNotif;
    }

    public Utilisateur getIdUser() {
        return idUser;
    }

    public void setIdUser(Utilisateur idUser) {
        this.idUser = idUser;
    }

    public Session getIdSes() {
        return idSes;
    }

    public void setIdSes(Session idSes) {
        this.idSes = idSes;
    }

    @Override
    public String toString() {
        return "Notification{" + "idNotif=" + idNotif + ", dateNotif=" + dateNotif + ", idUser=" + idUser + ", idSes=" + idSes + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.idNotif;
        hash = 89 * hash + Objects.hashCode(this.dateNotif);
        hash = 89 * hash + Objects.hashCode(this.idUser);
        hash = 89 * hash + Objects.hashCode(this.idSes);
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
        final Notification other = (Notification) obj;
        if (this.idNotif != other.idNotif) {
            return false;
        }
        if (!Objects.equals(this.dateNotif, other.dateNotif)) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        if (!Objects.equals(this.idSes, other.idSes)) {
            return false;
        }
        return true;
    }
    
}
