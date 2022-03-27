package gratis.curriculo.utils;

import java.util.Objects;

public class Db {
    public static void main(String[] args) {
        Reptil a = new Reptil("Sal", 1);
        Reptil b = new Reptil("Rex", 1);
        System.out.println(nonNunAndEquals(a,b));
    }

    public static <T> boolean nonNunAndEquals(T object, T compared){
        return Objects.nonNull(object) && object.equals(compared);
   }

  

}
