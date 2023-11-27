package gestorDatos;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.printing.PDFPageable;
import org.example.Libro;

import java.io.IOException;
import java.util.Date;

public class GestorPDF {

    private String[] lineasPdf;

    public void generarArchivoPDF(Libro libro) throws IOException{
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage(PDRectangle.A6);
        documento.addPage(pagina);
        String[] lineasPdf = this.obtenerLineasPdf(libro);
        this.rellenarPDF(documento, pagina, lineasPdf);
        documento.save(this.generarNombrePdf());
        documento.close();
        System.out.println("Se ha generado " + this.generarNombrePdf());
    }

    public void rellenarPDF(PDDocument documento, PDPage pagina, String[] lineasLibro){
        try(PDPageContentStream contenidoPagina = new PDPageContentStream(documento, pagina)){
            for(int linea=0; linea < lineasLibro.length; linea++){
               contenidoPagina.beginText();
               contenidoPagina.newLineAtOffset(10, pagina.getMediaBox().getHeight()-(10*linea));
               contenidoPagina.setFont(PDType1Font.HELVETICA_BOLD, 8f);
               contenidoPagina.showText(lineasLibro[linea]);
               contenidoPagina.endText();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] obtenerLineasPdf(Libro libro){
        String[] lineasPdf = new String[6];
        lineasPdf[0] = "";
        lineasPdf[1] = "Biblioteca UFRO";
        lineasPdf[2] = "Libro: " + libro.getNombre();
        lineasPdf[3] = "Autor: " + libro.getAutor();
        lineasPdf[4] = "Editorial: " + libro.getEditorial();
        lineasPdf[5] = "ISBN: " + libro.getIsbn();
        return lineasPdf;
    }

    private String generarNombrePdf(){
        Date fecha = new Date();
        String fechaArchivo = fecha.toString();
        fechaArchivo = fechaArchivo.replace(" ","").replace(":", "");
        return "target/"+"libro"+fechaArchivo+".pdf";
    }
}

