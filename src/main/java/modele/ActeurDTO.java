package modele;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder

public class ActeurDTO {
    private int nActeur;
    private String nomActeur;
    private String nom;
    private String prenom;
    private String nomPays;
}
