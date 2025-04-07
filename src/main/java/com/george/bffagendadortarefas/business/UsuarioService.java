package com.george.bffagendadortarefas.business;

import com.george.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.george.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.george.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.george.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.george.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.george.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.george.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import com.george.bffagendadortarefas.business.dto.out.ViaCepDTOResponse;
import com.george.bffagendadortarefas.infrastructure.client.UsuarioClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTO){
        return client.salvaUsuario(usuarioDTO);
    }


    public String loginUsuario(LoginRequestDTO loginRequestDTO){

        return client.login(loginRequestDTO);
    }


    public UsuarioDTOResponse buscarUsuarioPorEmail(String email, String token){
        return client.buscaUsuarioPorEmail(email, token);
    }


    public void deletaUsuarioPorEmail(String email, String token){

        client.deletaUsuarioPorEmail(email,token);
    }


    public UsuarioDTOResponse atualizaDadosUsuario(String token, UsuarioDTORequest usuarioDTO){
        return client.atualizaDadosUsuario(usuarioDTO, token);
    }


    public EnderecoDTOResponse atualizaEndereco(Long idEndereco, EnderecoDTORequest enderecoDTO, String token){
        return client.atualizaEndereco(enderecoDTO,idEndereco,token);
    }


    public TelefoneDTOResponse atualizaTelefone(Long idTelefone, TelefoneDTORequest telefoneDTO, String token){
        return client.atualizaTelefone(telefoneDTO,idTelefone,token);
    }


    public EnderecoDTOResponse cadastraEndereco(String token, EnderecoDTORequest enderecoDTO){
       return client.cadastraEndereco(enderecoDTO,token);
    }


    public TelefoneDTOResponse cadastraTelefone(String token, TelefoneDTORequest telefoneDTO){
       return client.cadastraTelefone(telefoneDTO,token);
    }

    public ViaCepDTOResponse buscarEnderecoPorCep(String cep) {
        return client.buscarDadosCep(cep);
    }

}
