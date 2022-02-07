package com.itis.course.Service;


import com.itis.course.Repository.LangueRepository;
import com.itis.course.Repository.PublicationRepository;
import com.itis.course.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class DbInit implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PublicationRepository publicationRepository;

    @Autowired
    LangueRepository langueRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        //userRepository.deleteAll();
        //langueRepository.deleteAll();
        //publicationRepository.deleteAll();

        /*


        Users user = new Users();
        user.setEmail("test@mail.com");
        user.setFirstName("TOTO");
        user.setLastName("TOTO");
        user.setAccountVerified(true);
        user.setPassword(passwordEncoder.encode("1234"));
        user.setRoles(new ArrayList<Role>(Collections.singleton(Role.USER)));
        Users user1 = new Users();
        user1.setEmail("sguerfi12@gmail.com");
        user1.setFirstName("TITI");
        user1.setLastName("TITI");
        user1.setAccountVerified(true);
        user1.setPassword(passwordEncoder.encode("12345"));
        user1.setRoles(new ArrayList<Role>(Collections.singleton(Role.ADMIN)));

        //add langue
        Langue langue = new Langue("Francais");
        langueRepository.save(langue);


        ArrayList<TypePublication> tagsOfPublication = new ArrayList<TypePublication>();
        TypePublication tag = TypePublication.ARTICLE;
        TypePublication tag2 = TypePublication.PROVERBE;
        tagsOfPublication.add(tag);
        tagsOfPublication.add(tag2);
        // init publications
        List<Publication> publicationList = new ArrayList<>();
        Publication publication = new Publication(
                1,
                "ceci est un test",
                user,
                "google.com",
                "juste un test en gros",
                tagsOfPublication,
                langue,
                user
        );
        Publication publication2 = new Publication(
                1,
                "Seconde publication",
                user,
                "facebook.com",
                "un deuxieme test",
                tagsOfPublication,
                langue,
                user
        );

        publicationList.add(publication);
        publicationList.add(publication2);

        user.setPublicationList(publicationList);

        userRepository.saveAndFlush(user);
        userRepository.saveAndFlush(user1);

         */



    }
}
