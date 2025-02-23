package com.example.ChakriHub.auth.service;

import com.example.ChakriHub.auth.dto.request.UserRequestDTO;
import com.example.ChakriHub.auth.dto.request.UserRoleRequestDTO;
import com.example.ChakriHub.auth.dto.request.UserUpdateRequestDto;
import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import com.example.ChakriHub.auth.model.Role;
import com.example.ChakriHub.auth.model.Status;
import com.example.ChakriHub.auth.model.User;
import com.example.ChakriHub.auth.repository.RoleRepo;
import com.example.ChakriHub.auth.repository.UserRepo;
import com.example.ChakriHub.config.notification.SSEService;
import com.example.ChakriHub.service.CloudneryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service

public class UserServiceIMPL implements UserService {

    private UserRepo userRepository;
    private  PasswordEncoder passwordEncoder;
    private RoleRepo roleRepository;
    private SSEService<User> userSSEService;

    @Autowired
    private CloudneryImageService cloudneryImageService;

   public UserServiceIMPL(UserRepo userRepo,PasswordEncoder passwordEncoder,RoleRepo roleRepository,SSEService<User> userSSEService) {
       this.userRepository = userRepo;
       this.passwordEncoder = passwordEncoder;
       this.roleRepository = roleRepository;
       this.userSSEService = userSSEService;
   }


   public User ConvertToEntity(User user, UserRequestDTO userRequestDTO, MultipartFile profilepic) throws IOException {
       String   profileImageUrl = "https://res.cloudinary.com/dxmwiwy6g/image/upload/v1740298839/jhp0yhawmfwffy195dn8.jpg";

       if (profilepic != null) {
           Map<String, Object> heroUploadResult = cloudneryImageService.upload(profilepic);
           profileImageUrl = (String) heroUploadResult.get("secure_url");
       }


       user.setUsername( userRequestDTO.getUsername() );
       user.setEmail( userRequestDTO.getEmail() );
       user.setPassword( passwordEncoder.encode(userRequestDTO.getPassword() ));
       user.setProfilpic(profileImageUrl);

       return user;
   }




    public void create(UserRequestDTO requestDto, MultipartFile heroImageFile) throws IOException {
       User user1=userRepository.findByUsername(requestDto.getUsername());
       if(user1!=null){
           throw new RuntimeException("User already exists");
       }

       User user = ConvertToEntity(new User(), requestDto, heroImageFile);

       userRepository.save(user);

       userSSEService.emit( user );

    }


    public CustomUserResponseDTO readOne(Long id ) {
        CustomUserResponseDTO singleUserById = userRepository.findUserByUserId(id);
        if ( Objects.isNull( singleUserById ) ) {
            throw new RuntimeException( "User with id " + id + " not found." );
        }
        return singleUserById;
    }


    public User setUserRoles( UserRoleRequestDTO requestDTO ) {

        User foundUser = userRepository.findById( requestDTO.userId() ).get();

        if ( Objects.isNull( foundUser ) ) {
            throw new RuntimeException( "User with id " + requestDTO.userId() + " not found." );
        }

        Set<Role> foundRoles = roleRepository.findAllByIdIn( requestDTO.roleIds() );
        foundUser.getRoles().addAll( foundRoles );

        return  userRepository.save( foundUser );

    }


    @Override
    public void updateUser(Long id, UserUpdateRequestDto userRequestDTO, MultipartFile heroImageFile) throws IOException {

       User user=userRepository.findById( id ).get();

       User updateUser = ConvertToEntityUpdate(user, userRequestDTO, heroImageFile);

       userRepository.save( updateUser );

    }

    @Override
    public CustomUserResponseDTO searchByUsername(String username) {
        return userRepository.searchByUsername( username );
    }

    public User ConvertToEntityUpdate(User user,UserUpdateRequestDto userRequestDTO,MultipartFile profilepic) throws IOException {

        Map<String, Object> heroUploadResult = cloudneryImageService.upload(profilepic);
        String profileImageUrl = (String) heroUploadResult.get("secure_url");
        user.setProfilpic(profileImageUrl);

        return user;
    }



    public void saveActiveUser(User user) {
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }

    public void disconnect(User user) {
        User storedUser = userRepository.findByUsername(user.getUsername());
        if (storedUser != null) {
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return userRepository.findAllByStatus(Status.ONLINE);
    }


}
