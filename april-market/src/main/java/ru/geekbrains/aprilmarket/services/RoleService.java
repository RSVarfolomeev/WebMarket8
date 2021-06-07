package ru.geekbrains.aprilmarket.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.aprilmarket.entities.Role;
import ru.geekbrains.aprilmarket.repositories.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
