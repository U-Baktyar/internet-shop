package shop.service;

public interface JwtService {
    String generateToken(String username);
    String getLoginFromToken(String token);
    boolean validateToken(String token);
}
