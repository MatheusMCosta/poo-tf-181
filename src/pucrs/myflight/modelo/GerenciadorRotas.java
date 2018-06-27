package pucrs.myflight.modelo;

import java.util.ArrayList;
import java.util.Collections;

public class GerenciadorRotas {

    private ArrayList<Rota> rotas;

    public GerenciadorRotas() {
        this.rotas = new ArrayList<>();
    }

    public void ordenarCias() {
        Collections.sort(rotas);
    }

    public void ordenarNomesCias() {
        rotas.sort( (Rota r1, Rota r2) ->
          r1.getCia().getNome().compareTo(
          r2.getCia().getNome()));
    }

    public void ordenarNomesAeroportos() {
        rotas.sort( (Rota r1, Rota r2) ->
                r1.getOrigem().getNome().compareTo(
                r2.getOrigem().getNome()));
    }

    public void ordenarNomesAeroportosCias() {
        rotas.sort( (Rota r1, Rota r2) -> {
           int result = r1.getOrigem().getNome().compareTo(
                   r2.getOrigem().getNome());
           if(result != 0)
               return result;
           return r1.getCia().getNome().compareTo(
                   r2.getCia().getNome());
        });
    }
    public void adicionar(Rota r) {
        rotas.add(r);
    }
    
    public void carregaDados(String nomeArq) throws IOException{
        Path path = Paths.get(nomeArq);
        try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {
            sc.useDelimiter("[;\n]");
            String header = sc.nextLine();
            String airline, from, to, equip, codeshare;
            int stops;
            while (sc.hasNext()){
                    airline = sc.next();
                    from = sc.next();
                    to = sc.next();
                    codeshare = sc.next();
                    stops = sc.nextInt();
                    CiaAeria ciaAeria = new ciaAeria();
                    ciaAeria = buscarCodigo(airline);
                    Aeroporto aeroOrigem = new Aeroporto();
                    aeroOrigem = buscarCodigo(from);
                    Aeroporto aeroDestino = new Aeroporto();
                    aeroDestino = buscarCOdigo(to);
                    Aeronave aeronave = new Aeronave();
                    aeronave = buscarCodigo(equip);
                    Rota nova = new Rota(ciaAeria, aeroOrigem, aeroDestino, aeronave);
                    adicionar(nova);
            }
        }
    }

    public ArrayList<Rota> listarTodas() {
        return new ArrayList<>(rotas);
    }

    public ArrayList<Rota> buscarOrigem(String codigo) {
        ArrayList<Rota> result = new ArrayList<>();
        for(Rota r: rotas)
            if(r.getOrigem().getCodigo().equals(codigo))
                result.add(r);
        return result;
    }
}
