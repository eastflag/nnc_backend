package com.eastflag.nnc;

import com.eastflag.nnc.common.CustomException;
import com.eastflag.nnc.user.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NncBackendApplicationTests {

	@Test
	void contextLoads() {
        Role role = Role.ADMIN;
        System.out.println("Authorities: " + role.getAuthorities());
        System.out.println("permissions: " + role.getPermissions());
	}

    @Test
    void test2() {
        var customException = new IllegalArgumentException("custom exception");
        System.out.println("customException: " + customException.getMessage());
    }
}
