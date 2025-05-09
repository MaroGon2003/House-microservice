package com.powerup.house_microservice.infrastructure.configuration.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.powerup.house_microservice.infrastructure.utils.InfrastructureConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtToken {

    @Value("${security.jwt.secret}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGeneratedToken;

    public DecodedJWT validateToken(String token) {

        try {

            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

            return JWT.require(algorithm)
                    .withIssuer(this.userGeneratedToken)
                    .build()
                    .verify(token);

        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(InfrastructureConstants.TOKEN_INVALID);
        }

    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claim) {
        return decodedJWT.getClaim(claim);
    }

}
