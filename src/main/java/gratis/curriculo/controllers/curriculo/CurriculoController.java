package gratis.curriculo.controllers.curriculo;

import gratis.curriculo.dtos.request.curriculo.CurriculoRequestDTO;
import gratis.curriculo.services.curriculo.CurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = "/curriculo")
public class CurriculoController {

    @Autowired
    CurriculoService curriculoService;

    @PostMapping(value = "/gerarIndividual")
    public void gerarCurriculo(HttpServletResponse response, @Valid @RequestBody CurriculoRequestDTO curriculoRequestDTO) throws IOException {
        curriculoService.gerar(response, curriculoRequestDTO);
    }
}
