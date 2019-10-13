package by.psu.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private String tokenHeader;

    public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService,
                                       JwtTokenUtil jwtTokenUtil,
                                       String tokenHeader) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.tokenHeader = tokenHeader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(this.tokenHeader);

        String username = null;
        String authToken = null;

        if (nonNull(requestHeader) && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            username = jwtTokenUtil.getUsernameFromToken(authToken);
        }

        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if (nonNull(username) && isNull(securityContext.getAuthentication())) {
            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            final boolean isValidToken = jwtTokenUtil.validateToken(authToken, userDetails);

            if (isValidToken) {
                final Collection<? extends GrantedAuthority> grantedAuthorities = userDetails.getAuthorities();

                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, grantedAuthorities);
                final WebAuthenticationDetails webAuthenticationDetails =
                        new WebAuthenticationDetailsSource().buildDetails(request);
                authentication.setDetails(webAuthenticationDetails);

                securityContext.setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
