package pl.recruitmenttask.controller;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.recruitmenttask.model.Album;
import pl.recruitmenttask.model.Artist;
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
    @ResponseBody
    public ResponseEntity showSongs() {
        List<Song> songs = songRepository.findAll();
        String jsonSongs = new Gson().toJson(songs);
        return new ResponseEntity(jsonSongs, HttpStatus.OK);
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
