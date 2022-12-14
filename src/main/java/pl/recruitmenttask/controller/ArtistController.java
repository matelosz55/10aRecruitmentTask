package pl.recruitmenttask.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.recruitmenttask.model.Artist;
import pl.recruitmenttask.repository.ArtistRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/artist", produces = "application/json")
public class ArtistController {
    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity showArtists() {
        List<Artist> artists = artistRepository.findAll();
        String jsonArtists = new Gson().toJson(artists);
        return new ResponseEntity(jsonArtists, HttpStatus.OK);
    }

    @GetMapping("/save")
    public String save(Model model){
        model.addAttribute("artist",new Artist());
        return "/artists/save";
    }

    @PostMapping("/save")
    public String getForm(@Valid final Artist artist, final BindingResult validationResult){
        if(validationResult.hasErrors()){
            return "/artists/save";
        }
        artistRepository.save(artist);
        return "redirect:all";
    }


    @GetMapping("/update/{id}")
    public String editById(@PathVariable long id, Model model){
        model.addAttribute("edit_url", "edit");
        model.addAttribute("artist", artistRepository.findById(id));
        return "artists/update";
    }

    @PostMapping("/update")
    public String editUser(@Valid Artist artist, BindingResult result) {
        if (result.hasErrors()) {
            return "artists/update";
        }
        artistRepository.save(artist);
        return "redirect:/all";
    }
}
