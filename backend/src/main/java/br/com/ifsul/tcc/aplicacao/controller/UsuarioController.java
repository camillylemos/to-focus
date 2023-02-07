package br.com.ifsul.tcc.aplicacao.controller;

import br.com.ifsul.tcc.aplicacao.domain.Usuario;
import br.com.ifsul.tcc.aplicacao.represetation.request.LogarUsuarioRequest;
import br.com.ifsul.tcc.aplicacao.represetation.response.TokenResponse;
import br.com.ifsul.tcc.aplicacao.services.usuario.BuscarInformacoesUsuarioService;
import br.com.ifsul.tcc.aplicacao.services.usuario.CadastrarUsuarioService;
import br.com.ifsul.tcc.aplicacao.services.usuario.LogarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    CadastrarUsuarioService cadastrarUsuarioService;

    @Autowired
    LogarUsuarioService logarUsuarioService;

    @Autowired
    BuscarInformacoesUsuarioService buscarInformacoesUsuarioService;

    @PostMapping("/cadastrar")
    public void cadastrarUsuario(@RequestBody @Valid Usuario request) {
        cadastrarUsuarioService.cadastrarUsuario(request);
    }

    @PostMapping("/login")
    public TokenResponse logarUsuario(@RequestBody @Valid LogarUsuarioRequest request) {
        return logarUsuarioService.logarUsuario(request);
    }

    @GetMapping("/me")
    public Usuario buscarInformacoesUsuario(@RequestHeader String authorization) {
        return buscarInformacoesUsuarioService.buscarInformacoesUsuario(authorization);
    }
}
