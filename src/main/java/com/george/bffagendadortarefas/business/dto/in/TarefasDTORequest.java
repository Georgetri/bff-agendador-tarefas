package com.george.bffagendadortarefas.business.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.george.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarefasDTORequest {

    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;


}
