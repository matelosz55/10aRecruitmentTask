package pl.recruitmenttask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.recruitmenttask.model.Album;
import pl.recruitmenttask.model.Artist;
import pl.recruitmenttask.repository.AlbumRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/album")
public class AlbumController {
    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("/all")
    public String showPosts(Model model) {
        List<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);
        return "/albums/all";
    }

    @GetMapping("/save")
    public String save(Model model){
        model.addAttribute("albums",new Album());
        return "/albums/save";
    }

    @PostMapping("/save")
    public String getForm(@Valid final Album albums, final BindingResult validationResult){
        if(validationResult.hasErrors()){
            return "/albums/save";
        }
        albumRepository.save(albums);
        return "redirect:all";
    }

    @GetMapping("delete/{id}")
    public String delete(Model model, @PathVariable long id){
        albumRepository.deleteById(id);
        List<Album> albums = albumRepository.findAll();
        model.addAttribute("album",albums);
        return "/albums/all";
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
