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
import javax.persistence.CascadeType;
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
@Entity
@Table(name = "commande")
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCmd")
    private Integer idCmd;
    @Column(name = "dateCmd")
    @Temporal(TemporalType.DATE)
    private Date dateCmd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montantCmd")
    private Double montantCmd;
    @Column(name = "dateLivCmd")
    @Temporal(TemporalType.DATE)
    private Date dateLivCmd;
    @Column(name = "addLiv")
    private String addLiv;
    @Column(name = "etatLivCmd")
    private String etatLivCmd;
    @Column(name = "etatCmd")
    private String etatCmd;
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    @ManyToOne
    private Utilisateur idUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    private Collection<LineCmd> lineCmdCollection;
    @OneToMany(mappedBy = "idCmd")
    private Collection<FeedBack> feedBackCollection;

    public Commande() {
    }

    public Commande(Integer idCmd) {
        this.idCmd = idCmd;
    }

    public Commande(java.sql.Date date, double d, java.sql.Date date0, String adr, String en_cours, String vrai) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getIdCmd() {
        return idCmd;
    }

    public void setIdCmd(Integer idCmd) {
        this.idCmd = idCmd;
    }

    public Date getDateCmd() {
        return dateCmd;
    }

    public void setDateCmd(Date dateCmd) {
        this.dateCmd = dateCmd;
    }

    public Double getMontantCmd() {
        return montantCmd;
    }

    public void setMontantCmd(Double montantCmd) {
        this.montantCmd = montantCmd;
    }

    public Date getDateLivCmd() {
        return dateLivCmd;
    }

    public void setDateLivCmd(Date dateLivCmd) {
        this.dateLivCmd = dateLivCmd;
    }

    public String getAddLiv() {
        return addLiv;
    }

    public void setAddLiv(String addLiv) {
        this.addLiv = addLiv;
    }

    public String getEtatLivCmd() {
        return etatLivCmd;
    }

    public void setEtatLivCmd(String etatLivCmd) {
        this.etatLivCmd = etatLivCmd;
    }

    public String getEtatCmd() {
        return etatCmd;
    }

    public void setEtatCmd(String etatCmd) {
        this.etatCmd = etatCmd;
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

    public Collection<FeedBack> getFeedBackCollection() {
        return feedBackCollection;
    }

    public void setFeedBackCollection(Collection<FeedBack> feedBackCollection) {
        this.feedBackCollection = feedBackCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCmd != null ? idCmd.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.idCmd == null && other.idCmd != null) || (this.idCmd != null && !this.idCmd.equals(other.idCmd))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Commande[ idCmd=" + idCmd + " ]";
    }
    
}
