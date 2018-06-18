package es.altair.springhibernate.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.Date;
import java.util.List;
import java.util.Random;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Pdf {
	
	 public void writePDF(List<PagosString> pagos) throws FileNotFoundException, DocumentException {
		// Se crea el documento
		 Document documento = new Document();
		 String home = System.getProperty("user.name");
		 

		Date fecha = new Date();
		System.out.println(fecha.toString());
		String nombrePago="pago"+fecha.getTime();
		 // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
		FileOutputStream ficheroPdf = new FileOutputStream("/Users/"+home+"/Downloads/" + nombrePago + ".pdf");
		
		 // Se asocia el documento al OutputStream y se indica que el espaciado entre
		 // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
		 PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);

		 // Se abre el documento.
		 documento.open();
		 documento.add(new Paragraph("Estos son todos tus pagos realizados al club de padel"));

		 documento.add( Chunk.NEWLINE );
		 
		 PdfPTable tabla = new PdfPTable(3);
		 
		 tabla.addCell("NOMBRE");
		 tabla.addCell("FECHA");
		 tabla.addCell("PAGO");
		 int cont=0;
		 for (PagosString pagosString : pagos) {
			 cont++;
			 tabla.addCell(pagosString.getNombre());
			 tabla.addCell(pagosString.getFecha());
			 tabla.addCell("20 euros");
		}
		 PdfPCell celdaFinal = new PdfPCell(new Paragraph("Pago total: "+cont*20+" euros"));
		 celdaFinal.setColspan(3);
         tabla.addCell(celdaFinal);
		 
		 documento.add(tabla);
		 documento.close();
	
	 
	 }
	
}
