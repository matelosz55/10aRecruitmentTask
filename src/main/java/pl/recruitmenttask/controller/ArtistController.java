package pl.recruitmenttask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.recruitmenttask.model.Artist;
import pl.recruitmenttask.repository.ArtistRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistRepository artistRepository;

    public ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping("/all")
    public String showPosts(Model model) {
        List<Artist> artists = artistRepository.findAll();
        model.addAttribute("artist", artists);
        return "/artists/all";
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

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable long id){
        artistRepository.deleteById(id);
        List<Artist> artists = artistRepository.findAll();
        model.addAttribute("artist",artists);
        return "/artists/all";
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
        return "redirect:/artist/all";
    }
}
