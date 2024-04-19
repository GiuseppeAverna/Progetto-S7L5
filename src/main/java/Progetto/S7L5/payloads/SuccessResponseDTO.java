package Progetto.S7L5.payloads;



import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseDTO {
    private int status;
    private String message;
}
