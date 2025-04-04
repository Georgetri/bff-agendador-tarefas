package com.george.bffagendadortarefas.controller;


import com.george.bffagendadortarefas.business.UsuarioService;
import com.george.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.george.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.george.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.george.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.george.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.george.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.george.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name="Usuario", description = "Cadastro e login de usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "salvar usuarios", description = "cria um novo usuario")
    @ApiResponse(responseCode = "200", description = "usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){

        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }


    @PostMapping("/login")
    @Operation(summary = "Login Usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "403", description = "Acesso negado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO){

        return usuarioService.loginUsuario(loginRequestDTO);
    }


    @GetMapping
    @Operation(summary = "Buscar dados de Usuários por Email",
            description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email,token));
    }


    @DeleteMapping("/{email}")
    @Operation(summary = "Deletar Usuários por Id", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name="Authorization", required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email,token);
        return ResponseEntity.ok().build();
    }


    @PutMapping
    @Operation(summary = "Atualizar Dados de Usuários",
            description = "Atualizar dados de usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTO,
                                                                   @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, usuarioDTO));
    }



    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço de Usuários",
            description = "Atualiza endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoDTO, token));

    }



    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone de Usuários",
            description = "Atualiza telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTOResponse> atualizaTelefone(@RequestBody TelefoneDTORequest telefoneDTO,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneDTO, token));

    }



    @PostMapping("/endereco")
    @Operation(summary = "Salva Endereço de Usuários",
            description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<EnderecoDTOResponse> cadastraEndereco(@RequestBody EnderecoDTORequest enderecoDTO,
                                                                @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token,enderecoDTO));
    }


    @PostMapping("/telefone")
    @Operation(summary = "Salva Telefone de Usuários",
            description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "Telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    public ResponseEntity<TelefoneDTOResponse> cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                                @RequestHeader(name="Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }

/*
    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca endereço pelo cep",
            description = "Busca dados de endereço recebendo um cep")
    @ApiResponse(responseCode = "200", description = "Dados de endereço retornados com sucesso")
    @ApiResponse(responseCode = "400", description = "Cep inválido")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ViaCepDTOResponse> buscarEndereco(@PathVariable("cep") String cep){
        return ResponseEntity.ok(usuarioService.buscarEnderecoPorCep(cep));
    }
    */
}