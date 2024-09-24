package com.eastflag.nnc.user.repository;

import com.eastflag.nnc.user.model.User;

import java.util.List;

public interface UserCustomRepository {
    List<User> findAllBySearch();
}
