package Content;

import Alert.UserAlert;
import Input.*;
import Screen.*;
import Util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HomeScreenContent extends JPanel{

    private JLabel          imageIcon;

    private JLabel                  labelFileSelector;
    private FileSelectorImages      fileSelector;
    
    private JLabel                  labelFileSaver;
    private FileSelectorSave        fileSaver;

    private JButton         buttonClean;
    private JButton         buttonInvertHorizontal;
    private JButton         buttonInvertVertical;
    private JButton         buttonGray;

    private JTextField      fieldQuant;
    private JButton         buttonQuant;

    private JTextField      fieldBrightness;
    private JButton         buttonBrightness;

    private JTextField      fieldContrast;
    private JButton         buttonContrast;

    private JButton         buttonNegative;

    private JLabel          labelRotate;
    private JButton         buttonRotateClockwise;
    private JButton         buttonRotateAnticlockwise;

    private JLabel          labelFiltros;

    private JButton         buttonGaussiano;
    private JButton         buttonLaplaciano;
    private JButton         buttonPassaAltaGenérico;
    private JButton         buttonPrewittHX;
    private JButton         buttonPrewittHY;
    private JButton         buttonSobelHX;
    private JButton         buttonSobelHY;

    private JTextField      matriz11;
    private JTextField      matriz12;
    private JTextField      matriz13;
    private JTextField      matriz21;
    private JTextField      matriz22;
    private JTextField      matriz23;
    private JTextField      matriz31;
    private JTextField      matriz32;
    private JTextField      matriz33;

    private String          filterSelected;

    private JButton         buttonFilterApply;

    private JLabel          labelZoom;
    private JTextField      fieldZoomOutX;
    private JTextField      fieldZoomOutY;
    private JTextField      fieldZoomInX;
    private JTextField      fieldZoomInY;
    private JButton         buttonZoomOut;
    private JButton         buttonZoomIn;

    private JLabel          labelHistogram;
    private JButton         buttonHistogram;
    private JButton         buttonHistogramEqualized;

    private JLabel          labelHistogramMatching;
    private FileSelector    fileSelectorHistogramMatching;

    public HomeScreenContent(int width, int height){

        //setSize(width, height);
        setLayout(null);
        setPreferredSize( new Dimension (400,1880 ) );
        setBackground(new Color(38,38,38));

        createComponents();
        configComponents();
        addComponents();

    }

    private void createComponents(){

        setImageIcon(new JLabel(new ImageIcon(ImageFunctions.imageResized("img/AppIcon.png", 90, 90, 1))));

        setLabelFileSelector(new JLabel("- CARREGAR IMAGEM -"));

        setFileSelector(new FileSelectorImages(205, new FileNameExtensionFilter("Imagens", "jpeg", "jpg")));   
        
        setLabelFileSaver(new JLabel("- SALVAR IMAGEM -"));

        setFileSaver(new FileSelectorSave(205, new FileNameExtensionFilter("Imagens", "jpeg"), fileSelector));  

        setButtonClean(new JButton("LIMPAR"));

        setButtonInvertHorizontal(new JButton("FLIP HORIZONTAL"));
        setButtonInvertVertical(new JButton("FLIP VERTICAL"));

        setButtonGray(new JButton("TONS DE CINZA"));

        setFieldQuant(new JTextField(""));
        setButtonQuant(new JButton("QUANTIZAR"));

        setFieldBrightness(new JTextField(""));
        setButtonBrightness(new JButton("BRILHO"));

        setFieldContrast(new JTextField(""));
        setButtonContrast(new JButton("CONTRASTE"));

        setButtonNegative(new JButton("NEGATIVO"));

        setLabelRotate(new JLabel("- ROTAÇÕES -"));

        setButtonRotateClockwise(new JButton("HORÁRIA"));
        setButtonRotateAnticlockwise(new JButton("ANTI-HORÁRIA"));

        setLabelFiltros(new JLabel("- FILTROS -"));

        setButtonGaussiano(new JButton("GAUSSIANO"));
        setButtonLaplaciano(new JButton("LAPLACIANO"));
        setButtonPassaAltaGenérico(new JButton("PASSA ALTA GENÉRICO"));
        setButtonPrewittHX(new JButton("PREWITT HX"));
        setButtonPrewittHY(new JButton("PREWITT HY"));
        setButtonSobelHX(new JButton("SOBEL HX"));
        setButtonSobelHY(new JButton("SOBEL HY"));

        setMatriz11(new JTextField("0.0"));
        setMatriz12(new JTextField("0.0"));
        setMatriz13(new JTextField("0.0"));
        setMatriz21(new JTextField("0.0"));
        setMatriz22(new JTextField("0.0"));
        setMatriz23(new JTextField("0.0"));
        setMatriz31(new JTextField("0.0"));
        setMatriz32(new JTextField("0.0"));
        setMatriz33(new JTextField("0.0"));

        setButtonFilterApply(new JButton("APLICAR FILTRO"));

        setLabelZoom(new JLabel("- ZOOM -"));

        setFieldZoomOutX(new JTextField("1"));
        setFieldZoomOutY(new JTextField("1"));

        setFieldZoomInX(new JTextField("2"));
        setFieldZoomInY(new JTextField("2"));

        setbuttonZoomOut(new JButton("ZOOM OUT X x Y"));
        setbuttonZoomIn(new JButton("ZOOM IN 2 x 2"));

        setLabelHistogram(new JLabel("- HISTOGRAMAS -"));
        setButtonHistogram(new JButton("CRIAR HISTOGRAMA"));

        setButtonHistogramEqualized(new JButton("IMAGEM EQUALIZADA"));

        setLabelHistogramMatching(new JLabel("- HISTOGRAM MATCHING -"));
        setFileSelectorHistogramMatching(new FileSelectorMatching(205, new FileNameExtensionFilter("Imagens", "jpeg", "jpg"), fileSelector));   
    }

    private void configComponents(){
        try{
            File fontStyle = new File("Fonts/pixelated_fancy_font.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontStyle);
            
            labelFileSelector.setFont(font.deriveFont(16f));
            labelFileSaver.setFont(font.deriveFont(16f));
            buttonClean.setFont(font.deriveFont(16f));
            buttonInvertHorizontal.setFont(font.deriveFont(16f));
            buttonInvertVertical.setFont(font.deriveFont(16f));
            buttonGray.setFont(font.deriveFont(16f));
            fieldQuant.setFont(font.deriveFont(16f));
            buttonQuant.setFont(font.deriveFont(16f));
            fieldBrightness.setFont(font.deriveFont(16f));
            buttonBrightness.setFont(font.deriveFont(16f));
            fieldContrast.setFont(font.deriveFont(16f));
            buttonContrast.setFont(font.deriveFont(16f));
            buttonNegative.setFont(font.deriveFont(16f));
            labelRotate.setFont(font.deriveFont(16f));
            buttonRotateClockwise.setFont(font.deriveFont(16f));
            buttonRotateAnticlockwise.setFont(font.deriveFont(16f));

            labelFiltros.setFont(font.deriveFont(16f));
            buttonGaussiano.setFont(font.deriveFont(16f));
            buttonLaplaciano.setFont(font.deriveFont(16f));
            buttonPassaAltaGenérico.setFont(font.deriveFont(16f));
            buttonPrewittHX.setFont(font.deriveFont(16f));
            buttonPrewittHY.setFont(font.deriveFont(16f));
            buttonSobelHX.setFont(font.deriveFont(16f));
            buttonSobelHY.setFont(font.deriveFont(16f));
            
            matriz11.setFont(font.deriveFont(16f));
            matriz12.setFont(font.deriveFont(16f));
            matriz13.setFont(font.deriveFont(16f));
            matriz21.setFont(font.deriveFont(16f));
            matriz22.setFont(font.deriveFont(16f));
            matriz23.setFont(font.deriveFont(16f));
            matriz31.setFont(font.deriveFont(16f));
            matriz32.setFont(font.deriveFont(16f));
            matriz33.setFont(font.deriveFont(16f));
            
            buttonFilterApply.setFont(font.deriveFont(16f));

            labelZoom.setFont(font.deriveFont(16f));
            fieldZoomOutX.setFont(font.deriveFont(16f));
            fieldZoomOutY.setFont(font.deriveFont(16f));
            fieldZoomInX.setFont(font.deriveFont(16f));
            fieldZoomInY.setFont(font.deriveFont(16f));
            buttonZoomOut.setFont(font.deriveFont(16f));
            buttonZoomIn.setFont(font.deriveFont(16f));
            
            labelHistogram.setFont(font.deriveFont(16f));
            buttonHistogram.setFont(font.deriveFont(16f));
            buttonHistogramEqualized.setFont(font.deriveFont(16f));

            fileSelectorHistogramMatching.setFont(font.deriveFont(16f));
            labelHistogramMatching.setFont(font.deriveFont(16f));

        }catch(Exception e){
            UserAlert userAlert = new UserAlert("ERRO - Erro ao carregar Fonte"); 
        }

        //---------------------------------------------------------

        labelFileSelector.setBounds(20, 125, 1000, 50);
        labelFileSelector.setForeground(Color.white);
        fileSelector.setBounds(20, 170, 545, 40);

        //---------------------------------------------------------

        labelFileSaver.setBounds(20, 205, 1000, 50);
        labelFileSaver.setForeground(Color.white);
        fileSaver.setBounds(20, 250, 545, 40);

        //---------------------------------------------------------

        imageIcon.setBounds(155, 30, 90, 90);
        imageIcon.setHorizontalAlignment(JLabel.CENTER);

        //---------------------------------------------------------

        buttonClean.setBounds(20, 310, 345, 40);
        buttonClean.setBackground(new Color(175, 62, 94));
        buttonClean.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonClean.setFocusable(false);
        buttonClean.setForeground(Color.WHITE);
        buttonClean.addActionListener(event -> eventClean());

        buttonInvertHorizontal.setBounds(20, 370, 165, 40);
        buttonInvertHorizontal.setBackground(new Color(120, 90,148));
        buttonInvertHorizontal.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonInvertHorizontal.setFocusable(false);
        buttonInvertHorizontal.setForeground(Color.WHITE);
        buttonInvertHorizontal.addActionListener(event -> eventInvertHorizontal());

        buttonInvertVertical.setBounds(200, 370, 165, 40);
        buttonInvertVertical.setBackground(new Color(120, 90,148));
        buttonInvertVertical.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonInvertVertical.setFocusable(false);
        buttonInvertVertical.setForeground(Color.WHITE);
        buttonInvertVertical.addActionListener(event -> eventInvertVertical());

        buttonGray.setBounds(20, 430, 345, 40);
        buttonGray.setBackground(new Color(120, 90,148));
        buttonGray.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonGray.setFocusable(false);
        buttonGray.setForeground(Color.WHITE);
        buttonGray.addActionListener(event -> eventGray());

        //---------------------------------------------------------
        
        fieldQuant.setBounds(20, 490, 172, 40);
        fieldQuant.setBackground(new Color(33, 33, 33));
        fieldQuant.setBorder(new LineBorder(new Color(33, 33, 33),10));
        fieldQuant.setForeground(new Color(204, 204, 204));
        fieldQuant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(fieldQuant, evt, null);
            }
        });

        buttonQuant.setBounds(192, 490, 173, 40);
        buttonQuant.setBackground(new Color(120, 90,148));
        buttonQuant.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonQuant.setFocusable(false);
        buttonQuant.setForeground(Color.WHITE);
        buttonQuant.addActionListener(event -> eventQuantization());

        //---------------------------------------------------------

        fieldBrightness.setBounds(20, 550, 172, 40);
        fieldBrightness.setBackground(new Color(33, 33, 33));
        fieldBrightness.setBorder(new LineBorder(new Color(33, 33, 33),10));
        fieldBrightness.setForeground(new Color(204, 204, 204));
        fieldBrightness.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(fieldBrightness, evt, "inteiros");
            }
        });

        buttonBrightness.setBounds(192, 550, 173, 40);
        buttonBrightness.setBackground(new Color(120, 90,148));
        buttonBrightness.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonBrightness.setFocusable(false);
        buttonBrightness.setForeground(Color.WHITE);
        buttonBrightness.addActionListener(event -> eventBrightness());

        //---------------------------------------------------------

        fieldContrast.setBounds(20, 610, 172, 40);
        fieldContrast.setBackground(new Color(33, 33, 33));
        fieldContrast.setBorder(new LineBorder(new Color(33, 33, 33),10));
        fieldContrast.setForeground(new Color(204, 204, 204));
        fieldContrast.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(fieldContrast, evt, null);
            }
        });

        buttonContrast.setBounds(192, 610, 173, 40);
        buttonContrast.setBackground(new Color(120, 90,148));
        buttonContrast.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonContrast.setFocusable(false);
        buttonContrast.setForeground(Color.WHITE);
        buttonContrast.addActionListener(event -> eventContrast());

        //-----------------------------------------------------

        buttonNegative.setBounds(20, 670, 345, 40);
        buttonNegative.setBackground(new Color(120, 90,148));
        buttonNegative.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonNegative.setFocusable(false);
        buttonNegative.setForeground(Color.WHITE);
        buttonNegative.addActionListener(event -> eventNegative());

        //-----------------------------------------------------

        labelRotate.setBounds(20, 705, 1000, 50);
        labelRotate.setForeground(Color.white);

        buttonRotateAnticlockwise.setBounds(20, 750, 165, 40);
        buttonRotateAnticlockwise.setBackground(new Color(120, 90,148));
        buttonRotateAnticlockwise.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonRotateAnticlockwise.setFocusable(false);
        buttonRotateAnticlockwise.setForeground(Color.WHITE);
        buttonRotateAnticlockwise.addActionListener(event -> eventRotation(false));

        buttonRotateClockwise.setBounds(200, 750, 165, 40);
        buttonRotateClockwise.setBackground(new Color(120, 90,148));
        buttonRotateClockwise.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonRotateClockwise.setFocusable(false);
        buttonRotateClockwise.setForeground(Color.WHITE);
        buttonRotateClockwise.addActionListener(event -> eventRotation(true));

        //--------------------------------------------------------

        labelFiltros.setBounds(20, 790, 1000, 50);
        labelFiltros.setForeground(Color.white);

        buttonGaussiano.setBounds(20, 835, 345, 40);
        buttonGaussiano.setBackground(new Color(120, 90,148));
        buttonGaussiano.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonGaussiano.setFocusable(false);
        buttonGaussiano.setForeground(Color.WHITE);
        buttonGaussiano.addActionListener(event -> matrixGeneration(new double[] {0.0625, 0.125, 0.0625, 0.125, 0.25, 0.125, 0.0625, 0.125, 0.0625}, "gaussiano"));

        buttonLaplaciano.setBounds(20, 895, 345, 40);
        buttonLaplaciano.setBackground(new Color(120, 90,148));
        buttonLaplaciano.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonLaplaciano.setFocusable(false);
        buttonLaplaciano.setForeground(Color.WHITE);
        buttonLaplaciano.addActionListener(event -> matrixGeneration(new double[] {0, -1, 0, -1, 4, -1, 0, -1, 0}, "laplaciano"));

        buttonPassaAltaGenérico.setBounds(20, 955, 345, 40);
        buttonPassaAltaGenérico.setBackground(new Color(120, 90,148));
        buttonPassaAltaGenérico.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonPassaAltaGenérico.setFocusable(false);
        buttonPassaAltaGenérico.setForeground(Color.WHITE);
        buttonPassaAltaGenérico.addActionListener(event -> matrixGeneration(new double[] {-1, -1, -1, -1, 8, -1, -1, -1, -1},"passaaltagenerico"));

        buttonPrewittHX.setBounds(20, 1015, 345, 40);
        buttonPrewittHX.setBackground(new Color(120, 90,148));
        buttonPrewittHX.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonPrewittHX.setFocusable(false);
        buttonPrewittHX.setForeground(Color.WHITE);
        buttonPrewittHX.addActionListener(event -> matrixGeneration(new double[] {-1, 0, 1, -1, 0, 1, -1, 0, 1},"prewitthx"));

        buttonPrewittHY.setBounds(20, 1075, 345, 40);
        buttonPrewittHY.setBackground(new Color(120, 90,148));
        buttonPrewittHY.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonPrewittHY.setFocusable(false);
        buttonPrewittHY.setForeground(Color.WHITE);
        buttonPrewittHY.addActionListener(event -> matrixGeneration(new double[] {-1,-1, -1, 0, 0, 0, 1, 1, 1}, "prewitthy"));

        buttonSobelHX.setBounds(20, 1135, 345, 40);
        buttonSobelHX.setBackground(new Color(120, 90,148));
        buttonSobelHX.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonSobelHX.setFocusable(false);
        buttonSobelHX.setForeground(Color.WHITE);
        buttonSobelHX.addActionListener(event -> matrixGeneration(new double[] {-1,0,1, -2, 0, 2, -1, 0, 1}, "sobelhx"));

        buttonSobelHY.setBounds(20, 1195, 345, 40);
        buttonSobelHY.setBackground(new Color(120, 90,148));
        buttonSobelHY.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonSobelHY.setFocusable(false);
        buttonSobelHY.setForeground(Color.WHITE);
        buttonSobelHY.addActionListener(event -> matrixGeneration(new double[] {-1, -2, -1, 0, 0, 0, 1, 2, 1}, "sobelhy"));

        matriz11.setBounds(20, 1255, 105, 40);
        matriz11.setBackground(new Color(33, 33, 33));
        matriz11.setBorder(new LineBorder(new Color(33, 33, 33),10));
        matriz11.setForeground(new Color(204, 204, 204));
        matriz11.setHorizontalAlignment(JTextField.CENTER);
        matriz11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(matriz11, evt, "matriz");
            }
        });

        matriz12.setBounds(140, 1255, 105, 40);
        matriz12.setBackground(new Color(33, 33, 33));
        matriz12.setBorder(new LineBorder(new Color(33, 33, 33),10));
        matriz12.setForeground(new Color(204, 204, 204));
        matriz12.setHorizontalAlignment(JTextField.CENTER);
        matriz12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(matriz12, evt, "matriz");
            }
        });

        matriz13.setBounds(260, 1255, 105, 40);
        matriz13.setBackground(new Color(33, 33, 33));
        matriz13.setBorder(new LineBorder(new Color(33, 33, 33),10));
        matriz13.setForeground(new Color(204, 204, 204));
        matriz13.setHorizontalAlignment(JTextField.CENTER);
        matriz13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(matriz13, evt, "matriz");
            }
        });

        matriz21.setBounds(20, 1315, 105, 40);
        matriz21.setBackground(new Color(33, 33, 33));
        matriz21.setBorder(new LineBorder(new Color(33, 33, 33),10));
        matriz21.setForeground(new Color(204, 204, 204));
        matriz21.setHorizontalAlignment(JTextField.CENTER);
        matriz21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(matriz21, evt, "matriz");
            }
        });

        matriz22.setBounds(140, 1315, 105, 40);
        matriz22.setBackground(new Color(33, 33, 33));
        matriz22.setBorder(new LineBorder(new Color(33, 33, 33),10));
        matriz22.setForeground(new Color(204, 204, 204));
        matriz22.setHorizontalAlignment(JTextField.CENTER);
        matriz22.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(matriz22, evt, "matriz");
            }
        });

        matriz23.setBounds(260, 1315, 105, 40);
        matriz23.setBackground(new Color(33, 33, 33));
        matriz23.setBorder(new LineBorder(new Color(33, 33, 33),10));
        matriz23.setForeground(new Color(204, 204, 204));
        matriz23.setHorizontalAlignment(JTextField.CENTER);
        matriz23.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(matriz23, evt, "matriz");
            }
        });

        matriz31.setBounds(20, 1375, 105, 40);
        matriz31.setBackground(new Color(33, 33, 33));
        matriz31.setBorder(new LineBorder(new Color(33, 33, 33),10));
        matriz31.setForeground(new Color(204, 204, 204));
        matriz31.setHorizontalAlignment(JTextField.CENTER);
        matriz31.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(matriz31, evt, "matriz");
            }
        });

        matriz32.setBounds(140, 1375, 105, 40);
        matriz32.setBackground(new Color(33, 33, 33));
        matriz32.setBorder(new LineBorder(new Color(33, 33, 33),10));
        matriz32.setForeground(new Color(204, 204, 204));
        matriz32.setHorizontalAlignment(JTextField.CENTER);
        matriz32.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(matriz32, evt, "matriz");
            }
        });

        matriz33.setBounds(260, 1375, 105, 40);
        matriz33.setBackground(new Color(33, 33, 33));
        matriz33.setBorder(new LineBorder(new Color(33, 33, 33),10));
        matriz33.setForeground(new Color(204, 204, 204));
        matriz33.setHorizontalAlignment(JTextField.CENTER);
        matriz33.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(matriz33, evt, "matriz");
            }
        });

        buttonFilterApply.setBounds(20, 1435, 345, 40);
        buttonFilterApply.setBackground(new Color(120, 90,148));
        buttonFilterApply.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonFilterApply.setFocusable(false);
        buttonFilterApply.setForeground(Color.WHITE);
        buttonFilterApply.addActionListener(event -> eventFilter());

        //----------------------------------------------------------------------------

        labelZoom.setBounds(20, 1475, 345, 50);
        labelZoom.setForeground(Color.white);

        fieldZoomOutX.setBounds(20, 1520, 50, 40);
        fieldZoomOutX.setBackground(new Color(33, 33, 33));
        fieldZoomOutX.setBorder(new LineBorder(new Color(33, 33, 33),10));
        fieldZoomOutX.setForeground(new Color(204, 204, 204));
        fieldZoomOutX.setHorizontalAlignment(JTextField.CENTER);
        fieldZoomOutX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(fieldZoomOutX, evt, "naturais");
            }
        });

        fieldZoomOutY.setBounds(85, 1520, 50, 40);
        fieldZoomOutY.setBackground(new Color(33, 33, 33));
        fieldZoomOutY.setBorder(new LineBorder(new Color(33, 33, 33),10));
        fieldZoomOutY.setForeground(new Color(204, 204, 204));
        fieldZoomOutY.setHorizontalAlignment(JTextField.CENTER);
        fieldZoomOutY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(fieldZoomOutY, evt, "naturais");
            }
        });

        fieldZoomInX.setBounds(20, 1580, 50, 40);
        fieldZoomInX.setBackground(new Color(33, 33, 33));
        fieldZoomInX.setBorder(new LineBorder(new Color(33, 33, 33),10));
        fieldZoomInX.setForeground(new Color(204, 204, 204));
        fieldZoomInX.setHorizontalAlignment(JTextField.CENTER);
        fieldZoomInX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(fieldZoomInX, evt, "naturais");
            }
        });

        fieldZoomInY.setBounds(85, 1580, 50, 40);
        fieldZoomInY.setBackground(new Color(33, 33, 33));
        fieldZoomInY.setBorder(new LineBorder(new Color(33, 33, 33),10));
        fieldZoomInY.setForeground(new Color(204, 204, 204));
        fieldZoomInY.setHorizontalAlignment(JTextField.CENTER);
        fieldZoomInY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textRegex(fieldZoomInY, evt, "naturais");
            }
        });

        buttonZoomIn.setBounds(150, 1580, 215, 40);
        buttonZoomIn.setBackground(new Color(120, 90,148));
        buttonZoomIn.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonZoomIn.setFocusable(false);
        buttonZoomIn.setForeground(Color.WHITE);
        buttonZoomIn.addActionListener(event -> eventZoomIn());

        buttonZoomOut.setBounds(150, 1520, 215, 40);
        buttonZoomOut.setBackground(new Color(120, 90,148));
        buttonZoomOut.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonZoomOut.setFocusable(false);
        buttonZoomOut.setForeground(Color.WHITE);
        buttonZoomOut.addActionListener(event -> eventZoomOut());

        labelHistogram.setBounds(20, 1620, 345, 50);
        labelHistogram.setForeground(Color.white);

        buttonHistogram.setBounds(20, 1665, 345, 40);
        buttonHistogram.setBackground(new Color(120, 90,148));
        buttonHistogram.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonHistogram.setFocusable(false);
        buttonHistogram.setForeground(Color.WHITE);
        buttonHistogram.addActionListener(event -> eventCreateHistogram(fileSelector.getImageScreenModified() == null? null : fileSelector.getImageScreenModified().getImageBuffer()));

        buttonHistogramEqualized.setBounds(20, 1725, 345, 40);
        buttonHistogramEqualized.setBackground(new Color(120, 90,148));
        buttonHistogramEqualized.setBorder(new LineBorder(new Color(0, 0, 0),0));
        buttonHistogramEqualized.setFocusable(false);
        buttonHistogramEqualized.setForeground(Color.WHITE);
        buttonHistogramEqualized.addActionListener(event -> eventCreateEqualizedImage(fileSelector.getImageScreenModified() == null? null : fileSelector.getImageScreenModified().getImageBuffer()));

        labelHistogramMatching.setBounds(20, 1765, 345, 50);
        labelHistogramMatching.setForeground(Color.white);

        fileSelectorHistogramMatching.setBounds(20, 1810, 545, 40);
    }

    private void addComponents(){

        add(labelFileSelector);     // Label do seletor
        add(fileSelector);          // Seletor de Arquivos LOAD

        add(labelFileSaver);     // Label do seletor
        add(fileSaver);          // Seletor de Arquivos SAVE

        add(imageIcon);

        add(buttonClean);
        add(buttonInvertHorizontal);
        add(buttonInvertVertical);
        add(buttonGray);

        add(fieldQuant);
        add(buttonQuant);

        add(fieldBrightness);
        add(buttonBrightness);

        add(fieldContrast);
        add(buttonContrast);

        add(buttonNegative);

        add(labelRotate);
        add(buttonRotateAnticlockwise);
        add(buttonRotateClockwise);

        add(labelFiltros);
        add(buttonGaussiano);
        add(buttonLaplaciano);
        add(buttonPassaAltaGenérico);
        add(buttonPrewittHX);
        add(buttonPrewittHY);
        add(buttonSobelHX);
        add(buttonSobelHY);

        add(matriz11);
        add(matriz12);
        add(matriz13);
        add(matriz21);
        add(matriz22);
        add(matriz23);
        add(matriz31);
        add(matriz32);
        add(matriz33);

        add(buttonFilterApply);

        add(labelZoom);
        add(fieldZoomOutX);
        add(fieldZoomOutY);
        add(fieldZoomInX);
        add(fieldZoomInY);
        add(buttonZoomOut);
        add(buttonZoomIn);

        add(labelHistogram);
        add(buttonHistogram);

        add(buttonHistogramEqualized);

        add(fileSelectorHistogramMatching);
        add(labelHistogramMatching);
    }

    private void eventInvertHorizontal(){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{
            ImageScreen imageScreenOriginal = fileSelector.getImageScreenOriginal();
            ImageFunctions.imageInverterHorizontal(imageScreenOriginal.getImageBuffer(), imageScreenOriginal.getImageRGBA());

            imageScreenOriginal.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenOriginal.getImageBuffer()));
            imageScreenOriginal.refreshComponents();

            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            ImageFunctions.imageInverterHorizontal(imageScreenModified.getImageBuffer(), imageScreenModified.getImageRGBA());

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();
        }
    }
    
    private void eventInvertVertical(){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{
            ImageScreen imageScreenOriginal = fileSelector.getImageScreenOriginal();
            ImageFunctions.imageInverterVertical(imageScreenOriginal.getImageBuffer(), imageScreenOriginal.getImageRGBA());

            imageScreenOriginal.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenOriginal.getImageBuffer()));
            imageScreenOriginal.refreshComponents();

            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            ImageFunctions.imageInverterVertical(imageScreenModified.getImageBuffer(), imageScreenModified.getImageRGBA());

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();
        }
    }

    private void eventGray(){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{
            
            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            imageScreenModified.setImageBuffer(ImageFunctions.imageToGray(imageScreenModified.getImageBuffer()));

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();
        }
    }

    private void eventClean(){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{
            ImageScreen imageScreenOriginal = fileSelector.getImageScreenOriginal();


            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            imageScreenModified.setImageBuffer(imageScreenOriginal.getImageBuffer());

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();

        }
    }
    
    private void eventQuantization(){

        int quantization = Integer.parseInt(fieldQuant.getText().compareTo("") == 0 ? "0" : fieldQuant.getText());

        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else if(quantization == 0){
            UserAlert userAlert = new UserAlert("ERRO - Você não pode quantizar sem nenhum tom!");
        }else{

            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            imageScreenModified.setImageBuffer(ImageFunctions.imageQuantization(imageScreenModified.getImageBuffer(), Integer.parseInt(fieldQuant.getText())));

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();
        }

    }

    private void eventBrightness(){
        try{
            int brightness = Integer.parseInt(fieldBrightness.getText().compareTo("") == 0 ? "0" : fieldBrightness.getText());
            if (!Screen.screenAlreadyExists("Imagem Modificada")) {
                UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
            }else if(brightness < -255 || brightness > 255){
                UserAlert userAlert = new UserAlert("ERRO - O brilho deve ser um valor no intervalo [-255, 255]");
            }else{
    
                ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
                imageScreenModified.setImageBuffer(ImageFunctions.imageBrightness(imageScreenModified.getImageBuffer(), Integer.parseInt(fieldBrightness.getText())));
    
                imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
                imageScreenModified.refreshComponents();
            }
        }catch(Exception e){
            UserAlert userAlert = new UserAlert("ERRO - Número digitado incorretamente!");
        }

    }


    private void eventContrast(){
        int contrast = Integer.parseInt(fieldContrast.getText().compareTo("") == 0 ? "0" : fieldContrast.getText());
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else if(contrast < 0 || contrast > 255){
            UserAlert userAlert = new UserAlert("ERRO - O brilho deve ser um valor no intervalo [-255, 255]");
        }else{

            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            imageScreenModified.setImageBuffer(ImageFunctions.imageContrast(imageScreenModified.getImageBuffer(), Integer.parseInt(fieldContrast.getText())));

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();
        }
    }

    private void eventNegative(){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{

            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            imageScreenModified.setImageBuffer(ImageFunctions.imageNegative(imageScreenModified.getImageBuffer()));

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();
        }
    }

    private void eventRotation(boolean rotationClockwise){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{
            ImageScreen imageScreenOriginal = fileSelector.getImageScreenOriginal();
            imageScreenOriginal.setImageBuffer(ImageFunctions.imageRotation(imageScreenOriginal.getImageBuffer(), imageScreenOriginal.getImageRGBA(), rotationClockwise));

            imageScreenOriginal.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenOriginal.getImageBuffer()));
            imageScreenOriginal.refreshComponents();

            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            imageScreenModified.setImageBuffer(ImageFunctions.imageRotation(imageScreenModified.getImageBuffer(), imageScreenModified.getImageRGBA(), rotationClockwise));

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();
        }
    }

    private void eventZoomOut(){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{
            int INTfieldZoomOutX = Integer.parseInt(fieldZoomOutX.getText());
            int INTfieldZoomOutY = Integer.parseInt(fieldZoomOutY.getText());

            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            imageScreenModified.setImageBuffer(ImageFunctions.imageZoomOut(imageScreenModified.getImageBuffer(), INTfieldZoomOutX, INTfieldZoomOutY));

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();
        }
    }

    private void eventZoomIn(){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{
            int INTfieldZoomOutX = Integer.parseInt(fieldZoomOutX.getText());
            int INTfieldZoomOutY = Integer.parseInt(fieldZoomOutY.getText());

            ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
            imageScreenModified.setImageBuffer(ImageFunctions.imageZoomIn(imageScreenModified.getImageBuffer()));

            imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
            imageScreenModified.refreshComponents();
        }
    }

    private void eventCreateHistogram(BufferedImage imageBuffer){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{
            String aux[] = fileSelector.getFieldFile().getText().split("\\\\");
            String fileName = aux[aux.length - 1];
            HistogramScreen histogramScreen = new HistogramScreen(fileName, ImageFunctions.imageHistogramArray(imageBuffer), true, 955, 20);
        }
    }

    private void eventCreateEqualizedImage(BufferedImage imageBuffer){
        if (!Screen.screenAlreadyExists("Imagem Modificada")) {
            UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
        }else{

            int[] normalHistogram = ImageFunctions.imageHistogramArray(imageBuffer);
            
            HistogramScreen histogramScreenNormal = new HistogramScreen("ANTES", normalHistogram, true, 600, 20);
            
            int[] equalizedHistogram = ImageFunctions.imageNormalizedCumulativeHistogramArray(imageBuffer);
            BufferedImage imageEqualized = ImageFunctions.imageHistogramEqualization(imageBuffer, equalizedHistogram);
            
            Screen.deleteDuplicatedScreens("Imagem Equalizada");
            ImageScreen imageScreen = new ImageScreen("Imagem Equalizada", imageEqualized, 955, 400, JFrame.DISPOSE_ON_CLOSE);

            HistogramScreen histogramScreenEqualized = new HistogramScreen("DEPOIS", ImageFunctions.imageHistogramArray(imageEqualized), true, 955, 20);
        }
    }

    private void eventFilter(){
        try{

            double valor_matriz11 = Double.parseDouble(matriz11.getText());
            double valor_matriz12 = Double.parseDouble(matriz12.getText());
            double valor_matriz13 = Double.parseDouble(matriz13.getText());
            double valor_matriz21 = Double.parseDouble(matriz21.getText());
            double valor_matriz22 = Double.parseDouble(matriz22.getText());
            double valor_matriz23 = Double.parseDouble(matriz23.getText());
            double valor_matriz31 = Double.parseDouble(matriz31.getText());
            double valor_matriz32 = Double.parseDouble(matriz32.getText());
            double valor_matriz33 = Double.parseDouble(matriz33.getText());
            
            if (!Screen.screenAlreadyExists("Imagem Modificada")) {
                UserAlert userAlert = new UserAlert("ERRO - Você não selecionou nenhuma Imagem!");
            }else{
    
                ImageScreen imageScreenModified = fileSelector.getImageScreenModified();
                
                //OBS: aqui já é realizada a rotação em 180° do kernel
                imageScreenModified.setImageBuffer(ImageFunctions.imageFilter(
                    imageScreenModified.getImageBuffer(),
                    new double[][]{
                        {valor_matriz33, valor_matriz32, valor_matriz31},
                        {valor_matriz23, valor_matriz22, valor_matriz21},
                        {valor_matriz13, valor_matriz12, valor_matriz11}},
                    filterSelected));
    
                imageScreenModified.setImageRGBA(ImageFunctions.imageToRGBA(imageScreenModified.getImageBuffer()));
                imageScreenModified.refreshComponents();
            }
        }catch(Exception e){
            UserAlert userAlert = new UserAlert("ERRO - Número digitado incorretamente na matriz de filtro!");
        }
    }

    private void matrixGeneration(double[] vetorComponents, String filter){
        matriz11.setText(Double.toString(vetorComponents[0]));
        matriz12.setText(Double.toString(vetorComponents[1]));
        matriz13.setText(Double.toString(vetorComponents[2]));
        matriz21.setText(Double.toString(vetorComponents[3]));
        matriz22.setText(Double.toString(vetorComponents[4]));
        matriz23.setText(Double.toString(vetorComponents[5]));
        matriz31.setText(Double.toString(vetorComponents[6]));
        matriz32.setText(Double.toString(vetorComponents[7]));
        matriz33.setText(Double.toString(vetorComponents[8]));

        setFilterSelected(filter);
    }

    private void textRegex(JTextField jtextfield, KeyEvent evt, String config){
        String content = jtextfield.getText();
        String regex;
        Pattern pattern;

        if(config != null && config.compareTo("inteiros") == 0){
            pattern = Pattern.compile("-?|[0-9]+");
        }else if(config != null && config.compareTo("matriz") == 0){
            pattern = Pattern.compile(".?|-?|[0-9]+");
            setFilterSelected("NO_FILTER");
        }else{
            pattern = Pattern.compile("[0-9]+");
        }

        if(!pattern.matcher(content).matches()){
            for(int i = 0; i < content.length(); i++){
                if(!pattern.matcher(String.valueOf(content.charAt(i))).matches()){
                    String aux = content.replace(String.valueOf(content.charAt(i)), "");
                    jtextfield.setText(aux);
                }
            }
        }
    }

    public JLabel getLabelFileSelector() {
        return labelFileSelector;
    }
    public void setLabelFileSelector(JLabel labelFileSelector) {
        this.labelFileSelector = labelFileSelector;
    }



    public FileSelectorImages getFileSelector() {
        return fileSelector;
    }
    public void setFileSelector(FileSelectorImages fileSelector) {
        this.fileSelector = fileSelector;
    }



    public JLabel getLabelFileSaver() {
        return labelFileSaver;
    }
    public void setLabelFileSaver(JLabel labelFileSaver) {
        this.labelFileSaver = labelFileSaver;
    }
    


    public FileSelectorSave getFileSaver() {
        return fileSaver;
    }
    public void setFileSaver(FileSelectorSave fileSaver) {
        this.fileSaver = fileSaver;
    }
    


    public JLabel getImageIcon() {
        return imageIcon;
    }
    public void setImageIcon(JLabel imageIcon) {
        this.imageIcon = imageIcon;
    }



    public JButton getButtonClean() {
        return buttonClean;
    }
    public void setButtonClean(JButton buttonClean) {
        this.buttonClean = buttonClean;
    }



    public JButton getButtonInvertHorizontal() {
        return buttonInvertHorizontal;
    }
    public void setButtonInvertHorizontal(JButton buttonInvertHorizontal) {
        this.buttonInvertHorizontal = buttonInvertHorizontal;
    }


    public JButton getButtonInvertVertical() {
        return buttonInvertVertical;
    }
    public void setButtonInvertVertical(JButton buttonInvertVertical) {
        this.buttonInvertVertical = buttonInvertVertical;
    }



    public JButton getButtonGray() {
        return buttonGray;
    }
    public void setButtonGray(JButton buttonGray) {
        this.buttonGray = buttonGray;
    }
    


    public JTextField getFieldQuant() {
        return fieldQuant;
    }
    public void setFieldQuant(JTextField fieldQuant) {
        this.fieldQuant = fieldQuant;
    }



    public JButton getButtonQuant() {
        return buttonQuant;
    }
    public void setButtonQuant(JButton buttonQuant) {
        this.buttonQuant = buttonQuant;
    }


    public JTextField getFieldBrightness() {
        return fieldBrightness;
    }
    public void setFieldBrightness(JTextField fieldBrightness) {
        this.fieldBrightness = fieldBrightness;
    }


    public JButton getButtonBrightness() {
        return buttonBrightness;
    }
    public void setButtonBrightness(JButton buttonBrightness) {
        this.buttonBrightness = buttonBrightness;
    }


    public JTextField getFieldContrast() {
        return fieldContrast;
    }
    public void setFieldContrast(JTextField fieldContrast) {
        this.fieldContrast = fieldContrast;
    }


    public JButton getButtonContrast() {
        return buttonContrast;
    }
    public void setButtonContrast(JButton buttonContrast) {
        this.buttonContrast = buttonContrast;
    }

    public JButton getButtonNegative() {
        return buttonNegative;
    }
    public void setButtonNegative(JButton buttonNegative) {
        this.buttonNegative = buttonNegative;
    }


    public JButton getButtonRotateAnticlockwise() {
        return buttonRotateAnticlockwise;
    }
    public void setButtonRotateAnticlockwise(JButton buttonRotateAnticlockwise) {
        this.buttonRotateAnticlockwise = buttonRotateAnticlockwise;
    }

    public JButton getButtonRotateClockwise() {
        return buttonRotateClockwise;
    }
    public void setButtonRotateClockwise(JButton buttonRotateClockwise) {
        this.buttonRotateClockwise = buttonRotateClockwise;
    }

    public JLabel getLabelRotate() {
        return labelRotate;
    }
    public void setLabelRotate(JLabel labelRotate) {
        this.labelRotate = labelRotate;
    }

    public JLabel getLabelFiltros() {
        return labelFiltros;
    }
    public void setLabelFiltros(JLabel labelFiltros) {
        this.labelFiltros = labelFiltros;
    }

    //========================= 1 ========================

    public JButton getButtonGaussiano() {
        return buttonGaussiano;
    }
    public void setButtonGaussiano(JButton buttonGaussiano) {
        this.buttonGaussiano = buttonGaussiano;
    }
    //========================= 2 ========================

    public JButton getButtonLaplaciano() {
        return buttonLaplaciano;
    }
    public void setButtonLaplaciano(JButton buttonLaplaciano) {
        this.buttonLaplaciano = buttonLaplaciano;
    }
    //========================= 3 ========================

    public JButton getButtonPassaAltaGenérico() {
        return buttonPassaAltaGenérico;
    }
    public void setButtonPassaAltaGenérico(JButton buttonPassaAltaGenérico) {
        this.buttonPassaAltaGenérico = buttonPassaAltaGenérico;
    }
    //========================= 4 ========================

    public JButton getButtonPrewittHX() {
        return buttonPrewittHX;
    }
    public void setButtonPrewittHX(JButton buttonPrewittHX) {
        this.buttonPrewittHX = buttonPrewittHX;
    }
    //========================= 5 ========================

    public JButton getButtonPrewittHY() {
        return buttonPrewittHY;
    }
    public void setButtonPrewittHY(JButton buttonPrewittHY) {
        this.buttonPrewittHY = buttonPrewittHY;
    }
    //========================= 6 ========================

    public JButton getButtonSobelHX() {
        return buttonSobelHX;
    }
    public void setButtonSobelHX(JButton buttonSobelHX) {
        this.buttonSobelHX = buttonSobelHX;
    }
    //========================= 7 ========================

    public JButton getButtonSobelHY() {
        return buttonSobelHY;
    }
    public void setButtonSobelHY(JButton buttonSobelHY) {
        this.buttonSobelHY = buttonSobelHY;
    }
    //===================== MATRIZ =========================
    public JTextField getMatriz11() {
        return matriz11;
    }
    public void setMatriz11(JTextField matriz11) {
        this.matriz11 = matriz11;
    }
    //---------------------
    public JTextField getMatriz12() {
        return matriz12;
    }
    public void setMatriz12(JTextField matriz12) {
        this.matriz12 = matriz12;
    }
    //---------------------
    public JTextField getMatriz13() {
        return matriz13;
    }
    public void setMatriz13(JTextField matriz13) {
        this.matriz13 = matriz13;
    }
    //---------------------
    public JTextField getMatriz21() {
        return matriz21;
    }
    public void setMatriz21(JTextField matriz21) {
        this.matriz21 = matriz21;
    }
    //---------------------
    public JTextField getMatriz22() {
        return matriz22;
    }
    public void setMatriz22(JTextField matriz22) {
        this.matriz22 = matriz22;
    }
    //---------------------
    public JTextField getMatriz23() {
        return matriz23;
    }
    public void setMatriz23(JTextField matriz23) {
        this.matriz23 = matriz23;
    }
    //---------------------
    public JTextField getMatriz31() {
        return matriz31;
    }
    public void setMatriz31(JTextField matriz31) {
        this.matriz31 = matriz31;
    }
    //---------------------
    public JTextField getMatriz32() {
        return matriz32;
    }
    public void setMatriz32(JTextField matriz32) {
        this.matriz32 = matriz32;
    }
    //---------------------
    public JTextField getMatriz33() {
        return matriz33;
    }
    public void setMatriz33(JTextField matriz33) {
        this.matriz33 = matriz33;
    }
    //---------------------
    public String getFilterSelected() {
        return filterSelected;
    }
    public void setFilterSelected(String filterSelected) {
        this.filterSelected = filterSelected;
    }


    public JButton getButtonFilterApply() {
        return buttonFilterApply;
    }
    public void setButtonFilterApply(JButton buttonFilterApply) {
        this.buttonFilterApply = buttonFilterApply;
    }

    //-------------------------------------------------------------------------------------

    public JLabel getLabelZoom() {
        return labelZoom;
    }
    public void setLabelZoom(JLabel labelZoom) {
        this.labelZoom = labelZoom;
    }

    public JTextField getFieldZoomOutX() {
        return fieldZoomOutX;
    }
    public void setFieldZoomOutX(JTextField fieldZoomOutX) {
        this.fieldZoomOutX = fieldZoomOutX;
    }
    
    public JTextField getFieldZoomOutY() {
        return fieldZoomOutY;
    }
    public void setFieldZoomOutY(JTextField fieldZoomOutY) {
        this.fieldZoomOutY = fieldZoomOutY;
    }

    public JTextField getFieldZoomInX() {
        return fieldZoomInX;
    }
    public void setFieldZoomInX(JTextField fieldZoomInX) {
        this.fieldZoomInX = fieldZoomInX;
    }
    
    public JTextField getFieldZoomInY() {
        return fieldZoomInY;
    }
    public void setFieldZoomInY(JTextField fieldZoomInY) {
        this.fieldZoomInY = fieldZoomInY;
    }

    public JButton getbuttonZoomOut() {
        return buttonZoomOut;
    }
    public void setbuttonZoomOut(JButton buttonZoomOut) {
        this.buttonZoomOut = buttonZoomOut;
    }

    public JButton getbuttonZoomIn() {
        return buttonZoomIn;
    }
    public void setbuttonZoomIn(JButton buttonZoomIn) {
        this.buttonZoomIn = buttonZoomIn;
    }

    
    public JLabel getLabelHistogram() {
        return labelHistogram;
    }
    public void setLabelHistogram(JLabel labelHistogram) {
        this.labelHistogram = labelHistogram;
    }
    public JButton getButtonHistogram() {
        return buttonHistogram;
    }
    public void setButtonHistogram(JButton buttonHistogram) {
        this.buttonHistogram = buttonHistogram;
    }

    public JButton getButtonHistogramEqualized() {
        return buttonHistogramEqualized;
    }
    public void setButtonHistogramEqualized(JButton buttonHistogramEqualized) {
        this.buttonHistogramEqualized = buttonHistogramEqualized;
    }



    public FileSelector getFileSelectorHistogramMatching() {
        return fileSelectorHistogramMatching;
    }
    public void setFileSelectorHistogramMatching(FileSelector fileSelectorHistogramMatching) {
        this.fileSelectorHistogramMatching = fileSelectorHistogramMatching;
    }


    public JLabel getLabelHistogramMatching() {
        return labelHistogramMatching;
    }
    public void setLabelHistogramMatching(JLabel labelHistogramMatching) {
        this.labelHistogramMatching = labelHistogramMatching;
    }
}
