package lk.ac.sliit.it3030.smart_campus_project.security;

import lk.ac.sliit.it3030.smart_campus_project.model.User;
import lk.ac.sliit.it3030.smart_campus_project.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        
        User user = userRepository.findByEmail(email).orElseThrow();
        String token = jwtUtil.generateToken(user);

        // Redirect to React Frontend with the token in the URL
        getRedirectStrategy().sendRedirect(request, response, "http://localhost:5173/oauth2/callback?token=" + token);
    }
}
