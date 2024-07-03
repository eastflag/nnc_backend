package com.eastflag.nnc.auth.service;

import com.eastflag.nnc.auth.dto.AuthenticationRequest;
import com.eastflag.nnc.auth.dto.AuthenticationResponse;
import com.eastflag.nnc.auth.dto.RegisterRequest;
import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.eastflag.nnc.config.JwtService;
import com.eastflag.nnc.token.Token;
import com.eastflag.nnc.token.TokenRepository;
import com.eastflag.nnc.token.TokenType;
import com.eastflag.nnc.user.User;
import com.eastflag.nnc.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  // signup 과 동시에 login
  @Transactional
  public CommonResponse register(RegisterRequest request) {
    var user = User.builder()
        .nickname(request.getNickname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    var savedUser = repository.save(user);
    var userDetails = user.getPrincipalDetails();
    var jwtToken = jwtService.generateToken(userDetails);

    saveUserToken(savedUser, jwtToken);
    var authenticationResponse = AuthenticationResponse.builder().accessToken(jwtToken).build();
    return CommonResponse.builder()
            .code(0)
            .message(ResponseMessage.SUCCESS)
            .data(authenticationResponse)
            .build();
  }

  @Transactional
  public CommonResponse authenticate(AuthenticationRequest request) {
    var optionalUser = repository.findByEmail(request.getEmail());
    if (optionalUser.isEmpty()) {
      return CommonResponse.builder()
              .code(100)
              .message(ResponseMessage.MEMBER_NOT_FOUND).build();
    }

    var user = optionalUser.get();
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return CommonResponse.builder()
              .code(100)
              .message(ResponseMessage.APP_LOGIN_FAIL).build();
    }

    var userDetails = user.getPrincipalDetails();
    var jwtToken = jwtService.generateToken(userDetails);

    // revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    var authenticationResponse = AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
    return CommonResponse.builder()
            .code(0)
            .message(ResponseMessage.SUCCESS)
            .data(authenticationResponse)
            .build();
  }

  @Transactional
  public CommonResponse logout(String accessToken) {
    if (accessToken == null ||!accessToken.startsWith("Bearer ")) {
      return CommonResponse.builder()
              .code(400)
              .message(ResponseMessage.JWT_NOT_FOUND)
              .build();
    }
    var jwt = accessToken.substring(7);
    var storedToken = tokenRepository.findByToken(jwt)
            .orElse(null);

    // black token 처리
    if (storedToken != null) {
      storedToken.setRevoked(true);
      tokenRepository.save(storedToken);
      SecurityContextHolder.clearContext();
    }

    return CommonResponse.builder()
            .code(0)
            .message(ResponseMessage.SUCCESS)
            .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  /**
   * 한 아이디로 중복 로그인 불가
   * @param user
   */
  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }
}
