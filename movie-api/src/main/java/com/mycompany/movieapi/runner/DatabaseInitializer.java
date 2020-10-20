package com.mycompany.movieapi.runner;

import com.mycompany.movieapi.model.Bookmark;
import com.mycompany.movieapi.model.Movie;
import com.mycompany.movieapi.model.User;
import com.mycompany.movieapi.security.WebSecurityConfig;
import com.mycompany.movieapi.security.oauth2.OAuth2Provider;
import com.mycompany.movieapi.service.BookmarkService;
import com.mycompany.movieapi.service.MovieService;
import com.mycompany.movieapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private static final List<User> USERS = Arrays.asList(
            new User("admin", "admin", "Admin", "admin@mycompany.com", WebSecurityConfig.ADMIN, null, OAuth2Provider.LOCAL, "1"),
            new User("user", "user", "User", "user@mycompany.com", WebSecurityConfig.USER, null, OAuth2Provider.LOCAL, "2")
    );
    private static final List<Movie> MOVIES = Arrays.asList(
            new Movie("tt9315054", "智能逆袭", "https://m.media-amazon.com/images/M/MV5BN2RlNmE0MzktNjEyNi00NDcxLThhYjItZWQ4NjM3ZDIzMDMxXkEyXkFqcGdeQXVyNjEwNTM2Mzc@._V1_UY268_CR16,0,182,268_AL_.jpg", "https://ddrk.me/next/"),
            new Movie("tt1190634", "黑袍纠察队 (第2季)", "https://m.media-amazon.com/images/M/MV5BNGEyOGJiNWEtMTgwMi00ODU4LTlkMjItZWI4NjFmMzgxZGY2XkEyXkFqcGdeQXVyNjcyNjcyMzQ@._V1_UX182_CR0,0,182,268_AL_.jpg", "https://ddrk.me/the-boys/")
    );
    private static final Set<Bookmark> BOOKMARKS = Stream.of(
            new Bookmark("影评", "不错！"),
            new Bookmark("今日影评", "不错！不错！")
    ).collect(Collectors.toSet());
    private static final Set<Bookmark> BOOKMARKS2 = Stream.of(
            new Bookmark("影评hah", "hahhahhah！"),
            new Bookmark("hah今日影评", "hah")
    ).collect(Collectors.toSet());
    private final UserService userService;
    private final BookmarkService bookmarkService;
    private final PasswordEncoder passwordEncoder;
    private final MovieService movieService;

    @Override
    public void run(String... args) {
        if (!userService.getUsers().isEmpty()) {
            return;
        }
        USERS.forEach(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.saveUser(user);
        });
        MOVIES.forEach(movieService::saveMovie);
        BOOKMARKS.forEach(bookmark -> {
            bookmark.setMovie(MOVIES.get(0));
            bookmark.setUser(USERS.get(1));
            bookmarkService.saveBookmark(bookmark);
        });
        BOOKMARKS2.forEach(bookmark -> {
            bookmark.setMovie(MOVIES.get(1));
            bookmark.setUser(USERS.get(1));
            bookmarkService.saveBookmark(bookmark);
        });
        log.info("Congratulation Database initialized");
    }

}
