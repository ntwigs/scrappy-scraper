import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class FileHandler {
    private String HTML = "html";
    private String LINKS = "links";
    private String NOTAGS = "noTags";
    private String WORDS = "words";
    private String ROOT = "data";
    private String name;

    FileHandler(String n) {
        name = n;
    }

    void setup() {
        createFolder("");
        createFolder(LINKS);
        createFolder(NOTAGS);
        createFolder(HTML);
        createFolder(WORDS);
    }

    void createFolder(String type) {
        File folder = new File("./" + ROOT + "/" + name + "/" + type);
        if (!folder.exists()) folder.mkdir();
    }

    void createRoot() {
        new File(ROOT).mkdir();
    }

    List<Path> read() throws IOException {
        return Files.walk(Paths.get(ROOT + "/" + name + "/" + "html"))
            .filter(Files::isRegularFile)
            .collect(Collectors.toList());
    }

    void createHTML(String html, String link) throws IOException {
        Files.write(Paths.get(ROOT + "/" + name + "/" + HTML + "/" + link), html.getBytes());
    }

    void createLink(String links, String link) throws IOException {
        String pageName = link.substring(link.lastIndexOf("/"), link.length());
        Files.write(Paths.get(ROOT + "/" + name + "/" + LINKS + "/" + pageName), links.getBytes());
    }

    void createNoTags(String html, String link) throws IOException {
        String pageName = link.substring(link.lastIndexOf("/"), link.length());
        Files.write(Paths.get(ROOT + "/" + name + "/" + NOTAGS + "/" + pageName), html.getBytes());
    }

    void createWordBag(String words, String link) throws IOException {
        String pageName = link.substring(link.lastIndexOf("/"), link.length());
        Files.write(Paths.get(ROOT + "/" + name + "/" + WORDS + "/" + pageName), words.getBytes());
    }
}
