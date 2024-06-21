package com.eastflag.nnc.auth;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.config.JwtService;
import com.eastflag.nnc.token.Token;
import com.eastflag.nnc.token.TokenRepository;
import com.eastflag.nnc.token.TokenType;
import com.eastflag.nnc.user.User;
import com.eastflag.nnc.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

  public AuthenticationResponse register(RegisterRequest request) {
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
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }

  public CommonResponse authenticate(AuthenticationRequest request) {
    var optionalUser = repository.findByEmail(request.getEmail());
    if (optionalUser.isEmpty()) {
      return CommonResponse.builder()
              .code(100)
              .message("FAIL").build();
    }

    var user = optionalUser.get();
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return CommonResponse.builder()
              .code(100)
              .message("FAIL").build();
    }

    var userDetails = user.getPrincipalDetails();
    var jwtToken = jwtService.generateToken(userDetails);

    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    var authenticationResponse = AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
    return CommonResponse.builder()
            .code(0)
            .message("SUCCESS")
            .data(authenticationResponse)
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
