package gratis.curriculo.services.login;

import gratis.curriculo.domains.Erros;
import gratis.curriculo.dtos.request.login.UsuarioLoginRequestDTO;
import gratis.curriculo.dtos.request.login.UsuarioRequestDTO;
import gratis.curriculo.dtos.request.login.UsuarioUpdateRequestDTO;
import gratis.curriculo.dtos.response.login.UsuarioLoginResponseDTO;
import gratis.curriculo.dtos.response.login.UsuarioResponseDTO;
import gratis.curriculo.models.login.Token;
import gratis.curriculo.models.login.Usuario;
import gratis.curriculo.models.requisicao.Erro;
import gratis.curriculo.repositorys.login.UsuarioRepository;
import gratis.curriculo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TokenService tokenService;


    public ResponseEntity<?> registrarUsuario(UsuarioRequestDTO usuario) {
        Optional<Usuario> u = usuarioRepository.findByEmail(usuario.getEmail());
        if (u.isPresent()) {
            Erro erro = Utils.gerarErro(Erros.USUARIO_JA_CADASTRADO.getDescricao());
            return ResponseEntity.badRequest().body(erro);
        }
        Usuario novoUsuario = Utils.converterUsuarioRequest(usuario);
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> on(UsuarioLoginRequestDTO usuario) {
        Optional<Usuario> u = usuarioRepository.findByEmail(usuario.getEmail());
        if (u.isEmpty()) {
            Erro erro = Utils.gerarErro(Erros.USUARIO_NAO_ENCONTRADO.getDescricao());
            return ResponseEntity.badRequest().body(erro);
        }
        Usuario usuarioConectando = u.get();
        Utils.validarSenha(usuario.getSenha(), usuarioConectando.getSenha());
        Token token = tokenService.gerarToken(usuarioConectando.getPrk());
        UsuarioLoginResponseDTO response = new UsuarioLoginResponseDTO(usuarioConectando.getPrk(), usuarioConectando.getNome(), token.getHash());
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> getUsuario(Long prk) {
        Optional<Usuario> usuario = usuarioRepository.findById(prk);
        if (usuario.isEmpty()) {
            Erro erro = Utils.gerarErro(Erros.USUARIO_NAO_ENCONTRADO.getDescricao());
            return ResponseEntity.badRequest().body(erro);
        }
        UsuarioResponseDTO response = Utils.converterUsuarioReponse(usuario.get());
        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<?> atualizarUsuario(UsuarioUpdateRequestDTO usuarioRequestDTO) {
        Optional<Usuario> u = usuarioRepository.findById(usuarioRequestDTO.getPrk());
        if (u.isEmpty()) {
            Erro erro = Utils.gerarErro(Erros.USUARIO_NAO_ENCONTRADO.getDescricao());
            return ResponseEntity.badRequest().body(erro);
        }
        Usuario usuarioConectado = u.get();
        Utils.validarSenha(usuarioRequestDTO.getSenha(), usuarioConectado.getSenha());
        if(Utils.notNullAndBlank(usuarioRequestDTO.getNovaSenha())){
            usuarioRequestDTO.setSenha(usuarioRequestDTO.getNovaSenha());
        }
        usuarioRepository.save(Utils.converterUsuarioUpdateRequest(usuarioRequestDTO));
        return ResponseEntity.ok().build();
    }


}
