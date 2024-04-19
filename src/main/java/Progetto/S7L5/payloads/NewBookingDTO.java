package Progetto.S7L5.payloads;



import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBookingDTO {
    @NotNull
    private Long eventId;

    // Aggiungere qui il campo per l'ID dell'utente se necessario
}
