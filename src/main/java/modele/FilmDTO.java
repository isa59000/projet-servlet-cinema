package modele;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class FilmDTO {
    private int id;
    private String titre;
    private String nomRealisateur;
    private int nacteur_principal;
    private String nomActeur;
}
