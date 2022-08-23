package pl.recruitmenttask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.recruitmenttask.model.Album;
import pl.recruitmenttask.model.Song;
import pl.recruitmenttask.repository.SongRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/song")
public class SongController {
    private final SongRepository songRepository;

    public SongController(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @GetMapping("/all")
    public String showPosts(Model model) {
        List<Song> songs = songRepository.findAll();
        model.addAttribute("songs", songs);
        return "/songs/all";
    }

    @GetMapping("/save")
    public String save(Model model){
        model.addAttribute("songs",new Song());
        return "/songs/save";
    }

    @PostMapping("/save")
    public String getForm(@Valid final Song songs, final BindingResult validationResult){
        if(validationResult.hasErrors()){
            return "/songs/save";
        }
        songRepository.save(songs);
        return "redirect:all";
    }

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable long id){
        songRepository.deleteById(id);
        List<Song> songs = songRepository.findAll();
        model.addAttribute("song",songs);
        return "/songs/all";
    }

    @GetMapping("/update/{id}")
    public String editById(@PathVariable long id, Model model){
        model.addAttribute("edit_url", "edit");
        model.addAttribute("songs", songRepository.findById(id));
        return "songs/update";
    }

    @PostMapping("/update")
    public String editUser(@Valid Song songs, BindingResult result) {
        if (result.hasErrors()) {
            return "songs/update";
        }
        songRepository.save(songs);
        return "redirect:/songs/all";
    }

}
