package gratis.curriculo.services.login;

import gratis.curriculo.domains.Erros;
import gratis.curriculo.models.login.Token;
import gratis.curriculo.models.login.Usuario;
import gratis.curriculo.repositorys.login.TokenRepository;
import gratis.curriculo.repositorys.login.UsuarioRepository;
import gratis.curriculo.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarToken(String hash, Long prk){
        Optional<Token> tokenQuery = tokenRepository.findByFrkUsuario(prk);
        if(tokenQuery.isEmpty()){
            throw new SecurityException("Token não cadastrado.");
        }
        Token token = tokenQuery.get();
        if(!token.getHash().equals(hash)){
            throw new SecurityException("Token inválido.");
        }
        if(LocalDateTime.now().isAfter(token.getDataExpiracao())){
            throw new SecurityException("Token expirado.");
        }
    }

    public void limparToken(Long prk){
        tokenRepository.limpar(prk);
    }

    public Token gerarToken(Long prk){
        Optional<Usuario> u = usuarioRepository.findById(prk);
        if(u.isEmpty()){
            throw new SecurityException(Erros.USUARIO_NAO_ENCONTRADO.getDescricao());
        }
        Usuario usuario = u.get();
        limparToken(prk);
        Token token = new Token();
        token.setUsuario(usuario);
        token.setDataExpiracao(LocalDateTime.now().plusHours(2));
        token.setHash(Utils.encriptarStringBCrypt(usuario.getSenha() + usuario.getEmail() + LocalDateTime.now()));
        return tokenRepository.save(token);
    }

}
