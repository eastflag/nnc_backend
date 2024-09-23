package com.eastflag.nnc.user;

import java.util.List;

public interface UserCustomRepository {
    List<User> findAllBySearch();
}
