package Progetto.S7L5.payloads;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @FutureOrPresent
    private LocalDateTime date;

    @NotBlank
    private String location;

    @NotNull
    private int availableSeats;

    // Aggiungere qui il campo per l'ID dell'organizzatore se necessario
}
