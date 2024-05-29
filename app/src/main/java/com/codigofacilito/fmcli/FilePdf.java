package com.codigofacilito.fmcli;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Transforma un archivo dado a un archivo pdf
 * La conversión solo soporta archivos txt,csv,png,jpg,jpeg
 */

public class FilePdf {

    /**
     * Debe ingresar el directorio donde se encuentra el archivo desado, el nombre del archivo y
     * el nombre que va te tener el archivo cuando se convierta en pdf
     */
    public PdfFileResult transformToPdf(String rootDirectory, String inputFileName, String outputFileName) {
        File inputFile = new File(rootDirectory, inputFileName);

        //Chequeamos que el nombre termine con .pdf y si no es el caso se lo agrega
        if (!outputFileName.endsWith(".pdf")) {
            outputFileName += ".pdf";
        }
        File outputFile = new File(rootDirectory, outputFileName);

        //Validamos si el archivo existe
        if (!inputFile.exists() || !inputFile.isFile()) {
            System.out.println("El archivo de entrada no existe o no es un archivo valido.");
            return new PdfFileError();
        }

        try (PdfWriter writer = new PdfWriter(outputFile);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document document = new Document(pdfDoc)) {

            String fileExtension = getFileExtension(inputFileName);

            //Validamos casos acorde al tipo de  extensión del archivo
            switch (fileExtension) {
                case "txt":
                case "csv":
                    String content = Files.readString(Path.of(inputFile.getAbsolutePath()));
                    document.add(new Paragraph(content));
                    break;
                case "png":
                case "jpg":
                case "jpeg":
                    Image img = new Image(ImageDataFactory.create(inputFile.getAbsolutePath()));
                    document.add(img);
                    break;
                default:
                    System.out.println("Formato de archivo no soportado: " + fileExtension);
                    return new PdfFileError();
            }
            System.out.println("Archivo creado exitosamente");
            return new PdfFileSuccess(outputFile);

        } catch (IOException e) {
            System.out.println("Error al crear el archivo PDF: " + e.getMessage());
            return new PdfFileError();
        }
    }

    private String getFileExtension(String fileName) {
        //Busca la posición del punto
        int dotIndex = fileName.lastIndexOf('.');

        //Extrae lo encontrado despues del punto
        //De no encontrar nada devuelve -1
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1).toLowerCase();
    }
}


