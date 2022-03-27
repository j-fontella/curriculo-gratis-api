package gratis.curriculo.utils;

import gratis.curriculo.domains.Erros;
import gratis.curriculo.dtos.request.curriculo.CurriculoRequestDTO;
import gratis.curriculo.dtos.request.login.UsuarioRequestDTO;
import gratis.curriculo.dtos.request.login.UsuarioUpdateRequestDTO;
import gratis.curriculo.dtos.response.login.UsuarioLoginResponseDTO;
import gratis.curriculo.dtos.response.login.UsuarioResponseDTO;
import gratis.curriculo.models.curriculo.Curriculo;
import gratis.curriculo.models.login.Usuario;
import gratis.curriculo.models.requisicao.Erro;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.InvalidClassException;
import java.util.Arrays;
import java.util.Objects;

public class Utils {

    public static String ordenacaoLista = "\u2022";

    public static Usuario converterUsuarioRequest(UsuarioRequestDTO u){
        Usuario usuario = new ModelMapper().map(u, Usuario.class);
        usuario.setSenha(Utils.encriptarStringBCrypt(u.getSenha()));
        return usuario;
    }

    public static Usuario converterUsuarioUpdateRequest(UsuarioUpdateRequestDTO u){
        Usuario usuario = new ModelMapper().map(u, Usuario.class);
        usuario.setSenha(Utils.encriptarStringBCrypt(u.getSenha()));
        return usuario;
    }

    public static String encriptarStringBCrypt(String str){
        return new BCryptPasswordEncoder().encode(str);
    }

    public static Erro gerarErro(String... msgErro){
        Erro erro = new Erro();
        Arrays.stream(msgErro).forEach(s -> erro.getErros().add(s));
        return erro;
    }


    public static Curriculo converterCurriculoRequest(CurriculoRequestDTO curriculoRequestDTO) {
        return new ModelMapper().map(curriculoRequestDTO, Curriculo.class);
    }

    public static boolean isNullOrBlank(String s){
        return Objects.isNull(s) || s.isBlank();
    }

    public static String formataDataFormatoBrasil(String data){
        String[] dateString = data.split("-");
        if(dateString.length != 3){
            return null;
        }
        String ano = dateString[0];
        String mes = dateString[1];
        String dia = dateString[2];
        return dia+"/"+mes+"/"+ano;
    }

    public static UsuarioResponseDTO converterUsuarioReponse(Usuario usuario) {
        return new ModelMapper().map(usuario, UsuarioResponseDTO.class);
    }

    public static void validarSenha(String senhaEnviada, String senhaArmazenada){
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        if(!bc.matches(senhaEnviada, senhaArmazenada)){
            throw new SecurityException(Erros.SENHA_INCORRETA.getDescricao());
        }
    }

    public static boolean notNullAndBlank(String str){
        return Objects.nonNull(str) && !str.isBlank();
    }

}
