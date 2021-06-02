package com.feriantes.portafolio;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Stream;

import com.feriantes.portafolio.to.DetalleProcesoTO;
import com.feriantes.portafolio.to.ProcesoTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfService {

	public static ByteArrayOutputStream generaPDF(List<ProcesoTO> listaPDF) throws FileNotFoundException, DocumentException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter.getInstance(document,out);
		document.open();

		PdfPTable table = new PdfPTable(7);
		addTableHeader(table);
		addRows(table, listaPDF);
//				addCustomRows(table);
		document.add(table);
		document.close();
		 return out;
	}

	private static void addTableHeader(PdfPTable table) {
		Stream.of("Id Proceso","Estado","Nombre Proceso","Fecha Inicio","Fecha Termino", "Nombre Producto", "cantidad").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.BLUE);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private static void addRows(PdfPTable table, List<ProcesoTO> listaPDF) {
		for (ProcesoTO procesoTO : listaPDF) {
			if( procesoTO.getListaDetalleProceso() == null)
				continue;
			for (DetalleProcesoTO item : procesoTO.getListaDetalleProceso()) {
				table.addCell(""+procesoTO.getIdProceso());
				table.addCell(procesoTO.getGlosaEstado());
				table.addCell(procesoTO.getNombreProceso());
				table.addCell(procesoTO.getFechaInicio());
				table.addCell(procesoTO.getFechaTermino());
				table.addCell(item.getNombreProducto());
				table.addCell(""+item.getCantidad());
			}
		}
		
		
		
	}

//	private static void addCustomRows(PdfPTable table) throws URISyntaxException, BadElementException, IOException {
//		Path path = Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
//		Image img = Image.getInstance(path.toAbsolutePath().toString());
//		img.scalePercent(10);
//
//		PdfPCell imageCell = new PdfPCell(img);
//		table.addCell(imageCell);
//
//		PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
//		horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
//		table.addCell(horizontalAlignCell);
//
//		PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
//		verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
//		table.addCell(verticalAlignCell);
//	}
}
