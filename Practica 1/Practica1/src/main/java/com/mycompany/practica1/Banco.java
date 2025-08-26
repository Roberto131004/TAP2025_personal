package com.mycompany.practica1;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Usuario> usuarios;

    public Banco() {
        usuarios = new ArrayList<>();
        
        usuarios.add(new Usuario("1234", "Juan", 1000.0));
        usuarios.add(new Usuario("5678", "Maria", 2500.0));
        usuarios.add(new Usuario("1111","Pedro",5000.0));
    }

    public Usuario autenticar(String pin) {
        for (Usuario usuario : usuarios) {
            if (usuario.getPin().equals(pin)) {
                return usuario;
            }
        }
        return null;
    }
}

