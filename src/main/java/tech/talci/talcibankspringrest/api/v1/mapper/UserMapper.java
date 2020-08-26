package tech.talci.talcibankspringrest.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.talci.talcibankspringrest.api.v1.model.UserDTO;
import tech.talci.talcibankspringrest.domain.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
