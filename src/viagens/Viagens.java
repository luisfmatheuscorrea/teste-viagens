package viagens;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Viagens {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean ativo = true;
        
        List<String> pessoas = new ArrayList<String>();
        List<Despesa> despesas = new ArrayList<Despesa>();
        
        while(ativo) {
            int option;
            int totalAtual = 0;
            int valorIndividual = 0;
            
            for (int i = 0; i < despesas.size(); i++) {
                totalAtual = totalAtual + (despesas.get(i).quantidade * despesas.get(i).valorUnit);
            }
            
            System.out.println("\n_________________________\n" + "Total Atual: R$ " + totalAtual / 100 + "\n_________________________\n");
            
            System.out.println("Digite o número correspondente à opção: \n1 - Adicionar uma pessoa\n2 - Adicionar uma despesa\n3 - Finalizar\n");
            option = scanner.nextInt();
            scanner.nextLine();
            
            if (option == 1) {
                String nome;
                
                System.out.println("\nDigite o nome da pessoa: ");
                nome = scanner.nextLine();
                
                pessoas.add(nome);
            } else if (option == 2) {
                Despesa despesa = new Despesa();
                
                System.out.println("\nDigite o nome da despesa: ");
                despesa.nome = scanner.nextLine();
                
                System.out.println("\nDigite a quantidade (Ex.: Pessoas, Noites, Peso e etc...): ");
                despesa.quantidade = scanner.nextInt();
                
                System.out.println("\nDigite o valor unitário ou por kg: ");
                despesa.valorUnit = (int)scanner.nextDouble() * 100;
                
                
                despesas.add(despesa);
            } else {
                Map<String, String> valoresIndividuais = new HashMap<String, String>();
                
                for (int i = 0; i < pessoas.size(); i++) {
                	Float valor = (float)(totalAtual / 100) / pessoas.size();
                	
                        if (i == 0) {
                           valoresIndividuais.put(pessoas.get(i), NumberFormat.getCurrencyInstance().format(arredondar(valor, 2, 0)));
                        } else {
                           valoresIndividuais.put(pessoas.get(i), NumberFormat.getCurrencyInstance().format(arredondar(valor, 2, 1)));
                        }
                }
                
                if (valoresIndividuais.isEmpty()) {
                	System.out.println("Não há despesas.");
                } else {
                	System.out.println(valoresIndividuais);
                }
                
                ativo = false;
            }
        }   
    }
    
    static double arredondar(double valor, int casas, int ceilOrFloor) {
        double arredondado = valor;
        arredondado *= (Math.pow(10, casas));
        if (ceilOrFloor == 0) {
            arredondado = Math.ceil(arredondado);           
        } else {
            arredondado = Math.floor(arredondado);
        }
        arredondado /= (Math.pow(10, casas));
        return arredondado;
    }
}
