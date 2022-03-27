package gratis.curriculo.services.curriculo;


import gratis.curriculo.dtos.request.curriculo.CurriculoRequestDTO;
import gratis.curriculo.models.curriculo.Curriculo;
import gratis.curriculo.models.curriculo.CurriculoPDF;
import gratis.curriculo.utils.Utils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CurriculoService {

    public void gerar(HttpServletResponse response, CurriculoRequestDTO curriculoRequestDTO) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        Curriculo curriculo = Utils.converterCurriculoRequest(curriculoRequestDTO);
        CurriculoPDF pdf = new CurriculoPDF(curriculo);
        pdf.imprimir(response);
    }
}
