package org.mosesidowu.onlinepollingsystem.services.userService;

import lombok.RequiredArgsConstructor;
import org.mosesidowu.onlinepollingsystem.data.models.Role;
import org.mosesidowu.onlinepollingsystem.data.models.User;
import org.mosesidowu.onlinepollingsystem.data.repository.UserRepository;
import org.mosesidowu.onlinepollingsystem.dtos.requests.EmailRequest;
import org.mosesidowu.onlinepollingsystem.dtos.responses.UserResponseDTO;
import org.mosesidowu.onlinepollingsystem.exception.UserNotFoundException;
import org.mosesidowu.onlinepollingsystem.security.JwtTokenProvider;
import org.mosesidowu.onlinepollingsystem.services.emailService.EmailService;
import org.mosesidowu.onlinepollingsystem.services.emailService.EmailServiceImpl;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.mosesidowu.onlinepollingsystem.util.UserMapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final EmailService emailService;


    @Override
    public UserResponseDTO registerOAuthUser(OAuth2User oauth2User) {
        getOauthResponse result = extractOauthInfo(oauth2User);
        User user = confirmRegisteredUser(result);
        User savedUser = userRepository.save(user);

        emailService.sendEmail(savedUser.getEmail());

        return getRegisteredUserResponse(savedUser);
    }



    @Override
    public UserResponseDTO findByEmail(String email) {
        Optional<User> userOptional = userRepository.findUsersByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return getResponseDTO(user);
        } else throw new UserNotFoundException("User with email not found.");
    }



    @Override
    public UserResponseDTO findById(Long id) {
        Optional<User> userOptionalId = userRepository.findById(id);
        if (userOptionalId.isPresent()) {
            User user = userOptionalId.get();
            return getResponseDTO(user);
        } else throw new UserNotFoundException("User with ID not found.");
    }



    private User confirmRegisteredUser(getOauthResponse result) {
        Optional<User> existingUser = userRepository.findUsersByOauth2Id(result.oauth2Id());
        User user;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            user.setEmail(result.email());
            user.setName(result.name());

        } else {
            user = new User();
            user.setEmail(result.email());
            user.setName(result.name());
            user.setOauth2Id(result.oauth2Id());
            user.setRole(Role.VOTER);
        }
        return user;
    }


    private static getOauthResponse extractOauthInfo(OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String oauth2Id = oauth2User.getName();

        return new getOauthResponse(email, name, oauth2Id);
    }

    private record getOauthResponse(String email, String name, String oauth2Id) {
    }
}
