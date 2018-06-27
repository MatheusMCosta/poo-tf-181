package pucrs.myflight.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class GerenciadorAeroportos {

    private ArrayList<Aeroporto> aeroportos;

    public GerenciadorAeroportos() {
        this.aeroportos = new ArrayList<>();
    }

    public void ordenarNomes() {
        Collections.sort(aeroportos);
    }

    public void adicionar(Aeroporto aero) {
        aeroportos.add(aero);
    }
    
    public void carregaDados(Striong nomeArq) throws IOException{
        Path path = Paths.get(nomeArq);
        try(Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
            sc.useDelimiter([;\n]);
            String header = new sc.nextLine();
            String cod, nome;
            double lati, longi;
            while (sc.haxNext()){
                cod = sc.next();
                nome = sc.next();
                lati = sc.nextDouble();
                longi = sc.nextDouble();
                Geo geo = new Geo(lati, longi);
                Aeroporto novo = new Aeroporto(cod, nome, geo);
                adicionar(novo);                
            }
        }
    }
    
    public ArrayList<Aeroporto> listarTodos() {
        return new ArrayList<>(aeroportos);
    }

    public Aeroporto buscarCodigo(String codigo) {
        for(Aeroporto a: aeroportos)
            if(a.getCodigo().equals(codigo))
                return a;
        return null;
    }
}
