package com.example.ChakriHub.service.impl;

import com.example.ChakriHub.auth.dto.response.CustomUserResponseDTO;
import com.example.ChakriHub.auth.model.User;
import com.example.ChakriHub.auth.repository.UserRepo;
import com.example.ChakriHub.entity.post.Post;
import com.example.ChakriHub.payload.request.PostRequestDto;
import com.example.ChakriHub.payload.response.PostResponseDto;
import com.example.ChakriHub.repository.PostRepository;
import com.example.ChakriHub.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;

    @Autowired
    UserRepo userRepo;

    CloudneryImageServiceImpl cloudneryImageService;

    public PostServiceImpl(PostRepository postRepository, CloudneryImageServiceImpl cloudneryImageService) {
        this.cloudneryImageService=cloudneryImageService;
        this.postRepository = postRepository;
    }


    public Post convertToEntity(PostRequestDto postRequestDto, Post post, MultipartFile image) throws IOException {

        if (image != null && !image.isEmpty()) {
            Map<String, Object> heroUploadResult = cloudneryImageService.upload(image);
            String coverImageUrl = (String) heroUploadResult.get("secure_url");
            post.setPicture(coverImageUrl);
        } else {
            post.setPicture(null);
        }

        post.setBody(postRequestDto.getBody());
        post.setUser(userRepo.findById(postRequestDto.getUserId()).get());


        return post;
    }

    public PostResponseDto convertToDto(Post post) {

     PostResponseDto postResponseDto = new PostResponseDto();

     postResponseDto.setBody(post.getBody());
     postResponseDto.setPhoto(post.getPicture());
     postResponseDto.setId(post.getId());
     postResponseDto.setUser(mapUserToDto(post.getUser()));

     return postResponseDto;
    }




    @Override
    public PostResponseDto getPost(Long id) {

        Post post = postRepository.findById(id).get();
        PostResponseDto postResponseDto = convertToDto(post);

        return postResponseDto;
    }


    @Override
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();


        posts.sort((p1, p2) -> Long.compare(p2.getId(), p1.getId()));

        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



    @Override
    public void addPost(PostRequestDto postRequestDto,MultipartFile pic) throws IOException {
        Post post = convertToEntity(postRequestDto,new Post(),pic);
        postRepository.save(post);
    }

    @Override
    public void updatePosts(PostRequestDto postRequestDto, Long id) throws IOException {

    }

    @Override
    public void deletePosts(Long id) {
       postRepository.deleteById(id);
    }

    public List<PostResponseDto> findByUserId(Long id) {
        List<Post> posts = postRepository.findByUserId(id);

        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    private CustomUserResponseDTO mapUserToDto(User user) {
        return new CustomUserResponseDTO() {
            @Override
            public Long getId() {
                return user.getId();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public String getEmail() {
                return user.getEmail();
            }

            @Override
            public String getProfilpic() {
                return user.getProfilpic();
            }

            @Override
            public String getChoose() {
                return user.getChoose();
            }

            @Override
            public CandidateInfo getCandidate() {
                if (user.getCandidate() == null) return null;

                return new CandidateInfo() {
                    @Override
                    public Long getId() {
                        return user.getCandidate().getId();
                    }

                    @Override
                    public String getFullName() {
                        return user.getCandidate().getFullName();
                    }

                    @Override
                    public String getBio() {
                        return user.getCandidate().getBio();
                    }

                    @Override
                    public String getPhoneNumber() {
                        return user.getCandidate().getPhoneNumber();
                    }

                    @Override
                    public String getLocation() {
                        return user.getCandidate().getLocation();
                    }

                    @Override
                    public String getSkills() {
                        return user.getCandidate().getSkills();
                    }

                    @Override
                    public String getLanguage() {
                        return user.getCandidate().getLanguage();
                    }

                    @Override
                    public String getAbout() {
                        return user.getCandidate().getAbout();
                    }

                    @Override
                    public String getPortfolioLinks() {
                        return user.getCandidate().getPortfolioLinks();
                    }

                    @Override
                    public String getPreferedPossion() {
                        return user.getCandidate().getPreferedPossion();
                    }

                    @Override
                    public String getYearsOfExperience() {
                        return user.getCandidate().getYearsOfExperience();
                    }

                    @Override
                    public String getCoverPic() {
                        return user.getCandidate().getCoverPic();
                    }

                    @Override
                    public String getEducationalQualifications() {
                        return user.getCandidate().getEducationalQualifications();
                    }

                    @Override
                    public String getPastExperience() {
                        return user.getCandidate().getPastExperience();
                    }

                    @Override
                    public String getCv() {
                        return user.getCandidate().getCv();
                    }
                };
            }

            @Override
            public RecruterInfo getRecruter() {
                if (user.getRecruter() == null) return null;

                return new RecruterInfo() {
                    @Override
                    public Long getId() {
                        return user.getRecruter().getId();
                    }

                    @Override
                    public String getName() {
                        return user.getRecruter().getName();
                    }

                    @Override
                    public String getCoverPhoto() {
                        return user.getRecruter().getCoverPhoto();
                    }

                    @Override
                    public String getCompanyName() {
                        return user.getRecruter().getCompanyName();
                    }

                    @Override
                    public String getOfficeLocation() {
                        return user.getRecruter().getOfficeLocation();
                    }

                    @Override
                    public String getCompanyDiscription() {
                        return user.getRecruter().getCompanyDiscription();
                    }

                    @Override
                    public String getIndustryType() {
                        return user.getRecruter().getIndustryType();
                    }

                    @Override
                    public String getPhoneNumber() {
                        return  user.getRecruter().getPhoneNumber();
                    }

                    @Override
                    public String getBio() {
                        return user.getRecruter().getBio();
                    }

                };
            }
        };
    }
}
