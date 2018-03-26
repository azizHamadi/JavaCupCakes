/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1;

import Entity.CategorieRec;
import Entity.Commentaire;
import Entity.Recette;
import Entity.Utilisateur;
import Entity.Thread;
import Services.CategorieRecetteService;
import Services.CommentaireService;
import Services.RecetteService;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aziz
 */
public class main {
        public static void main(String[] args) {
            /*try {
                CategorieRecetteService catRS = new CategorieRecetteService();
                RecetteService RecS = new RecetteService();
                CommentaireService ComS = new CommentaireService();
                CategorieRec idCatRec1 = new CategorieRec("Gateau");
                CategorieRec idCatRec = new CategorieRec(12, "Gateau");
                Utilisateur idUser = new Utilisateur(17,"feriel", "aa@aa.aa", "aa", "aa", "22", "aa");
                Recette Rec = new Recette("test2", "oui", "description", "imageRec", idCatRec, idUser);
                Thread Th = new Thread("59", "", true, 7);
                Commentaire com = new Commentaire("eeeeeeeee", 0, 0, Th, idUser);
                
                //Ajout CategorieRec , Recette , Commentaire , Note
                catRS.AjouterCategorieRecette(idCatRec1);
                RecS.AjouterRecette(Rec);
                ComS.AjouterCommentaire(com);
                
                //affichage CategorieRec , Recette , Commentaire , Note
                System.out.println("Liste des recettes : ");
                List<Recette> listR = RecS.AfficherRecette();
                for (Recette r : listR)
                    System.out.println(r);
                System.out.println("Liste des categories des recettes : ");
                List<CategorieRec> listCatRec = catRS.AfficherCategorieRecette() ;
                for(CategorieRec cr : listCatRec)
                    System.out.println(cr);
                System.out.println("Liste des commentaires : ");
                List<Commentaire> listCom = ComS.AfficherCommentaire();
                for(Commentaire c : listCom)
                    System.out.println(c);
                
                //Modification CategorieRec , Recette , Commentaire , Note
                idCatRec.setNomCatRec("Gateau");
                catRS.ModifierCategorieRecette(idCatRec);
                Recette RecModif = new Recette(67,"Modifier", "oui", "description", "imageRec", idCatRec, idUser);
                RecS.ModifierRecette(RecModif);
                Commentaire comModif = new Commentaire(124, "ddddd", "", 0, new Date(), 0, Th, idUser);
                ComS.UpdateCommentaire(comModif);
                
                //Suppression CategorieRec , Recette , Commentaire , Note
                CategorieRec caR = new CategorieRec(18, "Gateau");
                catRS.SupprimerCategorieRecette(caR);
                RecS.SupprimerRecette(RecModif);
                comModif.setState(1);
                ComS.SupprimerCommentaire(comModif);
                               
            } catch (SQLException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
*/
        }
}
