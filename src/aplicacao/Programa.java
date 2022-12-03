package aplicacao;

import entidades.Empregado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Programa {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        System.out.print("Caminho do arquivo: ");
        String arquivo = sc.nextLine();

        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            List<Empregado> list = new ArrayList<>();
            String linha = br.readLine();

            while (linha != null) {
                String[] fields = linha.split(",");

                list.add(new Empregado(fields[0], fields[1], Double.parseDouble(fields[2])));

                linha = br.readLine();
            }

            System.out.print("Salário: ");
            double salario = sc.nextDouble();

            Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());

            List<String> email = list.stream()
                    .filter(x -> x.getSalario() > salario)
                    .map(x -> x.getEmail())
                    .sorted(comp)
                    .collect(Collectors.toList());

            System.out.println("Email das pessoas que possuem salário maior que " + String.format("%.2f", salario) + ":");
            email.forEach(System.out::println);

            double soma = list.stream()
                    .filter(x -> x.getNome().charAt(0) == 'M')
                    .map(y -> y.getSalario())
                    .reduce(0.0, (x, z) -> x + z);

            System.out.println("Soma do salário dos funcionários que possuem nome iniciando com 'M': " + String.format("%.2f", soma));

        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        sc.close();
    }
}
