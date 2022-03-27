package gratis.curriculo.instances;

import gratis.curriculo.models.login.Usuario;

public class UsuarioLogado {

    private static Usuario logado;

    public static Usuario getInstance(){
        if(logado == null){
            logado = new Usuario();
            return logado;
        }
        return logado;
    }
}
