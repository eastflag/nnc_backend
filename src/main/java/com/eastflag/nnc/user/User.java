package com.eastflag.nnc.user;

import com.eastflag.nnc.auth.CustomUserDetails;
import com.eastflag.nnc.common.BaseEntity;
import com.eastflag.nnc.token.Token;
import jakarta.persistence.*;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String nickname;

  @Column(unique = true, nullable = false)
  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(mappedBy = "user")
  private List<Token> tokens;

  public CustomUserDetails getPrincipalDetails() {
    return new CustomUserDetails(this);
  }
}
