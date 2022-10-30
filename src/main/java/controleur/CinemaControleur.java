package controleur;

import dao.CinemaDao;
import modele.ActeurDTO;
import modele.FilmDTO;
import modele.GenreDTO;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/process"})
public class CinemaControleur extends HttpServlet {
    private CinemaDao dao = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao=new CinemaDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String choix = request.getParameter("action");

        if(choix.equals("init")){

            doInit(request, response);
        } else if (choix.equals("infoActeur")) {
            doInfoActeur(request, response);
        }
        else if (choix.equals("voirPanier")) {
            doVoirPanier(request, response);
        }

        else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //filmsDuGenre
        String choix = request.getParameter("action");

//        if (choix.equals("filmsDuGenre")) {
//            doFilmDuGenre(request,response);
//
//        else {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//        }

        // utilisation du switch lambda de Java 18
        switch (choix) {
            case "filmsDuGenre"      -> doFilmsDuGenre(request,response);
            case "filmsSelectionnes" ->doFilmsSelectionnes(request, response);
            default -> {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    private void doFilmsDuGenre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // recuperer le genre choisi
        String ngenre_Str = request.getParameter("genre");
        int ngenre = Integer.parseInt(ngenre_Str);

        //Acces à la couche métier --> recuperer les films d'un genre donné
        List<FilmDTO> ensFilms = dao.ensFilmsDuGenre(ngenre);
        GenreDTO genre = dao.GenreById(ngenre);

        //Mise à jour du modele (portée request)
        request.setAttribute("ensFilms", ensFilms);
        request.setAttribute("genre" , genre);

        //Cinématique ou navigation
        String url= "pages/afficherFilmsDuGenre.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    public void doInit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.init();
//        acces a la couche metier pour recuperer la liste des genres
        List<GenreDTO> ensGenres= dao.ensGenres();

//        Mise a jour du modele pour les Vues
//        request.getAttribute("ensGenres",ensGendre) utlisation de requestScope
        request.getSession().setAttribute("ensGenres", ensGenres);

        String url ="pages/choixGenre.jsp";

        request.getRequestDispatcher(url).forward(request,response);
    }

    private void doInfoActeur(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
        // 1 récuperer les parametres de la requete nomme id(identifiant de l'acteur choisi)
        String nacteur_str = request.getParameter("id");
        int nacteur = Integer.parseInt(nacteur_str);

        //2 acces au metier pour récuperer ActeurDTO dont on a le "nacteur"
        ActeurDTO acteur = dao.infoActeur(nacteur);

        //3 mise à jour du modele destiné a la VUE(page jsp)
        request.setAttribute("acteur", acteur);

        //4 navigation vers la proche VUE afin d'affichr les info. de cet acteur
        String url = "pages/afficherInfoActeur.jsp";
        request.getRequestDispatcher(url).forward(request, response);
    }

    private void doFilmsSelectionnes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1 grace au parametre choix (selection multiple : recuperation des films choisis
        String[] choix = request.getParameterValues("choix");

        //Creation du panier
        HttpSession session = request.getSession();

        //recuperation du panier si il existe
        Map<String, FilmDTO> ensChoisis =
                (HashMap<String, FilmDTO>) session
                        .getAttribute("ensChoix");
        //si le panier n'existe pas nous devons le creer
        if (ensChoisis==null) {
            ensChoisis = new HashMap<String, FilmDTO>();
        }
        //ensChoisis EXISTE(soit car on l'a trouvé soit car on l'a créé)
        for (String cle : choix) {
            int nfilm = Integer.parseInt(cle);
            FilmDTO film = dao.findFilmById(nfilm);
            ensChoisis.put(cle,film);

            //Sauvergarde du panier

            session.setAttribute("ensChoix", ensChoisis);

            // Cinematique vers la VUE
            String url="pages/afficherPanier.jsp";
            request.getRequestDispatcher(url).forward(request, response);
        }

    }


    private void doVoirPanier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Navigation vers la vue affichée
        String url = "pages/afficherPanier.jsp";
        request.getRequestDispatcher(url).forward(request, response);

    }
}