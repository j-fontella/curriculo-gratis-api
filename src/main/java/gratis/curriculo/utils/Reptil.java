package gratis.curriculo.utils;

public class Reptil {
    private String nome;
    private int id;

    public Reptil(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reptil reptil = (Reptil) o;

        return id == reptil.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
