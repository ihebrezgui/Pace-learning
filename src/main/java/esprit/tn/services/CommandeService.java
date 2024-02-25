package esprit.tn.services;

import esprit.tn.interfaces.InterfaceService;
import esprit.tn.models.Commande;
import esprit.tn.util.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CommandeService implements InterfaceService<Commande> {
     Connection cnx = MaConnexion.getInstance().getCnx();
    @Override
    public void ajouter(Commande commande) throws SQLException {
        String req="INSERT INTO commande (tel,nom,prenom,mail,panier,address) VALUES(" +
                "" +
                "'"+commande.getTel()+
                "','"+commande.getNom()+
                "','"+commande.getPrenom()+
                "','"+commande.getMail()+
                "','"+commande.getPanier()+
                "','"+commande.getAddress()+"')";

        Statement st= cnx.createStatement();
        st.executeUpdate(req);


    }

    @Override
    public void modifier(Commande commande) throws SQLException {
        String req="UPDATE commande SET tel=?,nom=?,prenom=?,mail=?,address=?,panier=? WHERE idc=?";

        PreparedStatement cs=cnx.prepareStatement(req);

        cs.setInt(1, (commande.getTel()));
        cs.setString(2,commande.getNom());
        cs.setString(3,commande.getPrenom());
        cs.setString(4,commande.getMail());
        cs.setString(5,commande.getAddress());
        cs.setString(6,commande.getPanier());
        cs.setInt(7,commande.getIdc());
        cs.executeUpdate();
        cs.close();

    }

    @Override
    public void supprimer(int idc) throws SQLException {
        String req="DELETE FROM commande WHERE idc=?";

        PreparedStatement cs=cnx.prepareStatement(req);
        cs.setInt(1,idc);
        cs.executeUpdate();

    }

    @Override
    public List<Commande>recuperer() throws SQLException {
        List<Commande> commandes=new ArrayList<>();
        String req="SELECT * FROM commande";

        Statement cs=cnx.createStatement();
        ResultSet rs= cs.executeQuery(req);
        while (rs.next())
        {
            Commande co = new Commande();
            co.setIdc(rs.getInt("idc"));
            co.setTel(rs.getInt("tel"));
            co.setMail(rs.getString("mail"));
            co.setNom(rs.getString("nom"));
            co.setPrenom(rs.getString("prenom"));
            co.setAddress(rs.getString("address"));
            co.setPanier(rs.getString("panier"));
            commandes.add(co);
        }
        return commandes;

    }

    @Override
    public List<Commande> tri_par_nom() throws SQLException {
        List<Commande> commandes=new ArrayList<>();
        String req = "SELECT * FROM commande ORDER BY nom asc";
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(req);

        while (rs.next())
        {
            Commande co=new Commande();

            co.setIdc(rs.getInt("idc"));
            co.setTel(rs.getInt("tel"));
            co.setMail(rs.getString("mail"));
            co.setNom(rs.getString("nom"));
            co.setPrenom(rs.getString("prenom"));
            co.setAddress(rs.getString("address"));
            co.setPanier(rs.getString("panier"));
            commandes.add(co);
        }
        return commandes;


    }

    @Override
    public List<Commande> chercher(String nom) throws SQLException {
        List<Commande> commandes=new ArrayList<>();
        String req = "SELECT * FROM commande WHERE nom = ?";
        PreparedStatement preparedStatement = cnx.prepareStatement(req);
        preparedStatement.setString(1, nom);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next())
        {
            Commande co=new Commande();
            co.setIdc(rs.getInt("idc"));
            co.setTel(rs.getInt("tel"));
            co.setNom(rs.getString("nom"));
            co.setPrenom(rs.getString("prenom"));
            co.setMail(rs.getString("mail"));
            co.setPanier(rs.getString("panier"));
            co.setAddress(rs.getString("address"));

            commandes.add(co);
        }
        return commandes;


    }

    @Override
    public List<Commande> tri_par_nom2() throws SQLException {
        List<Commande> commandes=new ArrayList<>();
        String req = "SELECT * FROM commande ORDER BY nom desc";
        Statement statement = cnx.createStatement();
        ResultSet rs = statement.executeQuery(req);

        while (rs.next())
        {
            Commande co=new Commande();

            co.setIdc(rs.getInt("idc"));
            co.setTel(rs.getInt("tel"));
            co.setMail(rs.getString("nom"));
            co.setNom(rs.getString("prenom"));
            co.setPrenom(rs.getString("mail"));
            co.setAddress(rs.getString("address"));
            co.setPanier(rs.getString("panier"));
            commandes.add(co);
        }
        return commandes;
    }
}
