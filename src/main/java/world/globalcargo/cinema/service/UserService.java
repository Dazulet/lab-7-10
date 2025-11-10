package world.globalcargo.cinema.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import world.globalcargo.cinema.dto.UserDTO;
import world.globalcargo.cinema.entite.User;
import world.globalcargo.cinema.repositories.UserRepository;
import world.globalcargo.cinema.service.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDtoList = new ArrayList<>();
        users.forEach(user -> userDtoList.add(toDto(user)));
        return userDtoList;
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            return null;
        }
        return toDto(user);
    }

    @Override
    public UserDTO addUser(UserDTO userDto) {
        User user = toEntity(userDto);
        User createdUser = userRepository.save(user);
        return toDto(createdUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDto) {
        UserDTO checkUser = getUser(id);
        if (Objects.isNull(checkUser)) {
            return null;
        }
        User user = toEntity(userDto);
        user.setId(id);
        User updatedUser = userRepository.save(user);
        return toDto(updatedUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        UserDTO checkUser = getUser(id);
        if (Objects.isNull(checkUser)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    private UserDTO toDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .build();
    }

    private User toEntity(UserDTO dto) {
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        return user;
    }

}
