import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Main {

    // Method untuk membaca PDF
    public static String readPDF(String filePath) throws IOException {
        try (PDDocument document = Loader.loadPDF(Path.of(filePath).toFile())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    // Method untuk menyimpan ke TXT
    public static void writeTXT(String filePath, String content) throws IOException {

        // Membuat folder output jika belum ada
        Files.createDirectories(Path.of("output"));

        // Menulis isi ke file TXT
        Files.writeString(Path.of(filePath), content);
    }

    public static void main(String[] args) {

        String pdfFile = "input/putusan_1012_pid.sus_2026_pn_sby_20260928093625.pdf";
        String txtFile = "output/hasil.txt";

        try {

            // Membaca isi PDF
            String content = readPDF(pdfFile);

            // Menghapus teks yang tidak ingin disalin
            content = content.replace(
                    "Mahkamah Agung Republik Indonesia",
                    ""
            );

            // Menyimpan hasil ke TXT
            writeTXT(txtFile, content);

            // Menampilkan pesan
            System.out.println("PDF berhasil dibaca.");
            System.out.println("Hasil disimpan ke: " + txtFile);

        } catch (IOException e) {

            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}