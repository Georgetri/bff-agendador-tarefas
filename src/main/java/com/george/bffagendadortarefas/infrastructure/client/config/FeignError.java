package com.george.bffagendadortarefas.infrastructure.client.config;

import com.george.bffagendadortarefas.infrastructure.exceptions.BusinessException;
import com.george.bffagendadortarefas.infrastructure.exceptions.ConflitException;
import com.george.bffagendadortarefas.infrastructure.exceptions.IllegalArgumentException;
import com.george.bffagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.george.bffagendadortarefas.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        String mensabemErro = mensagemErro(response);

        switch (response.status()){
            case 409:
                return new ConflitException("Erro  " + mensabemErro);
            case 403:
                return new ResourceNotFoundException("Erro  " + mensabemErro);
            case 401:
                return new UnauthorizedException("Erro  " + mensabemErro);
            case 400:
                return new IllegalArgumentException("Erro  " + mensabemErro);
            default:
                return new BusinessException("Erro  " + mensabemErro);
        }
    }

    private String mensagemErro(Response response){
        try {
            if(Objects.isNull(response.body())){
                return "";
            }
           return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
