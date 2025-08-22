package com.codewithmaggnity.store.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithmaggnity.store.model.Usuario;
import com.codewithmaggnity.store.repository.UsuarioRepository;
import com.codewithmaggnity.store.security.JwtUtil;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {

        this.usuarioRepository = usuarioRepository;
        this.encoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(String username, String password) {

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!encoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Senha inválida!");
        }

        return jwtUtil.generateToken(usuario.getUsername());

    }
}