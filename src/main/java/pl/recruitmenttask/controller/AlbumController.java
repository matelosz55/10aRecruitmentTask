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
import pl.recruitmenttask.repository.AlbumRepository;
import pl.recruitmenttask.repository.ArtistRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/album", produces = "application/json")
public class AlbumController {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;

    public AlbumController(AlbumRepository albumRepository, ArtistRepository artistRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity showAlbums() {
        List<Album> albums = albumRepository.findAll();
        String jsonAlbums = new Gson().toJson(albums);
        return new ResponseEntity(jsonAlbums, HttpStatus.OK);
    }

    @GetMapping("/save")
    @Transactional
    public String save(Model model){
        List<Artist> artists = artistRepository.findAll();
        model.addAttribute("artist",artists);
        model.addAttribute("album",new Album());
        return "/albums/save";
    }

    @PostMapping("/save")
    @Transactional
    public String getForm(@Valid final Album albums, final BindingResult validationResult){
        if(validationResult.hasErrors()){
            return "/albums/save";
        }
        albumRepository.save(albums);
        return "redirect:all";
    }


    @GetMapping("/update/{id}")
    public String editById(@PathVariable long id, Model model){
        model.addAttribute("edit_url", "edit");
        model.addAttribute("albums", albumRepository.findById(id));
        return "albums/update";
    }

    @PostMapping("/update")
    public String editUser(@Valid Album albums, BindingResult result) {
        if (result.hasErrors()) {
            return "albums/update";
        }
        albumRepository.save(albums);
        return "redirect:/albums/all";
    }
}
