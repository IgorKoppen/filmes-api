package com.github.igorkoppen.filmes.api.service;

import com.github.igorkoppen.filmes.api.dto.RoleDTO;
import com.github.igorkoppen.filmes.api.dto.UserDTO;
import com.github.igorkoppen.filmes.api.dto.UserInsertDTO;
import com.github.igorkoppen.filmes.api.exception.DatabaseException;
import com.github.igorkoppen.filmes.api.exception.ResourceNotFoundException;
import com.github.igorkoppen.filmes.api.model.Role;
import com.github.igorkoppen.filmes.api.model.User;
import com.github.igorkoppen.filmes.api.projections.UserDetailsProjection;
import com.github.igorkoppen.filmes.api.repository.RoleRepository;
import com.github.igorkoppen.filmes.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO insert(UserInsertDTO userDTO) {
        User user = new User();
        copyToEntity(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userRepository.save(user);
        return toDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not founded with id: " + id));
        return toDTO(user);
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not founded with id: " + id));
        copyToEntity(userDTO, user);
        user = userRepository.save(user);
        return toDTO(user);
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado! Id: " + id);
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyToEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.getRoles().clear();
        for (RoleDTO roleDTO : dto.getRoles()) {
            Role role = roleRepository.getReferenceById(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> list = userRepository.findUserAndRolesByEmail(username);
        if (list.isEmpty()) {
            throw new UsernameNotFoundException("Email não encontrado");
        }

        User user = new User();
        user.setName(username);
        user.setPassword(list.get(0).getPassword());
        for (UserDetailsProjection projection : list) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
