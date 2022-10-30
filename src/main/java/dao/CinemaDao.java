package dao;

import db_utils.MyConnection;
import modele.ActeurDTO;
import modele.FilmDTO;
import modele.GenreDTO;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class CinemaDao {

    private final static String SQLfilmById =
            """
                         select f.nfilm ,
                            f.titre ,
                            f.realisateur ,
                            f.nacteurprincipal ,
                            a.nom

                     from film f
                     join acteur a
                     on f.nacteurprincipal = a.nacteur
                     where f.nfilm = ?
             """;
    private final static String SQLInfoActeur =
            """
              select   a.nacteur ,
                       a.nom ,
                       a.prenom ,
                       p.nom as nomPays
              from           acteur a
                 inner join pays p
                 on a.nationalite = p.npays
              where a.nacteur = ?
                    """;

    private final static  String SQLfilmsDuGenre =
            """
                        select f.nfilm ,
                           f.titre ,
                           f.realisateur ,
                           f.nacteurprincipal ,
                           a.nom

                    from film f
                    join acteur a
                    on f.nacteurprincipal = a.nacteur
                    where f.ngenre = ?
            """;
    private static final String SQLFINDALLGENRES =
            """
            SELECT ngenre, nature
            FROM genre
            ORDER BY nature
            """;

    private static final String SQLfindGenreById =
            """
            SELECT ngenre, nature
            FROM genre
            where ngenre = ?
            """;

    public List<GenreDTO> ensGenres() {
        List<GenreDTO> liste = new ArrayList<>();

        try {
            // ouvrir une connection
            Connection conn = MyConnection.getINSTANCE();
            Statement instr = conn.createStatement();
            ResultSet rs = instr.executeQuery(SQLFINDALLGENRES);

            while(rs.next()) {
                int ngenre = rs.getInt("ngenre");
                String nature = rs.getString("NATURE");
                liste.add(new GenreDTO(ngenre,nature));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public List<FilmDTO> ensFilmsDuGenre(int ngenre) {

        List<FilmDTO> liste = new ArrayList<>();

        // lecture de la connecgtion
        Connection conn = MyConnection.getINSTANCE();
        try {
            PreparedStatement ps = conn.prepareStatement(SQLfilmsDuGenre);
            ps.setInt(1,ngenre);// communication de ngenre choisi par le FRONT
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FilmDTO filmDTO = FilmDTO.builder()
                        .id (rs.getInt(1))
                        .titre(rs.getString(2))
                        .nomRealisateur(rs.getString(3))
                        .nacteur_principal(rs.getInt(4))
                        .nomActeur(rs.getString(5))
                        .build();

                /*
                FilmDTO filmDTO = new FilmDTO();
                filmDTO.setId(rs.gentInt(1));
                filmDTO.setTitre(rs.getString(2));
                ...
                 */

                liste.add(filmDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public ActeurDTO infoActeur(int nacteur) {
        ActeurDTO acteur = null;

        try {
            Connection conn = MyConnection.getINSTANCE();
            PreparedStatement ps = conn.prepareStatement(SQLInfoActeur);
            ps.setInt(1,nacteur);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acteur = ActeurDTO.builder()
                        .nActeur(rs.getInt(1))
                        .nom(rs.getString(2))
                        .prenom(rs.getString(3))
                        .nomPays(rs.getString(4))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acteur;
    }
    public GenreDTO GenreById (int ngenre) {
        GenreDTO genre = null;

        try {
            Connection conn = MyConnection.getINSTANCE();
            PreparedStatement ps = conn.prepareStatement(SQLfindGenreById);
            ps.setInt(1,ngenre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                genre = GenreDTO.builder()
                        .ngenre(rs.getInt(1))
                        .nature(rs.getString(2))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }

    public FilmDTO findFilmById (int nfilm) {
        FilmDTO film = null;
        // lecture de la connection
        Connection conn = MyConnection.getINSTANCE();
        try {
            PreparedStatement ps = conn.prepareStatement(SQLfilmById);
            ps.setInt(1,nfilm);// communication de ngenre choisi par le FRONT
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                film = FilmDTO.builder()
                        .id(rs.getInt(1))
                        .titre(rs.getString(2))
                        .nomRealisateur(rs.getString(3))
                        .nacteur_principal(rs.getInt(4))
                        .nomActeur(rs.getString(5))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return film;

    }




    public static void main(String[] args) {
        CinemaDao dao = new CinemaDao();

        // System.out.println(dao.ensFilmsDuGenre(1));

//        System.out.println(dao.GenreById(2));
        System.out.println(dao.findFilmById(3));
//        System.out.println(dao.ensGenres());
//
        for (FilmDTO f : dao.ensFilmsDuGenre(2)){
           System.out.println(f);
       }
       System.out.println(dao.infoActeur(5));

    }

}


