package Util;
import Alert.UserAlert;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import javax.swing.ImageIcon;

public class ImageFunctions{
    
    // Gera imagem com tamanhos recalculados para caber em tela
    public static Image imageResized(BufferedImage imageBuffer, int imageHeight, int imageWidth, int smooth){

        ImageIcon imageIcon = new ImageIcon(imageBuffer); 
        Image image = imageIcon.getImage();
        return image.getScaledInstance(imageHeight, imageWidth,  smooth);
    }
    public static Image imageResized(String imagePath, int imageHeight, int imageWidth, int smooth){

        ImageIcon imageIcon = new ImageIcon(imagePath); 
        Image image = imageIcon.getImage();
        return image.getScaledInstance(imageHeight, imageWidth,  smooth);
    }

    // Faz cópia do buffer de uma imagem
    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    // Inverte a Imagem em tela Horizontalmente
    public static void imageInverterHorizontal(BufferedImage imageBuffer, int[][] imageRGBA) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        for(int lin = 0; lin < imageHeight; lin++){
            for(int col = 0; col < imageWidth; col++){
                // Para inverter Horizontalmente apenas as colunas devem ser trocadas de posição
                imageBuffer.setRGB(imageWidth - 1 - col, lin, imageRGBA[lin][col]); // Carrega-se o buffer com a imagem alterada
            }
        }
    }

    // Inverte a Imagem em tela Verticalmente
    public static void imageInverterVertical(BufferedImage imageBuffer, int[][] imageRGBA) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        for(int lin = 0; lin < imageHeight; lin++){
            for(int col = 0; col < imageWidth; col++){
                // Para inverter Verticalmente apenas as linhas devem ser trocadas de posição
                imageBuffer.setRGB(col, imageHeight - 1 - lin, imageRGBA[lin][col]); // Carrega-se o buffer com a imagem alterada
            }
        }
    }

    // Recebe o buffer de uma imagem e a converte em Matriz de cores com bits corrigidos
    public static int[][] imageToRGBA(BufferedImage imageBuffer) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData(); // Pega os bytes dos pixels da bufferedImage
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null; // Se é uma imagem com opacidade

        int[][] result = new int[imageHeight][imageWidth];

        // Imagem com canal alpha tem shft de bits diferente
        if (!hasAlphaChannel){
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0;

                // Posiciona os conjuntos de 8 bits da imagem em um padrão de 32 bits com -> red | green | blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 16);    // red
                argb += (((int) pixels[pixel + 1] & 0xff) << 8);     // green
                argb += ((int) pixels[pixel] & 0xff);                // blue

                result[row][col] = argb;

                col++;

                if (col == imageWidth) {
                    col = 0;
                    row++;
                }
            }
         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
  
        return result;
     }


     public static BufferedImage imageToGray(BufferedImage imageBuffer) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        BufferedImage imageBufferRetorno = new BufferedImage(imageWidth, imageHeight, imageBuffer.getType());

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;

        if (!hasAlphaChannel){
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0, r, g, b;
                
                r = ((int) pixels[pixel + 2] & 0xff);
                g = ((int) pixels[pixel + 1] & 0xff);
                b = ((int) pixels[pixel] & 0xff);

                int average = (int) (r * 0.299 + g * 0.587 + b * 0.114); 
                
                argb += average; // blue
                argb += (average << 8); // green
                argb += (average << 16); // red

                imageBufferRetorno.setRGB(col, row, argb);

               col++;
               if (col == imageWidth) {
                  col = 0;
                  row++;
               }
            }
         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
         return imageBufferRetorno;
     }

     public static BufferedImage imageQuantization(BufferedImage imageBuffer, int quantization) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        BufferedImage imageBufferRetorno = new BufferedImage(imageWidth, imageHeight, imageBuffer.getType());

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        // Largura e altura da imagem enviada em PXs

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;

        if (!hasAlphaChannel) {

            final int pixelLength = 3;

            // Loop pra conseguir valores de luminância MÁXIMA e MÍNIMA
            int lumMAX = 0;
            int lumMIN = 255;
            int lumREP;
            int tamBIN;

            // Loop por todos os pixels da imagem
            for (int pixel = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int r, g, b;
                
                // Pega os valores R, G e B da imagem no intrvalo [0,255]
                r = ((int) pixels[pixel + 2] & 0xff);
                g = ((int) pixels[pixel + 1] & 0xff);
                b = ((int) pixels[pixel] & 0xff);

                // Soma dos valores 
                int sum = (int) (r * 0.299 + g * 0.587 + b * 0.114); 

                if(sum > lumMAX){
                    lumMAX = sum;
                }
                if(sum < lumMIN){
                    lumMIN = sum;
                }

                col++;
                if (col == imageWidth) {
                    col = 0;
                }
            }

            lumREP = lumMAX - lumMIN + 1;
            tamBIN = lumREP / quantization;

            // Loop de criação da quantização

            if(quantization <= lumREP){
                for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                    int argb = 0, r, g, b;
                    
                    r = ((int) pixels[pixel + 2] & 0xff);
                    g = ((int) pixels[pixel + 1] & 0xff);
                    b = ((int) pixels[pixel] & 0xff);

                    int average = (int) (r * 0.299 + g * 0.587 + b * 0.114); 

                    for(int countBIN = 1; countBIN <= quantization; countBIN++){
                        if((average >= lumMIN - 0.5 + ((countBIN - 1) * tamBIN))
                        && (average <= lumMIN - 0.5 + ((countBIN) * tamBIN))
                        ){
                            average = lumMIN + ((countBIN - 1) * tamBIN);
                        }
                    }
                    
                    argb += average; // blue
                    argb += (average << 8); // green
                    argb += (average << 16); // red

                    imageBufferRetorno.setRGB(col, row, argb);

                    col++;
                    if (col == imageWidth) {
                        col = 0;
                        row++;
                    }
                }
            }else{
                imageBufferRetorno = imageToGray(imageBuffer);
            }
         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
         return imageBufferRetorno;
     }

     public static BufferedImage imageBrightness(BufferedImage imageBuffer, int brightness) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        BufferedImage imageBufferRetorno = new BufferedImage(imageWidth, imageHeight, imageBuffer.getType());

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;

        if (!hasAlphaChannel) {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0, r = 0, g = 0, b = 0;
                
                r = ((int) pixels[pixel + 2] & 0xff) + brightness;
                g = ((int) pixels[pixel + 1] & 0xff) + brightness;
                b = ((int) pixels[pixel] & 0xff) + brightness;
                if(r > 255) r = 255;
                if(r < 0) r = 0;
                if(g > 255) g = 255;
                if(g < 0) g = 0;
                if(b > 255) b = 255;
                if(b < 0) b = 0;

                
                argb += b; // blue
                argb += (g << 8); // green
                argb += (r << 16); // red

                imageBufferRetorno.setRGB(col, row, argb);

               col++;
               if (col == imageWidth) {
                  col = 0;
                  row++;
               }
            }
         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
         return imageBufferRetorno;
     }


     public static BufferedImage imageContrast(BufferedImage imageBuffer, int contrast) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        BufferedImage imageBufferRetorno = new BufferedImage(imageWidth, imageHeight, imageBuffer.getType());

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;

        if (!hasAlphaChannel) {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0, r = 0, g = 0, b = 0;
                
                r = ((int) pixels[pixel + 2] & 0xff) * contrast;
                g = ((int) pixels[pixel + 1] & 0xff) * contrast;
                b = ((int) pixels[pixel] & 0xff) * contrast;
                if(r > 255) r = 255;
                if(r < 0) r = 0;
                if(g > 255) g = 255;
                if(g < 0) g = 0;
                if(b > 255) b = 255;
                if(b < 0) b = 0;

                
                argb += b; // blue
                argb += (g << 8); // green
                argb += (r << 16); // red

                imageBufferRetorno.setRGB(col, row, argb);

               col++;
               if (col == imageWidth) {
                  col = 0;
                  row++;
               }
            }
         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
         return imageBufferRetorno;
     }

     public static BufferedImage imageNegative(BufferedImage imageBuffer) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        BufferedImage imageBufferRetorno = new BufferedImage(imageWidth, imageHeight, imageBuffer.getType());

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;

        if (!hasAlphaChannel) {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0, r = 0, g = 0, b = 0;
                
                r = 255 - ((int) pixels[pixel + 2] & 0xff);
                g = 255 - ((int) pixels[pixel + 1] & 0xff);
                b = 255 - ((int) pixels[pixel] & 0xff);
                if(r > 255) r = 255;
                if(r < 0) r = 0;
                if(g > 255) g = 255;
                if(g < 0) g = 0;
                if(b > 255) b = 255;
                if(b < 0) b = 0;

                
                argb += b; // blue
                argb += (g << 8); // green
                argb += (r << 16); // red

                imageBufferRetorno.setRGB(col, row, argb);

               col++;
               if (col == imageWidth) {
                  col = 0;
                  row++;
               }
            }
         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
         return imageBufferRetorno;
     }


     public static BufferedImage imageRotation(BufferedImage imageBuffer, int[][] imageRGBA, boolean rotationClockwise) {

        int imageHeight = imageRGBA.length;
        int imageWidth = imageRGBA[0].length;

        BufferedImage imageBufferRetorno = new BufferedImage(imageHeight, imageWidth, imageBuffer.getType());

        for(int lin = 0; lin < imageHeight; lin++){
            for(int col = 0; col < imageWidth; col++){
                // Para inverter Verticalmente apenas as linhas devem ser trocadas de posição
                if(rotationClockwise == false){
                    imageBufferRetorno.setRGB(lin, imageWidth - col - 1, imageRGBA[lin][col]); // Carrega-se o buffer com a imagem alterada
                }else{
                    imageBufferRetorno.setRGB(imageHeight - lin - 1, col, imageRGBA[lin][col]);
                }
            }
        }

         return imageBufferRetorno;
     }


     public static BufferedImage imageFilter(BufferedImage imageBuffer, double[][] vectorFilter, String filterSelected) {

        int imageWidth = imageBuffer.getWidth();

        int somaBrilho = 0;

        // Todos menos o filtro passa baixa devem ser passados para tons de luminância
        if(filterSelected.compareTo("gaussiano") != 0){
            imageBuffer = imageToGray(imageBuffer);
        }
        if(filterSelected.compareTo("prewitthx") == 0
        || filterSelected.compareTo("prewitthy") == 0
        || filterSelected.compareTo("sobelhx") == 0
        || filterSelected.compareTo("sobelhy") == 0){
            somaBrilho = 127;
        }

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        BufferedImage imageBufferRetorno = deepCopy(imageBuffer);

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;

        if (!hasAlphaChannel) {
            final int pixelLength = 3;
            for (int pixel = (pixelLength * imageWidth) + pixelLength, row = 1, col = 1; pixel + 2 < pixels.length - (pixelLength * imageWidth) - pixelLength; pixel += pixelLength) {
                
                int argb = 0;


                // Voltamos 1 linha e 1 elemento
                int pixelAux = pixel - (pixelLength * imageWidth) - pixelLength;
                
                int[] r1 = {0,0,0}, g1 = {0,0,0}, b1 = {0,0,0};
                for(int mt = 0; mt < 3; mt++){
                    r1[mt] = ((int) pixels[pixelAux + 2] & 0xff);
                    g1[mt] = ((int) pixels[pixelAux + 1] & 0xff);
                    b1[mt] = ((int) pixels[pixelAux ] & 0xff);
                    pixelAux += pixelLength;
                }

                
                // Voltamos 1 elemento
                pixelAux = pixel - pixelLength;
                int[] r2 = {0,0,0}, g2 = {0,0,0}, b2 = {0,0,0};
                for(int mt = 0; mt < 3; mt++){
                    r2[mt] = ((int) pixels[pixelAux + 2] & 0xff);
                    g2[mt] = ((int) pixels[pixelAux + 1] & 0xff);
                    b2[mt] = ((int) pixels[pixelAux] & 0xff);
                    pixelAux += pixelLength;
                }

                
                // Avançamos 1 linha menos 1 elemento
                pixelAux = pixel + (pixelLength * imageWidth) - pixelLength;
                int[] r3 = {0,0,0}, g3 = {0,0,0}, b3 = {0,0,0};
                for(int mt = 0; mt < 3; mt++){
                    r3[mt] = ((int) pixels[pixelAux + 2] & 0xff);
                    g3[mt] = ((int) pixels[pixelAux + 1] & 0xff);
                    b3[mt] = ((int) pixels[pixelAux] & 0xff);
                    pixelAux += pixelLength;
                }

                
                double resultRED = 0, resultGREEN = 0, resultBLUE = 0;
                for(int sum = 0; sum < 3; sum++){
                    resultRED       = resultRED     +   (r1[sum] * vectorFilter[0][sum]) + (r2[sum] * vectorFilter[1][sum]) + (r3[sum] * vectorFilter[2][sum]); 
                    resultGREEN     = resultGREEN   +   (g1[sum] * vectorFilter[0][sum]) + (g2[sum] * vectorFilter[1][sum]) + (g3[sum] * vectorFilter[2][sum]); 
                    resultBLUE      = resultBLUE    +   (b1[sum] * vectorFilter[0][sum]) + (b2[sum] * vectorFilter[1][sum]) + (b3[sum] * vectorFilter[2][sum]); 
                }

                resultRED += somaBrilho;
                resultGREEN += somaBrilho;
                resultBLUE += somaBrilho;

                if(resultRED > 255){
                    resultRED = 255;
                }else if(resultRED < 0 ){
                    resultRED = 0;
                }if(resultGREEN > 255){
                    resultGREEN = 255;
                }else if(resultGREEN < 0 ){
                    resultGREEN = 0;
                }if(resultBLUE > 255){
                    resultBLUE = 255;
                }else if(resultBLUE < 0 ){
                    resultBLUE = 0;
                }

                argb += (((int) resultBLUE)); // blue
                argb += (((int) resultGREEN) << 8); // green
                argb += (((int) resultRED) << 16); // red
                

                imageBufferRetorno.setRGB(col, row, argb);

                //nao deixa chegar em ultimas colunas
                col++;
                if (col + 1 == imageWidth) {
                    col = 0;
                    row++;
                    pixel += pixelLength;
                }
            }
         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
         return imageBufferRetorno;
     }

     public static BufferedImage imageZoomOut(BufferedImage imageBuffer, int xDiv, int yDiv) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        BufferedImage imageBufferRetorno = new BufferedImage(((imageWidth - (imageWidth % xDiv)) / xDiv) + (imageWidth % xDiv == 0 ? 0 : 1), ((imageHeight - (imageHeight % yDiv)) / yDiv) + (imageHeight % yDiv == 0 ? 0 : 1), imageBuffer.getType());

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;

        if (!hasAlphaChannel) {
            final int pixelLength = 3;
            int nextCol = 0;
            int nextRow = 0;

            int colRESULT = 0, rowRESULT = 0;
            for (int pixel = 0, col = 0, row = 0; pixel + 2 < pixels.length; pixel += pixelLength) {

                int argb = 0;
                int pixelAux = pixel;
                
                if(col == nextCol && row == nextRow){
                    
                    int avarageAuxRED = 0, avarageAuxGREEN = 0, avarageAuxBLUE  = 0;
                    int countPixelsSelected = 0;

                    for(int rowAux = row; rowAux < row + yDiv; rowAux++){
                        for(int colAux = col; colAux < col + xDiv; colAux++){
                            if(pixelAux + 2 < pixels.length){
                                avarageAuxRED += ((int) pixels[pixelAux + 2] & 0xff);
                                avarageAuxGREEN += ((int) pixels[pixelAux + 1] & 0xff);
                                avarageAuxBLUE += ((int) pixels[pixelAux] & 0xff);

                                countPixelsSelected++;
                            }
                            pixelAux += pixelLength;
                        }
                        // Volta coluna para primeira do loop, e avança uma linha
                        pixelAux = (pixelAux - (xDiv * pixelLength)) + (pixelLength * imageWidth);
                    }
                    avarageAuxRED   = avarageAuxRED     / countPixelsSelected; 
                    avarageAuxGREEN = avarageAuxGREEN   / countPixelsSelected;
                    avarageAuxBLUE  = avarageAuxBLUE    / countPixelsSelected;

                    argb += (((int) avarageAuxBLUE)); // blue
                    argb += (((int) avarageAuxGREEN) << 8); // green
                    argb += (((int) avarageAuxRED) << 16); // red

                    
                    imageBufferRetorno.setRGB(colRESULT, rowRESULT, argb);

                    if(nextCol + xDiv >= imageWidth){
                        nextCol = 0;
                        colRESULT = 0;
                        if(nextRow + yDiv >= imageHeight){
                            break;
                        }else{
                            nextRow += yDiv;
                            rowRESULT++;
                        }
                    }else{
                        nextCol += xDiv;
                        colRESULT++;
                    }
                }

                col++;
                if (col == imageWidth) {
                   col = 0;
                   row++;
                }
            }
         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
         return imageBufferRetorno;
     }

     public static BufferedImage imageZoomIn(BufferedImage imageBuffer) {

        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        // Pega os bytes dos pixels da bufferedImage
        byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        BufferedImage imageBufferRetorno = new BufferedImage((imageWidth * 2) - 1, (imageHeight * 2) - 1, imageBuffer.getType());

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;

        if (!hasAlphaChannel) {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0, r = 0, g = 0, b = 0;
                
                r = ((int) pixels[pixel + 2] & 0xff);
                g = ((int) pixels[pixel + 1] & 0xff);
                b = ((int) pixels[pixel] & 0xff);

                argb += b; // blue
                argb += (g << 8); // green
                argb += (r << 16); // red

                imageBufferRetorno.setRGB(col * 2, row * 2, argb);

               col++;
               if (col == imageWidth) {
                  col = 0;
                  row++;
               }
            }

            pixels = ((DataBufferByte) imageBufferRetorno.getRaster().getDataBuffer()).getData();
            imageHeight = imageBufferRetorno.getHeight();
            imageWidth = imageBufferRetorno.getWidth();

            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0;
            
                int r1 = 0, g1 = 0, b1 = 0;
                int r2 = 0, g2 = 0, b2 = 0;

                if(col % 2 == 1 && row % 2 == 0){
                    r1 = ((int) pixels[pixel - pixelLength + 2] & 0xff);
                    g1 = ((int) pixels[pixel - pixelLength + 1] & 0xff);
                    b1 = ((int) pixels[pixel - pixelLength]     & 0xff);
                
                    r2 = ((int) pixels[pixel + pixelLength + 2] & 0xff);
                    g2 = ((int) pixels[pixel + pixelLength + 1] & 0xff);
                    b2 = ((int) pixels[pixel + pixelLength]     & 0xff);
                
                    argb += (int) ((b1 + b2) / 2); // blue
                    argb += ((int)((g1 + g2) / 2) << 8); // green
                    argb += ((int)((r1 + r2) / 2) << 16); // red
                    
                    imageBufferRetorno.setRGB(col, row, argb);
                }
                if(row % 2 == 1 && col % 2 == 0){
                    r1 = ((int) pixels[pixel - (pixelLength * imageWidth) + 2] & 0xff);
                    g1 = ((int) pixels[pixel - (pixelLength * imageWidth) + 1] & 0xff);
                    b1 = ((int) pixels[pixel - (pixelLength * imageWidth)]     & 0xff);
                
                    r2 = ((int) pixels[pixel + (pixelLength * imageWidth) + 2] & 0xff);
                    g2 = ((int) pixels[pixel + (pixelLength * imageWidth) + 1] & 0xff);
                    b2 = ((int) pixels[pixel + (pixelLength * imageWidth)]     & 0xff);
                
                    argb += (int) ((b1 + b2) / 2); // blue
                    argb += ((int)((g1 + g2) / 2) << 8); // green
                    argb += ((int)((r1 + r2) / 2) << 16); // red
                    
                    imageBufferRetorno.setRGB(col, row, argb);
                }
               col++;
               if (col == imageWidth) {
                  col = 0;
                  row++;
               }
            }

            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0;
            
                int r1 = 0, g1 = 0, b1 = 0;
                int r2 = 0, g2 = 0, b2 = 0;

                if(col % 2 == 1 && row % 2 == 1){
                    r1 = ((int) pixels[pixel - pixelLength + 2] & 0xff);
                    g1 = ((int) pixels[pixel - pixelLength + 1] & 0xff);
                    b1 = ((int) pixels[pixel - pixelLength]     & 0xff);
                
                    r2 = ((int) pixels[pixel + pixelLength + 2] & 0xff);
                    g2 = ((int) pixels[pixel + pixelLength + 1] & 0xff);
                    b2 = ((int) pixels[pixel + pixelLength]     & 0xff);
                
                    argb += (int) ((b1 + b2) / 2); // blue
                    argb += ((int)((g1 + g2) / 2) << 8); // green
                    argb += ((int)((r1 + r2) / 2) << 16); // red
                    
                    imageBufferRetorno.setRGB(col, row, argb);
                }
               col++;
               if (col == imageWidth) {
                  col = 0;
                  row++;
               }
            }

         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
         return imageBufferRetorno;
     }

     public static int[] imageHistogramArray(BufferedImage imageBuffer) {
        imageBuffer = imageToGray(imageBuffer);

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;
        
        int[] arrayReturn = new int[256];

        if (!hasAlphaChannel) {
            final int pixelLength = 3;
            for (int pixel = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int r, g, b;
                
                r = ((int) pixels[pixel + 2] & 0xff);
                g = ((int) pixels[pixel + 1] & 0xff);
                b = ((int) pixels[pixel] & 0xff);

                int average = (int) (r * 0.299 + g * 0.587 + b * 0.114); 

                arrayReturn[average]++;
            }
         }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
         }
         return arrayReturn;
    }

    public static int[] imageNormalizedCumulativeHistogramArray(BufferedImage imageBuffer){
        int[] histogram = imageHistogramArray(imageBuffer);
        double[] histogram_aux = new double[256];
        int[] histogram_return = new int[256];

        double alpha = 255.0 / (imageBuffer.getHeight() * imageBuffer.getWidth());
        
        histogram_aux[0] = (alpha * histogram[0]);
        for(int i = 1; i < 256; i++){
            histogram_aux[i] = (histogram_aux[i - 1] + (alpha * histogram[i]));
            histogram_return[i] = (int)histogram_aux[i];
        }
        return histogram_return;
    }

    public static BufferedImage imageHistogramEqualization(BufferedImage imageBuffer, int[] normalizedHistogram){
        int imageHeight = imageBuffer.getHeight();
        int imageWidth = imageBuffer.getWidth();

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBuffer.getRaster().getDataBuffer()).getData();

        BufferedImage imageBufferRetorno = new BufferedImage(imageWidth, imageHeight, imageBuffer.getType());

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBuffer.getAlphaRaster() != null;

        if (!hasAlphaChannel) {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0, r = 0, g = 0, b = 0;
                
                r = ((int) pixels[pixel + 2] & 0xff);
                g = ((int) pixels[pixel + 1] & 0xff);
                b = ((int) pixels[pixel] & 0xff);

                int average = (int) (r * 0.299 + g * 0.587 + b * 0.114); 

                int newLuminance = normalizedHistogram[average];

                argb += newLuminance; // blue
                argb += (newLuminance << 8); // green
                argb += (newLuminance << 16); // red
                
                imageBufferRetorno.setRGB(col, row, argb);

                col++;
                if (col == imageWidth) {
                    col = 0;
                    row++;
                }
            }
        }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
        }
        return imageBufferRetorno;
    }

    public static BufferedImage imageHistogramMatchingMonochromatic(BufferedImage imageBufferSource, BufferedImage imageBufferTarget){
        int[] histogramSourceCumulative = imageNormalizedCumulativeHistogramArray(imageBufferSource);
        int[] histogramTargetCumulative = imageNormalizedCumulativeHistogramArray(imageBufferTarget);

        int[] histogramMatching = new int[256];

        for(int i = 0; i < 256; i++){
            histogramMatching[i] = findClosestInHistogram(histogramTargetCumulative, histogramSourceCumulative[i]);
        }

        int imageHeight = imageBufferSource.getHeight();
        int imageWidth = imageBufferSource.getWidth();

        // Pega os bytes dos pixels da bufferedImage
        final byte[] pixels = ((DataBufferByte) imageBufferSource.getRaster().getDataBuffer()).getData();

        BufferedImage imageBufferRetorno = new BufferedImage(imageWidth, imageHeight, imageBufferSource.getType());

        // Se é uma imagem com opacidade
        final boolean hasAlphaChannel = imageBufferSource.getAlphaRaster() != null;

        if (!hasAlphaChannel) {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
                int argb = 0, r = 0, g = 0, b = 0;
                
                r = ((int) pixels[pixel + 2] & 0xff);
                g = ((int) pixels[pixel + 1] & 0xff);
                b = ((int) pixels[pixel] & 0xff);

                int average = (int) (r * 0.299 + g * 0.587 + b * 0.114); 

                int newLuminance = histogramMatching[average];

                argb += newLuminance; // blue
                argb += (newLuminance << 8); // green
                argb += (newLuminance << 16); // red
                
                imageBufferRetorno.setRGB(col, row, argb);

                col++;
                if (col == imageWidth) {
                    col = 0;
                    row++;
                }
            }
        }else{
            UserAlert userAlert = new UserAlert("ERRO - A imagem possui canal alpha!");
        }
        return imageBufferRetorno;
    }

    public static int findClosestInHistogram(int[] histogram, int valueSearched){
        int match = 0;
        float lastOneDif = 255;
        for(int i = 0; i < histogram.length; i++){
            if(Math.abs(histogram[i] - valueSearched) < lastOneDif){
                match = i;
                lastOneDif = Math.abs(histogram[i] - valueSearched);
            }
        }
        return match;
    }
}