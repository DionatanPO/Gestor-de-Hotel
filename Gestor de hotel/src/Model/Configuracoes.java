package Model;

import java.awt.Color;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JButton;

public class Configuracoes {

    private String cor;
    private int corSelecionada = 0;
    private int verde = 4103219;
    private int azul = 2524105;

    public String leitor() throws IOException {

        Path caminho = Paths.get(System.getProperty("user.home"), "Documents/file.txt");
        Stream<String> linhas = Files.lines(caminho, StandardCharsets.ISO_8859_1);
        linhas.forEach((t) -> {
            String streamToString = Stream.of(t).collect(Collectors.joining());
            cor = streamToString;
        });

        return cor;
    }

    public void escritor(String cor) throws IOException {
        String content = cor;
        Files.write(Paths.get(System.getProperty("user.home"), "Documents/file.txt"), content.getBytes());

    }

    public int converterCor() {

        String color = "";
        try {
            color = leitor();

            if (color == null) {

            } else {
                if (color.equals("BLUE")) {
                    corSelecionada = azul;
                }
                if (color.equals("VERDE")) {
                    corSelecionada = verde;
                }
            }

        } catch (Exception ex) {
            System.out.println("Erro ao converter cor ");

        }

        return corSelecionada;
    }

    public void bgButton(JButton jButton) {
        jButton.setBackground(new Color(230, 227, 227));
    }

    public void bgwButton(JButton jButton) {
        jButton.setBackground(new Color(255, 255, 255));
    }
    public void bgBlackButton(JButton jButton) {
        jButton.setBackground(new Color(145, 144, 144));
    }
    public void bgButtonclosse(JButton jButton) {
        jButton.setBackground(new Color(245, 12, 12));
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getVerde() {
        return verde;
    }

    public void setVerde(int verde) {
        this.verde = verde;
    }

    public int getAzul() {
        return azul;
    }

    public void setAzul(int azul) {
        this.azul = azul;
    }

}
