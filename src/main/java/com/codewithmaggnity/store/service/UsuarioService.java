package com.codewithmaggnity.store.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithmaggnity.store.model.Usuario;
import com.codewithmaggnity.store.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = bCryptPasswordEncoder;
    }

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscaPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {

        Usuario usuario = buscaPorId(id);

        usuario.setUsername(usuarioAtualizado.getUsername());

        if (usuarioAtualizado.getPassword() != null && !usuario.getPassword().isEmpty()) {
            usuario.setPassword(encoder.encode(usuarioAtualizado.getPassword()));
        }

        usuario.setRole(usuarioAtualizado.getRole());

        return usuarioRepository.save(usuario);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
