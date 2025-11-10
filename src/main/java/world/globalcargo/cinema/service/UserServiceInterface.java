package world.globalcargo.cinema.service;

import world.globalcargo.cinema.dto.UserDTO;

import java.util.List;

public interface UserServiceInterface {
    List<UserDTO> getAllUsers();

    UserDTO getUser(Long id);

    UserDTO addUser(UserDTO user);

    UserDTO updateUser(Long id, UserDTO user);

    boolean deleteUser(Long id);
}
