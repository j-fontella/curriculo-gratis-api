package gratis.curriculo.controllers.login;

import gratis.curriculo.dtos.request.login.UsuarioLoginRequestDTO;
import gratis.curriculo.dtos.request.login.UsuarioRequestDTO;
import gratis.curriculo.dtos.request.login.UsuarioUpdateRequestDTO;
import gratis.curriculo.services.login.LoginService;
import gratis.curriculo.services.login.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    TokenService tokenService;

    @Autowired
    LoginService loginService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid UsuarioRequestDTO usuario) {
        return loginService.registrarUsuario(usuario);
    }

    @PostMapping("/on")
    public ResponseEntity<?>on(@RequestBody @Valid UsuarioLoginRequestDTO usuario) {
        return loginService.on(usuario);
    }

    @GetMapping("/getUsuario/{prk}")
    public ResponseEntity<?> registrarUsuario(@PathVariable Long prk, @RequestHeader String token) {
        tokenService.validarToken(token,prk);
        return loginService.getUsuario(prk);
    }

    @PatchMapping("/atualizar")
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid UsuarioUpdateRequestDTO usuarioRequestDTO, @RequestHeader String token) {
        tokenService.validarToken(token,usuarioRequestDTO.getPrk());
        return loginService.atualizarUsuario(usuarioRequestDTO);
    }

}
