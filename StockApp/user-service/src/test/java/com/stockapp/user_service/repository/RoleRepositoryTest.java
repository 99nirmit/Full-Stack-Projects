package com.stockapp.user_service.repository;

import com.stockapp.user_service.models.Permission;
import com.stockapp.user_service.models.Role;
import com.stockapp.user_service.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testFindByRoleName() {
        //Arrange
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("RETAIL_USER");
        role.setPermissions(Set.of(new Permission()));
        role.setUsers(Set.of(new User()));

        roleRepository.save(role);

        //Act
        Optional<Role> result =  roleRepository.findByRoleName("RETAIL_USER");

        //Assert

        assertEquals(1L, result.get().getId());
        assertEquals("RETAIL_USER", result.get().getRoleName());
        assertTrue(result.isPresent());
    }
}