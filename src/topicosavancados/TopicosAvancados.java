
package topicosavancados;

import ij.ImagePlus;
import ij.process.ImageProcessor;
import java.io.File;
import javax.swing.JFileChooser;
import ij.IJ;
import ij.ImagePlus;
import ij.Prefs;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;
import java.io.File;

public class TopicosAvancados {

    public static void main(String[] args) {
        File escolherArquivo = escolherArquivo();
        String path = escolherArquivo.getAbsolutePath();

        ImagePlus image = new ImagePlus(path);
        ImageProcessor ip = image.getProcessor();
        ImagePlus original = new ImagePlus("Imagem Original", ip);
        original.show();

        ImagePlus imgGrayscale = image.duplicate();
        ImageConverter converterGrayscale = new ImageConverter(imgGrayscale);
        converterGrayscale.convertToGray8();//convertendo a imagem para tons de cinza
        IJ.run(imgGrayscale, "8-bit", "");
        ImageProcessor ipGrayscale = imgGrayscale.getProcessor().duplicate();

        System.out.println("Largura:" + imgGrayscale.getWidth());
        System.out.println("Altura:" + imgGrayscale.getHeight());
        System.out.println("Valores de pixels:");

        String nPixels = "";
        int [] histograma = new int[256];
        for (int i=0;i<256;i++){
            histograma[i]=0;
        }
        for (int linha = 0; linha < imgGrayscale.getWidth(); linha++) {
            for (int coluna = 0; coluna < imgGrayscale.getHeight(); coluna++) {

               histograma[ipGrayscale.getPixel(linha, coluna)]++;
                       //nPixels += +ipGrayscale.getPixel(linha, coluna) + "\n";

            }
        }
        for (int i=0; i<256;i++){
            System.out.println(i+" Qtd "+histograma[i]);
        }
        System.out.println(nPixels);
        imgGrayscale.show();

    }

    public static File escolherArquivo() {
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {

        } else {
            File arquivo = file.getSelectedFile();
            return arquivo;
        }
        return null;
    }

}
