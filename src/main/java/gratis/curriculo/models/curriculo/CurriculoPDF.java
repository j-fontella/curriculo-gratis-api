package gratis.curriculo.models.curriculo;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import gratis.curriculo.models.login.Usuario;
import gratis.curriculo.utils.Utils;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;


public class CurriculoPDF {

    private final Curriculo curriculo;

    public CurriculoPDF(Curriculo curriculo) {
        this.curriculo = curriculo;
    }

    public void gerarHeader(Document document){
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(22);
        font.setColor(Color.BLACK);

        Usuario usuario = curriculo.getUsuario();

        Paragraph header = new Paragraph(usuario.getNome(), font);
        header.setAlignment(Paragraph.ALIGN_LEFT);
        header.setLeading(10);
        document.add(header);

        String textoHeaderBody = usuario.getNacionalidade() + ", " + usuario.getEstadoCivil() + ", " + usuario.getIdade()  + " anos" + "\n"
                                + usuario.getEndereco().getEnderecoCompletoParaImpressao() + "\n"
                                + "Telefone: " + usuario.getTelefone() + "\n"
                                + "Email: " + usuario.getEmail();
        font = getFontTamanhoPadrao();
        Paragraph headerBody = new Paragraph(textoHeaderBody, font);
        headerBody.setAlignment(Paragraph.ALIGN_LEFT);
        headerBody.setLeading(20);
        document.add(Chunk.NEWLINE);
        document.add(headerBody);
        document.add(Chunk.NEWLINE);

    }

    public void gerarBody(Document document){
            for(Especificacao especificacao : curriculo.getEspecificacoes()){
                String textoEspecificacao = especificacao.getCategoria();
                Font font = getFontTamanhoCategoria();
                Paragraph especificacaoParagraph = new Paragraph(textoEspecificacao, font);
                especificacaoParagraph.setAlignment(Paragraph.ALIGN_LEFT);
                especificacaoParagraph.setLeading(17);
                LineSeparator ln = new LineSeparator();
                ln.setAlignment(LineSeparator.ALIGN_CENTER);
                document.add(especificacaoParagraph);
                document.add(Chunk.NEWLINE);
                document.add(ln);

                if(especificacao.getItens().isEmpty()){
                    continue;
                }
                if(especificacao.getItens().size() != 1){
                    especificacao.getItens().forEach(item -> {
                        insereItemMultiploDocument(document,item);
                    });
                }else{
                    Item item = especificacao.getItens().get(0);
                    insereItemIndividualDocument(document,item);
                }
            }

    }

    public void imprimir(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        gerarHeader(document);
        gerarBody(document);
        document.close();
    }

    private Font getFontTamanhoPadrao(){
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        font.setColor(Color.BLACK);
        return font;
    }

    private Font getFontTamanhoPadraoBold(){
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(12);
        font.setColor(Color.BLACK);
        return font;
    }

    private Font getFontTamanhoCategoria(){
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(14);
        font.setColor(Color.BLACK);
        return font;
    }

    private void insereItemIndividualDocument(Document document, Item item){
        if(item.temHeader()){
            Paragraph itemHeaderParagraph = new Paragraph(item.getTextoHeader(), getFontTamanhoPadraoBold());
            itemHeaderParagraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(itemHeaderParagraph);
        }
        String textoItem = item.getDescricao();
        Font fontItem = getFontTamanhoPadrao();

        Paragraph itemParagraph = new Paragraph(textoItem, fontItem);
        itemParagraph.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(itemParagraph);
        document.add(Chunk.NEWLINE);
    }

    private void insereItemMultiploDocument(Document document, Item item){
        boolean temHeader = false;
        if(item.temHeader()){
            temHeader = true;
            Paragraph itemHeaderParagraph = new Paragraph(Utils.ordenacaoLista + " " + item.getTextoHeader(), getFontTamanhoPadraoBold());
            itemHeaderParagraph.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(itemHeaderParagraph);
        }
        String textoItem = temHeader ? item.getDescricao() : Utils.ordenacaoLista + " " + item.getDescricao();
        Font fontItem = getFontTamanhoPadrao();

        Paragraph itemParagraph = new Paragraph(textoItem, fontItem);
        itemParagraph.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(itemParagraph);
        document.add(Chunk.NEWLINE);
    }


}
