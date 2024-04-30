import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Parser {
    static private File hdd;

    

    public Parser(String pathname) {
        Parser.hdd = new File(pathname);
        try {
            if(Parser.hdd.exists() == false){
                Parser.hdd.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Ficheiro Não foi criado");
        }
    }
    
    public Parser(File file){
        Parser.hdd = new File(file.getPath());
        try {
            if(Parser.hdd.exists() == false){
                Parser.hdd.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Ficheiro Não foi criado");
        }
    }

    public void saveTurma(Turma t) throws IOException{
        try (FileWriter fw = new FileWriter(Parser.hdd)) {
            fw.write("Numero;Nota;Nome;Curso;\n");
            for(Aluno a : t.getAlunos()){
                fw.write(a.toString());
            }
            fw.close();
        }
    }

    public Turma loadTurma() throws IOException,FileNotFoundException{
        Aluno[] ff = new Aluno[4];
        try (Scanner reader = new Scanner(Parser.hdd)) {
            reader.nextLine();
            int i = 0;
            while(reader.hasNextLine()){
                String line = reader.nextLine();
                ff[i] = parseAluno(line);
                i++;
            }
            reader.close();
            Turma t = new Turma("uminho",20,0,ff);
            return t;
        } catch (Exception e) {
            System.err.println("erro humano");
        }
        return null;
    }
    
    public static void main(String[] args){
        Parser p = new Parser("teste.csv");
        /*
        Aluno a = new Aluno("1", 12, "antonio", "LEI");
        Aluno b = new Aluno("2", 13, "josefino", "LEI");
        Aluno c = new Aluno("3", 14, "aristides", "LEI");
        Aluno d = new Aluno("4", 15, "jose", "LEI");
        Aluno[] ff = {a,b,c,d};
        Turma t = new Turma("UMINHO", 5, 4,ff);
        
        try{
            p.saveTurma(t);
        } catch (IOException e){
            System.err.println("Não existe ficheiro");
        } catch (NullPointerException np) {
            System.err.println("NULL Pointer");
        }
        */
        
        try {
            Turma t = p.loadTurma();
            t.printAlunos();
        } catch (IOException e) {
            System.err.println("erro humano");
        }
        
    }



    public File getHdd() {
        return hdd;
    }

    public void setHdd(File hdd) {
        Parser.hdd = new File(hdd.getPath());
    }
    
    public Aluno parseAluno(String a){
        // num;nota;nome;curso;
        String[] values = a.split(";");

        String numero = values[0];

        int nota = Integer.parseInt(values[1]);
        
        String nome = values[2];
        
        String curso = values[3];
        
        return new Aluno(numero, nota, nome, curso);
    }
}
