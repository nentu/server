package com.example.server;

import com.example.server.exception.BadCredentialsException;
import com.example.server.exception.UnauthorizedUserException;
import com.example.server.storage.dto.CoordinateDTO;
import com.example.server.storage.dto.UserDTO;
import com.example.server.storage.entities.Coordinate;
import com.example.server.storage.entities.User;
import com.example.server.storage.repository.CoordinateRepository;
import com.example.server.storage.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@RestController
@EnableWebMvc
@SessionAttributes(value = "user")
class Controller {
    private final UserRepository userRepository;
    private final CoordinateRepository coordinateRepository;

    private final static User nullUser = new User();

    Controller(UserRepository userRepository, CoordinateRepository coordinateRepository) {
        this.userRepository = userRepository;
        this.coordinateRepository = coordinateRepository;
    }

    private User getUser(HttpSession session) {
        if (!isLogin(session))
            throw new UnauthorizedUserException();
        return (User) session.getAttribute("user");
    }

    @GetMapping("/isLogin")
    boolean isLogin(HttpSession session) {
        var maybeUser = session.getAttribute("user");
        return !(maybeUser == null || maybeUser.equals(nullUser));
    }

    @PostMapping("/login")
    User login(@RequestBody UserDTO credentials, HttpSession session) {
        var user = userRepository.findByLogin(credentials.getLogin());
        if (user != null && user.getPassword().equals(credentials.getPassword())) {
            session.setAttribute("user", user);
            return user;
        } else
            throw new BadCredentialsException(credentials);
    }

    @GetMapping("/sign_out")
    void signOut(HttpSession session) {
        session.setAttribute("user", new User("a", "a", "a"));
        session.getId();
    }

    @PostMapping("/coord")
    boolean coordinate(@RequestBody CoordinateDTO coordinate, HttpSession session) {
        CoordinateChecker.check(coordinate);

        var owner = getUser(session);

        var entity = new Coordinate();
        entity.setX(coordinate.getX());
        entity.setY(coordinate.getY());
        entity.setR(coordinate.getR());
        entity.setSuccess(CoordinateChecker.check(coordinate));
        entity.setAuthor(owner);
        coordinateRepository.save(entity);

        return entity.isSuccess();
    }

    @GetMapping("/table")
    List<CoordinateDTO> getAll(HttpSession session) {
        var owner = getUser(session);
        var coordList = coordinateRepository.findAllByAuthor(owner);
        List<CoordinateDTO> result = new ArrayList<>();
        for (var i : coordList) {
            result.add(new CoordinateDTO(i));
        }
        return result;
    }

    @Transactional
    @GetMapping("/clear")
    public void clearAll(HttpSession session) {
        var owner = getUser(session);
        coordinateRepository.deleteAllByAuthor(owner);
    }

}
